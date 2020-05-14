package cn.hengyi.dao;

import cn.hengyi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/*
   * JpaRepository 封装了 crud
   * JpaSpecificationExecutor 封装 复杂查询
   *
* */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    @Query(value = "from Customer where custName = ?1 ")
    public Customer findUser(String custmerName);
    @Query(value = "update Customer set custAddress =?1 where custId = ?2 and  custLevel = ?3")
    @Modifying
    public void   modifyUser(String custAddress ,long custId ,String custLevel);
}
