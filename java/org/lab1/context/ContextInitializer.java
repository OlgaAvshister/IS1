package org.lab1.context;

import org.lab1.data.entity.*;

import org.lab1.data.Actions;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        User existingAdmin = Actions.getUserByLogin("main");
        User admin = new User();
        admin.setLogin("main");
        admin.setPassword("1234");
        admin.setAdmin(true);
        admin.setNotMain(false);
        if (existingAdmin == null) {
            Actions.addMain(admin);
        }
        ServletContextListener.super.contextInitialized(sce);
    }
}
