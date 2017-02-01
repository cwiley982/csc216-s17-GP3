/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Checks for and handles possible conflicts between activities added to the schedule
 * 
 * @author Caitlyn Wiley
 *
 */
public interface Conflict {

	/**
	 * Checks for a conflict in activity times
	 * @param possibleConflictingActivity the activity that is trying to be added
	 * @throws ConflictException a checked exception thrown if the times for two activities overlap
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
}
