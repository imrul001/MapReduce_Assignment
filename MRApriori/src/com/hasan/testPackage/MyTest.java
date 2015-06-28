package com.hasan.testPackage;
/**
 * @author: Hasan
 * 26th June 2015
 * 
 **/

import java.util.ArrayList;
import java.util.List;

import com.hasan.Apriori.Apriori;
import com.hasan.Apriori.CandidateItemset;
import com.hasan.Apriori.FrequentItemset;
import com.hasan.Apriori.Transcation;

import junit.framework.TestCase;

public class MyTest extends TestCase {
	
	protected Transcation t1 = new Transcation();
	protected Transcation t2 = new Transcation();
	protected Transcation t3 = new Transcation();
	protected CandidateItemset candidateItemset = new CandidateItemset();
	protected List<Transcation> T = new ArrayList<Transcation>();
	protected List<FrequentItemset> L = new ArrayList<FrequentItemset>();
	protected FrequentItemset f1 = new FrequentItemset();
	protected FrequentItemset f2 = new FrequentItemset();
	protected FrequentItemset f3 = new FrequentItemset();
	protected List<CandidateItemset> iteCandidateItemsets = new ArrayList<CandidateItemset>();
	
	protected void setup() throws Exception{
		
		t1.setTranscationId("T1");t2.setTranscationId("T2");t3.setTranscationId("T3");
		t1.setItemSet("A,B,C");t2.setItemSet("B,C,E");t3.setItemSet("E,F,B");
		T.add(t1); T.add(t2); T.add(t3);
		candidateItemset.setItemSet("BC");
		candidateItemset.setFrequency(0);
	}
	
	public void testHas(){
		boolean flag = true;
		Apriori apriori = new Apriori();
		t1.setItemSet("A,B,C");
		candidateItemset.setItemSet("BC");
		candidateItemset.setFrequency(0);
		boolean check = apriori.has(t1, candidateItemset);
		assertEquals(flag, check);
		
	}
	//test getOneItemset with prune 
	public void testGetoneItemset(){
		Apriori apriori = new Apriori();
		t1.setTranscationId("T1");t2.setTranscationId("T2");t3.setTranscationId("T3");
		t1.setItemSet("A,B,C");t2.setItemSet("B,C,E");t3.setItemSet("E,F,B");
		List<Transcation> T = new ArrayList<Transcation>();
		T.add(t1); T.add(t2); T.add(t3);
		List<FrequentItemset> FrequentItemsets = apriori.getOneItemset(T,2);
		assertEquals(3, FrequentItemsets.size());
	}
	
	public void testGenerateCandidate(){
		Apriori apriori = new Apriori();
		int candidateNumber = 3;
		f1.setItemSet("b");f1.setFrequency(3);
		f2.setItemSet("c");f2.setFrequency(2);
		f3.setItemSet("e");f3.setFrequency(2);
		L.add(f1);L.add(f2);L.add(f3);
		iteCandidateItemsets = apriori.generateCandidate(L, 2);
		assertEquals(candidateNumber, iteCandidateItemsets.size());
	}
	
	public void testApriori(){
		int result = 0;
		t1.setTranscationId("T1");
		t2.setTranscationId("T2");
		t3.setTranscationId("T3");
		t3.setItemSet("E,F,B");
		t1.setItemSet("A,B,C");
		t2.setItemSet("B,C,E");
		T.add(t1); T.add(t2); T.add(t3);
		Apriori apriori = new Apriori();
		assertEquals(result, apriori.performApriori(T, 2).size());
		
	}
	

}
