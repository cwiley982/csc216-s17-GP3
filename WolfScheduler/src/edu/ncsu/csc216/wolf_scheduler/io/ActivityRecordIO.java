/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Creates an ActivityRecordIO object to manage the record keeping of activities
 * 
 * @author Caitlyn Wiley
 *
 */
public class ActivityRecordIO {

	/**
	 * Constructs the object ActivityRecordIO
	 */
	public ActivityRecordIO() {
		//basic constructor of activity record io
	}

	/**
	 * Writes Courses to course record file specified
	 * 
	 * @param fileName name of file to write course records to
	 * @param activities arraylist of list of activities
	 * @throws IOException if file cannot be written to
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> activities) 
			throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < activities.size(); i++) {
			fileWriter.println(activities.get(i).toString());
		}
		fileWriter.close();
	}

}
