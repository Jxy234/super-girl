package com.cdvtc.news.service;

import com.cdvtc.news.model.User;

public interface UserService {

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
}
