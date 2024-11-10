package com.example.cw_code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class WinnersReport implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;

    private final ObservableList<Project> projectList;

    public WinnersReport(ObservableList<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Points");

        for (Project project : projectList) {
            series.getData().add(new XYChart.Data(project.getProjectName(), project.getPoints()));
        }

        barChart.getData().add(series);
    }
}
