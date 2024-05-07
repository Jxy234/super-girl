package com.cdvtc.news.dao;

import com.cdvtc.news.model.User;

public interface UserDao {
    /**
     * 用户登陆
     * @param account (账号/手机号/电子邮件)
     * @param password
     * @return
     */
    User login(String account, String password);


    /**
     * 判断用户是否已经存在
     * @param key （只能是：account:账户 nickname:昵称  mobile:手机号码 email:电子邮件）
     * @param value
     * @return
     */
    boolean isUserExisted(String key, String value);


    /**
     * 增加用户
     * @param user
     * @return boolean 是否创建成功
     */
    boolean addUser(User user);

    /**
     * 更新用户信息（不包括密码）
     * @param user
     */
    void updateUser(User user);
}
