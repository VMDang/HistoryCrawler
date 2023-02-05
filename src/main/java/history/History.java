package history;

import java.io.IOException;
import java.util.List;

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
        if(name!=null) this.name = name;
        else this.name = "Không rõ";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
    	if(time!=null) this.time = time;
        else this.time = "Không rõ";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
    	if(description!=null) this.description = description;
        else this.description = "";
    }

    public abstract List loadDataJson() throws IOException;
    public boolean filterProperty(String filter, String type) {
        if (filter == null || filter.isEmpty()) {
            return true;
        } else {
    		if (type == "name") {
    			if (this.getName().toLowerCase().indexOf(filter.toLowerCase()) != -1) {
    				return true;
    			}
    		}
        return false;
        }
	}
	public abstract String hienthi();
}
