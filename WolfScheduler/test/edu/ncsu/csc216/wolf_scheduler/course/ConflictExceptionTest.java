/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Caitlyn Wiley
 *
 */
public class ConflictExceptionTest {

	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}
	
	@Test
	public void testConflictException() {
	    ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
