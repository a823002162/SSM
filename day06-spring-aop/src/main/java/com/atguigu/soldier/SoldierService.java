package com.atguigu.soldier;

public interface SoldierService {

    int saveSoldier(String soldierName);

    int removeSoldier(Integer soldierId);

    int updateSoldier(Integer soldierId, String soldierName);

    String getSoldierNameById(Integer soldierId);

}
