package org.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Maven плагин, выводящий информацию о разработчиках в лог.
 * По умолчанию информация выводится при сборке проекта на этапе компиляции.
 * */
@Mojo(name = "print", defaultPhase = LifecyclePhase.COMPILE, threadSafe = true)
public class DevelopersMojo extends AbstractMojo {

    /**
     * Информация о проекте из pom.xml проекта
     * */
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    /**
     * Метод логирования информации о разработчиках проекта.
     * */
    @Override
    public void execute() {
        final StringBuilder developersInfo = new StringBuilder("Project developers:" + System.lineSeparator());
        if (project.getDevelopers().isEmpty()) {
            developersInfo.append("There is no information about the developers");
        } else {
            project.getDevelopers().stream()
                    .map(DeveloperInfo::new)
                    .forEach(developersInfo::append);
        }
        getLog().info(developersInfo.toString());
    }

}
