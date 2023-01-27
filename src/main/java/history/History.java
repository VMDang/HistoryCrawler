package history;

public abstract class History {
    private String name ;
    private String time ;
    private String description;

    public History() {
    	super();
    }
    public History(String name, String time, String description) {
		super();
		this.name = name;
		this.time = time;
		this.description = description;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
