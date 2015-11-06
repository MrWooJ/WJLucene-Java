package com.lucene;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.lucene.analysis.fa.PersianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {

	IndexSearcher indexSearcher;
	QueryParser queryParser;
	Query query;

	private String operatorStr;

	public Searcher(String indexDirectoryPath) throws IOException {

		Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath));
		IndexReader indexReader = IndexReader.open(indexDirectory);
		indexSearcher = new IndexSearcher(indexReader);
		PersianAnalyzer persianAnalyzer = new PersianAnalyzer(Version.LUCENE_36);
		queryParser = new QueryParser(Version.LUCENE_36,LuceneConstants.DEFAULT_FIELD,persianAnalyzer);
		queryParser.setAutoGeneratePhraseQueries(true);
	}

	public TopDocs search(String searchQuery) throws IOException, ParseException {

		System.out.println(LuceneConstants.PROGRAMNAME+"Query: "+searchQuery);
		query = queryParser.parse(searchQuery);
		System.out.println(LuceneConstants.PROGRAMNAME+"ParseQuery: "+query.toString());
		return indexSearcher.search(query, LuceneConstants.MAX_SEARCH);
	}

	public TopDocs searchCombinedQuery(Map<String, String> map, boolean operatorStatus) throws IOException, ParseException {
		
		return search(produceQuery(map, operatorStatus));
	}

	public String produceQuery (Map<String, String> map, boolean operatorIsAnd) {

		StringBuffer queryString = new StringBuffer("");

		if(operatorIsAnd)
			operatorStr = "AND ";
		else
			operatorStr = "OR ";

		if(map.get(LuceneConstants.CORE_NEWSURL) != null)
			queryString.append(LuceneConstants.CORE_NEWSURL+":"+map.get(LuceneConstants.CORE_NEWSURL)+" "+operatorStr);
		if(map.get(LuceneConstants.CORE_NEWSNUMBER) != null)
			queryString.append(LuceneConstants.CORE_NEWSNUMBER+":"+map.get(LuceneConstants.CORE_NEWSNUMBER)+" "+operatorStr);
		if(map.get(LuceneConstants.CORE_NEWSTITLE) != null)
			queryString.append(LuceneConstants.CORE_NEWSTITLE+":"+map.get(LuceneConstants.CORE_NEWSTITLE)+" "+operatorStr);
		if(map.get(LuceneConstants.CORE_NEWSBODY) != null)
			queryString.append(LuceneConstants.CORE_NEWSBODY+":"+map.get(LuceneConstants.CORE_NEWSBODY)+" "+operatorStr);
		if(map.get(LuceneConstants.CORE_NEWSDATE) != null)
			queryString.append(LuceneConstants.CORE_NEWSDATE+":"+map.get(LuceneConstants.CORE_NEWSDATE)+" "+operatorStr);
		if(map.get(LuceneConstants.CORE_NEWSSOURCE) != null)
			queryString.append(LuceneConstants.CORE_NEWSSOURCE+":"+map.get(LuceneConstants.CORE_NEWSSOURCE)+" "+operatorStr);

		if(map.get(LuceneConstants.COMMENT_ID) != null)
			queryString.append(LuceneConstants.COMMENT_ID+":"+map.get(LuceneConstants.COMMENT_ID)+" "+operatorStr);
		if(map.get(LuceneConstants.COMMENT_PARENTID) != null)
			queryString.append(LuceneConstants.COMMENT_PARENTID+":"+map.get(LuceneConstants.COMMENT_PARENTID)+" "+operatorStr);
		if(map.get(LuceneConstants.COMMENT_COMMENTER) != null)
			queryString.append(LuceneConstants.COMMENT_COMMENTER+":"+map.get(LuceneConstants.COMMENT_COMMENTER)+" "+operatorStr);
		if(map.get(LuceneConstants.COMMENT_LOCATION) != null)
			queryString.append(LuceneConstants.COMMENT_LOCATION+":"+map.get(LuceneConstants.COMMENT_LOCATION)+" "+operatorStr);
		if(map.get(LuceneConstants.COMMENT_DATE) != null)
			queryString.append(LuceneConstants.COMMENT_DATE+":"+map.get(LuceneConstants.COMMENT_DATE)+" "+operatorStr);
		if(map.get(LuceneConstants.COMMENT_LIKECOMMENT) != null)
			queryString.append(LuceneConstants.COMMENT_LIKECOMMENT+":"+map.get(LuceneConstants.COMMENT_LIKECOMMENT)+" "+operatorStr);
		if(map.get(LuceneConstants.COMMENT_DISLIKECOMMENT) != null)
			queryString.append(LuceneConstants.COMMENT_DISLIKECOMMENT+":"+map.get(LuceneConstants.COMMENT_DISLIKECOMMENT)+" "+operatorStr);
		if(map.get(LuceneConstants.COMMENT_RESPONSECOUNT) != null)
			queryString.append(LuceneConstants.COMMENT_RESPONSECOUNT+":"+map.get(LuceneConstants.COMMENT_RESPONSECOUNT)+" "+operatorStr);
		if(map.get(LuceneConstants.COMMENT_BODY) != null)
			queryString.append(LuceneConstants.COMMENT_BODY+":"+map.get(LuceneConstants.COMMENT_BODY)+" "+operatorStr);

		if(map.get(LuceneConstants.CATEGORY_NEWS) != null)
			queryString.append(LuceneConstants.CATEGORY_NEWS+":"+map.get(LuceneConstants.CATEGORY_NEWS)+" "+operatorStr);
		if(map.get(LuceneConstants.LABEL_NEWS) != null)
			queryString.append(LuceneConstants.LABEL_NEWS+":"+map.get(LuceneConstants.LABEL_NEWS)+" "+operatorStr);

		String query = new String(queryString);

		if (queryString.length() != 0) {
			if (operatorIsAnd)
				query = query.substring(0, query.length()-5);
			else
				query = query.substring(0, query.length()-4);
		}
		
		return query;
	}
	
	public String produceQuery (ArrayList<String> values, String fieldName ,boolean operatorIsAnd) {
	
		StringBuffer queryString = new StringBuffer("");

		if(operatorIsAnd)
			operatorStr = "AND ";
		else
			operatorStr = "OR ";
		
		for (int i = 0; i < values.size(); i++)
			queryString.append(fieldName+":"+values.get(i)+" "+operatorStr);
		
		String query = new String(queryString);

		if (queryString.length() != 0) {
			if (operatorIsAnd)
				query = query.substring(0, query.length()-5);
			else
				query = query.substring(0, query.length()-4);
		}
		
		return query;
	}

	public String produceQuery(ArrayList<String> labelValues, ArrayList<String> categoryValues, String NewsbodyQuery, String commentBodyQuery, Date startNewsDate, Date endNewsDate, boolean operatorIsAnd) throws IOException {
		
		if (labelValues == null && categoryValues == null && NewsbodyQuery == null && commentBodyQuery == null && startNewsDate == null && commentBodyQuery == null) {
		
			 IOException e = new IOException();
			   throw e;
		}
		
		StringBuffer queryString = new StringBuffer("");

		if(operatorIsAnd)
			operatorStr = "AND ";
		else
			operatorStr = "OR ";
		
		if (labelValues.size() > 0 && labelValues != null)
			for (int i = 0; i < labelValues.size(); i++)
				queryString.append(LuceneConstants.LABEL_NEWS+":"+labelValues.get(i)+" "+operatorStr);
		
		if (categoryValues.size() > 0 && categoryValues != null)
			for (int i = 0; i < categoryValues.size(); i++)
				queryString.append(LuceneConstants.CATEGORY_NEWS+":"+categoryValues.get(i)+" "+operatorStr);

		if (NewsbodyQuery != null && NewsbodyQuery.length() > 0)
			queryString.append(LuceneConstants.CORE_NEWSBODY+":"+NewsbodyQuery+" "+operatorStr);
		
		if (commentBodyQuery != null && commentBodyQuery.length() > 0)
			queryString.append(LuceneConstants.COMMENT_BODY+":"+commentBodyQuery+" "+operatorStr);
		
		if (startNewsDate != null && endNewsDate != null) {
			DateFormat df = new SimpleDateFormat("MM/dd/yy");
	    	String startDateString = df.format(startNewsDate);
	    	String endDateString = df.format(endNewsDate);
	    	String dateQuery = new String("["+startDateString+" TO "+endDateString+"]");
	    	queryString.append(LuceneConstants.CORE_NEWSDATE+":"+dateQuery+" "+operatorStr);
		}
		else if (startNewsDate == null) {
			DateFormat df = new SimpleDateFormat("MM/dd/yy");
	    	String endDateString = df.format(endNewsDate);
	    	String dateQuery = new String("["+"1/1/00"+" TO "+endDateString+"]");
	    	queryString.append(LuceneConstants.CORE_NEWSDATE+":"+dateQuery+" "+operatorStr);
		}
		else if (endNewsDate == null) {
			DateFormat df = new SimpleDateFormat("MM/dd/yy");
			Date today = Calendar.getInstance().getTime();        
			String todayString = df.format(today);
	    	String startDateString = df.format(startNewsDate);
	    	String dateQuery = new String("["+startDateString+" TO "+todayString+"]");
	    	queryString.append(LuceneConstants.CORE_NEWSDATE+":"+dateQuery+" "+operatorStr);
		}
		
		String query = new String(queryString);

		if (queryString.length() != 0) {
			if (operatorIsAnd)
				query = query.substring(0, query.length()-5);
			else
				query = query.substring(0, query.length()-4);
		}
		
		return query;
	}

	
	public Document getDocument(ScoreDoc scoreDoc) throws CorruptIndexException, IOException {
		
		return indexSearcher.doc(scoreDoc.doc);	
	}

	public void close() throws IOException{
		
		indexSearcher.close();
	}
}