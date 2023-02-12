package crawler.entity.character;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.entity.Character;
public class DataAggregation {
	public List<Character> loadDataJsonNKS() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/characterNKS.json"));
        List<Character> NKS = Arrays.asList(gson.fromJson(reader, Character[].class)); 
        reader.close();

        return NKS;
    }
	public List<Character> loadDataJsonVanSu() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/characterVanSu.json"));
        List<Character> VanSu = Arrays.asList(gson.fromJson(reader, Character[].class)); 
        reader.close();

        return VanSu;
    } 
	public List<Character> Aggregation(List<Character> NKS, List<Character> VS) {
		List<Character> nv = new ArrayList<Character>();
		int numb = 0;
		int NKSnumb = 0;
		int VSnumb = 0;
		for(Character e : NKS ) {	
			NKSnumb++;
			boolean ktra = false;
			String name1 = e.getName();
			for(Character f : VS) {
				String name2 = f.getName();
				if(name1.equals(name2)) {
					ktra = true;
					String name = name2;
					String time = null;
					String timeNKS = e.getTime();
					String timeVS = f.getTime();
					time = timeVS;
					if(timeNKS.equals("Không rõ")||timeNKS.equals("Không rõ---Không rõ")) time = timeVS;					
					if(timeVS.equals("Không rõ")) time = timeNKS;
					
					String depcription = e.getDescription();
					String aotherName = f.getAotherName();
					String place = f.getPlace();
					List<String> era = f.getEra();
					Character nvNew = new Character(name,time,depcription,aotherName,place,era);
					nv.add(nvNew);
					numb++;
					
					break;
				}
			}
			if(ktra == false) {
				nv.add(e); 
				numb++;
				
			}
		}
		for(Character f: VS) {
			VSnumb++;
			boolean ktra = false;
			String name1 = f.getName();
			for(Character e : nv) {
				String name2 = e.getName();
				if(name1.equals(name2)) {
					ktra = true;
					break;
				}
			}
			if(ktra == false) {
				numb++;
				nv.add(f);
			}
		}
		System.out.println(NKSnumb);
		System.out.println(VSnumb);
		System.out.println(numb);
		return nv;
	}
	public void Start() throws IOException {
		DataAggregation dA = new DataAggregation();
		List<Character> listNKS = dA.loadDataJsonNKS();
		List<Character> listVS = dA.loadDataJsonVanSu();
		List<Character> nv = dA.Aggregation(listNKS, listVS);
		
		try (Writer file = new FileWriter("src\\main\\java\\json\\character1.json")){
			  file.write("[\n");
			  for(Character e : nv) { 
				  Gson gson = new GsonBuilder().setPrettyPrinting().create();
				  gson.toJson(e, file);
				  file.write(",\n");
			  }
			  file.write("]");
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws IOException {
		DataAggregation dA = new DataAggregation();
		List<Character> listNKS = dA.loadDataJsonNKS();
		List<Character> listVS = dA.loadDataJsonVanSu();
		List<Character> nv = dA.Aggregation(listNKS, listVS);
		
		try (Writer file = new FileWriter("src\\main\\java\\json\\character1.json")){
			  file.write("[\n");
			  for(Character e : nv) { 
				  Gson gson = new GsonBuilder().setPrettyPrinting().create();
				  gson.toJson(e, file);
				  file.write(",\n");
			  }
			  file.write("]");
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
