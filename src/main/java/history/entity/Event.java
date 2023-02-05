package history.entity;

import history.History;

import java.util.List;

public class Event extends History {
	public Event() {
		
	}
	public Event(String name, String time, String description) {
		super(name, time, description);
	}

	@Override
	public List<Event> loadDataJson() {
		return null;
	}
	
}
