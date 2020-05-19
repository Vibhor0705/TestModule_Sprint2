package org.cap.testmgt.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.cap.testmgt.dao.ITestDao;
import org.cap.testmgt.entities.Test;
import org.cap.testmgt.entities.User;
import org.cap.testmgt.exceptions.TestNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestServiceImpl implements ITestService {
	private ITestDao testDao;

	public ITestDao getTestDao() {
		return testDao;
	}

	@Autowired
	public void setTestDao(ITestDao testDao) {
		this.testDao = testDao;
	}
	private IUserService userService;
	

	public IUserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public Test addTest(Test test) {
		test = testDao.save(test);
		return test;
	}

	@Override
	public Test updateTest(Test test) {
		boolean exists = testDao.existsById(test.getTestId());
		if (exists) {
			test = testDao.save(test);
			return test;
		}
		throw new TestNotFoundException("Test not found for id="+test.getTestId());
	}

	@Override
	public Test deleteTest(BigInteger testId) {
		Test test = findById(testId);
		testDao.delete(test);
		return test;
	}

	@Override
	public boolean assignTest(Long userId, BigInteger testId) {
		boolean testExists = testDao.existsById(testId);
		if (!testExists) {
			return false;
		}
		Test test = findById(testId);
		User user = userService.findById(userId);
		user.setUserTest(test);
		user = userService.addUser(user);
		return true;
	}

	@Override
	public Test findById(BigInteger testId) {
		Optional<Test> optional = testDao.findById(testId);
		if (optional.isPresent()) {
			Test test = optional.get();
			return test;
		}
		throw new TestNotFoundException("Test not found for id=" + testId);
	}

	@Override
	public List<Test> fetchAll() {
		List<Test> tests = testDao.findAll();
		return tests;
	}
}
