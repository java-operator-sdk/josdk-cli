import picocli.CommandLine;
import picocli.CommandLine.Command;


@Command(name = "init", mixinStandardHelpOptions = true,
        description = "initialize a project by default in the current path")
public class InitCommand implements Runnable {

    @CommandLine.Option(names = {"-b", "--buildTool"}, description = "The build tool you would like to use, choices: ['maven', 'gradle']")
    private String buildTool;

    @CommandLine.Option(names = {"-f", "--framework"}, description = "The framework to be used in developing the operator, choices: ['none', 'quarkus','spring-boot']")
    private String framework;


    public void run() {
        System.out.println("hello world");
        System.out.println(framework);
        System.out.println(buildTool);
    }
}
