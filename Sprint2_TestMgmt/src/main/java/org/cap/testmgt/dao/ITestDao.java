package org.cap.testmgt.dao;

import java.math.BigInteger;

import org.cap.testmgt.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITestDao extends JpaRepository<Test,BigInteger>{

}
