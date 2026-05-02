package com.klef.fsad.exam;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                config.configure("hibernate.cfg.xml");
                ServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties())
                        .build();
                sessionFactory = config.buildSessionFactory(registry);
            } catch (Exception e) {
                System.out.println("Error creating SessionFactory: " + e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
