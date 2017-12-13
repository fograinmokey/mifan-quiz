package com.mifan.quiz;

import org.moonframework.core.context.ApplicationContextHolder;
import org.moonframework.model.mybatis.service.Services;
import org.moonframework.web.context.ServletContextHolder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * <p>Web Application Configuration</p>
 * <p>To enable @AspectJ support with Java @Configuration add the @EnableAspectJAutoProxy annotation:</p>
 * <p>To enable support for @Scheduled and @Async annotations add @EnableScheduling and @EnableAsync to one of your @Configuration classes:</p>
 *
 * @author quzile
 * @version 1.0
 * @since 2016/06/13
 */
@Configuration
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
public class WebApplicationConfig {


    @Bean
    public ApplicationContextHolder springContextHolder() {
        return new ApplicationContextHolder();
    }

    @Bean
    public ServletContextHolder servletContextHolder() {
        return new ServletContextHolder();
    }

    @Bean
    public Services services() {
        return new Services();
    }

    /**
     * <p>A Validator</p>
     * <p>Use Hibernate Validator e.g: validator.setProviderClass(HibernateValidator.class)</p>
     *
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    /**
     * <p>I18N config</p>
     * <p>Use ReloadableResourceBundleMessageSource Or ResourceBundleMessageSource</p>
     *
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages/exceptions", "classpath:messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
