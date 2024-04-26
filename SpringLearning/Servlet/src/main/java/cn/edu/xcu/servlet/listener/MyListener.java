package cn.edu.xcu.servlet.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyListener init");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MyListener destroyed");
    }
}
