package my.task.com.dao;

import my.task.com.model.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;


//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    public List<User> getUserByLogin(String login) {
//        Session session = sessionFactory.getCurrentSession();
        Query query = getSession().createQuery("from User where login=:login");
        query.setParameter("login", login);
        List list = query.getResultList();
        return list;
    }

    public void addUser(User user) {
//        Session session = sessionFactory.getCurrentSession();
        long save = (Long) getSession().save(user);
        System.out.println(save);
    }

    public void edit(String login,String lastName) {

//        Session session = sessionFactory.getCurrentSession();
        Query query = getSession().createQuery("update User set lastName = :lastName where login = :login");

        query.setParameter("lastName", lastName);
        query.setParameter("login", login);

        int result = query.executeUpdate();
        System.out.println(result);
    }

    public boolean exist(String login) {
        Session session;

        session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.setProjection(Projections.rowCount());
        long count = (Long) criteria.uniqueResult();
        session.getTransaction().commit();
        if (count != 0) {
            return true;
        } else {
            return false;
        }


    }
}



