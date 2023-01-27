package history.entity;

import history.History;

public class Event extends History {
	public Event() {
		
	}
	public Event(String name, String time, String description) {
		super(name, time, description);
	}
}
