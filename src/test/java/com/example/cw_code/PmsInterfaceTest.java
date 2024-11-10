package com.example.cw_code;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


public class PmsInterfaceTest extends ApplicationTest {
    private PmsInterface pmsInterface;
    private ObservableList<Project> projectList = FXCollections.observableArrayList();
    private TableView<Project> ShowProjectTable;
    private TextField Judge1, Judge2, Judge3, Judge4;
    private TableView<Project> RandomTable;
    private Label Displayselected;



    @Override
    public void start(Stage stage) {

        pmsInterface = new PmsInterface();
        PmsInterface.ProjectID = new TextField();
        PmsInterface.ProjectName = new TextField();
        PmsInterface.ProjectCategory = new TextField();
        PmsInterface.members = new TextArea();
        PmsInterface.Description = new TextArea();
        PmsInterface.Country = new TextField();
        PmsInterface.logoPathField = new TextField();
        ShowProjectTable = new TableView<>(projectList);
        pmsInterface.ShowProjectTable = ShowProjectTable;

        pmsInterface.projectList = projectList;
    }

    @BeforeEach
    public void setUp() {
        pmsInterface = new PmsInterface();
        RandomTable = new TableView<>();
        Judge1 = new TextField();
        Judge2 = new TextField();
        Judge3 = new TextField();
        Judge4 = new TextField();
        Displayselected = new Label();
        projectList = FXCollections.observableArrayList();

        pmsInterface.RandomTable = RandomTable;
        pmsInterface.Judge1 = Judge1;
        pmsInterface.Judge2 = Judge2;
        pmsInterface.Judge3 = Judge3;
        pmsInterface.Judge4 = Judge4;
        pmsInterface.Displayselected = Displayselected;
        pmsInterface.projectList = projectList;


        PmsInterface.ProjectID.clear();
        PmsInterface.ProjectName.clear();
        PmsInterface.ProjectCategory.clear();
        PmsInterface.members.clear();
        PmsInterface.Description.clear();
        PmsInterface.Country.clear();
        projectList.clear();



    }

    @Test
    public void testAddProjectWithString() {
        PmsInterface.ProjectID.setText("1");
        PmsInterface.ProjectName.setText("Test Project");
        PmsInterface.ProjectCategory.setText("Test Category");
        PmsInterface.members.setText("Test Members");
        PmsInterface.Description.setText("Test Description");
        PmsInterface.Country.setText("Test Country");
        PmsInterface.logoPathField.setText("Test Logo Path");

        pmsInterface.AddProject(null);

        assertEquals(1, projectList.size());
        Project addedProject = projectList.get(0);
        assertEquals(1, addedProject.getProjectId());
        assertEquals("Test Project", addedProject.getProjectName());
        assertEquals("Test Category", addedProject.getProjectCategory());
        assertEquals("Test Members", addedProject.getTeamMembers());
        assertEquals("Test Description", addedProject.getDescription());
        assertEquals("Test Country", addedProject.getCountry());
        assertEquals("Test Logo Path", addedProject.getLogo());

    }

    @Test
    public void testAddProjectWithNonNumericId() {
        PmsInterface.ProjectID.setText("abc");
        PmsInterface.ProjectName.setText("Test Project");
        PmsInterface.ProjectCategory.setText("Test Category");
        PmsInterface.members.setText("Test Members");
        PmsInterface.Description.setText("Test Description");
        PmsInterface.Country.setText("Test Country");
        PmsInterface.logoPathField.setText("Test Logo Path");

        assertThrows(NumberFormatException.class, () -> pmsInterface.AddProject(null));
    }


    @Test
    public void testAddProjectWithNullId() {
        PmsInterface.ProjectID.setText(null);
        PmsInterface.ProjectName.setText("Test Project");
        PmsInterface.ProjectCategory.setText("Test Category");
        PmsInterface.members.setText("Test Members");
        PmsInterface.Description.setText("Test Description");
        PmsInterface.Country.setText("Test Country");
        PmsInterface.logoPathField.setText("Test Logo Path");

        assertThrows(IllegalStateException.class, () -> pmsInterface.AddProject(null));
    }

    @Test
    public void testAddProjectWithoutName() {
        Platform.runLater(() -> {
            PmsInterface.ProjectID.setText("1");
            PmsInterface.ProjectName.setText("");
            PmsInterface.ProjectCategory.setText("Test Category");
            PmsInterface.members.setText("Test Members");
            PmsInterface.Description.setText("Test Description");
            PmsInterface.Country.setText("Test Country");
            PmsInterface.logoPathField.setText("Test Logo Path");

            pmsInterface.AddProject(null);

            assertEquals(0, projectList.size());
        });

        //JavaFX operations waiting
        WaitForAsyncUtils.waitForFxEvents();
    }


    @Test
    public void testAddProjectWithoutCategory() {
        Platform.runLater(() -> {
            PmsInterface.ProjectID.setText("1");
            PmsInterface.ProjectName.setText("Test Project");
            PmsInterface.ProjectCategory.setText("");
            PmsInterface.members.setText("Test Members");
            PmsInterface.Description.setText("Test Description");
            PmsInterface.Country.setText("Test Country");
            PmsInterface.logoPathField.setText("Test Logo Path");

            pmsInterface.AddProject(null);

            assertEquals(0, projectList.size()); // Assuming the project is not added
        });

        WaitForAsyncUtils.waitForFxEvents();
    }





    @Test
    void testCheckDuplicates_withoutDuplicate() {
        Project project = new Project(1, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        Project project1 = new Project(2, "Another Project", "Another Category", "Another Members", "Another Description", "Another Country", "Another Logo Path");

        projectList.add(project);
        projectList.add(project1);

        boolean result = pmsInterface.checkDuplicates(project);

        assertEquals(true, result);
    }

    @Test
    void checkDuplicatesWithDuplicate() {

        Project project = new Project(1, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        Project project1 = new Project(1, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        projectList.add(project);
        projectList.add(project1);
        boolean result = pmsInterface.checkDuplicates(project);

        assertEquals(true, result);
    }

    @Test
    void bubbleSortProjectsById() {
        Project project1 = new Project(3, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        Project project2 = new Project(4, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        Project project5 = new Project(7, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        Project project = new Project(2, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        Project project3 = new Project(5, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        Project project4 = new Project(6, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        projectList.add(project);
        projectList.add(project1);
        projectList.add(project2);
        projectList.add(project3);
        projectList.add(project4);
        projectList.add(project5);

        pmsInterface.bubbleSortProjectsById();
        assertEquals(2, projectList.get(0).getProjectId());
        assertEquals(3, projectList.get(1).getProjectId());
        assertEquals(4, projectList.get(2).getProjectId());
        assertEquals(5, projectList.get(3).getProjectId());
        assertEquals(6, projectList.get(4).getProjectId());
        assertEquals(7, projectList.get(5).getProjectId());
    }


    @Test
    void saveProjectsToFile() throws IOException {
        Project project = new Project(1, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        projectList.add(project);
        pmsInterface.saveProjectsToFile();
        assertTrue(Files.exists(Paths.get("projects.txt")));
    }

    @Test
    void clearFields() {
        pmsInterface.clearFields();
        assertEquals("", PmsInterface.ProjectID.getText());
        assertEquals("", PmsInterface.ProjectName.getText());
        assertEquals("", PmsInterface.ProjectCategory.getText());
        assertEquals("", PmsInterface.members.getText());
        assertEquals("", PmsInterface.Description.getText());
        assertEquals("", PmsInterface.Country.getText());
        assertEquals("", PmsInterface.logoPathField.getText());
    }

    @Test
    void LoadProjectsFromFile() throws IOException {
        projectList.clear();
        Project project = new Project(1, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        projectList.add(project);
        pmsInterface.saveProjectsToFile();
        projectList.clear();
        pmsInterface.loadProjectsFromFile();

        assertEquals(1, projectList.size());
        Project loadedProject = projectList.get(0);
        assertEquals("Test Project", loadedProject.getProjectName());
        assertEquals("Test Category", loadedProject.getProjectCategory());
        assertEquals("Test Members", loadedProject.getTeamMembers());
        assertEquals("Test Description", loadedProject.getDescription());
        assertEquals("Test Country", loadedProject.getCountry());
        assertEquals("Test Logo Path", loadedProject.getLogo());
    }



    @Test
    void handleRandomSpotlight() {
        projectList.clear();

        Project project = new Project(1, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        projectList.add(project);

        ActionEvent event = new ActionEvent();

        pmsInterface.RandomSpotlight(event);

        assertEquals(1, projectList.size());
        Project selectedProject = projectList.get(0);
        assertEquals("Test Project", selectedProject.getProjectName());
        assertEquals("Test Category", selectedProject.getProjectCategory());
        assertEquals("Test Members", selectedProject.getTeamMembers());
        assertEquals("Test Description", selectedProject.getDescription());
        assertEquals("Test Country", selectedProject.getCountry());
        assertEquals("Test Logo Path", selectedProject.getLogo());
    }



    @Test
    void testSubmitPointsWithInvalidCharacters() {
        Platform.runLater(() -> {
            Project project = new Project(1, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
            projectList.add(project);
            RandomTable.getItems().add(project);
            RandomTable.getSelectionModel().select(project);

            Judge1.setText("****");
            Judge2.setText("***");
            Judge3.setText("*****");
            Judge4.setText("**abc"); // Invalid rating

            pmsInterface.SubmitPoints(new ActionEvent());

            assertEquals(0, project.getPoints());
            assertNotEquals("points submitted for Test Project", Displayselected.getText());
        });
    }

    @Test
    void testSubmitPointsWithMoreThan5Stars() {
        Platform.runLater(() -> {
            // Add a project to the random table selection
            Project project = new Project(1, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
            projectList.add(project);
            RandomTable.getItems().add(project);
            RandomTable.getSelectionModel().select(project);

            // Set more than 5 stars for any judge

            Judge1.setText("******");
            Judge2.setText("***");
            Judge3.setText("*****");
            Judge4.setText("********"); // Invalid rating

            pmsInterface.SubmitPoints(new ActionEvent());

            // Verify points are not set due to invalid rating
            assertEquals(0, project.getPoints());
            assertNotEquals("points submitted for Test Project", Displayselected.getText());
        });
    }

    @Test
    void getTopThreeProjects() {

        Project project = new Project(1, "Test Project", "Test Category", "Test Members", "Test Description", "Test Country", "Test Logo Path");
        projectList.add(project);
        List<Project> result = pmsInterface.getTopThreeProjects();
        assertEquals(1, ((List<?>) result).size());
        assertEquals("Test Project", result.get(0).getProjectName());
        assertEquals("Test Category", result.get(0).getProjectCategory());
        assertEquals("Test Members", result.get(0).getTeamMembers());
        assertEquals("Test Description", result.get(0).getDescription());
        assertEquals("Test Country", result.get(0).getCountry());
        assertEquals("Test Logo Path", result.get(0).getLogo());
    }



}
