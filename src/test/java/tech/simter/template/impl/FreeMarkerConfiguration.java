package tech.simter.template.impl;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfiguration {
  @Bean
  public Configuration getConfiguration() {
    System.out.println("getConfiguration");
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
    cfg.setClassForTemplateLoading(this.getClass(), "/"); // 指向根路径
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    return cfg;
  }
}