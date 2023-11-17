package model;

public class Project {
    private String idProject;
    private String projectName;
    private String description;
    private String email;
    private String author;
    private String devs;
    private String languages; 


    public Project(String projectName, String description, String email, String author, String devs, String languages) {
        this.idProject = (projectName + "/" + author).replace(" ", "_");
        this.projectName = projectName;
        this.description = description;
        this.email = email;
        this.author = author;
        this.devs = devs;
        this.languages = languages;
    }

    public String getId() {
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
    
    public String getLanguages() {
        return languages;
    }


    // Settersz
    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setDevs(String devs) {
        this.devs = devs;
    }
    public void setLanguages(String languages) {
        this.languages = languages;
    }
}