/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Creates a Course object with fields specifying the name, section, instructor's id, times, etc
 * 
 * @author Caitlyn Wiley
 *
 */
public class Course extends Activity {
	
	/** Number of digits in section number */
	private static final int SECTION_LENGTH = 3;
	/** Max characters in course name */
	private static final int MAX_NAME_LENGTH = 6;
	/** Minimum characters in course name */
	private static final int MIN_NAME_LENGTH = 4;
	/** Max credits for a course */
	private static final int MAX_CREDITS = 5;
	/** Minimum credits for a course */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 * Creates a course with all fields
	 * 
	 * @param name
	 *            - the name of the course
	 * @param title
	 *            - the title of the course
	 * @param section
	 *            - the course section
	 * @param credits
	 *            - credit hours for the course
	 * @param instructorId
	 *            - instructor's unity id
	 * @param meetingDays
	 *            - days the course meets
	 * @param startTime
	 *            - time course starts
	 * @param endTime
	 *            - time course ends
	 */
	public Course(String name, String title, String section, int credits, String instructorId,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Creates a course with the name, title, section, credits, instructor's id,
	 * and meeting days of the course
	 * 
	 * @param name
	 *            - the name of the course
	 * @param title
	 *            - the title of the course
	 * @param section
	 *            - the course section
	 * @param credits
	 *            - credit hours for the course
	 * @param instructorId
	 *            - instructor's unity id
	 * @param meetingDays
	 *            - days the course meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId,
			String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name
	 * 
	 * @param name
	 *            the name to set
	 * @throws IllegalArgumentException
	 *             if name is null, greater than 6 characters or less than 4
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Returns Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets Course's section
	 * 
	 * @param section
	 *            the section to set
	 */
	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException();
		}
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < SECTION_LENGTH; i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException();
			}
		}
		this.section = section;
	}

	/**
	 * Returns Course's credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets Course's credits
	 * 
	 * @param credits
	 *            the credits to set
	 */
	public void setCredits(int credits) {
		if (credits > MAX_CREDITS || credits < MIN_CREDITS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Returns the instructor's id
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Gets the instructor's id
	 * 
	 * @param instructorId
	 *            the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null) {
			throw new IllegalArgumentException();
		}
		if (instructorId.length() < 1) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
	}

	/**
	 * Sets the meeting days for the course
	 * 
	 * @param meetingDays the days the course will be held
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null) {
			throw new IllegalArgumentException();
		}
		if (meetingDays.length() < 1) {
			throw new IllegalArgumentException();
		}
		if (meetingDays.indexOf("A") >= 0 && meetingDays.length() != 1) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char letter = meetingDays.charAt(i);
			if (letter != 'M' && letter != 'T' && letter != 'W' && letter != 'H' && letter != 'F'
					&& letter != 'A') {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + 
					"," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," 
		+ getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	@Override
	public String[] getShortDisplayArray() {
		String[] shortArray = {this.getName(), this.getSection(), this.getTitle(), 
				this.getMeetingString()};
		return shortArray;
	}

	@Override
	public String[] getLongDisplayArray() {
		String[] longArray = {this.getName(), this.getSection(), this.getTitle(), 
				Integer.toString(this.getCredits()), this.getInstructorId(), 
				this.getMeetingString(), ""};
		return longArray;
	}

	@Override
	public boolean isDuplicate(Activity activity) {
		Course course = null;
		if (activity instanceof Course) {
			course = (Course) activity;
			return course.getName().equals(this.getName()); //if names are equal, condition is true so true is returned
		} else { //if activity is an event
			return false;
		}
	}
}
