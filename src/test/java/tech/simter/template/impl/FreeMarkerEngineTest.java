package tech.simter.template.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tech.simter.template.TemplateEngine;
import tech.simter.template.bean.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author RJ
 */
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {FreeMarkerEngine.class, FreeMarkerConfiguration.class})
@ContextConfiguration(classes = {FreeMarkerEngine.class})
public class FreeMarkerEngineTest {
  @Inject
  @Named(TemplateEngine.TYPE_FREEMARKER)
  private TemplateEngine templateEngine;

  @Test
  public void renderTemplate() {
    // Create a data-model
    Map<String, Object> data = new HashMap<>();
    data.put("msg", "Hello");

    // default root resource path is '/'

    // load resource 'src/test/resources/test.ftl' and render it
    assertThat(templateEngine.renderTemplate("test.ftl", data), is("/test.ftl:Hello"));

    // load resource 'src/test/resources/templates/test.ftl' and render it
    assertThat(templateEngine.renderTemplate("templates/test.ftl", data), is("/templates/test.ftl:Hello"));
  }

  @Test
  public void renderContent() {
    // Create a data-model
    Map<String, Object> data = new HashMap<>();
    data.put("msg", "Hello");

    // Render
    assertThat(templateEngine.renderContent("--${msg}--", data), is("--Hello--"));

    // Render
    User user = new User();
    user.id = 1; // freeMarker not support field
    user.setAccount("test");
    data.put("user", user);
    assertThat(templateEngine.renderContent("${user.id!2}/${user.account!'a'}", data), is("2/test"));
  }
}