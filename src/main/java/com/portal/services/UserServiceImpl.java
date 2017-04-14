package com.portal.services;

import com.portal.model.User;
import com.portal.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 private UserDao userDao;

	@Override
	public User findById(Long id){
		return userDao.findOne(id);
	}

	@Override
	public User findByName(String name){
		return userDao.findUserByUsername(name);
	}

	@Override
	public void saveUser(User user){
		userDao.save(user);
	}

	@Override
	public ResponseEntity<User> updateUser(User realUser, User user){
		if (realUser == null) {
			System.out.println("User with id " + realUser.getId() + " not found");
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		realUser.setUsername(user.getUsername());
		realUser.setLocation(user.getLocation());
		realUser.setEmail(user.getEmail());
		userDao.save(realUser);

		return ResponseEntity.ok(realUser);
	}

	@Override
	public void deleteUserById(long id){
		User user = userDao.findOne(id);
		if(user != null)
			userDao.delete(user);
	}

	@Override
	public List<User> getAllUsers(){
		List<User> list = new ArrayList<>();
		Iterable<User> users = userDao.findAll();
		for(User user: users){
			list.add(user);
		}
		return list;
	}

	@Override
	public boolean isUserExist(User user){
		return userDao.findOne(user.getId()) != null;
	}
	
}
