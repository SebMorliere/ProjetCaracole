package services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import play.Logger;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    //Créé une unique instance de l'objet
    static{
        try{
            Logger.debug("sessionFactory:");
//            Nouvelle méthode
//            Configuration configuration = new Configuration();
//            configuration.configure();
//            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//            Méthode dépréciée
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        }catch(HibernateException e){
            Logger.error("sessionFactoryError:", e);
            throw new RuntimeException("Problème de configuration hibernate : " + e.getMessage(), e);
        }
    }

    public static void HibernateTest() {

        try {
            Logger.debug("sessionFactoryTEST:");
//            Nouvelle méthode
//            Configuration configuration = new Configuration();
//            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings((Map) configuration.setProperty("hibernate.connection.url", "jdbc:mysql://10.0.0.174:3306/caracoletest")).buildServiceRegistry();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//            Méthode dépréciée
            sessionFactory = new Configuration()
                    .configure("hibernatetest.cfg.xml")
                    .buildSessionFactory();
        } catch (HibernateException e) {
            Logger.error("sessionFactoryTESTError:", e);
            throw new RuntimeException("Problème de configuration hibernate : " + e.getMessage(), e);
        }
    }

    //Renvoie la session Hibernate
    public static Session getSession() throws HibernateException{
        Logger.debug("getSession:");
        return sessionFactory.openSession();
    }
}
