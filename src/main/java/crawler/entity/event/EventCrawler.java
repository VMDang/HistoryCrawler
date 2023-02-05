package crawler.entity.event;

import crawler.BaseWebCrawler;
import history.entity.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class EventCrawler extends BaseWebCrawler {
	public EventCrawler(String url) {
		super(url);
	}

	public EventCrawler() {
	}

	protected List<Event> eventList = new ArrayList<>();

	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	@Override
	public boolean connect(String url) {
		Document document = null;
		try {
			document = Jsoup
					.connect(url)
					.userAgent("Jsoup client")
					.timeout(20000).get();
			setDoc(document);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public abstract void getData() throws IOException;

	public abstract void start() throws IOException;
}