package io.javaoperatorsdk.cli.renderer;

import com.samskivert.mustache.Mustache;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;
import java.util.Map;

public class TemplateRenderer {

    private String outDirectory;

    public TemplateRenderer(String outDirectory) {
        this.outDirectory = outDirectory;
    }

    public TemplateRenderer() {
        this(System.getProperty("user.dir"));
    }

    Mustache.Compiler mustacheCompiler = Mustache.compiler();

    public void render(String templatePath, Map data) {
        final var templatePathPattern = templatePath + File.separator + "**";
        PathMatchingResourcePatternResolver s = new PathMatchingResourcePatternResolver();

        Resource[] resources;
        try {
            resources = s.getResources(templatePathPattern);
            for (Resource r : resources) {
                if (r.isFile() && r.isReadable()) {
                    final var relativePath = getRelativePath(templatePath, r);
                    final var template = mustacheCompiler.compile(new InputStreamReader(r.getInputStream()));
                    writeFile(data, relativePath, template);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(Map data, String relativePath, com.samskivert.mustache.Template template) throws IOException {
        final var file = new File(this.outDirectory + File.separator + relativePath);
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
