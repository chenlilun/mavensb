package com.luttece.luttece.service;

import com.luttece.luttece.po.User;

public interface UserService {
    public String getString(String key);
    public User selectById(String key) ;
}
