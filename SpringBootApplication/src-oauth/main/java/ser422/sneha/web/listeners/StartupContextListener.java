package ser422.sneha.web.listeners;

import ser422.sneha.web.factory.ArticleDAOFactory;
import ser422.sneha.web.factory.PersonDAOFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Properties;

public class StartupContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
        String persistence = servletContextEvent.getServletContext().getInitParameter("persistence");
            if (persistence.equals("mongo")) {
                String host = servletContextEvent.getServletContext().getInitParameter("mongoHost").trim();
                String port = servletContextEvent.getServletContext().getInitParameter("mongoPort").trim();

                ArticleDAOFactory.initialize(false, host, port);
                PersonDAOFactory.initialize(false, host,port);
            }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down the application!");
    }

}
