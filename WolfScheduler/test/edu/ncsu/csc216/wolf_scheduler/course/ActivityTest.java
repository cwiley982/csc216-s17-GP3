/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests checkConflict method in Activity class
 * 
 * @author Caitlyn Wiley
 */
public class ActivityTest {

	/**
	 * tests non-conflicting activities
	 * tests an activity starting as another activity ends (end at noon and start at noon)
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		//tests non-conflicting activities 
		try {
			a1.checkConflict(a2);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		
		//a2 starts as a1 ends
		a1.setMeetingDays("TH");
		a1.setActivityTime(1445, 1530);
		try {
		    a1.checkConflict(a2);
		    fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
		    //Check that the internal state didn't change during method call.
		    assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
	}
	
	/**
	 * tests one activity starting during another activity
	 */
	@Test
	public void testCheckConflict2() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1345, 1600);
		//a2 starts during a1
		try {
			a1.checkConflict(a2);
			fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			//Check that the internal state didn't change during method call.
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("MW 1:45PM-4:00PM", a2.getMeetingString());
		}
	}
	
	/**
	 * tests one activity ending during another activity
	 */
	@Test
	public void testCheckConflict3() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MH", 1200, 1400);
		//a2 ends during a1
		try {
			a1.checkConflict(a2);
			fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
			//Check that the internal state didn't change during method call.
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("MH 12:00PM-2:00PM", a2.getMeetingString());
		}
	}
}
