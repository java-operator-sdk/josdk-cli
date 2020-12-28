package x.y.z;

import io.quarkus.runtime.annotations.RecordableConstructor;
import java.net.URL;
import java.util.List;

public class ResourceURLLocator {
  private List<URL> urls;

  @RecordableConstructor
  public ResourceURLLocator() {}

  @RecordableConstructor
  public ResourceURLLocator(List<URL> urls) {
    this.urls = urls;
  }

  public List<URL> getUrls() {
    return urls;
  }
}
