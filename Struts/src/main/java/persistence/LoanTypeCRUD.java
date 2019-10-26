package main.java.persistence;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.HashSet;
import java.util.List;

public class LoanTypeCRUD {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private static void configureDB() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void deleteLoanType(Integer loanID) {
        configureDB();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            LoanType loanType = (LoanType) session.load(LoanType.class, loanID);
            session.delete(loanType);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<LoanType> loadAllLoanTypes() {
        configureDB();
        Session session = sessionFactory.openSession();
        List<LoanType> loanTypes;
        try {
            Criteria selectCriteria = session.createCriteria(LoanType.class);
            loanTypes = selectCriteria.list();
            return loanTypes;
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public static LoanType loadLoanByTypeName(String typeName) {
        configureDB();
        Session session = sessionFactory.openSession();
        List<LoanType> loanTypes;
        Criteria selectCriteria = session.createCriteria(LoanType.class);
        selectCriteria.add(Restrictions.eq("typeName", typeName));
        try {
            loanTypes = (List<LoanType>) selectCriteria.list();
            for (LoanType loanType : loanTypes) {
                Hibernate.initialize(loanType.getGrantConditions());
            }
            return loanTypes.get(0);
        }
        finally {
            session.close();
            sessionFactory.close();

        }
    }

    public static long addLoan(String typeName, int interestRate, HashSet grantConditions) {
        configureDB();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Long loanID = null;
        try {
            transaction = session.beginTransaction();
            LoanType loanType = new LoanType(typeName, interestRate);
            loanType.setGrantConditions(grantConditions);
            loanID = (Long) session.save(loanType);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return loanID;
    }
}
