package com.hengyi.test;

import com.hengyi.domain.Customer;
import com.hengyi.domain.utils.JpaUtils;
import org.hibernate.persister.spi.PersisterFactory;
import org.junit.Test;

import javax.persistence.*;

public class MyJPATest {
    @Test
    public void  t1(){
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //获取实体管理器
        EntityManager em = JpaUtils.getFactory();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer  = new Customer() ;
        customer.setCustName("LiSi");
        customer.setCustAddress("CHINA");
        em.persist(customer);
        tx.commit();
        em.close();
//        factory.close();

    }
    @Test
   public void  find(){
        EntityManager em = JpaUtils.getFactory();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        long a = 2 ;
//        Customer customer = em.find(Customer.class, a);
        Customer customer = em.getReference(Customer.class, a);
        System.out.println(customer); // 异步查询 用的时候才用  懒加载 动态代理对象

        tx.commit();
        em.close();
    }
    @Test
    public void  remove(){
        EntityManager em = JpaUtils.getFactory();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        long a = 3 ;
//        Customer customer = em.find(Customer.class, a);
        Customer customer = em.find(Customer.class, a);
//        em.remove(customer);
//         em.getReference() // 异步查询 用的时候才用  懒加载 动态代理对象
        //更新
        customer.setCustName("小红");
        em.merge(customer);

        tx.commit();
        em.close();
    }
}
