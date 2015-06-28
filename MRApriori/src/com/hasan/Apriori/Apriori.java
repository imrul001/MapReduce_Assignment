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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public List<FrequentItemset> performApriori(List<Transcation> TDB, int minSupport){
		List<FrequentItemset> frequentItemSetList = new ArrayList<FrequentItemset>();
		frequentItemSetList = getOneItemset(TDB, minSupport);
		int k = 2;
		while(frequentItemSetList.size()!=0){
			List<CandidateItemset> candidateItemsets = new ArrayList<CandidateItemset>();
			candidateItemsets = generateCandidate(frequentItemSetList, k);
			for(CandidateItemset candidateItemset: candidateItemsets){
				int count = 0; 
				for(Transcation T : TDB){
					if(has(T,candidateItemset)){
						count = count +1;
					}
				}
				candidateItemset.setFrequency(count);
			}
			frequentItemSetList = pruneCandidate(candidateItemsets, minSupport);
			for(FrequentItemset frequentItemset: frequentItemSetList){
				System.out.println("k="+k+" "+frequentItemset.getItemSet()+" "+frequentItemset.getFrequency());
			}
			k++;
		}
		return frequentItemSetList;
	}
	
	public List<CandidateItemset> generateCandidate(List<FrequentItemset> L, int itemsetLength){
		List<CandidateItemset> iteCandidateItemsets = new ArrayList<CandidateItemset>();
		List<Character> myList = new ArrayList<>();
		for(FrequentItemset itemset : L){
			String item = itemset.getItemSet();
			int len = item.length();
			for(int i =0 ; i< len; i++){
				char a_char = item.charAt(i);
				myList.add(a_char);				
			}
		}
		Set<Character> myset = new HashSet<>();
		myset.addAll(myList);
		myList.clear();
		myList.addAll(myset);
		Object[] elements = myList.toArray();
		List<String> mystring = Combination.combination(elements, itemsetLength);
		for(int i = 0; i < mystring.size(); i++){
			CandidateItemset candidateItemset = new CandidateItemset();
			candidateItemset.setItemSet(mystring.get(i));
			iteCandidateItemsets.add(candidateItemset);
		}
		return iteCandidateItemsets;
	}
	
	public List<FrequentItemset> pruneCandidate(List<CandidateItemset> candidateItemsets, int minSupport){
		List<FrequentItemset> frequentItemsets = new ArrayList<FrequentItemset>();
		for(CandidateItemset itemset: candidateItemsets){
			if(itemset.getFrequency() >= minSupport){
				FrequentItemset frequentItemset = new FrequentItemset();
				frequentItemset.setItemSet(itemset.getItemSet());
				frequentItemset.setFrequency(itemset.getFrequency());
				frequentItemsets.add(frequentItemset);
			}
		}
		return frequentItemsets;
	}
	
	public List<FrequentItemset> getOneItemset(List<Transcation> T, int minSupport){
		List<FrequentItemset> frequentItemsets = new ArrayList<FrequentItemset>();
		List<String> myList = new ArrayList<>();
		for(Transcation transcation : T){
			String[] temp = transcation.getItemSet().split(",");
			for(int	i = 0; i < temp.length; i++){
				myList.add(temp[i]);
			}
		}
//		System.out.println(myList);
		Set<String> mySet = new HashSet<String>();
		mySet.addAll(myList);
		List<String> myListNoDup = new ArrayList<String>();
		myListNoDup.addAll(mySet);
		
		for(int i = 0; i < myListNoDup.size(); i++){
			int occurance = Collections.frequency(myList, myListNoDup.get(i));
			if(occurance >= minSupport){
				FrequentItemset frequentItemset = new FrequentItemset();
				frequentItemset.setItemSet(myListNoDup.get(i));
				frequentItemset.setFrequency(occurance);
				frequentItemsets.add(frequentItemset);
			}
		}
		return frequentItemsets;
	}
	
	public boolean has(Transcation T, CandidateItemset candidateItemset){
		boolean flag = true;
		String[] transactionSet = T.getItemSet().split(",");
		String candidateItemSetString = candidateItemset.getItemSet(); 
		for(int i=0; i<candidateItemSetString.length(); i++){
			char a_char = candidateItemSetString.charAt(i);
			int check = 0;
			for(int j=0; j < transactionSet.length; j++){
				if(!transactionSet[j].equals(Character.toString(a_char))){
					check = 0;
				}else{
					check = 1;
					break;
				}
			}
			if(check==0){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public static void main(String[] args) throws IOException{
		Apriori apriori = new Apriori();
//		String filePath = System.getProperty("user.dir")+"/src/com/hasan/Apriori/input.txt";
//		apriori.getInputFile(filePath);
		Transcation t1 = new Transcation();
		Transcation t2 = new Transcation();
		Transcation t3 = new Transcation();
		t1.setTranscationId("T1");t2.setTranscationId("T2");t3.setTranscationId("T3");
		t1.setItemSet("A,B,C");t2.setItemSet("B,C,E");t3.setItemSet("E,F,B");
		List<Transcation> T = new ArrayList<Transcation>();
		T.add(t1); T.add(t2); T.add(t3);
		
		List<FrequentItemset> frequentItemsets = apriori.performApriori(T, 2);
		System.out.println(frequentItemsets.size());
	}

}
