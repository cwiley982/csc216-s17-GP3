/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Creates the checked exception ConflictException that will be thrown if the times for two
 * activities overlap
 * 
 * @author Caitlyn Wiley
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	public ConflictException(String message) {
		super(message);
	}
	
	public ConflictException() {
		super("Schedule conflict.");
	}
}
