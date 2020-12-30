package io.javaoperatorsdk.quarkus.resources;

import io.quarkus.runtime.annotations.RecordableConstructor;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceLocator {

  private List<String> paths;

  @RecordableConstructor
  public ResourceLocator(List<String> paths) {
    this.paths = paths;
  }

  public List<String> contains(String path) {
    return this.paths.stream().filter(url -> url.contains(path)).collect(Collectors.toList());
  }
}
