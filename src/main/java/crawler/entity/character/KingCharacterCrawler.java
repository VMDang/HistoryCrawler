package crawler.entity.character;

import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import crawler.manager.CrawlerManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.entity.King;



public class KingCharacterCrawler extends CharacterCrawler{
	public KingCharacterCrawler(String urls) {
		super(urls);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {	
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";	
		KingCharacterCrawler kingCrawler = new KingCharacterCrawler(url);
		kingCrawler.connect(url);
		List<String> allUrls = kingCrawler.getAllUrl(url);
		kingCrawler.getData(allUrls);
		
		
	}
	@Override
	public List<String> getAllUrl(String url) {
		// TODO Auto-generated method stub
		Document doc = getDoc();			
		   Elements table = doc.select("table[cellpadding = 0] tbody");
		   List<String> links = new ArrayList<>();
		  
		   //Vua
		   
		   for(Element i : table) {
			  Elements hang = i.select("tr[style *= height:50px;]");
			  for(Element i1 : hang) {
				  Element x = i1.select("td").get(1);
				  Element y = x.getElementsByTag("a").get(0);
				  links.add(y.attr("href"));
				  CrawlerManager.setCountUrlBrowsed();
			  }
		   }
		   return links;
	}

	public KingCharacterCrawler() {
	}

	@Override
	public void getData(List<String> allUrl) {
		// TODO Auto-generated method stub
		//get all urls
  		Document doc = getDoc();			
	   Elements table = doc.select("table[cellpadding = 0] tbody");
	   try (Writer file = new FileWriter("src\\main\\java\\json\\King1.json")){
			  file.write("[\n");

		int count = 0;
	   for(int k = 0 ; k < allUrl.size() ; k++ ) {
		   		String name = null;
		   		String time = null;
		   		String depcription = "";
			   	String mienHieu = null;
				String thuyHieu = null;
				String nienHieu = null;
				String tenHuy = null;
				String triVi = null;
				String tienNhiem = null;
				String keNhiem = null;
				String sinh = null;
				String mat = null;
				String trieuDai = null;
				String anTang = null;
				String thanPhu = null;
				String thanMau = null;
			  String href = allUrl.get(k);
		      String link = ("https://vi.wikipedia.org" + allUrl.get(k));
		      // connect to page a king
			  Connection webConnection = Jsoup.connect(link);  
			  Document data = webConnection.get();
			  Elements info = data.getElementsByClass("infobox").select("[style=width:22em]"); //select table info in page
			  Elements pTags= data.select("div[class = mw-parser-output] > p");
			  if(pTags.size()>0) depcription+= pTags.get(0).text();
			  for(Element i : info) { 
				  King kingDetail = new King();
				  name = i.select("th").get(0).text();
				  kingDetail.setName(name);
				  Elements infomation = i.select("tr");
				  //get name in table web parent 
				  for(Element tb : table) {
					  Elements hang = tb.select("tr[style *= height:50px;]");
					  for(Element i1 : hang) {
						  Elements dataHang = i1.select("td");
						  String hrefParent = i1.select("td").get(1).select("a").attr("href");
						  
						  if(hrefParent.equals(href)) {
							  Elements tenTruongs = tb.select("tr").get(0).select("th");
							  for(int th = 0 ; th<tenTruongs.size();th++) {
								  String key = tenTruongs.get(th).text();
								  String value = dataHang.get(th).text();
								  if(key.charAt(key.length()-1)==']') {
									  key = key.substring(0,key.indexOf("["));
								  }
								  if(value.length()>0)
								  if(value.charAt(value.length()-1)==']') {
									  value = value.substring(0,value.indexOf("["));
								  }
//								  System.out.println(key);
								  if(key.equals("Miếu hiệu")) {	  
									  mienHieu = value;
								  }
								  if(key.equals("Thụy hiệu")||key.equals("Tôn hiệu hoặc Thụy hiệu")) {
									  thuyHieu = value;
								  }
								  if(key.equals("Tên húy")) {
									  tenHuy = value;
								  }
								  if(key.equals("Niên hiệu")) {
									  nienHieu = value;
								  }
								  
							  }
						  }
					  }
				   }
				  //get data in table in page
				  for(Element infoElement : infomation.select("[scope=row]")) {
					  if(infoElement.text().equals("Trị vì")||infoElement.text().equals("Tại vị")||infoElement.text().equals("Lãnh đạo")) {
						  triVi= infoElement.parent().select("td").text();
						  continue;
					  }
					  else if(infoElement.text().equals("Tiền nhiệm")) {
						  tienNhiem = infoElement.parent().select("td").text();
						  continue;
					  }
					  else if(infoElement.text().equals("Kế nhiệm")) {
						  keNhiem = infoElement.parent().select("td").text();
						  continue;
					  }
					  else if(infoElement.text().equals("Sinh")) {
						  sinh = infoElement.parent().select("td").text();
						  continue;
					  }
					  else if(infoElement.text().equals("Mất")) {
						  mat = infoElement.parent().select("td").text();
						  continue;
					  }
					  else if(infoElement.text().equals("An táng")) {
						  anTang =  infoElement.parent().select("td").text();
						  continue;
					  }
					  else if(infoElement.text().equals("Thời kỳ")||infoElement.text().equals("Triều đại")) {
						  trieuDai = infoElement.parent().select("td").text();
						  continue;
					  }
					  else if(infoElement.text().equals("Thân phụ")) {
						  thanPhu = infoElement.parent().select("td").text();
						  continue;
					  }
					  else if(infoElement.text().equals("Thân mẫu")) {
						  thanMau = infoElement.parent().select("td").text();
						  continue;
					  }
				  }
				  kingDetail.setTime(time);
				  kingDetail.setDescription(depcription);
				  kingDetail.setMat(mat);
				  kingDetail.setSinh(sinh);
				  kingDetail.setAntang(anTang);
				  kingDetail.setKeNhiem(keNhiem);
				  kingDetail.setMienHieu(mienHieu);
				  kingDetail.setNienHieu(nienHieu);
				  kingDetail.setTenHuy(tenHuy);
				  kingDetail.setThanPhu(thanPhu);
				  kingDetail.setThanMau(thanMau);
				  kingDetail.setThuyHieu(thuyHieu);
				  kingDetail.setTienNhiem(tienNhiem);
				  kingDetail.setTrieuDai(trieuDai);
				  kingDetail.setTriVi(triVi);
				  kingDetail.setSinhMat();
				  count++;
				  Gson gson = new GsonBuilder().setPrettyPrinting().create();
				  gson.toJson(kingDetail, file);
				  file.write(",\n");  
			  }
	   
		   }

	   CrawlerManager.setEntityCrawled("Vua - Wikipedia", count);
	   file.write("]");

	   }catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	   }
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		KingCharacterCrawler kingCrawler = new KingCharacterCrawler(url);
		kingCrawler.connect(url);
		List<String> allUrls = kingCrawler.getAllUrl(url);
		kingCrawler.getData(allUrls);
		CrawlerManager.setBaseWebList("King", url);
	}
	
}
