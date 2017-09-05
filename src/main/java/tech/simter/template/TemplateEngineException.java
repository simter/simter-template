package tech.simter.template;

/**
 * The Template Exception.
 */
public class TemplateEngineException extends RuntimeException {
  public TemplateEngineException() {
    super();
  }

  public TemplateEngineException(String message) {
    super(message);
  }

  public TemplateEngineException(Throwable cause) {
    super(cause);
  }

  public TemplateEngineException(String message, Throwable cause) {
    super(message, cause);
  }
}
