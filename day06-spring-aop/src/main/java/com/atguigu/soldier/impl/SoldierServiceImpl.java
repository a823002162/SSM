package com.atguigu.soldier.impl;

import com.atguigu.soldier.SoldierService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 10:32 PM
 */
@Slf4j
public class SoldierServiceImpl implements SoldierService {

    @Override
    public int saveSoldier(String soldierName) {

        log.debug("核心业务逻辑：保存到数据库……");

        return 1;
    }

    @Override
    public int removeSoldier(Integer soldierId) {

        log.debug("核心业务逻辑：从数据库删除……");

        return 1;
    }

    @Override
    public int updateSoldier(Integer soldierId, String soldierName) {

        log.debug("核心业务逻辑：更新……");

        return 1;
    }

    @Override
    public String getSoldierNameById(Integer soldierId) {

        log.debug("核心业务逻辑：查询数据库……");

        return "good";
    }
}
