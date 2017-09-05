package tech.simter.template.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import tech.simter.template.TemplateEngine;
import tech.simter.template.TemplateEngineException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Optional;

/**
 * The FreeMarker implementation of {@link TemplateEngine}.
 * <p>
 * Can config resourceLoaderClass by key 'simter.template-engine.freemarker.resource-loader-class',
 * default is this instance class.
 *
 * @author RJ
 */
@Named(TemplateEngine.TYPE_FREEMARKER)
@Singleton
public class FreeMarkerEngine implements TemplateEngine {
  private Configuration configuration;

  // Use Optional is same as @Autowired(require = true)
  // https://stackoverflow.com/questions/19485878/can-inject-be-made-optional-in-jsr-330-like-autowirerequired-false
  @Inject
  public FreeMarkerEngine(Optional<Configuration> configuration,
                          @Value("${simter.template-engine.freemarker.resource-loader-class:#{null}}") Class<?> resourceLoaderClass) {
    this.configuration = configuration.orElse(createDefaultConfiguration(resourceLoaderClass));
  }

  /**
   * Create default configuration.
   * <p>
   * see http://freemarker.org/docs/pgui_quickstart_all.html
   *
   * @return The default configuration
   */
  private Configuration createDefaultConfiguration(Class<?> resourceLoaderClass) {
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);

    // 指向根路径
    configuration.setClassForTemplateLoading(resourceLoaderClass != null ? resourceLoaderClass : this.getClass(), "/");

    configuration.setDefaultEncoding("UTF-8");
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    return configuration;
  }

  @Override
  public String renderTemplate(String templatePath, Object data) {
    try (Writer out = new StringWriter()) {
      // Get the template (uses cache internally)
      Template template = configuration.getTemplate(templatePath);

      // Merge data-model with template
      template.process(data, out);
      return out.toString();
    } catch (IOException | freemarker.template.TemplateException e) {
      throw new TemplateEngineException(e);
    }
  }

  @Override
  public String renderContent(String templateContent, Object data) {
    if (templateContent == null || templateContent.isEmpty()) return "";

    try (Writer out = new StringWriter()) {
      // Create template
      Template template = new Template("innerName", new StringReader(templateContent), configuration);

      // Merge data-model with template
      template.process(data, out);
      return out.toString();
    } catch (IOException | freemarker.template.TemplateException e) {
      throw new TemplateEngineException(e);
    }
  }
}