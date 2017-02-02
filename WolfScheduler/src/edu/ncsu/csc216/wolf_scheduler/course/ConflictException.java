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
	
	/**
	 * Constructs the checked exception ConflictException with the given error message by calling
	 * the super class
	 * @param message the message to be displayed when this error is thrown
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructs the checked exception ConflictException with the default error message by calling
	 * the previous parameterized constructor
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
