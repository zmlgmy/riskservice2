package com.chanpay.service.api.service;

import com.chanpay.service.api.entity.TestBean;

import java.util.List;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-14 15:46
 * @Description:
 */

public interface TestService {
    Iterable<TestBean> findAll();

    void save(List<TestBean> list);

    void save(TestBean bean);

    List<TestBean> findByName(String text);

    List<TestBean> findByNameOrDesc(String name);
}


