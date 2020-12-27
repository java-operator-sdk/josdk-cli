package io.javaoperatorsdk.cli;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.inject.Inject;
import x.y.z.TestResource;

import java.util.HashMap;

@QuarkusMain
// @CommandLine.Command(
//        subcommands = {InitCommand.class},
//        customSynopsis = "Usage: jops [COMMAND]")
public class Jops implements QuarkusApplication {

  @Inject private TestResource indexes;

  public static void main(String[] args) {
    Quarkus.run(Jops.class, args);
    //        System.exit(new CommandLine(new Jops()).execute(args));
  }

  @Override
  public int run(String... args) throws Exception {
    System.out.println(indexes);
    return 0;
  }
}
