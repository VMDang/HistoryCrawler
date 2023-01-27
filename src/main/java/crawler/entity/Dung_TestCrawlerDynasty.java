package crawler.entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.*;
import history.entity.Dynasty;


public class Dung_TestCrawlerDynasty {

	public static void main(String[] args) throws IOException {
		ArrayList<Dynasty> theNameList = new ArrayList<Dynasty>();
		ArrayList<Dynasty> theDesList = new ArrayList<Dynasty>();
		File theFile = new File("dynasties_NguoiKeSu.json");
		File theFile2 = new File("dynasties_Description_NguoiKeSu.json");
		Document doc = Jsoup.connect("https://nguoikesu.com/dong-lich-su").get();
		//ArrayList<String> Name = new ArrayList<String>();
		Elements names = doc.select("h3[class = item-title]	");
		for (Element name : names) {
			//Name.add(link.text());
			Dynasty dk1 = new Dynasty();
			dk1.setName(name.text());
			theNameList.add(dk1);
		}
		
		Elements scripts = doc.select("ul[class = issues] li div[class = inner] div");
		for (Element script : scripts) {
			Dynasty dk1 = new Dynasty();
			dk1.setDescription(script.text());
			theDesList.add(dk1);
		}
		
		try {
			FileWriter fileWriter = new FileWriter(theFile);
			Gson pretty_gs = new GsonBuilder().setPrettyPrinting().create();
			pretty_gs.toJson(theNameList, fileWriter);
			fileWriter.close();
			
		} catch (IOException e){
			System.err.println("Error in writing a file.");
		}
		
		try {
			FileWriter fileWriter = new FileWriter(theFile2);
			Gson pretty_gs = new GsonBuilder().setPrettyPrinting().create();
			pretty_gs.toJson(theDesList, fileWriter);
			fileWriter.close();
			
		} catch (IOException e){
			System.err.println("Error in writing a file.");
		}

	}

}
