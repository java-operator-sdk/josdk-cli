package io.javaoperatorsdk.cli.commands;

import io.javaoperatorsdk.cli.renderer.TemplateRenderer;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.Map;


@Command(name = "init", mixinStandardHelpOptions = true,
        description = "initialize a project by default in the current path")
public class InitCommand implements Runnable {

    @CommandLine.Option(names = {"-b", "--buildTool"}, description = "The build tool you would like to use, choices: ['maven', 'gradle']")
    private String buildTool;

    @CommandLine.Option(names = {"-f", "--framework"}, description = "The framework to be used in developing the operator, choices: ['none', 'quarkus','spring-boot']")
    private String framework;

    @CommandLine.Option(names = {"-o", "--output"}, description = "The framework to be used in developing the operator, choices: ['none', 'quarkus','spring-boot']")
    private String outDirectory = System.getProperty("user.dir");


    public void run() {
        final var renderer = new TemplateRenderer(outDirectory);
        renderer.render("templates" + File.separator + buildTool + File.separator + framework, Map.of("name", "jops"));
        System.out.println("The project initialized successfully!");
    }
}
