package io.javaoperatorsdk.cli.renderer;

import com.samskivert.mustache.Mustache;
import io.javaoperatorsdk.quarkus.resources.ResourceLocator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

public class TemplateRenderer {

  Mustache.Compiler mustacheCompiler = Mustache.compiler();
  private ResourceLocator resourceLocator;

  public TemplateRenderer(ResourceLocator resourceLocator) {
    this.resourceLocator = resourceLocator;
  }

  public void render(String templatePath, Map<String, String> data, String outDirectory) {
    List<String> paths = resourceLocator.contains(templatePath);
    try {
      for (String path : paths) {
        boolean isDirectory = path.endsWith(File.separator);
        if (isDirectory) {
          continue;
        }
        final var relativePath = getRelativePath(templatePath, path);
        if (path.startsWith("/")) {
          path = path.substring(1);
        }
        final var template =
            mustacheCompiler.compile(
                new InputStreamReader(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream(path)));
        writeFile(data, relativePath, template, outDirectory);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeFile(
      Map<String, String> data,
      String relativePath,
      com.samskivert.mustache.Template template,
      String outDirectory)
      throws IOException {
    final var file = new File(outDirectory + File.separator + relativePath);
    file.getParentFile().mkdirs();
    try (final var outputStream = new OutputStreamWriter(new FileOutputStream(file))) {
      template.execute(data, outputStream);
      outputStream.flush();
    }
  }

  private String getRelativePath(String templatePath, String path) {
    final var startIndex = path.indexOf(templatePath);
    return path.substring(startIndex + templatePath.length() + 1);
  }
}
