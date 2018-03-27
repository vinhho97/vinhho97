package com.hoangvinh.springboot.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hoangvinh.springboot.admin.dao.UserDao;
import com.hoangvinh.springboot.admin.entity.UserLocal;

@Service
@Transactional
public class UserLoginService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserLocal userLocal = userDao.getUserByUsername(username);
		if (userLocal == null) {
			throw new UsernameNotFoundException("not found user");
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userLocal.getRole()));
		
		UserDetails userDetails = new User(userLocal.getUsername(), userLocal.getPassword(), true, true, true, true, authorities);
		return userDetails;
	}

}
