package com.example.cw_code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.scene.control.TableCell;

public class PmsInterface {
    @FXML
    public Button Home;
    @FXML
    public Button Projects;
    @FXML
    public Button Judgement;
    @FXML
    public TextField ProjectID;
    @FXML
    public TextField ProjectName;
    @FXML
    public TextArea members;
    @FXML
    public AnchorPane ProjectPanel;
    @FXML
    public ImageView UserIcon;
    @FXML
    public AnchorPane ButtonPanel;
    @FXML
    public Label Displayselected;
    @FXML
    public Button Score;
    @FXML
    public AnchorPane WinnerPanel;
    @FXML
    public ImageView firstplaceimg;
    @FXML
    public Label firstplace;
    @FXML
    public Label secondplace;
    @FXML
    public Label thirdplace;
    @FXML
    public ImageView secondplaceimg;
    @FXML
    public ImageView thidplaceimg;
    @FXML
    public Label FirstPlaceDetials;
    @FXML
    public Label SecondPlaceDetials;
    @FXML
    public Label ThirdPlaceDetials;
    @FXML
    public Label dateLabel;
    @FXML
    public Label ProjectCount;
    @FXML
    TableView<Project> ShowProjectTable;
    @FXML
    public Button DeleteButton;
    @FXML
    public Button ClearButton;
    @FXML
    public Button UpdateButton;
    @FXML
    public Button AddProjectButton;
    @FXML
    public Button LogoUpload;
    @FXML
    public TextField logoPathField;
    @FXML
    public TextField Country;
    @FXML
    public AnchorPane JudgementPanel;
    @FXML
    public TableView<Project> RandomTable;
    @FXML
    public Button RandomSpotlight;
    @FXML
    public Button SubmitPoints;
    @FXML
    public AnchorPane ScorePanel;
    @FXML
    public CategoryAxis BarChart;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    TextField Judge1;
    @FXML
    TextField Judge2;
    @FXML
    TextField Judge3;
    @FXML
    TextField Judge4;
    @FXML
    public Button SeeWinner;
    @FXML
    public TextField ProjectCategory;
    @FXML
    public TextArea Description;
    @FXML
    public AnchorPane HomePanel;
    @FXML
    public TableColumn<Project, Integer> projectIdColumn;
    @FXML
    public TableColumn<Project, String> projectNameColumn;
    @FXML
    public TableColumn<Project, String> projectCategoryColumn;
    @FXML
    public TableColumn<Project, String> teamMembersColumn;
    @FXML
    public TableColumn<Project, String> descriptionColumn;
    @FXML
    public TableColumn<Project, String> countryColumn;
    @FXML
    private TableColumn<Project, String> logoColumn;
    @FXML
    public TableColumn<Project, String> JudgeCategory;
    @FXML
    public TableColumn<Project, Integer> JudgeProjectID;
    @FXML
    public TableColumn<Project, String> JudgeName;
    @FXML
    public TableColumn<Project, String> JudgeMembers;
    @FXML
    public TableColumn<Project, String> JudgeDescription;
    @FXML
    public TableColumn<Project, String> JudgeCountry;
    @FXML
    public TableColumn<Project, String> JudgeLogo;

    public boolean RandomSpot = false;

    public boolean Submit = false;


    ObservableList<Project> projectList = FXCollections.observableArrayList();



    public void SwitchPanels(ActionEvent event) {
        if (event.getSource() == Home) {
            ProjectPanel.setVisible(false);
            JudgementPanel.setVisible(false);
            ScorePanel.setVisible(false);
            HomePanel.setVisible(true);
            WinnerPanel.setVisible(false);
        } else if (event.getSource() == Judgement) {
            ProjectPanel.setVisible(false);
            JudgementPanel.setVisible(true);
            ScorePanel.setVisible(false);
            HomePanel.setVisible(false);
            WinnerPanel.setVisible(false);
        } else if (event.getSource() == Projects) {
            ProjectPanel.setVisible(true);
            JudgementPanel.setVisible(false);
            ScorePanel.setVisible(false);
            HomePanel.setVisible(false);
            WinnerPanel.setVisible(false);
        }else if (event.getSource() == Score) {
            WinnerPanel.setVisible(false);
            ProjectPanel.setVisible(false);
            JudgementPanel.setVisible(false);
            ScorePanel.setVisible(true);
            HomePanel.setVisible(false);

    }

    }



    @FXML
    public void initialize() {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        dateLabel.setText("Date: " + currentDate.format(formatter));


        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        projectCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("projectCategory"));
        teamMembersColumn.setCellValueFactory(new PropertyValueFactory<>("teamMembers"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        logoColumn.setCellValueFactory(new PropertyValueFactory<>("logo"));
        logoColumn.setCellFactory(new Callback<TableColumn<Project, String>, TableCell<Project, String>>() {
            @Override
            public TableCell<Project, String> call(TableColumn<Project, String> param) {
                return new TableCell<Project, String>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image image = new Image("file:" + item);
                            imageView.setImage(image);
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });


        JudgeProjectID.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        JudgeName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        JudgeCategory.setCellValueFactory(new PropertyValueFactory<>("projectCategory"));
        JudgeMembers.setCellValueFactory(new PropertyValueFactory<>("teamMembers"));
        JudgeDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        JudgeCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        JudgeLogo.setCellValueFactory(new PropertyValueFactory<>("logo"));
        JudgeLogo.setCellFactory(new Callback<TableColumn<Project, String>, TableCell<Project, String>>() {
            @Override
            public TableCell<Project, String> call(TableColumn<Project, String> param) {
                return new TableCell<Project, String>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image image = new Image("file:" + item);
                            imageView.setImage(image);
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });

        loadProjectsFromFile();
        ShowProjectTable.setItems(projectList);

        if (projectList != null) {
            int projectCount = projectList.size();
            ProjectCount.setText("Total Projects: " + projectCount);
        } else {
            ProjectCount.setText("Total Projects: 0");
        }


    }




    public void loadProjectsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("projects.txt"))) {
            String line;
            Project project = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Category: ")) {
                    continue; // Skip the category line
                }

                if (line.startsWith("========================")) {
                    continue; // Skip the separator line
                }

                if (line.startsWith("Project ID: ")) {
                    if (project != null) {
                        projectList.add(project);
                    }

                    project = new Project(
                            Integer.parseInt(line.substring(12)),
                            "", "", "", "", "", ""
                    );
                }

                if (line.startsWith("Project Name: ")) {
                    project.setProjectName(line.substring(14));
                }

                if (line.startsWith("Project Category: ")) {
                    project.setProjectCategory(line.substring(18));
                }

                if (line.startsWith("Team Members: ")) {
                    project.setTeamMembers(line.substring(14));
                }

                if (line.startsWith("Description: ")) {
                    project.setDescription(line.substring(13));
                }

                if (line.startsWith("Country: ")) {
                    project.setCountry(line.substring(9));
                }

                if (line.startsWith("Logo: ")) {
                    project.setLogo(line.substring(6));
                }

                // Read the blank line separating projects
                if (line.isEmpty()) {
                    if (project != null) {
                        projectList.add(project);
                        project = null;
                    }
                }
            }

            if (project != null) {
                projectList.add(project);
            }

            bubbleSortProjectsById();

        } catch (IOException e) {
            System.out.println("Error loading projects from file: " + e.getMessage());
        }
    }




    @FXML
    public void AddProject(ActionEvent event) {
        if (RandomSpot) {
            showAlert("Cannot add projects after random spotlight has been executed.");
            return;
        }

        // Validate input fields
        if (ProjectID.getText() == null || ProjectID.getText().isEmpty()) {
            showAlert("Project ID is required.");
            return;
        }
        if (ProjectName.getText() == null || ProjectName.getText().isEmpty()) {
            showAlert("Project Name is required.");
            return;
        }
        if (ProjectCategory.getText() == null || ProjectCategory.getText().isEmpty()) {
            showAlert("Project Category is required.");
            return;
        }

        try {
            Project newProject = new Project(
                    Integer.parseInt(ProjectID.getText()),
                    ProjectName.getText(),
                    ProjectCategory.getText(),
                    members.getText(),
                    Description.getText(),
                    Country.getText(),
                    logoPathField.getText()
            );

            if (checkDuplicates(newProject)) {
                showAlert("Duplicate Project ID");
                return;
            }

            projectList.add(newProject);
            bubbleSortProjectsById();
            saveProjectsToFile();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Project ID (Project ID must be Integer): " + e.getMessage());
        }
    }



    @FXML
    public void uploadLogo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Logo Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(ShowProjectTable.getScene().getWindow());
        if (selectedFile != null) {
            logoPathField.setText(selectedFile.getAbsolutePath());
        }
    }




    @FXML
    void handleclickcolumn(MouseEvent event) {
        Project selectedProject = ShowProjectTable.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            ProjectID.setText(String.valueOf(selectedProject.getProjectId()));
            ProjectName.setText(selectedProject.getProjectName());
            ProjectCategory.setText(selectedProject.getProjectCategory());
            members.setText(selectedProject.getTeamMembers());
            Description.setText(selectedProject.getDescription());
            Country.setText(selectedProject.getCountry());
            logoPathField.setText(selectedProject.getLogo());
        }
    }




    @FXML
    public void UpdateProject(ActionEvent event) {
        if (RandomSpot) {
            showAlert("Cannot update projects after random spotlight has been executed.");
            return;
        }
        try {
            ObservableList<Project> currentTableData = ShowProjectTable.getItems();
            int currentProjectID = Integer.parseInt(ProjectID.getText());

            for (Project project : currentTableData) {
                if (project.getProjectId() == currentProjectID) {
                    Project updatedProject = new Project(
                            currentProjectID,
                            ProjectName.getText(),
                            ProjectCategory.getText(),
                            members.getText(),
                            Description.getText(),
                            Country.getText(),
                            logoPathField.getText()
                    );


                    project.setProjectName(ProjectName.getText());
                    project.setProjectCategory(ProjectCategory.getText());
                    project.setTeamMembers(members.getText());
                    project.setDescription(Description.getText());
                    project.setCountry(Country.getText());
                    project.setLogo(logoPathField.getText());
                    ShowProjectTable.refresh();
                    saveProjectsToFile();
                    break;
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Project ID ");
        }
    }




    @FXML
    public void DeleteProject(ActionEvent event) {
        if (RandomSpot) {
            showAlert("Cannot delete projects after random spotlight has been executed.");
            return;
        }
        int selectedProjectID = ShowProjectTable.getSelectionModel().getSelectedIndex();
        if (selectedProjectID >= 0) {
            ShowProjectTable.getItems().remove(selectedProjectID);
            clearFields();
            saveProjectsToFile();
        }
    }




    @FXML
    public void ClearFields(ActionEvent event) {

        clearFields();

    }




    public void clearFields() {
        ProjectID.clear();
        ProjectName.clear();
        ProjectCategory.clear();
        members.clear();
        Description.clear();
        Country.clear();
        logoPathField.clear();
    }




    boolean checkDuplicates(Project newProject) {
        for (Project project : projectList) {
            if (project.getProjectId() == newProject.getProjectId()) {
                return true; // Duplicate found
            }
        }
        return false; // No duplicates
    }




    void bubbleSortProjectsById() {
        int n = projectList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (projectList.get(j).getProjectId() > projectList.get(j + 1).getProjectId()) {
                    Project temp = projectList.get(j);
                    projectList.set(j, projectList.get(j + 1));
                    projectList.set(j + 1, temp);
                }
            }
        }
    }




    public void saveProjectsToFile() {
        Map<String, List<Project>> projectsByCategory = projectList.stream()
                .collect(Collectors.groupingBy(Project::getProjectCategory));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt"))) {
            for (Map.Entry<String, List<Project>> entry : projectsByCategory.entrySet()) {
                String category = entry.getKey();
                List<Project> projectsInCategory = entry.getValue();

                writer.write("Category: " + category + "\n");
                writer.write("========================\n");

                for (Project project : projectsInCategory) {
                    writer.write("Project ID: " + project.getProjectId() + "\n");
                    writer.write("Project Name: " + project.getProjectName() + "\n");
                    writer.write("Project Category: " + project.getProjectCategory() + "\n");
                    writer.write("Team Members: " + project.getTeamMembers() + "\n");
                    writer.write("Description: " + project.getDescription() + "\n");
                    writer.write("Country: " + project.getCountry() + "\n");
                    writer.write("Logo: " + project.getLogo() + "\n");
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving projects to file: " + e.getMessage());
        }
    }



    @FXML
    public boolean RandomSpotlight(ActionEvent event) {
        Map<String, List<Project>> categoryMap = projectList.stream()
                .collect(Collectors.groupingBy(Project::getProjectCategory));

        ObservableList<Project> randomProjects = FXCollections.observableArrayList();
        Random random = new Random();

        for (List<Project> categoryProjects : categoryMap.values()) {
            Project randomProject = categoryProjects.get(random.nextInt(categoryProjects.size()));
            randomProjects.add(randomProject);
        }

        RandomTable.setItems(randomProjects);
        RandomSpot = true;
        return RandomSpot;
    }



    @FXML
    public void handleRandomTableClick(MouseEvent event) {
        Project randomProject = RandomTable.getSelectionModel().getSelectedItem();
        Displayselected.setText(randomProject.getProjectName());
        if (randomProject != null) {
            Judge1.setPromptText("Enter points (* up to 5)");
            Judge2.setPromptText("Enter points (* up to 5)");
            Judge3.setPromptText("Enter points (* up to 5)");
            Judge4.setPromptText("Enter points (* up to 5)");
        }
    }




    @FXML
    public boolean SubmitPoints(ActionEvent event) {
        Project selectedProject = RandomTable.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            int points1 = countStars(Judge1.getText());
            int points2 = countStars(Judge2.getText());
            int points3 = countStars(Judge3.getText());
            int points4 = countStars(Judge4.getText());

            if (points1 != -1 && points2 != -1 && points3 != -1 && points4 != -1) {
                int totalPoints = points1 + points2 + points3 + points4;
                selectedProject.setPoints(totalPoints);

                for (Project project : projectList) {
                    if (project.getProjectId() == selectedProject.getProjectId()) {
                        project.setPoints(totalPoints);
                        break;
                    }
                }
                Displayselected.setText("points submitted for "+selectedProject.getProjectName());

                Judge1.clear();
                Judge2.clear();
                Judge3.clear();
                Judge4.clear();
            }
        }
        Submit=true;
        return Submit;
    }

    int countStars(String stars) {
        int points = 0;
        for (char c : stars.toCharArray()) {
            if (c == '*') {
                points++;
                if (points > 5) {
                    showAlert("Enter Valid Points (Maximum Score is 5)");
                    return -1;
                }
            } else {
                showAlert("Enter Valid Points (Only '*' is allowed)");
                return -1;
            }
        }
        return points;
    }



    List<Project> getTopThreeProjects() {
        return projectList.stream()
                .sorted(Comparator.comparingInt(Project::getPoints).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }



    @FXML
    public void SeeWinners(ActionEvent event) {

        if (!RandomSpot) {
            showAlert("Cannot See winners before random spotlight");
            return;
        }

        if (!Submit) {
            showAlert("You haven't submitted points yet");
            return;
        }
        WinnerPanel.setVisible(true);
        List<Project> topThreeProjects = getTopThreeProjects();

        if (topThreeProjects.size() > 0) {
            Project firstProject = topThreeProjects.get(0);
            firstplaceimg.setImage(new Image("file:" + firstProject.getLogo()));
            firstplace.setText("First Place: " + firstProject.getProjectName());
            FirstPlaceDetials.setText("Country: " + firstProject.getCountry());
        }
        if (topThreeProjects.size() > 1) {
            Project secondProject = topThreeProjects.get(1);
            secondplaceimg.setImage(new Image("file:" + secondProject.getLogo()));
            secondplace.setText("Second Place: " + secondProject.getProjectName());
            SecondPlaceDetials.setText("Country: " + secondProject.getCountry());
        }
        if (topThreeProjects.size() > 2) {
            Project thirdProject = topThreeProjects.get(2);
            thidplaceimg.setImage(new Image("file:" + thirdProject.getLogo()));
            thirdplace.setText("Third Place: " + thirdProject.getProjectName());
            ThirdPlaceDetials.setText("Country: " + thirdProject.getCountry());
        }

    }



    @FXML
    public void ShowScoreBarGraph(ActionEvent event) {
        if (!RandomSpot) {
            showAlert("Cannot display score chart before random spotlight");
            return;
        }
        ScorePanel.setVisible(true);
        JudgementPanel.setVisible(false);

        displayBarGraph();
    }



    public void displayBarGraph() {
        barChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Project Scores");

        ObservableList<Project> randomProjects = RandomTable.getItems();

        for (Project project : randomProjects) {
            series.getData().add(new XYChart.Data<>(project.getProjectName(), project.getPoints()));
        }
        barChart.getData().add(series);
    }



    public void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert !!!");
        alert.setHeaderText(null);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }


}
