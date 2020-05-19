package org.cap.testmgt.exceptions;

public class TestNotFoundException extends RuntimeException {
	public TestNotFoundException(String msg) {
		super(msg);
	}
}
