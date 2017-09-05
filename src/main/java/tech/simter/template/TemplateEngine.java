package tech.simter.template;

/**
 * The template engine interface.
 *
 * @author RJ
 */
public interface TemplateEngine {
  /**
   * The FreeMarker TemplateEngine bean name.
   * <p>
   * see http://freemarker.org
   */
  String TYPE_FREEMARKER = "freemarker-template-engine";
  /**
   * The Velocity TemplateEngine bean name.
   * <p>
   * see http://www.velocity.com
   */
  String TYPE_VELOCITY = "velocity-template-engine";
  /**
   * The Thymeleaf TemplateEngine bean name.
   * <p>
   * see http://www.thymeleaf.org
   */
  String TYPE_THYMELEAF = "thymeleaf-template-engine";

  /**
   * Load template and render it with the specific data.
   *
   * @param templatePath The template path name
   * @param data         The data-model
   * @return The rendered string
   */
  String renderTemplate(String templatePath, Object data);

  /**
   * Render the content with the specific data.
   *
   * @param templateContent The template's actual content
   * @param data            The data-model
   * @return The rendered string
   */
  String renderContent(String templateContent, Object data);
}