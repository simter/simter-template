package tech.simter.template.impl;

import tech.simter.template.TemplateEngine;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * The Thymeleaf implementation of {@link TemplateEngine}.
 *
 * @author RJ
 */
@Named(TemplateEngine.TYPE_THYMELEAF)
@Singleton
public class ThymeleafEngine implements TemplateEngine {
  @Override
  public String renderTemplate(String templatePath, Object data) {
    throw new UnsupportedOperationException("Not Implemented.");
  }

  @Override
  public String renderContent(String templateContent, Object data) {
    throw new UnsupportedOperationException("Not Implemented.");
  }
}