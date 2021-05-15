package com.king.shiro.service;

import com.king.shiro.pojo.User;

public interface UserService {

    User findUserByUserName(String username);
}
