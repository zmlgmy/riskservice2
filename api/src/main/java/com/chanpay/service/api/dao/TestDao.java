package com.chanpay.service.api.dao;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-14 15:45
 * @Description:
 */


import com.chanpay.service.api.entity.TestBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestDao extends CrudRepository<TestBean, Long> {
    List<TestBean> findByName(String name);

    List<TestBean> findByNameOrDesc(String text);
}

