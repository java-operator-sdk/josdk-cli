package x.y.z;

import static org.junit.jupiter.api.Assertions.*;

import io.quarkus.test.QuarkusUnitTest;
import javax.inject.Inject;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class ResourceProcessorTest {

  @RegisterExtension
  static final QuarkusUnitTest config =
      new QuarkusUnitTest()
          .setArchiveProducer(
              () ->
                  ShrinkWrap.create(JavaArchive.class)
                      .addClasses(TestResource.class, ResourceBuildStep.class));

  @Inject private TestResource indexes;

  @Test
  public void t() {
    System.out.println(indexes);
  }
}
