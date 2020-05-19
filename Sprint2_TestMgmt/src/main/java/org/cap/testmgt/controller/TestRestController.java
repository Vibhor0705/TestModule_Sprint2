package org.cap.testmgt.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;


import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.cap.testmgt.dto.TestDetails;
import org.cap.testmgt.dto.TestDto;
import org.cap.testmgt.dto.TestQuestionDto;
import org.cap.testmgt.dto.UserDto;
import org.cap.testmgt.entities.Test;
import org.cap.testmgt.entities.User;
import org.cap.testmgt.exceptions.TestNotFoundException;
import org.cap.testmgt.service.ITestService;
import org.cap.testmgt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestRestController {

	private static final Logger Log = LoggerFactory.getLogger(TestRestController.class);
	@Autowired
	private ITestService service;
	@Autowired
	private IUserService userService;

	@GetMapping("/get/{id}")
	public ResponseEntity<TestDetails> getTest(@PathVariable("id") BigInteger testId) {
		Test test = service.findById(testId);
		// List<TestQuestionDto> questions =
		// fallbackFetchQuestionsByTestId(test.getTestId());
		TestDetails details = convertToTestDetails(test);
		ResponseEntity<TestDetails> response = new ResponseEntity<>(details, HttpStatus.OK);
		return response;

	}

	TestDetails convertToTestDetails(Test test) {
		TestDetails details = new TestDetails();
		List<TestQuestionDto> questions = fallbackFetchQuestionsByTestId(test.getTestId());
		details.setQuestions(questions);
		details.setTestId(test.getTestId());
		details.setTestTitle(test.getTestTitle());
		details.setTestTotalMarks(test.getTestTotalMarks());
		return details;
	}

	 @GetMapping
	   public ResponseEntity<List<Test>>fetchAll(){
	       List<Test>tests=service.fetchAll();
	       ResponseEntity<List<Test>>response=new ResponseEntity<>(tests,HttpStatus.OK);
	       return response;
	   }
	 

	public List<TestQuestionDto> fallbackFetchQuestionsByTestId(BigInteger testId) {
		TestQuestionDto question = new TestQuestionDto();
		question.setQuestionId(BigInteger.valueOf(1));
		question.setQuestionTitle("java");
		List<TestQuestionDto> list = new ArrayList<>();
		list.add(question);
		return list;

	}

	@PostMapping("/add")
	public ResponseEntity<Test> createTest(@RequestBody @Valid TestDto testDto) {
		Test test = convertFromDto(testDto);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
		LocalDateTime startTime =  LocalDateTime.parse(testDto.getStartTime(), formatter);
		LocalDateTime endTime =  LocalDateTime.parse(testDto.getEndTime(), formatter);
		test.setStartTime(startTime);
		test.setEndTime(endTime);
		test = service.addTest(test);
		ResponseEntity<Test> response = new ResponseEntity<>(test, HttpStatus.OK);
		return response;
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<Test> findTest(@PathVariable("id") BigInteger testId) {
		Test test = service.findById(testId);
		ResponseEntity<Test> response = new ResponseEntity<>(test, HttpStatus.OK);
		return response;

	}


	@GetMapping("/remove/{id}")
	public ResponseEntity<Test> deleteTest(@PathVariable("id") BigInteger testId) {
		Test result = service.deleteTest(testId);
		ResponseEntity<Test> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@PutMapping("/update")
	public ResponseEntity<Test> updateTest(	@RequestBody @Valid TestDto testDto) {
		Test test = convertFromDto(testDto);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
		LocalDateTime startTime =  LocalDateTime.parse(testDto.getStartTime(), formatter);
		LocalDateTime endTime =  LocalDateTime.parse(testDto.getEndTime(), formatter);
		test.setStartTime(startTime);
		test.setEndTime(endTime);
		test = service.updateTest(test);
		ResponseEntity<Test> response = new ResponseEntity<>(test, HttpStatus.OK);
		return response;
	}
	@PostMapping("/addUser")
	public ResponseEntity<User> createAddress(@RequestBody @Valid UserDto userDto) {
		User  user = convertFromUserDto(userDto);
		user = userService.addUser(user);
		ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
		return response;
	}

	@GetMapping("/assign/{testId}/{userId}")
	public ResponseEntity<Boolean> assignTest(@PathVariable("testId") BigInteger testId,
			@PathVariable("userId") Long userId) {
		Boolean isAssign = service.assignTest(userId, testId);
		ResponseEntity<Boolean> response = new ResponseEntity<>(isAssign, HttpStatus.OK);
		return response;
	}

	public Test convertFromDto(TestDto dto) {
		Test test = new Test();
		test.setTestId(dto.getTestId());
		//test.setEndTime(dto.getEndTime());
		//test.setStartTime(dto.getStartTime());
		test.setTestDuration(dto.getTestDuration());
		test.setTestMarksScored(dto.getTestMarksScored());
		test.setTestTitle(dto.getTestTitle());
		test.setTestTotalMarks(dto.getTestTotalMarks());
		return test;
	}
	public User convertFromUserDto(UserDto dto) {
		User user = new User();
		user.setUserId(dto.getUserId());
		user.setUserName(dto.getUserName());
		user.setIsAdmin(dto.getIsAdmin());
		user.setUserPassword(dto.getUserPassword());
		return user;
	}

	@ExceptionHandler(TestNotFoundException.class)
	public ResponseEntity<String> handleTestNotFound(TestNotFoundException ex) {
		Log.error("test not found exception", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
		Log.error("constraint violation", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		return response;
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleAll(Throwable ex) {
		Log.error("exception caught", ex);
		String msg = ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}

}
