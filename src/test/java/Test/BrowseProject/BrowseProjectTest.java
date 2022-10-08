package Test.BrowseProject;

import Model.BrowseProject.BrowseProjectModel;
import org.example.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BrowseProjectTest {
    BrowseProjectModel browseProjectModel;

    @BeforeEach
    public void openTab() {
        browseProjectModel = new BrowseProjectModel();
        browseProjectModel.goToUrlAndMaximizeWindow("https://jira-auto.codecool.metastage.net/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        browseProjectModel.doLogin();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @ParameterizedTest
    @ValueSource(strings = {"MTP", "JETI", "TOUCAN", "COALA"})
    public void browseProject(String projectType) {
        browseProjectModel.goToUrlAndMaximizeWindow(String.format("https://jira-auto.codecool.metastage.net/projects/%s/summary", projectType));
        Assertions.assertTrue(browseProjectModel.getProjectKey().contains(projectType));
    }

    @ParameterizedTest
    @ValueSource(strings = {"DUMMYDATA"})
    public void browseNonExistingProject(String projectType) {
        browseProjectModel.goToUrlAndMaximizeWindow(String.format("https://jira-auto.codecool.metastage.net/projects/%s/summary", projectType));
    }
}
