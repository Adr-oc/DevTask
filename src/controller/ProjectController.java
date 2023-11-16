package controller;
import java.io.*;
import java.util.*;
import model.Project;

public class ProjectController {

    private String csvFileName = "src/db/projects.csv";
    private List<Project> projects = new ArrayList<>();

    public ProjectController() {
        this.projects = readProjectsFromCSV();
    }

    private List<Project> readProjectsFromCSV() {
        List<Project> projects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Project project = new Project(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
                projects.add(project);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projects;
    }

    private void writeProjectToCSV(Project project) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFileName, true))) {
            bw.write(project.getId() + "," + project.getProjectName() + "," + project.getDescription() + "," + project.getEmail() + "," + project.getAuthor() + "," + project.getDevs() + "," + project.getLanguages());
            bw.newLine();
            projects.add(project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newProject(String id, String projectName, String description, String email, String author, String devs, String languages) {
        Project project = new Project(id, projectName, description, email, author, devs, languages);
        writeProjectToCSV(project);
    }
/* 
    public Project findProjectById(String id) {
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                return project;
            }
        }
        return null;
    }*/

    public List<Project> getAllProjects() {
        return projects;
    }
    
}