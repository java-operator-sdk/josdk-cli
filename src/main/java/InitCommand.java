import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;


@Command(name = "init", mixinStandardHelpOptions = true,
        description = "initialize a project by default in the current path")
public class InitCommand implements Runnable {

    @CommandLine.Option(names = {"-b", "--buildTool"}, description = "The build tool you would like to use, choices: ['maven', 'gradle']")
    private String buildTool;

    @CommandLine.Option(names = {"-f", "--framework"}, description = "The framework to be used in developing the operator, choices: ['none', 'quarkus','spring-boot']")
    private String framework;


    public void run() {
        Template template = Mustache.compiler().withLoader(
                new Mustache.TemplateLoader() {
                    @Override
                    public Reader getTemplate(String s) throws Exception {
                        return new InputStreamReader(
                                Thread.currentThread().getContextClassLoader().getResourceAsStream(s)
                        );
                    }
                }
        ).loadTemplate("templates/maven/none/pom.xml");
        System.out.println(template.execute(Map.of("name", "jops")));
        System.out.println(framework);
        System.out.println(buildTool);
    }
}
