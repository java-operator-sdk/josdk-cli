package x.y.z;

import io.quarkus.runtime.annotations.RecordableConstructor;
import java.net.URL;
import java.util.List;

public class TestResource {
  private List<URL> urls;

  @RecordableConstructor
  public TestResource() {}

  @RecordableConstructor
  public TestResource(List<URL> urls) {
    this.urls = urls;
  }

  public List<URL> getUrls() {
    return urls;
  }
}
