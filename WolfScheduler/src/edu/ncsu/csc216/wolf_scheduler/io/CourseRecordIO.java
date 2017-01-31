/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Caitlyn Wiley
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses.
	 * Any invalid Courses are ignored. If the file to read cannot be found or
	 * the permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName
	 *            file to read course records from
	 * @return a list of valid courses
	 * @throws FileNotFoundException
	 *             if file can't be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) 
			throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(fileName));
		ArrayList<Course> courses = new ArrayList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && 
							course.getSection().equals(c.getSection())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return courses;
	}

	private static Course readCourse(String nextLine) {
		Scanner lineScan = new Scanner(nextLine);
		lineScan.useDelimiter(",");
		Course course = null;
		try {
			String name = lineScan.next();
			String title = lineScan.next();
			String section = lineScan.next();
			int credits = lineScan.nextInt();
			String instructorId = lineScan.next();
			String meetingDays = lineScan.next();
			int startTime = 0;
			int endTime = 0;
			if (meetingDays.equals("A")) {
				try {
					startTime = lineScan.nextInt();
					endTime = lineScan.nextInt();
					course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
				} catch (NoSuchElementException e) {
					course = new Course(name, title, section, credits, instructorId, meetingDays);
				}
			} else {
				startTime = lineScan.nextInt();
				endTime = lineScan.nextInt();
				course = new Course(name, title, section, credits, instructorId, meetingDays, 
						startTime, endTime);
			}
			lineScan.close();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		return course;
	}

}
