package io.github.zhengyhn.practice.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        SessionFactory sessionFactory;
        try {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            configuration.addAnnotatedClass(Sample.class);
            ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(srvcReg);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Sample sample = new Sample();
        sample.setId(1L);
        sample.setLowerStatus(BaStatus.A);
        sample.setUpperStatus(ABStatus.A);
        session.save(sample);
        session.flush();
        transaction.commit();

//        testLowerCase(session);
        testUpperCase(session);

        session.close();
    }

    private static void testLowerCase(Session session) {
        String sql = "select s from Sample s where s.lowerStatus = io.github.zhengyhn.practice.hibernate.BaStatus.A";
        List result = session.createQuery(sql).list();
        System.out.println(result.get(0));
    }

    private static void testUpperCase(Session session) {
        String sql = "select s from Sample s where s.upperStatus = io.github.zhengyhn.practice.hibernate.ABStatus.A";
        List result = session.createQuery(sql).list();
        System.out.println(result.get(0));
    }
}
