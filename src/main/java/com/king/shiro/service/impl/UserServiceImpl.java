package com.king.shiro.service.impl;

import com.king.shiro.pojo.User;
import com.king.shiro.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findUserByUserName(String username) {



        if ("zhangsan".equals(username)) {
            return new User(1L, "zhangsan", (new Md5Hash("123456","admin",3)).toString());
        }
        return null;
    }



}
