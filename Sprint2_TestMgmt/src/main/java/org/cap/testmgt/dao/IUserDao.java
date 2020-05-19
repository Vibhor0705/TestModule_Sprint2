package org.cap.testmgt.dao;

import org.cap.testmgt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Long> {

}
