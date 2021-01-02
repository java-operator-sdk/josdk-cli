package io.javaoperatorsdk.quarkus.resources;

import io.quarkus.runtime.annotations.Recorder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.reflections8.Reflections;
import org.reflections8.scanners.ResourcesScanner;
import org.reflections8.util.Utils;

@Recorder
public class TestResourceRecorder {

  private List<String> resourcePaths = new ArrayList<>();

  public TestResourceRecorder() {
    List<String> resources = getResourceFiles("templates");
    resourcePaths.addAll(resources);
  }

  private List<String> getResourceFiles(String path) {
    Reflections reflections = new Reflections(null, new ResourcesScanner());

    return reflections.getStore().get(Utils.index(ResourcesScanner.class))
        .values()
        .stream()
        .flatMap(Set::stream)
        .filter(
            p -> {
              System.out.println(p);
              return p.contains(path);
            }).collect(Collectors.toList());
  }

  public Supplier<ResourceLocator> getSupplier() {
    return () -> new ResourceLocator(resourcePaths);
  }
}
