package io.javaoperatorsdk.cli;

import io.javaoperatorsdk.cli.commands.InitCommand;
import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

@TopCommand
@CommandLine.Command(
    subcommands = {InitCommand.class},
    customSynopsis = "Usage: jops [COMMAND]")
public class Jops {}
