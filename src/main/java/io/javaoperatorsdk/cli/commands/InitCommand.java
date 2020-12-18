package io.javaoperatorsdk.cli.commands;

import io.javaoperatorsdk.cli.renderer.TemplateRenderer;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.Map;


@Command(name = "init", mixinStandardHelpOptions = true,
        description = "initialize a project by default in the current path")
public class InitCommand implements Runnable {

    @CommandLine.Option(names = {"-b", "--buildTool"}, defaultValue = "maven", description = "The build tool you would like to use, choices: ['maven', 'gradle']")
    private String buildTool;


    @CommandLine.Option(names = {"-f", "--framework"}, defaultValue = "none", description = "The framework to be used in developing the operator, choices: ['none', 'quarkus','spring-boot']")
    private String framework;

    @CommandLine.Option(names = {"-o", "--output"}, description = "The output directory, default: the current working directory.")
    private String outDirectory = System.getProperty("user.dir");

    @CommandLine.Option(names = {"-g", "--groupid"})
    private String groupId = "io.javaoperatorsdk.sample";

    @CommandLine.Option(names = {"-a", "--artifactid"})
    private String artifactId = "sample-operator";

    @CommandLine.Option(names = {"-n", "--name"})
    private String projectName = "A sample project";

    @CommandLine.Option(names = {"-d", "--description"})
    private String description = "A sample project";

    @CommandLine.Option(names = {"-v", "--sdk-version"})
    private String sdkVersion = "1.5.0";


    public void run() {
        final var renderer = new TemplateRenderer();
        // generate build tools files
        Map<String, String> context = Map.of(
                "groupid", groupId,
                "artifactid", artifactId,
                "name", projectName,
                "description", description,
                "sdkversion", sdkVersion
        );
        System.out.printf(System.getProperty("user.dir"));
        System.out.println("The out directory is " + outDirectory);
        renderer.render("templates" + File.separator + framework + File.separator + buildTool + File.separator + "build", context, outDirectory);
        System.out.println("Build tool files generated successfully");
        renderer.render("templates" + File.separator + framework + File.separator + buildTool + File.separator + "codes", context,
                outDirectory + File.separator + ("src.main.java." + groupId).replaceAll("\\.", File.separator));
        System.out.println("Source code files generated successfully");

    }
}
