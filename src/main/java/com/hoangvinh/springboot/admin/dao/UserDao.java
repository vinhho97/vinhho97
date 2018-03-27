package com.hoangvinh.springboot.admin.dao;

import com.hoangvinh.springboot.admin.entity.UserLocal;

public interface UserDao {
	UserLocal getUserByUsername(String username);
}
