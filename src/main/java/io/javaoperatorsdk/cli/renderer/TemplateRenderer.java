package io.javaoperatorsdk.cli.renderer;

import com.samskivert.mustache.Mustache;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;
import java.util.Map;

public class TemplateRenderer {
    Mustache.Compiler mustacheCompiler = Mustache.compiler();

    public void render(String templatePath, Map<String, String> data, String outDirectory) {
        final var templatePathPattern = templatePath + File.separator + "**";
        PathMatchingResourcePatternResolver s = new PathMatchingResourcePatternResolver();

        Resource[] resources;
        try {
            resources = s.getResources(templatePathPattern);
            for (Resource r : resources) {
                if (r.isReadable()) {
                    final var relativePath = getRelativePath(templatePath, r);
                    final var template = mustacheCompiler.compile(new InputStreamReader(r.getInputStream()));
                    writeFile(data, relativePath, template, outDirectory);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(Map<String, String> data, String relativePath, com.samskivert.mustache.Template template, String outDirectory) throws IOException {
        final var file = new File(outDirectory + File.separator + relativePath);
        file.getParentFile().mkdirs();
        try (final var outputStream = new OutputStreamWriter(new FileOutputStream(file))) {
            template.execute(data, outputStream);
            outputStream.flush();
        }
    }

    private String getRelativePath(String templatePath, Resource r) throws IOException {
        final var startIndex = r.getURL().getPath().indexOf(templatePath);
        return r.getURL().getPath().substring(startIndex + templatePath.length() + 1);
    }
}
