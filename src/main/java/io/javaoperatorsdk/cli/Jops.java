package io.javaoperatorsdk.cli;

import io.javaoperatorsdk.cli.commands.InitCommand;
import picocli.CommandLine;

@CommandLine.Command(
    subcommands = {InitCommand.class},
    customSynopsis = "Usage: jops [COMMAND]")
public class Jops {
  public static void main(String[] args) {
    System.exit(new CommandLine(new Jops()).execute(args));
  }
}
