package com.cdvtc.news.dao;

import com.cdvtc.news.model.Admin;

public interface AdminDao {
    /**
     * 根据编号获取管理员（编辑）
     * @param adminId
     * @return
     */
    Admin getAdminById(int adminId);
}
