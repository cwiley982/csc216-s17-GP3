/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Creates a schedule that courses can be added to or removed from
 * 
 * @author Caitlyn
 *
 */
public class WolfScheduler {

	/** Array list of courses available to add to a schedule */
	public ArrayList<Course> courseCatalog;
	/** Array list of courses currently on the schedule */
	public ArrayList<Activity> schedule;
	/** Title of the schedule */
	public String scheduleTitle;
	
	/**
	 * Constructs a schedule
	 * 
	 * @param fileName name of file to be used
	 */
	public WolfScheduler(String fileName) {
		courseCatalog = new ArrayList<Course>();
		schedule = new ArrayList<Activity>();
		scheduleTitle = "My Schedule";
		try {
			courseCatalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Generates a 2D array containing the name, section and title for each course in courseCatalog
	 * 
	 * @return the 2D array with the info for the courses in courseCatalog
	 */
	public String[][] getCourseCatalog() {
		if (courseCatalog == null) {
			return new String[1][1];
		}
		String catalog[][] = new String[courseCatalog.size()][4];
		for (int i = 0; i < courseCatalog.size(); i++) {
			catalog[i][0] = courseCatalog.get(i).getName();
			catalog[i][1] = courseCatalog.get(i).getSection();
			catalog[i][2] = courseCatalog.get(i).getTitle();
			catalog[i][3] = courseCatalog.get(i).getMeetingString();
		}
		return catalog;
	}

	/**
	 * Gets the courses currently on the schedule and creates a 2D array containing each courses
	 * name, section, title, credits, instructor's id and meeting days
	 * 
	 * @return the 2D array containing all of the information for each course in schedule
	 */
	public String[][] getFullScheduledActivities() {
		if (schedule == null) {
			return new String[1][1];
		}
		String fullSchedule[][] = new String[schedule.size()][6];
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i) instanceof Course) {
				fullSchedule[i] = schedule.get(i).getLongDisplayArray();
			} else if (schedule.get(i) instanceof Event) {
				fullSchedule[i] = schedule.get(i).getLongDisplayArray();
			}
			
			/*fullSchedule[i][0] = schedule.get(i).getName();
			fullSchedule[i][1] = schedule.get(i).getSection();
			fullSchedule[i][2] = schedule.get(i).getTitle();
			fullSchedule[i][3] = Integer.toString(schedule.get(i).getCredits());
			fullSchedule[i][4] = schedule.get(i).getInstructorId();
			fullSchedule[i][5] = schedule.get(i).getMeetingString();*/
		}
		return fullSchedule;
	}

	/**
	 * Gets the courses currently on the schedule and creates a 2D array containing each courses
	 * name, section and title
	 * 
	 * @return the 2D array containing the schedule info
	 */
	public String[][] getScheduledActivities() {
		if (schedule == null) {
			return new String[1][1];
		}
		String partialSchedule[][] = new String[schedule.size()][3];
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i) instanceof Course) {
				partialSchedule[i] = schedule.get(i).getShortDisplayArray();
			} else if (schedule.get(i) instanceof Event) {
				partialSchedule[i] = schedule.get(i).getShortDisplayArray();
			}
			/*partialSchedule[i][0] = schedule.get(i).getName();
			partialSchedule[i][1] = schedule.get(i).getSection();
			partialSchedule[i][2] = schedule.get(i).getTitle();*/
		}
		return partialSchedule;
	}

	/**
	 * Gets the current title of the schedule
	 * 
	 * @return the title of the schedule
	 */
	public String getTitle() {
		return scheduleTitle;
	}

	/**
	 * Sets the title of the schedule
	 * 
	 * @param title the schedule will be set to
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		scheduleTitle = title;
	}
	
	/**
	 * Writes the schedule to a file
	 * 
	 * @param fileName the file the schedule will be written to
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

	/**
	 * Tells whether a course can be removed from the schedule or not based on whether it's already
	 * in the schedule
	 * @param idx index of the activity to be removed
	 * 
	 * @return true if activity can be removed, false if not
	 */
	public boolean removeActivity(int idx) {
		if (idx >= 0 && idx < schedule.size()) {
			schedule.remove(idx);
			return true;
		}
		return false;
	}

	/**
	 * Tells whether a course can be added to the schedule or not based on whether it's already
	 * in the schedule and if it's in the catalog
	 * 
	 * @param name of course
	 * @param section of course
	 * @return true if course can be added
	 */
	public boolean addCourse(String name, String section) {
		//if course isn't a duplicate and exists in the catalog, add it to the schedule
		Course course = null;
		//finds course in catalog, this will be passed into isDuplicate method
		for (int i = 0; i < courseCatalog.size(); i++) {
			if (courseCatalog.get(i).getName().equals(name) && courseCatalog.get(i).getSection().equals(section)) {
				course = courseCatalog.get(i);
			}
		}
		//if course doesn't exist so course is still null
		if (course == null) {
			return false;
		}
		//goes through entire schedule to see if student is already enrolled in the course (if it's a duplicate)
		int count = 0;
		for (int i = 0; i < schedule.size(); i++) {
			if (course.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			} else {
				count++;
			}
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		//adds course to schedule
		if (count == schedule.size()) {
			schedule.add(course);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Adds an event to schedule - the ArrayList of activities
	 * 
	 * @param title of the event
	 * @param meetingDays of the event
	 * @param startTime of the event
	 * @param endTime of the event
	 * @param weeklyRepeat number of weeks the event will repeat
	 * @param eventDetails any extra information about the event
	 */
	public void addEvent(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		Event event = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails);
		int count = 0;
		for (int i = 0; i < schedule.size(); i++) {
			if (event.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You have already created an event called " + event.getTitle());
			} else {
				count++;
			}
			try {
				schedule.get(i).checkConflict(event);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		if (count == schedule.size()) {
			schedule.add(event);
		}
	}

	/**
	 * Gets the course with the specified name and section from the courseCatalog
	 * 
	 * @param name of the course
	 * @param section of the course
	 * @return the course from courseCatalog with the same name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < courseCatalog.size(); i++) {
			if (courseCatalog.get(i).getName().equals(name) && courseCatalog.get(i).getSection().equals(section)) {
				return courseCatalog.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Resets the schedule to an empty ArrayList
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();	
	}

}
