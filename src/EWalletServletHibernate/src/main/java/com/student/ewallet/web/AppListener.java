package com.student.ewallet.web;

import com.student.ewallet.util.JpaUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        JpaUtil.createEntityManager().close();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        JpaUtil.close();
    }
}
