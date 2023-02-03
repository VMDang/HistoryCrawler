package history.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class ReadDynastyJsonFile {

	public static void main(String[] args) throws IOException {
		
		/*ArrayList<Dynasty> theList = new ArrayList<Dynasty>();
		//File theFile = new File("Dynasties_NKS.json");
		try {
			FileReader fileReader = new FileReader("Dynasties_NKS.json");
			Type type = new TypeToken<ArrayList<Dynasty>>(){}.getType();
			Gson gson = new Gson();
			theList = gson.fromJson(fileReader, type);
			fileReader.close();
			for (Dynasty dk : theList) {
				System.out.println("The name: " + dk.getName());
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error in creating a FileReader object.");
		} catch (IOException e) {
			System.err.println("Error in closing the file.");
		}*/
		
		Gson gson = new Gson();
		Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Dynasties_NKS.json"));
		List<Dynasty> dks = Arrays.asList(gson.fromJson(reader, Dynasty[].class));
		/*for (Dynasty dk : dks) {
			System.out.println("Name: " + dk.getKing());
		}*/
		reader.close();

	}

}
