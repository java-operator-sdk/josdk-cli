package io.javaoperatorsdk.quarkus.resources;

import io.quarkus.runtime.annotations.RecordableConstructor;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceURLLocator {

  private List<URL> urls;

  @RecordableConstructor
  public ResourceURLLocator() {
  }

  @RecordableConstructor
  public ResourceURLLocator(List<URL> urls) {
    this.urls = urls;
  }

  public List<URL> contains(String path) {
    return this.urls.stream()
        .filter(url -> url.getPath().contains(path))
        .collect(Collectors.toList());
  }
}
