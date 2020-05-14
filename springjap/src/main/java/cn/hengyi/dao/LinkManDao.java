package cn.hengyi.dao;

import cn.hengyi.domain.Customer;
import cn.hengyi.domain.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/*
   * JpaRepository 封装了 crud
   * JpaSpecificationExecutor 封装 复杂查询
   *
* */
public interface LinkManDao extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {

}
