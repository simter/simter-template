package tech.simter.template.impl;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.template.TemplateEngine;
import tech.simter.template.bean.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RJ
 */
@SpringJUnitConfig(FreeMarkerEngine.class)
class FreeMarkerEngineTest {
  @Inject
  @Named(TemplateEngine.TYPE_FREEMARKER)
  private TemplateEngine templateEngine;

  @Test
  void renderTemplate() {
    // Create a data-model
    Map<String, Object> data = new HashMap<>();
    data.put("msg", "Hello");

    // default root resource path is '/'

    // load resource 'src/test/resources/test.ftl' and render it
    assertEquals("/test.ftl:Hello", templateEngine.renderTemplate("test.ftl", data));

    // load resource 'src/test/resources/templates/test.ftl' and render it
    assertEquals("/templates/test.ftl:Hello", templateEngine.renderTemplate("templates/test.ftl", data));
  }

  @Test
  void renderContent() {
    // Create a data-model
    Map<String, Object> data = new HashMap<>();
    data.put("msg", "Hello");

    // Render
    assertEquals("--Hello--", templateEngine.renderContent("--${msg}--", data));

    // Render
    User user = new User();
    user.id = 1; // freeMarker not support field
    user.setAccount("test");
    data.put("user", user);
    assertEquals("2/test", templateEngine.renderContent("${user.id!2}/${user.account!'a'}", data));
  }
}