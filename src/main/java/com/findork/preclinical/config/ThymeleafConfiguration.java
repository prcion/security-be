//package com.findork.preclinical.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Description;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
//
//import java.nio.charset.StandardCharsets;
//
//@Configuration
//public class ThymeleafConfiguration {
//
//    @Value("${spring.mail.templates-path}")
//    private String templatesPath;
//
//    @Bean
//    @Description("Thymeleaf template resolver serving HTML 5 emails")
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
//        emailTemplateResolver.setPrefix(templatesPath);
//        emailTemplateResolver.setSuffix(".html");
//        emailTemplateResolver.setTemplateMode("HTML");
//        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        emailTemplateResolver.setOrder(1);
//        return emailTemplateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine(){
//        // SpringTemplateEngine automatically applies SpringStandardDialect and
//        // enables Spring's own MessageSource message resolution mechanisms.
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
//        // speed up execution in most scenarios, but might be incompatible
//        // with specific cases when expressions in one template are reused
//        // across different data types, so this flag is "false" by default
//        // for safer backwards compatibility.
//        templateEngine.setEnableSpringELCompiler(true);
//        return templateEngine;
//    }
//
//}
