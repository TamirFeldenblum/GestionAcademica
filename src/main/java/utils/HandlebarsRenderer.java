package utils;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.http.Context;
import io.javalin.plugin.rendering.FileRenderer;

import java.io.StringWriter;
import java.util.Map;

public class HandlebarsRenderer implements FileRenderer {

    private final Handlebars handlebars;

    public HandlebarsRenderer() {
        this.handlebars = new Handlebars();
    }

    @Override
    public String render(String filePath, Map<String, Object> model, Context context) throws Exception {
        Template template = handlebars.compile(filePath.replace(".hbs", ""));
        StringWriter writer = new StringWriter();
        template.apply(model, writer);
        return writer.toString();
    }
}
