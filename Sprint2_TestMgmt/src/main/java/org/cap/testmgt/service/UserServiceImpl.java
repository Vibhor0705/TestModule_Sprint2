package org.cap.testmgt.service;

import java.util.Optional;

import org.cap.testmgt.dao.IUserDao;
import org.cap.testmgt.entities.User;
import org.cap.testmgt.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserServiceImpl implements IUserService{
	private IUserDao userDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User addUser(User user) {
		user=userDao.save(user);
		return user;
	}

	@Override
	public User findById(Long userId) {
		Optional<User> optional = userDao.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			return user;
		}
		throw new UserNotFoundException("Test not found for id=" + userId);
	}

}
