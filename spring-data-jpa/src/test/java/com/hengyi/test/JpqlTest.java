package com.hengyi.test;

import com.hengyi.domain.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {
    @Test
    public void findAll(){
        EntityManager em = JpaUtils.getFactory();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql = "from com.hengyi.domain.Customer";
        Query query = em.createQuery(jpql);
        List list = query.getResultList();
        list.stream().forEach(System.out::print);


        tx.commit();
        em.close();
    }

    @Test
    public void findAllOrderByCustId(){
        EntityManager em = JpaUtils.getFactory();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql = "from com.hengyi.domain.Customer order by custId desc ";
        Query query = em.createQuery(jpql);
        List list = query.getResultList();
        list.stream().forEach(System.out::print);


        tx.commit();
        em.close();
    }

    @Test
    public void testCount(){
        EntityManager em = JpaUtils.getFactory();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql = "select count (custId) from Customer";
        Query query = em.createQuery(jpql);
        Object singleResult = query.getSingleResult();
        System.out.println("记录："+singleResult);


        tx.commit();
        em.close();
    }
    @Test
    public void testFenye(){
        EntityManager em = JpaUtils.getFactory();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);
        query.setFirstResult(1 );
        query.setMaxResults(2) ;
        query.getResultList().forEach(System.out::print);

        tx.commit();
        em.close();
    }

    @Test
    public void testCondition(){
        EntityManager em = JpaUtils.getFactory();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql = "from Customer where custName like ?";

        Query query = em.createQuery(jpql);
        query.setParameter(1,"小%") ;
        query.setFirstResult(0 );
        query.setMaxResults(2) ;
        query.getResultList().forEach(System.out::print);

        tx.commit();
        em.close();
    }
}
