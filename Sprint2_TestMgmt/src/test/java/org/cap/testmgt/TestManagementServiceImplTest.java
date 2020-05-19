package org.cap.testmgt;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.cap.testmgt.exceptions.TestNotFoundException;
import org.cap.testmgt.service.TestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import({TestServiceImpl.class})
public class TestManagementServiceImplTest {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private TestServiceImpl service;

	@Test
	public void testAddTest_1() {
		BigInteger testId;
		testId = BigInteger.valueOf(10);
		String testTitle = "java";
		org.cap.testmgt.entities.Test test = new org.cap.testmgt.entities.Test();
		test.setTestTitle(testTitle);
		test.setTestId(testId);
		org.cap.testmgt.entities.Test result = service.addTest(test);
		List<org.cap.testmgt.entities.Test> fetched = entityManager.createQuery("from Test").getResultList();
		Assertions.assertEquals(1, fetched.size());// verifying one got inserted
		org.cap.testmgt.entities.Test expected = fetched.get(0);
		Assertions.assertEquals(expected, result);// verifying fetch and stored are equal
		Assertions.assertEquals(testTitle, expected.getTestTitle());
		Assertions.assertEquals(testId, expected.getTestId());

	}

	@Test
	public void testDeleteTest_1() {
		BigInteger testId;
		testId = BigInteger.valueOf(10);
		String testTitle = "java";
		org.cap.testmgt.entities.Test test = new org.cap.testmgt.entities.Test();
		test.setTestTitle(testTitle);
		test.setTestId(testId);
		test = service.addTest(test);
		org.cap.testmgt.entities.Test result = service.deleteTest(testId);

		Assertions.assertEquals(result.getTestId(), test.getTestId());// verifying fetch and stored are equal
		Assertions.assertEquals(result.getTestTitle(), test.getTestTitle());

	}

	  @Test
	    public void testDeleteTest_2() {
	        //Executable class is in junit, don't choose the other one
	        Executable executable = () -> service.deleteTest(BigInteger.valueOf(25));
	        /**
	         equivalent to above code
	         Executable executable2=new Executable() {
	        @Override public void execute() throws Throwable {
	        roomService.findRoomById(7634);
	        }
	        };
	         **/

	        Assertions.assertThrows(TestNotFoundException.class, executable);

	    }
	@Test
	public void testFindById_1() {
		// Executable class is in junit, don't choose the other one
		BigInteger id;
		id = BigInteger.valueOf(16);
		Executable executable = () -> service.findById(id);
		/**
		 * equivalent to above code Executable executable2=new Executable() {
		 * 
		 * @Override public void execute() throws Throwable {
		 *           roomService.findRoomById(7634); } };
		 **/

		Assertions.assertThrows(TestNotFoundException.class, executable);

	}

	@Test
	public void testFindByTestId_1() {
		// Executable class is in junit, don't choose the other one
		BigInteger testId;
		testId = BigInteger.valueOf(100);
		String testTitle = "java";
		org.cap.testmgt.entities.Test test = new org.cap.testmgt.entities.Test();
		test.setTestTitle(testTitle);
		test.setTestId(testId);
		test = service.addTest(test);

		org.cap.testmgt.entities.Test result = service.findById(testId);
		/**
		 * equivalent to above code Executable executable2=new Executable() {
		 * 
		 * @Override public void execute() throws Throwable {
		 *           roomService.findRoomById(7634); } };
		 **/

		Assertions.assertEquals(test, result);
		Assertions.assertEquals(testId, result.getTestId());
	}

}
