package org.cap.testmgt.service;

import java.math.BigInteger;
import java.util.List;

import org.cap.testmgt.entities.Test;

public interface ITestService {

	Test addTest(Test test);

	Test updateTest( Test test);

	Test deleteTest(BigInteger testId);

	boolean assignTest(Long userId, BigInteger testId);

	List<Test> fetchAll();

	Test findById(BigInteger testId);
}
