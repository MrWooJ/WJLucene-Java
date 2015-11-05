package com.lucene;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.jdesktop.swingx.prompt.PromptSupport;

public class ViewController extends CommonUI {

	final static ViewController view = new ViewController();

	static JLabel headerLabel = new JLabel("WELCOME", JLabel.CENTER);

	static ArrayList<Document> resultArray = new ArrayList<Document>();

	static JButton searchButton = new JButton("Search");    
	static JButton editButton = new JButton("Edit");    


	public static void main(String[] args) throws BiffException {

		final LuceneTester tester;
		tester = new LuceneTester();

		//		If there was a need to remove former indexed filed, Uncomment line below
		//		FileUtils.deleteDirectory(new File(LuceneConstants.INDEX_DIRECTORY));

		JFrame frame = new JFrame("Lucene - Java");
		frame.setSize(500, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(23,1));
		frame.setResizable(false);

		JPanel emptyPanel1 = new JPanel(new FlowLayout());
		JPanel emptyPanel2 = new JPanel(new FlowLayout());

		JPanel panel0 = new JPanel(new FlowLayout());
		panel0.add(headerLabel);

		JPanel panel1 = new JPanel(new FlowLayout());
		final JTextField textField1 = new JTextField(20);
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("CORE NEWSURL", textField1);
		panel1.add(textField1);

		JPanel panel2 = new JPanel(new FlowLayout());
		final JTextField textField2 = new JTextField(20);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("CORE NEWSNUMBER", textField2);
		panel2.add(textField2);

		JPanel panel3 = new JPanel(new FlowLayout());
		final JTextField textField3 = new JTextField(20);
		textField3.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("CORE NEWSTITLE", textField3);
		panel3.add(textField3);

		JPanel panel4 = new JPanel(new FlowLayout());
		final JTextField textField4 = new JTextField(20);
		textField4.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("CORE NEWSBODY", textField4);
		panel4.add(textField4);

		JPanel panel5 = new JPanel(new FlowLayout());
		final JTextField textField5 = new JTextField(20);
		textField5.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("CORE NEWSDATE", textField5);
		panel5.add(textField5);

		JPanel panel6 = new JPanel(new FlowLayout());
		final JTextField textField6 = new JTextField(20);
		textField6.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("CORE NEWSSOURCE", textField6);
		panel6.add(textField6);

		JPanel panel7 = new JPanel(new FlowLayout());
		final JTextField textField7 = new JTextField(20);
		textField7.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("COMMENT ID", textField7);
		panel7.add(textField7);

		JPanel panel8 = new JPanel(new FlowLayout());
		final JTextField textField8 = new JTextField(20);
		textField8.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("COMMENT PARENTID", textField8);
		panel8.add(textField8);

		JPanel panel9 = new JPanel(new FlowLayout());
		final JTextField textField9 = new JTextField(20);
		textField9.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("COMMENT COMMENTER", textField9);
		panel9.add(textField9);

		JPanel panel10 = new JPanel(new FlowLayout());
		final JTextField textField10 = new JTextField(20);
		textField10.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("COMMENT LOCATION", textField10);
		panel10.add(textField10);

		JPanel panel11 = new JPanel(new FlowLayout());
		final JTextField textField11 = new JTextField(20);
		textField11.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("COMMENT DATE", textField11);
		panel11.add(textField11);

		JPanel panel12 = new JPanel(new FlowLayout());
		final JTextField textField12 = new JTextField(20);
		textField12.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("COMMENT LIKECOMMENT", textField12);
		panel12.add(textField12);

		JPanel panel13 = new JPanel(new FlowLayout());
		final JTextField textField13 = new JTextField(20);
		textField13.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("COMMENT DISLIKECOMMENT", textField13);
		panel13.add(textField13);

		JPanel panel14 = new JPanel(new FlowLayout());
		final JTextField textField14 = new JTextField(20);
		textField14.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("COMMENT RESPONSECOUNT", textField14);
		panel14.add(textField14);

		JPanel panel15 = new JPanel(new FlowLayout());
		final JTextField textField15 = new JTextField(20);
		textField15.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("COMMENT BODY", textField15);
		panel15.add(textField15);

		JPanel panel16 = new JPanel(new FlowLayout());
		final JTextField textField16 = new JTextField(20);
		textField16.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("CATEGORY NEWS", textField16);
		panel16.add(textField16);

		JPanel panel17 = new JPanel(new FlowLayout());
		final JTextField textField17 = new JTextField(20);
		textField17.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("LABEL NEWS", textField17);
		panel17.add(textField17);

		JPanel panel18 = new JPanel(new FlowLayout());
		final JTextField textField18 = new JTextField(20);
		textField18.setHorizontalAlignment(SwingConstants.CENTER);
		PromptSupport.setPrompt("DEFAULT OPERATOR", textField18);
		panel18.add(textField18);

		JPanel panel19 = new JPanel(new FlowLayout());

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Map<String, String> map = new HashMap<String, String>();
				
				if (textField1.getText() != null && textField1.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSURL, textField1.getText());
				if (textField2.getText() != null && textField2.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSNUMBER, textField2.getText());
				if (textField3.getText() != null && textField3.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSTITLE, textField3.getText());
				if (textField4.getText() != null && textField4.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSBODY, textField4.getText());
				if (textField5.getText() != null && textField5.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSDATE, textField5.getText());
				if (textField6.getText() != null && textField6.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSSOURCE, textField6.getText());

				if (textField7.getText() != null && textField7.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_ID, textField7.getText());
				if (textField8.getText() != null && textField8.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_PARENTID, textField8.getText());
				if (textField9.getText() != null && textField9.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_COMMENTER, textField9.getText());
				if (textField10.getText() != null && textField10.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_LOCATION, textField10.getText());
				if (textField11.getText() != null && textField11.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_DATE, textField11.getText());
				if (textField12.getText() != null && textField12.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_LIKECOMMENT, textField12.getText());
				if (textField13.getText() != null && textField13.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_DISLIKECOMMENT, textField13.getText());
				if (textField14.getText() != null && textField14.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_RESPONSECOUNT, textField14.getText());
				if (textField15.getText() != null && textField15.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_BODY, textField15.getText());

				if (textField16.getText() != null && textField16.getText().length() != 0)
					map.put(LuceneConstants.CATEGORY_NEWS, textField16.getText());
				if (textField17.getText() != null && textField17.getText().length() != 0)
					map.put(LuceneConstants.LABEL_NEWS, textField17.getText());

				boolean operatorIsAnd = false;
				if (textField18.getText() != null && textField18.getText().length() != 0)
					if (textField18.getText().equalsIgnoreCase("AND"))
						operatorIsAnd = true;

				try {
					tester.searchCombinedQuery(map, operatorIsAnd, view);
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}

			}          
		});

		JButton clearButton = new JButton("Clear");    
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				textField5.setText("");
				textField6.setText("");
				textField7.setText("");
				textField8.setText("");
				textField9.setText("");
				textField10.setText("");
				textField11.setText("");
				textField12.setText("");
				textField13.setText("");
				textField14.setText("");
				textField15.setText("");
				textField16.setText("");
				textField17.setText("");
				textField18.setText("");
			}          
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JFrame editFrame = new JFrame("Manual Query Searcher");
				editFrame.setSize(400, 400);
				editFrame.setLocationRelativeTo(null);
				editFrame.setLayout(new FlowLayout());
				editFrame.setResizable(false);

				Map<String, String> map = new HashMap<String, String>();
				
				if (textField1.getText() != null && textField1.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSURL, textField1.getText());
				if (textField2.getText() != null && textField2.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSNUMBER, textField2.getText());
				if (textField3.getText() != null && textField3.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSTITLE, textField3.getText());
				if (textField4.getText() != null && textField4.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSBODY, textField4.getText());
				if (textField5.getText() != null && textField5.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSDATE, textField5.getText());
				if (textField6.getText() != null && textField6.getText().length() != 0)
					map.put(LuceneConstants.CORE_NEWSSOURCE, textField6.getText());

				if (textField7.getText() != null && textField7.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_ID, textField7.getText());
				if (textField8.getText() != null && textField8.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_PARENTID, textField8.getText());
				if (textField9.getText() != null && textField9.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_COMMENTER, textField9.getText());
				if (textField10.getText() != null && textField10.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_LOCATION, textField10.getText());
				if (textField11.getText() != null && textField11.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_DATE, textField11.getText());
				if (textField12.getText() != null && textField12.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_LIKECOMMENT, textField12.getText());
				if (textField13.getText() != null && textField13.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_DISLIKECOMMENT, textField13.getText());
				if (textField14.getText() != null && textField14.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_RESPONSECOUNT, textField14.getText());
				if (textField15.getText() != null && textField15.getText().length() != 0)
					map.put(LuceneConstants.COMMENT_BODY, textField15.getText());

				if (textField16.getText() != null && textField16.getText().length() != 0)
					map.put(LuceneConstants.CATEGORY_NEWS, textField16.getText());
				if (textField17.getText() != null && textField17.getText().length() != 0)
					map.put(LuceneConstants.LABEL_NEWS, textField17.getText());

				boolean operatorIsAnd = false;
				if (textField18.getText() != null && textField18.getText().length() != 0)
					if (textField18.getText().equalsIgnoreCase("AND"))
						operatorIsAnd = true;

				String searchQuery = new String("");
				try {
					searchQuery = tester.getSearchQuery(map,operatorIsAnd);
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}

				JTextArea textArea = new JTextArea(17,25);
				textArea.setMargin(new Insets(10,10,10,10));
				textArea.setText(searchQuery);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);

				JScrollPane scrollView = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

				JPanel buttonPanel = new JPanel(new FlowLayout());
				JButton searchQueryButton = new JButton("Search Query");

				final String queryToSearch = new String(searchQuery);

				searchQueryButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							tester.search(queryToSearch, view);
						} catch (IOException | ParseException e1) {
							e1.printStackTrace();
						}

						editFrame.dispose();
					}          
				});

				buttonPanel.add(searchQueryButton);

				JPanel textAreaPanel = new JPanel(new FlowLayout());
				textAreaPanel.add(scrollView);

				editFrame.add(textAreaPanel);
				editFrame.add(buttonPanel);
				editFrame.setVisible(true);
			}          
		});

		panel19.add(searchButton);
		panel19.add(clearButton);
		panel19.add(editButton);


		frame.add(panel0);
		frame.add(emptyPanel1);
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
		frame.add(panel5);
		frame.add(panel6);
		frame.add(panel7);
		frame.add(panel8);
		frame.add(panel9);
		frame.add(panel10);
		frame.add(panel11);
		frame.add(panel12);
		frame.add(panel13);
		frame.add(panel14);
		frame.add(panel15);
		frame.add(panel16);
		frame.add(panel17);
		frame.add(panel18);
		frame.add(emptyPanel2);
		frame.add(panel19);

		frame.setVisible(true);

		try {
			tester.createIndex(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void changeHeaderMessage(String message) {

		headerLabel.setText(message);
	}

	public void showResult(ArrayList<Document> documents) {

		final JFrame resultFrame = new JFrame("Search Result");
		resultFrame.setSize(1000, 800);
		resultFrame.setLocationRelativeTo(null);
		resultFrame.setLayout(new GridLayout(1,1));
		resultFrame.setResizable(false);

		JPanel panel = new JPanel(new GridLayout(documents.size(),1));
		JScrollPane scrollView = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		for (final Document doc: documents) {

			JPanel resPanel = new JPanel(new BorderLayout());
			JPanel titlePanel = new JPanel(new GridLayout(2,1));
			JLabel resTitleLabel = new JLabel(doc.get(LuceneConstants.CORE_NEWSTITLE), JLabel.CENTER);
			JLabel resURLLabel = new JLabel(doc.get(LuceneConstants.CORE_NEWSURL), JLabel.CENTER);
			titlePanel.add(resTitleLabel);
			titlePanel.add(resURLLabel);

			JButton openURLButton = new JButton("OpenURL");        

			openURLButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if(Desktop.isDesktopSupported()){
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.browse(new URI(doc.get(LuceneConstants.CORE_NEWSURL)));
						} catch (IOException | URISyntaxException e1) {
							e1.printStackTrace();
						}
					}else{
						Runtime runtime = Runtime.getRuntime();
						try {
							runtime.exec("xdg-open " + doc.get(LuceneConstants.CORE_NEWSURL));
						} catch (IOException e2) {
							e2.printStackTrace();
						} 
					}
				}
			});

			resPanel.add(titlePanel, BorderLayout.CENTER);
			resPanel.add(openURLButton, BorderLayout.EAST);
			panel.add(resPanel);
		}

		resultFrame.add(scrollView);


		resultFrame.setVisible(true);
	}

	public void enableButtons(boolean status) {

		searchButton.setEnabled(status);
		editButton.setEnabled(status);
	}
	
	
	/*
	 * Implementation of CommonUI Abstract Class
	 * Note: All the details and query types are implemented within the previous section.
	 * In addition, as Dr. Neshati said I implement the CommonUI Abstract Implementation.
	 */
	
    // this function remove the old Index if exists
    public void cleanIndex() {
    
    	try {
			FileUtils.deleteDirectory(new File(LuceneConstants.INDEX_DIRECTORY));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    // this function create the Index and put it in indexPath
    public void makeIndex(String indexPath, String corePath,String labelPath, String categoryPath, String commentPath) {
    	
    }

    // this function return list of URLs by search in title of the news
    public ArrayList<String> getURLListByTitleSearch(String query) {
    	
    	ArrayList<String> urlsArray = new ArrayList<String>();
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put(LuceneConstants.CORE_NEWSTITLE, query);
    	
		final LuceneTester tester;
		tester = new LuceneTester();

		ArrayList<String> results = new ArrayList<String>();
		try {
			results = tester.searchCombinedQuery(map, true, LuceneConstants.CORE_NEWSURL);
			for (String string : results) 
				System.out.println("NEWS URL: " + string);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}

		urlsArray = results;
    	return urlsArray;
    }

    // this function return list of URLs by search in title of the news
    public ArrayList<String> getURLListByBodySearch(String query) {

    	ArrayList<String> urlsArray = new ArrayList<String>();

    	Map<String, String> map = new HashMap<String, String>();
    	map.put(LuceneConstants.CORE_NEWSBODY, query);
    	
		final LuceneTester tester;
		tester = new LuceneTester();

		ArrayList<String> results = new ArrayList<String>();
		try {
			results = tester.searchCombinedQuery(map, true, LuceneConstants.CORE_NEWSURL);
			for (String string : results) 
				System.out.println("NEWS URL: " + string);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
    	
		urlsArray = results;
    	return urlsArray;
    }

    // this function return list of URLs by search in body of the news and Date filter on News
    public ArrayList<String> getURLListByBodySearchAndDateRange(String query, Date start, Date end) {
    	
    	ArrayList<String> urlsArray = new ArrayList<String>();
    	
    	DateFormat df = new SimpleDateFormat("MM/dd/yy");

    	String startDateString = df.format(start);
    	String endDateString = df.format(end);
    	
    	String dateQuery = new String("["+startDateString+" TO "+endDateString+"]");
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put(LuceneConstants.CORE_NEWSBODY, query);
    	map.put(LuceneConstants.CORE_NEWSDATE, dateQuery);
    	
		final LuceneTester tester;
		tester = new LuceneTester();

		ArrayList<String> results = new ArrayList<String>();
		try {
			results = tester.searchCombinedQuery(map, true, LuceneConstants.CORE_NEWSURL);
			for (String string : results) 
				System.out.println("NEWS URL: " + string);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
    	
		urlsArray = results;
    	return urlsArray;
    }

    // this function return id of comments by search in body of the comment and Date filter on News
    public ArrayList<String> getCommentIDByCommentBodySearchAndDateRange(String query, Date start, Date end) {
    	
    	ArrayList<String> commentIDsArray = new ArrayList<String>();
    	
    	DateFormat df = new SimpleDateFormat("MM/dd/yy");

    	String startDateString = df.format(start);
    	String endDateString = df.format(end);
    	
    	String dateQuery = new String("["+startDateString+" TO "+endDateString+"]");
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put(LuceneConstants.COMMENT_BODY, query);
    	map.put(LuceneConstants.CORE_NEWSDATE, dateQuery);
    	
		final LuceneTester tester;
		tester = new LuceneTester();

		ArrayList<String> results = new ArrayList<String>();
		try {
			results = tester.searchCombinedQuery(map, true, LuceneConstants.COMMENT_ID);
			for (String string : results) 
				System.out.println("COMMENT ID: " + string);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
    
		commentIDsArray = results;
    	return commentIDsArray;
    }

    // this function return list of URLS by search in body of the comment and Date filter on Comments
    public ArrayList<String> getURLListByCommentBodySearchAndDateRange(String query, Date start, Date end) {
    	
    	ArrayList<String> urlsArray = new ArrayList<String>();

    	DateFormat df = new SimpleDateFormat("MM/dd/yy");

    	String startDateString = df.format(start);
    	String endDateString = df.format(end);
    	
    	String dateQuery = new String("["+startDateString+" TO "+endDateString+"]");
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put(LuceneConstants.COMMENT_BODY, query);
    	map.put(LuceneConstants.COMMENT_DATE, dateQuery);
    	
		final LuceneTester tester;
		tester = new LuceneTester();

		ArrayList<String> results = new ArrayList<String>();
		try {
			results = tester.searchCombinedQuery(map, true, LuceneConstants.CORE_NEWSURL);
			for (String string : results) 
				System.out.println("NEWS URL: " + string);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
    	
		urlsArray = results;
    	return urlsArray;
    }

    // List of URLS by label
    public ArrayList<String> getURLListByLabel(ArrayList<String> labels) {
    	
    	ArrayList<String> urlsArray = new ArrayList<String>();

		final LuceneTester tester;
		tester = new LuceneTester();

		ArrayList<String> results = new ArrayList<String>();
		try {
			results = tester.searchCombinedQuery(labels, LuceneConstants.LABEL_NEWS, true, LuceneConstants.CORE_NEWSURL);
			for (String string : results) 
				System.out.println("NEWS URL: " + string);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
    	
		urlsArray = results;
    	return urlsArray;
    }

    // search Comments by commenter Wild card query should be supported
    public ArrayList<String> getCommentIDByCommneterWildCardQuery(String wildCardQuery) {
    	
    	ArrayList<String> commentIDsArray = new ArrayList<String>();
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put(LuceneConstants.COMMENT_COMMENTER, wildCardQuery);
    	
		final LuceneTester tester;
		tester = new LuceneTester();

		ArrayList<String> results = new ArrayList<String>();
		try {
			results = tester.searchCombinedQuery(map, true, LuceneConstants.CORE_NEWSURL);
			for (String string : results) 
				System.out.println("NEWS URL: " + string);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
    	
		commentIDsArray = results;
    	return commentIDsArray;
    }

    // return URL list by applying filter on
    // 1) labels (if labels is null then the function ignores the label of news)
    // 2) categories (if categories is null then the function ignores the category of news)
    // 3) newsBodyQuery (if NewsbodyQuery is null then the function ignores the body filtering
    // 4) commentBodyQuery (if NewsbodyQuery is null then the function ignores the body filtering
    // 5) startNewsDate (if startNewsDate is null then the function return all urls with publish date before than endNewsDate
    // 6) endNewsDate (if endNewsDate is null then the function return all urls with publish date after than endNewsDate
    // 7) if all filters are null the function should throw an Exception

    public ArrayList<String> getURLListByCommpoundSearch(ArrayList<String> labels, ArrayList<String> categories, String NewsbodyQuery, String commentBodyQuery, Date startNewsDate, Date endNewsDate) {
    	
    	ArrayList<String> urlsArray = new ArrayList<String>();
    	
		final LuceneTester tester;
		tester = new LuceneTester();

		ArrayList<String> results = new ArrayList<String>();
		try {
			results = tester.searchCombinedQuery(labels, categories, NewsbodyQuery, commentBodyQuery, startNewsDate, endNewsDate, true, LuceneConstants.CORE_NEWSURL);
			for (String string : results) 
				System.out.println("NEWS URL: " + string);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
    	
		urlsArray = results;
    	return urlsArray;
    }

}
