package ua.org.gostroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.org.gostroy.service.ProjectService;

@SpringBootApplication
public class GoRestApplication {

    private static ProjectService projectService;

    public static void main(String[] args) {
        SpringApplication.run(GoRestApplication.class, args);

//        projectService.loadConfigurationToMemory();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Autowired
    public static void setProjectService(ProjectService projectService) {
        GoRestApplication.projectService = projectService;
    }
}
