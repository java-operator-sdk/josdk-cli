package io.javaoperatorsdk.quarkus.resources;

import io.quarkus.runtime.annotations.Recorder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Recorder
public class TestResourceRecorder {
  private List<URL> resourcePaths = new ArrayList<>();

  public TestResourceRecorder() {
    PathMatchingResourcePatternResolver s = new PathMatchingResourcePatternResolver();
    try {
      Resource[] resources = s.getResources("templates/**");
      resourcePaths =
          Arrays.stream(resources)
              .map(
                  r -> {
                    try {
                      return r.getURL();
                    } catch (IOException e) {
                      e.printStackTrace();
                      return null;
                    }
                  })
              .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Supplier<ResourceURLLocator> getSupplier() {
    return () -> new ResourceURLLocator(resourcePaths);
  }
}
