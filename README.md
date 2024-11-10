# Project Management System (PMS)

This Project Management System (PMS) is a JavaFX-based application that allows users to manage projects, add team details, and evaluate projects based on scores. It features functionalities to add, update, and delete projects and display top projects based on a scoring system.

## Features

- **Add Projects**: Add new projects with details including project ID, name, category, team members, description, country, and logo.
- **Update Projects**: Edit and update existing project details.
- **Delete Projects**: Remove projects from the system.
- **Random Project Spotlight**: Randomly selects projects from different categories for evaluation.
- **Score Submission**: Judges can assign scores to selected projects, and the system calculates the total score for each project.
- **Top 3 Projects Display**: Display the top three projects based on the submitted scores.
- **Bar Chart Visualization**: Visualize project scores using a bar chart for selected projects.
- **Project Count and Date Display**: Shows the total number of projects and current date on the home panel.

## Project Structure

- **Main Interface**: The primary interface for adding, updating, and deleting projects.
- **Judgement Panel**: The panel for evaluating selected projects based on judge scores.
- **Score Panel**: The panel to visualize scores in a bar chart format.
- **Winner Panel**: Displays the top three projects based on the scores.

## How to Use

1. **Adding Projects**: 
   - Go to the **Projects** panel.
   - Fill in all the required fields (Project ID, Project Name, Category, Team Members, Description, Country, and Logo).
   - Click **Add** to save the project.

2. **Updating Projects**:
   - Select a project from the project table.
   - Update the fields as needed.
   - Click **Update** to save changes.

3. **Deleting Projects**:
   - Select a project from the project table.
   - Click **Delete** to remove it.

4. **Random Spotlight**:
   - Click **Random Spotlight** to randomly select projects for evaluation.
   
5. **Submitting Scores**:
   - For each project in the spotlight, enter scores (up to 5 stars per judge) and submit them.
   
6. **Viewing Top 3 Projects**:
   - After submitting scores, view the top three projects by clicking **See Winners**.

## Technologies Used

- **Java**: Core programming language
- **JavaFX**: GUI framework for building the user interface
- **JUnit**: Unit testing framework

## Setup and Installation

1. **Prerequisites**:
   - Java Development Kit (JDK 17 or above)
   - JavaFX SDK
   - Maven

2. **Clone the Repository**:
   ```bash
   git clone https://github.com/KalanaBimsara/Project-Management-System_-CW-.git
   cd Project-Management-System_-CW-
