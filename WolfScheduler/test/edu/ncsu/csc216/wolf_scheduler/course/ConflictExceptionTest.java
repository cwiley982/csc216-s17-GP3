/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests ConflictException to make sure the constructors throw the correct messages
 * 
 * @author Caitlyn Wiley
 */
public class ConflictExceptionTest {

	/**
	 * tests the parameterized constructor to make sure it displays the custom exception message
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}
	
	/**
	 * tests the unparameterized constructor to confirm it displays the default message
	 */
	@Test
	public void testConflictException() {
	    ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
