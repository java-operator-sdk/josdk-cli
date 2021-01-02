package io.javaoperatorsdk.quarkus.resources;

import io.quarkus.runtime.annotations.Recorder;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.reflections8.Reflections;
import org.reflections8.scanners.ResourcesScanner;
import org.reflections8.util.Utils;

@Recorder
public class TestResourceRecorder {

  private List<String> resourcePaths = new ArrayList<>();

  public TestResourceRecorder() {
    //        PathMatchingResourcePatternResolver s = new PathMatchingResourcePatternResolver();
    //        try {
    //            Resource[] resources = s.getResources("templates/**");
    //            resourcePaths =
    //                    Arrays.stream(resources)
    //                            .map(
    //                                    r -> {
    //                                        try {
    //                                            final var splitted =
    // r.getURL().getPath().split("!");
    //                                            return splitted[splitted.length - 1];
    //                                        } catch (IOException e) {
    //                                            e.printStackTrace();
    //                                            return null;
    //                                        }
    //                                    })
    //                            .collect(Collectors.toList());
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }

    try {
      List<String> resources = getResourceFiles("templates");
      resourcePaths.addAll(resources);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private List<String> getResourceFiles(String path) {
    Reflections reflections = new Reflections(null, new ResourcesScanner());
    final var resources =
        reflections.getStore().get(Utils.index(ResourcesScanner.class)).values().stream()
            .flatMap(Set::stream)
            .filter(
                p -> {
                  System.out.println(p);
                  return p.contains(path);
                })
            .collect(Collectors.toList());

    return new ArrayList<>(resources);
  }

  public Supplier<ResourceLocator> getSupplier() {
    return () -> new ResourceLocator(resourcePaths);
  }
}
