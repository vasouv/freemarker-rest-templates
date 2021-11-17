package vs.freemarkerresttemples;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("freemarker")
public class FreemarkerController {

    final Configuration configuration;

    public FreemarkerController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String freemarker(@RequestParam("username") String username, @RequestParam("password") String password) throws IOException, TemplateException {

        List<String> metal = List.of("Heavy", "Power", "Epic");
        Map<String, Object> model = Map.of("username", username, "password", password, "metal", metal.toString());

        StringWriter writer = new StringWriter();
        configuration.getTemplate("freemarker-template.ftlh").process(model, writer);

        return writer.getBuffer().toString();
    }

}
