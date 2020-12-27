package x.y.z;

import io.quarkus.runtime.annotations.Recorder;
import java.util.function.Supplier;

@Recorder
public class TestResourceRecorder {
  public Supplier<TestResource> getSupplier() {
    return new Supplier<TestResource>() {
      @Override
      public TestResource get() {
        return new TestResource();
      }
    };
  }
}
