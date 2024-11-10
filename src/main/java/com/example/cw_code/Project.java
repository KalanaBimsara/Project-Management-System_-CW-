package com.example.cw_code;

import javafx.beans.property.*;

public class Project {
    private final IntegerProperty projectId;
    private final StringProperty projectName;
    private final StringProperty projectCategory;
    private final StringProperty teamMembers;
    private final StringProperty description;
    private final StringProperty country;
    private final StringProperty logo;
    private Integer points;




    public Project(int projectId, String projectName, String projectCategory, String teamMembers, String description, String country, String logo) {
        this.projectId = new SimpleIntegerProperty(projectId);
        this.projectName = new SimpleStringProperty(projectName);
        this.projectCategory = new SimpleStringProperty(projectCategory);
        this.teamMembers = new SimpleStringProperty(teamMembers);
        this.description = new SimpleStringProperty(description);
        this.country = new SimpleStringProperty(country);
        this.logo = new SimpleStringProperty(logo);
        this.points = 0;
    }

    public int getProjectId() {
        return projectId.get();
    }


    public String getProjectName() {
        return projectName.get();
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }

    public String getProjectCategory() {
        return projectCategory.get();
    }


    public void setProjectCategory(String projectCategory) {
        this.projectCategory.set(projectCategory);
    }

    public String getTeamMembers() {
        return teamMembers.get();
    }


    public void setTeamMembers(String teamMembers) {
        this.teamMembers.set(teamMembers);
    }

    public String getDescription() {
        return description.get();
    }


    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getLogo() {
        return logo.get();
    }


    public void setLogo(String logo) {
        this.logo.set(logo);
    }
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }


}
