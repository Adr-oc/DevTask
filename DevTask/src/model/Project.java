package model;

public class Project {
    private String idProject;
    private String projectName;
    private String description;
    private String email;
    private String author;
    private String devs;

    public Project(String idProject, String projectName, String description, String email, String author, String devs) {
        this.idProject = idProject;
        this.projectName = projectName;
        this.description = description;
        this.email = email;
        this.author = author;
        this.devs = devs;
    }

    public String getIdProject() {
        return idProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthor() {
        return author;
    }

    public String getDevs() {
        return devs;
    }
}