package com.hengyi.utils;

import com.hengyi.utils.test.Order;
import com.hengyi.utils.test.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestTemple {
    @Test
    public void t1() {
        Session s = HibernateUtil.getSession(); //这里直接调用HibernateUtil工具类中的getSession()方法获得Session
        Transaction tx = s.beginTransaction(); //开启事务
        String q = "from User ";
        Query query = s.createQuery(q);
        List<User> test = query.list();
        test.forEach(user -> {
            System.out.println(user.toString() + "\t");
            ;
        });


    }

    @Test
    public void t2() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Order order = new Order() ;
        order.setGoodsName("啤酒");
        order.setOrderId(1);
        order.setName("张三");
        order.setOrderNum("9021362");
        session.save(order) ;
        tx.commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }

    @Test
    public void t3() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        User user = new User() ;
        user.setSex("男");
        user.setUsername("潇湘");
        session.save(user) ;
        tx.commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
    @Test
    public void t4() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        User user = session.get(User.class, "4028f79771fa4cb20171fa4cb5810000");
        user.setUsername("小红");
        session.update(user);
        System.out.println(user);
        tx.commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
