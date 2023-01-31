package crawler.entity;

import crawler.BaseWebCrawler;
import history.entity.Character;

import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;

public class CharacterCrawler extends BaseWebCrawler {
	private static Document doc;
    public CharacterCrawler(List<String> urls) {
        super(urls);
        CharacterCrawler.doc = null;
    }
    
	public static Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		CharacterCrawler.doc = doc;
	}
	public String getUrlIndex(int i) {
		return this.urls.get(i);
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
	
	public List<String> getAllUrl_NKS(String url){
		List<String> allUrl = new ArrayList<String>();
		
		Document Doc1 = getDoc();
		Elements charaterATag1 = Doc1.select("h2[itemprop=name] a");
        for(Element a : charaterATag1) {
        	String charaterUrl = "https://nguoikesu.com"+ a.attr("href");
        	System.out.println(charaterUrl);
        	allUrl.add(charaterUrl);
        }
        
		Elements li_tags = Doc1.select("nav.pagination__wrapper li");
		Element nextPageButton = li_tags.get(li_tags.size()-2);
		
		while(!li_tags.get(li_tags.size()-2).className().equals("disabled page-item")) {
			String hrefNext = "https://nguoikesu.com" + nextPageButton.select("a").get(0).attr("href");
			System.out.println(hrefNext + "\n\n");
			
			Document page = null;
	        try{
	            page = Jsoup
	                    .connect(hrefNext)
	                    .userAgent("Jsoup client")
	                    .timeout(20000).get();
	            Elements charaterATags = page.select("h2[itemprop=name] a");
	            for(Element a : charaterATags) {
	            	String charaterUrl = "https://nguoikesu.com"+ a.attr("href");
	            	System.out.println(charaterUrl);
	            	allUrl.add(charaterUrl);
	            } 
	            li_tags = page.select("nav.pagination__wrapper li");
		        nextPageButton = li_tags.get(li_tags.size()-2);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
		}
		return allUrl;
	}
	public void getdata_NKS(List<String> allUrl) {
		try (Writer writer = new FileWriter("src\\main\\java\\json\\character.json", true)) {
		    writer.write('[');
		}catch(IOException e) {
			e.printStackTrace();
		}
		for(String url : allUrl) {
			Character nv = new Character();
			try {
				Document characterPage = Jsoup
	                     				.connect(url)
	                     				.userAgent("Jsoup client")
	                     				.timeout(20000).get();
				String name = characterPage.select("div.page-header h2").text();
				
				Elements infoBoxs = characterPage.select("div[class*=infobox]");
				Elements articleBody = characterPage.select("div[itemprop=articleBody]"); 
				String Sinh = null;
				String Mat = null;
				String description = "";
				if(infoBoxs.size()> 0) {
					Element infoTable = infoBoxs.get(0).select("table").get(0);
					Elements rows = infoTable.select("tbody tr");					
					for(Element r : rows) {
						Elements col1 = r.select("th");
						Elements col2 = r.select("td");
						if(col1.size() > 0 & col2.size() > 0) {
							String Key = col1.get(0).text();
							String Value = col2.get(0).text();
							if(Key.equals("Sinh")) {
								Sinh = Value;
							}else 
							if(Key.equals("Mất")) {
									Mat = Value;
							}else {
								String KeyValue = Key + " : " + Value +"\n";
								description += KeyValue;
							}	
						}else if(col2.size() == 0) {
							String Value = r.text();
							description += Value +"\n";
						}
					}
					nv.setName(name);
					nv.setTime((Sinh==null?"Không rõ":Sinh.equals("?")?"Không rõ":Sinh) + "---" + (Mat==null?"Không rõ":Mat.equals("?")?"Không rõ":Mat));
					Elements pTags = articleBody.select("div[class=infobox] ~ *");
					if(pTags.size()>0) {
						for(Element p : pTags) {
							if(!p.text().equals("")) description+=p.text()+"\n";
						}
					}
					nv.setDescription(description);
					try (Writer writer = new FileWriter("src\\main\\java\\json\\character.json", true)) {
					    Gson gson = new GsonBuilder().setPrettyPrinting().create();
					    gson.toJson(nv, writer);
					    writer.write(",\n");
					}catch(IOException e) {
						e.printStackTrace();
					}
					
				}else {
					Elements pTags = articleBody.select("p");
					if(pTags.size()>0) {
						String info = pTags.get(0).text();
						String timeRegex ="("+name+" )"+"(\\()([^)]*)(\\))";
						Pattern p = Pattern.compile(timeRegex);
					    Matcher matcher = p.matcher(info);
					    boolean matchFound = matcher.find();
					    if(matchFound) {
					      String nameTime = matcher.group();
					      String Regex = "\\(.*\\)";
					      Pattern pattern = Pattern.compile(Regex);
					      Matcher m = pattern.matcher(nameTime);
					      boolean match = m.find();
					      String time = "Không rõ";
					      if(match){
					      	String str = m.group();
					      	time = str.substring(1, str.length()-1);
					      }
					      nv.setName(name);
					      nv.setTime(time);
					      Elements pTags1 = articleBody.select("#toc ~ *");
					      if(pTags1.size()>0) {
							for(Element p1 : pTags1) {
								if(!p1.text().equals("")) description+=p1.text()+"\n";
							}
					      }else {
					    	  description = "";
					      }
					      nv.setDescription(description);
					      try (Writer writer = new FileWriter("src\\main\\java\\json\\character.json", true)) {
							    Gson gson = new GsonBuilder().setPrettyPrinting().create();
							    gson.toJson(nv, writer);
							    writer.write(",\n");
					      }catch(IOException e) {
							e.printStackTrace();
					      }
					    }else {
					    	nv.setName(name);
					    	nv.setTime("Không rõ");
					    	Elements pTags1 = articleBody.select("#toc ~ *");
						      if(pTags1.size()>0) {
								for(Element p1 : pTags1) {
									if(!p1.text().equals("")) description+=p1.text()+"\n";
								}
						      }else {
						    	  description = "";
						      }
						    nv.setDescription(description);
						    try (Writer writer = new FileWriter("src\\main\\java\\json\\character.json", true)) {
							    Gson gson = new GsonBuilder().setPrettyPrinting().create();
							    gson.toJson(nv, writer);
							    writer.write(",\n");
							}catch(IOException e) {
								e.printStackTrace();
							}
					    }
					}
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		try (Writer writer = new FileWriter("src\\main\\java\\json\\character.json", true)) {
		    writer.write(']');
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		urls.add("https://nguoikesu.com/nhan-vat");
		
		CharacterCrawler test = new CharacterCrawler(urls);
		String url = test.getUrlIndex(0);
		test.connect(url);
		List<String> allUrl = test.getAllUrl_NKS(url);
		System.out.println(allUrl.size());
		
		test.getdata_NKS(allUrl);
	}
}
