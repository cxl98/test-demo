package com.cxl.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WenInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext configWebApplicationContext=new AnnotationConfigWebApplicationContext();
        configWebApplicationContext.register(WebConfig.class);
        configWebApplicationContext.setServletContext(servletContext);
        configWebApplicationContext.refresh();

        ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher",new DispatcherServlet(configWebApplicationContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
