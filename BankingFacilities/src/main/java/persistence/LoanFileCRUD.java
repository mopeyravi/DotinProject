package persistence;

import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.management.*;
import java.util.List;

public class LoanFileCRUD {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private static void configureDB() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void deleteLoanFile(Long loanID) {
        configureDB();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            LoanFile loanFile= (LoanFile) session.load(LoanFile.class, loanID);
            session.delete(loanFile);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<LoanFile> loadAllLoanFiles() {
        configureDB();
        Session session = sessionFactory.openSession();
        List<LoanFile> loanFiles;
        try {
            Criteria selectCriteria = session.createCriteria(LoanFile.class);
            loanFiles= selectCriteria.list();
            return loanFiles;
        } finally {
            session.close();
            sessionFactory.close();
        }
    }


    public static long openLoanFile(LoanFile loanFile) {
        configureDB();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Long loanID = null;
        try {
            transaction = session.beginTransaction();
            String hql="From LoanType where id=:loanTypeID";
            Query query=session.createQuery(hql);
            query.setParameter("loanTypeID",loanFile.getLoanType().getId());
            LoanType loanType=(LoanType)query.uniqueResult();
            loanFile.setLoanType(loanType);
            loanID = (Long) session.save(loanFile);
            transaction.commit();
            return loanID;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new Long(0);
        } finally {

            session.close();
            sessionFactory.close();
        }

    }
}
