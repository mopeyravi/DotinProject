package persistence;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class CustomerCRUD {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    static Logger log = Logger.getLogger(CustomerCRUD.class.getName());

    private static void configureDB() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static Long addLegalCustomer(String companyName, String economicCode, String submissionDate) {
        configureDB();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Long customerID = null;
        try {
            transaction = session.beginTransaction();
            LegalCustomer legalCustomer = new LegalCustomer(companyName, economicCode, submissionDate);
            customerID = (Long) session.save(legalCustomer);
            transaction.commit();
            log.info("New legal customer is defined successfully");
        } catch (HibernateException e) {
            log.error("Error in inserting legal Customer");
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return customerID;
    }

    public static Long addRealCustomer(String firstName, String lastName, String fatherName, String birthDate, String nationalCode) {
        configureDB();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Long customerID = null;
        try {
            transaction = session.beginTransaction();
            RealCustomer realCustomer = new RealCustomer(firstName, lastName, fatherName, birthDate, nationalCode);
            customerID = (Long) session.save(realCustomer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return customerID;
    }

    public static List<RealCustomer> loadRealCustomer(Long customerID, String firstName, String lastName, String nationalCode) {
        configureDB();
        Session session = sessionFactory.openSession();
        List<RealCustomer> realCustomers;
        Criteria selectCriteria = session.createCriteria(RealCustomer.class);
        if (!customerID.equals(new Long(0))) {
            selectCriteria.add(Restrictions.eq("customerID", customerID));
        }
        if (!firstName.isEmpty()) {
            selectCriteria.add(Restrictions.eq("firstName", firstName));
        }
        if (!lastName.isEmpty()) {
            selectCriteria.add(Restrictions.eq("lastName", lastName));
        }
        if (!nationalCode.isEmpty()) {
            selectCriteria.add(Restrictions.eq("nationalCode", nationalCode));
        }
        try {
            realCustomers = selectCriteria.list();
            return realCustomers;
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }

    public static List<RealCustomer> listRealCustomer(Long customerID, String firstName, String lastName, String nationalCode) {
        configureDB();
        Session session = sessionFactory.openSession();
        List<RealCustomer> realCustomers;

        String hql = "FROM RealCustomer WHERE ";

        int flag = 0;
        if (!customerID.equals(new Long(0))) {
            hql += "customerID = :customerid,";
            flag = 1;
        }
        if (!firstName.isEmpty()) {
            flag = 1;
            hql += "firstName = :firstname,";
        }
        if (!lastName.isEmpty()) {
            flag = 1;
            hql += "lastName = :lastname,";
        }
        if (!nationalCode.isEmpty()) {
            flag = 1;
            hql += "nationalCode = :nationalcode,";
        }
        if (hql.endsWith(",")) {
            flag = 1;
            hql = hql.substring(0, hql.length() - 1);
        }
        if (flag == 0) {
            hql = "FROM RealCustomer ";
        }
        Query query = session.createQuery(hql);
        if (hql.contains("customerID")) {
            query.setParameter("customerid", customerID);
        }
        if (hql.contains("firstName")) {
            query.setParameter("firstname", firstName);
        }
        if (hql.contains("lastName")) {
            query.setParameter("lastname", lastName);
        }
        if (hql.contains("nationalCode")) {
            query.setParameter("nationalcode", nationalCode);
        }
        try {
            realCustomers = (ArrayList<RealCustomer>) query.list();
            return realCustomers;
        }
        finally {
            session.close();
        }
    }

    public static List<LegalCustomer> listLegalCustomer(Long customerID, String companyName, String economicCode) {
        configureDB();
        Session session = sessionFactory.openSession();
        List<LegalCustomer> legalCustomers;

        String hql = "FROM LegalCustomer WHERE ";

        int flag = 0;
        if (!customerID.equals(new Long(0))) {
            hql += "customerID = :customerid,";
            flag = 1;
        }
        if (!companyName.isEmpty()) {
            flag = 1;
            hql += "companyName = :companyname,";
        }
        if (!economicCode.isEmpty()) {
            flag = 1;
            hql += "economicCode = :economiccode,";
        }

        if (hql.endsWith(",")) {
            flag = 1;
            hql = hql.substring(0, hql.length() - 1);
        }
        if (flag == 0) {
            hql = "FROM LegalCustomer ";
        }
        Query query = session.createQuery(hql);
        if (hql.contains("customerID")) {
            query.setParameter("customerid", customerID);
        }
        if (hql.contains("companyName")) {
            query.setParameter("companyname", companyName);
        }
        if (hql.contains("economicCode")) {
            query.setParameter("economiccode", economicCode);
        }
        try {
            legalCustomers = (ArrayList<LegalCustomer>) query.list();
            return legalCustomers;

        }finally {
            session.close();
        }
    }

    public static List<LegalCustomer> loadLegalCustomer(Long customerID, String companyName, String economicCode) {
        configureDB();
        Session session = sessionFactory.openSession();
        List<LegalCustomer> legalCustomers;
        Criteria selectCriteria = session.createCriteria(LegalCustomer.class);
        if (!customerID.equals(new Long(0))) {
            selectCriteria.add(Restrictions.eq("customerID", customerID));
        }
        if (!companyName.isEmpty()) {
            selectCriteria.add(Restrictions.eq("companyName", companyName));
        }
        if (!economicCode.isEmpty()) {
            selectCriteria.add(Restrictions.eq("economicCode", economicCode));
        }
        try {
            legalCustomers = selectCriteria.list();
            session.close();
            return legalCustomers;
        }
        finally {
            sessionFactory.close();
        }

    }

    public static void updateRealCustomer(Long customerID, String firstName, String lastName, String fatherName, String nationalCode, String birthDate) {
        configureDB();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            RealCustomer realCustomer = (RealCustomer) session.get(RealCustomer.class, customerID);
            realCustomer.setFirstName(firstName);
            realCustomer.setLastName(lastName);
            realCustomer.setFatherName(fatherName);
            realCustomer.setBirthDate(birthDate);
            realCustomer.setNationalCode(nationalCode);
            session.update(realCustomer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public static void updateLegalCustomer(Long customerID, String companyName, String economicCode, String submitDate) {
        configureDB();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            LegalCustomer legalCustomer = (LegalCustomer) session.get(LegalCustomer.class, customerID);
            legalCustomer.setCompanyName(companyName);
            legalCustomer.setEconomicCode(economicCode);
            legalCustomer.setSubmissionDate(submitDate);
            session.update(legalCustomer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public static void deleteRealCustomer(Long customerID) {
        Configuration configuration = new Configuration();
        configuration.configure();
        System.out.print("Delete Customer" + customerID);
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            RealCustomer realCustomer = (RealCustomer) session.get(RealCustomer.class, customerID);
            session.delete(realCustomer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public static void deleteLegalCustomer(Long customerID) {
        configureDB();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            LegalCustomer legalCustomer = (LegalCustomer) session.get(LegalCustomer.class, customerID);
            session.delete(legalCustomer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
