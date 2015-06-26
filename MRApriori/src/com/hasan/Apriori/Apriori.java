package com.hasan.Apriori;

/**
 * @author: Hasan
 * 26th June 2015
 * 
 **/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Apriori {
	
	public void getInputFile(String fileDirectory) throws IOException{
		BufferedReader reader = null;
	
		try {
			String sCurrentLine;
			reader = new BufferedReader(new FileReader(fileDirectory));
			while((sCurrentLine = reader.readLine())!=null){
				System.out.println(sCurrentLine);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				if (reader!=null){
					reader.close();
				}
			}catch(IOException exception){
				exception.printStackTrace();
			}
		}
		
	}
	
	public List<FrequentItemset> performApriori(List<Transcation> T, float minSupport){
		List<FrequentItemset> itemSetList = new ArrayList<FrequentItemset>();
		
		return itemSetList;
	}
	
	public static void main(String[] args) throws IOException{
		Apriori apriori = new Apriori();
		String filePath = System.getProperty("user.dir")+"/src/com/hasan/Apriori/input.txt";
		apriori.getInputFile(filePath);
	}

}
