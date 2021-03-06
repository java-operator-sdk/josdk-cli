package io.javaoperatorsdk.cli.commands;

import io.javaoperatorsdk.cli.renderer.TemplateRenderer;
import io.javaoperatorsdk.quarkus.resources.ResourceLocator;
import java.io.File;
import java.util.Map;
import javax.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "init",
    mixinStandardHelpOptions = true,
    description = "initialize a project by default in the current path")
public class InitCommand implements Runnable {

  @CommandLine.Option(
      names = {"-b", "--buildTool"},
      defaultValue = "maven",
      description = "The build tool you would like to use, choices: ['maven', 'gradle']")
  private String buildTool;

  @CommandLine.Option(
      names = {"-f", "--framework"},
      defaultValue = "none",
      description =
          "The framework to be used in developing the operator, choices: ['none', 'quarkus','spring-boot']")
  private String framework;

  @CommandLine.Option(
      names = {"-o", "--output"},
      description = "The output directory, default: the current working directory.")
  private String outDirectory = System.getProperty("user.dir");

  @CommandLine.Option(
      names = {"-g", "--groupid"},
      defaultValue = "io.javaoperatorsdk.sample")
  private String groupId;

  @CommandLine.Option(
      names = {"-a", "--artifactid"},
      defaultValue = "sample-operator")
  private String artifactId;

  @CommandLine.Option(
      names = {"-n", "--name"},
      defaultValue = "A sample project")
  private String projectName;

  @CommandLine.Option(
      names = {"-d", "--description"},
      defaultValue = "A sample project")
  private String description;

  @CommandLine.Option(
      names = {"-v", "--sdk-version"},
      defaultValue = "1.6.2-SNAPSHOT")
  private String sdkVersion;

  @CommandLine.Option(
      names = {"-i", "--image"},
      defaultValue = "mycontroller",
      description = "The image name of the operator.")
  private String imageName;

  @Inject private ResourceLocator indexes;

  public void run() {
    final var renderer = new TemplateRenderer(indexes);
    Map<String, String> context = generateContext();

    renderer.render(
        "templates"
            + File.separator
            + framework
            + File.separator
            + buildTool
            + File.separator
            + "build",
        context,
        outDirectory);
    renderer.render(
        "templates" + File.separator + framework + File.separator + "sources",
        context,
        outDirectory
            + File.separator
            + ("src.main.java." + groupId).replaceAll("\\.", File.separator));

    renderer.render(
        "templates" + File.separator + framework + File.separator + "deployment",
        context,
        outDirectory + File.separator + "deployment");
  }

  private Map<String, String> generateContext() {
    return Map.of(
        "groupid", groupId,
        "artifactid", artifactId,
        "name", projectName,
        "description", description,
        "sdkversion", sdkVersion,
        "image", imageName);
  }
}
