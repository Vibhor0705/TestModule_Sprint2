package org.cap.testmgt.service;

import org.cap.testmgt.entities.User;

public interface IUserService {
	User addUser(User user);

	User findById(Long userId);
}
