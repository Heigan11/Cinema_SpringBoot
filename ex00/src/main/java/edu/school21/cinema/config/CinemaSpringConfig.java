package edu.school21.cinema.config;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Locale;

@Configuration
public class CinemaSpringConfig implements WebMvcConfigurer {

    @Autowired
    private ServletContext context;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeHolderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return placeHolderConfigurer;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException, TemplateException
    {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer()
        {

            @Override
            protected void postProcessConfiguration(freemarker.template.Configuration config) throws IOException, TemplateException
            {
                WebappTemplateLoader WebAppTplLoader = new WebappTemplateLoader(context, "/WEB-INF/ftl");
                ClassTemplateLoader classTplLoader = new ClassTemplateLoader(context.getClassLoader(), "/templates");
                ClassTemplateLoader baseMvcTplLoader = new ClassTemplateLoader(FreeMarkerConfigurer.class, "");
                MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[]
                        {
                                WebAppTplLoader,
                                classTplLoader,
                                baseMvcTplLoader
                        });
                config.setTemplateLoader(mtl);
            }
        };
        configurer.setDefaultEncoding("UTF-8");
        configurer.setPreferFileSystemAccess(false);
        return configurer;
    }

    @Bean
    public FreeMarkerViewResolver viewResolver()
    {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setExposeRequestAttributes(true);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        slr.setCookieName("LOCALE");
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
}
