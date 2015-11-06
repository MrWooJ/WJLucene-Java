package com.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneTester {

	Indexer 	indexer;
	Searcher 	searcher;

	public void createIndex(ViewController vc) throws IOException, BiffException {

		File file = new File(LuceneConstants.INDEX_DIRECTORY);
		
		if (file.listFiles().length > 2) {
			vc.changeHeaderMessage(LuceneConstants.MESSAGE_INDEXED);
			vc.enableButtons(true);
		}
		else {
			vc.enableButtons(false);
			vc.changeHeaderMessage(LuceneConstants.MESSAGE_INDEXING);
			System.out.println(LuceneConstants.PROGRAMNAME+"Start Indexing Proccess!");

			indexer = new Indexer(LuceneConstants.INDEX_DIRECTORY);

			int numIndexed;
			long startTime = System.currentTimeMillis();	
			numIndexed = indexer.createIndex(LuceneConstants.DATA_DIRECTORY, new TextFileFilter());
			long endTime = System.currentTimeMillis();

			indexer.close();

			System.out.println(LuceneConstants.PROGRAMNAME+numIndexed+" File Indexed. Time: " +(endTime-startTime)+" ms");
			vc.changeHeaderMessage(LuceneConstants.MESSAGE_INDEXED);
			vc.enableButtons(true);
		}		
	}

	public void search(String searchQuery, ViewController vc) throws IOException, ParseException {

		searcher = new Searcher(LuceneConstants.INDEX_DIRECTORY);

		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();

		ArrayList<Document> docsArray = new ArrayList<Document>();

		System.out.println(LuceneConstants.PROGRAMNAME+hits.totalHits +" Documents Found. Time :" + (endTime - startTime));
		vc.changeHeaderMessage(hits.totalHits + LuceneConstants.MESSAGE_SEARCH + (endTime - startTime) + "ms.");

		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			docsArray.add(doc);
			System.out.println(LuceneConstants.PROGRAMNAME+"Result File: " + doc.toString());
		}

		searcher.close();

		if(hits.totalHits != 0)
			vc.showResult(docsArray);
	}

	public void searchCombinedQuery(Map<String, String> dictionary, boolean Operator, ViewController vc) throws IOException, ParseException {

		searcher = new Searcher(LuceneConstants.INDEX_DIRECTORY);

		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.searchCombinedQuery(dictionary, Operator);
		long endTime = System.currentTimeMillis();

		ArrayList<Document> docsArray = new ArrayList<Document>();

		System.out.println(LuceneConstants.PROGRAMNAME+hits.totalHits +" Documents Found. Time :" + (endTime - startTime));
		vc.changeHeaderMessage(hits.totalHits + LuceneConstants.MESSAGE_SEARCH + (endTime - startTime) + "ms.");

		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			docsArray.add(doc);
			System.out.println(LuceneConstants.PROGRAMNAME+"File: " + doc.toString());
		}

		searcher.close();

		if(hits.totalHits != 0)
			vc.showResult(docsArray);
	}

	public ArrayList<String> searchCombinedQuery(Map<String, String> dictionary, boolean Operator, String returnValue) throws IOException, ParseException {

		ArrayList<String> results = new ArrayList<String>();
		searcher = new Searcher(LuceneConstants.INDEX_DIRECTORY);

		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.searchCombinedQuery(dictionary, Operator);
		long endTime = System.currentTimeMillis();

		System.out.println(LuceneConstants.PROGRAMNAME+hits.totalHits +" Documents Found. Time :" + (endTime - startTime));

		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			results.add(doc.get(returnValue));
		}

		searcher.close();
		return results;
	}

	public ArrayList<String> searchCombinedQuery(ArrayList<String> values, String fieldName, boolean Operator, String returnValue) throws IOException, ParseException {

		ArrayList<String> results = new ArrayList<String>();

		searcher = new Searcher(LuceneConstants.INDEX_DIRECTORY);
		String str = new String(searcher.produceQuery(values, fieldName, Operator));

		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(str);
		long endTime = System.currentTimeMillis();

		System.out.println(LuceneConstants.PROGRAMNAME+hits.totalHits +" Documents Found. Time :" + (endTime - startTime));

		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			results.add(doc.get(returnValue));
		}

		searcher.close();
		return results;
	}

	public ArrayList<String> searchCombinedQuery(ArrayList<String> labels, ArrayList<String> categories, String NewsbodyQuery, String commentBodyQuery, Date startNewsDate, Date endNewsDate, boolean Operator, String returnValue) throws IOException, ParseException {

		ArrayList<String> results = new ArrayList<String>();

		searcher = new Searcher(LuceneConstants.INDEX_DIRECTORY);
		String str = null;
		str = new String(searcher.produceQuery(labels, categories, NewsbodyQuery, commentBodyQuery, startNewsDate, endNewsDate, Operator));

		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(str);
		long endTime = System.currentTimeMillis();

		System.out.println(LuceneConstants.PROGRAMNAME+hits.totalHits +" Documents Found. Time :" + (endTime - startTime));

		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			results.add(doc.get(returnValue));
		}

		searcher.close();
		return results;
	}

	public String getSearchQuery(Map<String, String> dictionary, boolean Operator) throws IOException, ParseException {

		searcher = new Searcher(LuceneConstants.INDEX_DIRECTORY);
		String str = new String(searcher.produceQuery(dictionary, Operator));
		searcher.close();

		return str;
	}

}