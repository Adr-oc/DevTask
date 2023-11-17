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
                Project project = new Project(values[1], values[2], values[3], values[4], values[5], values[6]);
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

    public boolean doesProjectExist(String projectName, String email) {
        for (Project project : projects) {
            if (project.getProjectName().equals(projectName) && project.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    public void newProject(String projectName, String description, String email, String author, String devs, String languages) throws Exception {
        
        if (doesProjectExist(projectName, email)) {
            throw new Exception("Este proyecto ya lo has creado cambia el nombre para crear uno nuevo.");
        } else if (validateProject(projectName, languages, description)) {
            Project project = new Project(projectName, description, email, author, devs, languages);
            writeProjectToCSV(project);
        } else {
            throw new Exception("Error creating project.");
        }
        
    }

    public Project findProjectById(String id) {
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                return project;
            }
        }
        return null;
    }


    public Boolean validateProject(String nombreProyecto, String lenguajesSeleccionados, String descripcion) throws Exception {
        if (nombreProyecto == null || nombreProyecto.trim().isEmpty()) {
            throw new Exception("El nombre del proyecto es obligatorio.");
        }

        if (lenguajesSeleccionados == null || lenguajesSeleccionados.trim().isEmpty()) {
            throw new Exception("Debe seleccionar al menos un lenguaje.");
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new Exception("La descripción es obligatoria.");
        }

        return true;
    }


    public List<Project> getAllProjects() {
        return projects;
    }


    public List<Project> getUserProjects(String email) {
        List<Project> userProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getEmail().equals(email)) {
                userProjects.add(project);
            }
        }
        return userProjects;
    }
    


    public void addDevToProject(String projectId, String dev) throws IOException {
        File inputFile = new File(csvFileName);
        File tempFile = new File("temp.csv");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            String[] fields = currentLine.split(",");
            if(fields[0].equals(projectId)) {
                List<String> devs = new ArrayList<>(Arrays.asList(fields[5].split(";")));
                if(!devs.contains(dev)) {
                    devs.add(dev);
                    fields[5] = String.join(";", devs);
                }
            }
            writer.write(String.join(",", fields) + "\n");
        }

        writer.close(); 
        reader.close(); 

        if(!inputFile.delete()) {
            System.out.println("Could not delete original file");
        }

        if(!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file");
        }
    }
    


    public boolean isDevAssignedToProject(String projectId, String dev) throws IOException {
        File inputFile = new File(csvFileName);

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            String[] fields = currentLine.split(",");
            if(fields[0].equals(projectId)) {
                List<String> devs = new ArrayList<>(Arrays.asList(fields[5].split(";")));
                if(devs.contains(dev)) {
                    reader.close();
                    return true;
                }
            }
        }

        reader.close();
        return false;
    }


    public void removeDevFromProject(String projectId, String dev) throws IOException {
        File inputFile = new File(csvFileName);
        File tempFile = new File("temp.csv");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            String[] fields = currentLine.split(",");
            if(fields[0].equals(projectId)) {
                List<String> devs = new ArrayList<>(Arrays.asList(fields[5].split(";")));
                if(devs.contains(dev)) {
                    devs.remove(dev);
                    fields[5] = String.join(";", devs);
                }
            }
            writer.write(String.join(",", fields) + "\n");
        }

        writer.close(); 
        reader.close(); 

        if(!inputFile.delete()) {
            System.out.println("Could not delete original file");
        }

        if(!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file");
        }
    }

    
    private boolean checkDevInProject(Project project, String email) {
        return project.getDevs().contains(email);
    }
    public List<Project> getDevProjects(String email) {
        List<Project> devProjects = new ArrayList<>();
        List<Project> allProjects = getAllProjects();

        for (Project project : allProjects) {
            boolean isDevInProject = checkDevInProject(project, email); // Este método debe ser definido según su implementación
            if (isDevInProject) {
                devProjects.add(project);
            }
        }

        return devProjects;
    }
}