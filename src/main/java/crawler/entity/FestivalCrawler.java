package crawler.entity;

import crawler.BaseWebCrawler;
import history.entity.Festival;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FestivalCrawler extends BaseWebCrawler {
    public FestivalCrawler(List<String> urls) {
        super(urls);
    }

	@Override
	public boolean connect(String url) {
		// TODO Auto-generated method stub
		return false;
	}
	public static void main(String[] args) throws IOException  {
		 Festival[] festival = new Festival[300];   
		 ArrayList<String> listName = new ArrayList<String>();
		 ArrayList<String> listTime = new ArrayList<String>();
		 ArrayList<String> listAddress = new ArrayList<String>(); 
		    
		     String url = "https://www.couturetravelcompany.com/cac-le-hoi-o-viet-nam/";
	    	   
	    	   Document doc = Jsoup.connect(url).get() ;
	    	   System.out.println(doc);
	    	   
	    	   for(Element row : doc.getElementsByTag("h4")) {
	    		   listName.add(row.text());
	    		   
	    	   }
	    	   listName.remove(listName.size()-1);
	    	   
	    	   
	    	   for(Element row : doc.select("li:matches(Thời gian:)")) {
	    		   listTime.add(row.text());	    		   
	    		  
	    	   }
	    	   
	    	   for(Element row : doc.select("li:matches(Địa điểm:)")) {
	    		   listAddress.add(row.text());
	   	   
	    	   }
	    	   for(int i=0;i<listName.size();i++) {
	    		   festival[i] = new Festival();
	    		   festival[i].setNameFes(listName.get(i));
	    		   festival[i].setTimeFes(listTime.get(i));
	    		   festival[i].setPlaceFes(listAddress.get(i));
	    	   }
	    	   Writer writer = new FileWriter("C:\\Users\\ASUS\\Documents\\ThuongOOP\\HistoryCrawler\\src\\main\\java\\crawler\\json\\festival.json", true);
	    	   for(int i=0;i<listName.size();i++) {
	    		   Gson gson = new GsonBuilder().create();
			       gson.toJson(festival[i], writer);
			       writer.write('\n');
	    	   }
	    	   System.out.println(listName.size());
	    	   System.out.println(festival[listName.size()-1].getPlaceFes());
	    	   
	    	   
	    	   
	 }
}

