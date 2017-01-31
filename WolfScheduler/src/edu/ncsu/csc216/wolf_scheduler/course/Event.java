/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Creates an event so Students can add things like lunch to their schedule
 * 
 * @author Caitlyn
 *
 */
public class Event extends Activity {

	/** Number of weeks the event will repeat */
	public int weeklyRepeat;
	/** Extra information about the event */
	public String eventDetails;
	
	/**
	 * Constructs the Event object using the super class
	 * 
	 * @param title of the event
	 * @param meetingDays of the event
	 * @param startTime of the event
	 * @param endTime of the event
	 * @param weeklyRepeat number of weeks the event will repeat
	 * @param eventDetails extra information about the event
	 */
	public Event (String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}

	/**
	 * Gets the number of times the event will repeat
	 * 
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Sets the number of times the event will repeat
	 * 
	 * @param weeklyRepeat the weeklyRepeat to set
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if (weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * Gets the extra details about the event
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the extra details about the event
	 * 
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;
	}
	
	/**
	 * Sets the days the event will occur on
	 * 
	 * @param meetingDays the days on which the event will occur
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		if (meetingDays.indexOf("A") != -1) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char letter = meetingDays.charAt(i);
			if (letter != 'M' && letter != 'T' && letter != 'W' && letter != 'H' && letter != 'F' && letter != 'S' && letter != 'U') {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}
	
	@Override
	public String getMeetingString() {
		return super.getMeetingString() + " (every " + this.getWeeklyRepeat() + " weeks)";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + weeklyRepeat + "," + eventDetails;
	}

	@Override
	public String[] getShortDisplayArray() {
		String[] shortArray = {"", "", this.getTitle(), this.getMeetingString()};
		return shortArray;
	}

	@Override
	public String[] getLongDisplayArray() {
		String[] longArray = {"", "", this.getTitle(), "", "", this.getMeetingString(), this.getEventDetails()};
		return longArray;
	}

	@Override
	public boolean isDuplicate(Activity activity) {
		Event event = null;
		if (activity instanceof Event) {
			event = (Event) activity;
			if (event.getTitle().equals(this.getTitle())) {
				return true; //if event titles do match, therefore they're duplicates
			}
			return false; //if event titles don't match
		} else {
			return false; //if activity is a course
		}
	}
}
