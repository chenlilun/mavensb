package cn.hengi;

import cn.hengyi.dao.CustomerDao;
import cn.hengyi.dao.LinkManDao;
import cn.hengyi.domain.Customer;
import cn.hengyi.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class T {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao ;
    @Test
    public void find(){
        Customer c = customerDao.findOne(3l);
        System.out.println(c);
    }

    @Test
    public void save(){
       Customer customer = new Customer();
       customer.setCustId(7l);
       customer.setCustAddress("china");
       customer.setCustName("张三");
       customer.setCustPhone("15988830453");
       customerDao.save(customer) ;
    }
    @Test
    public void jpql(){
        Customer c = customerDao.findUser("小红");
        System.out.println(c);
    }
    @Test
    @Transactional
    @Rollback(value = false) // 设置不 rollback
    public void jpqlMOdify(){
        customerDao.modifyUser("55",4l,"f");
    }

    @Test
    public void findOne(){
/*        Customer one = customerDao.findOne((root, cq, cb) -> {
            Path<Object> custName = root.get("custName");
            Path<Object> custId = root.get("custId");

            Predicate p = cb.equal(custName, "小红");
            Predicate p2 = cb.equal(custId, 3l);
            Predicate and = cb.and(p, p2);
            return and;
        });*/
        customerDao.findAll((root, cq, cb) -> {
            Path<String> p = root.get("custIndustry");
            Predicate like = cb.like(p, "j%");
            return like;
        }).forEach(System.out::print);
        customerDao.findAll((root, cq, cb) -> {
            Path<String> p = root.get("custIndustry");
            Predicate like = cb.like(p, "j%");
            return like;
        }  ,new Sort(Sort.Direction.DESC,"custId")).forEach(System.out::print); ;
    }



    @Test
    @Transactional
    @Rollback(value = false) // 设置不 rollback
    public void add(){
        LinkMan linkMan = new LinkMan() ;
        linkMan.setLkmName("联系人");
        Customer customer = new Customer() ;
        customer.setCustName("客户公司");
//        customer.getLinkMans().add(linkMan) ;
        linkMan.setCustomer(customer); //1 的一方放弃外键维护权利  解决多一条 update 语句
        linkManDao.save(linkMan);
        customerDao.save(customer) ;  //
    }
    @Test
    @Transactional
    @Rollback(value = false) // 设置不 rollback
    public void addCasecade(){  //级联添加
        Customer customer = new Customer();
        customer.setCustName("百度1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李1");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
    }
}
