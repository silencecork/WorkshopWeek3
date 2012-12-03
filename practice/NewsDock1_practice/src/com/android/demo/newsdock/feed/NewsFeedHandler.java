package com.android.demo.newsdock.feed;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class NewsFeedHandler extends DefaultHandler {

	private boolean inTitle;
	private boolean inItem;
	private boolean inLink;
	private boolean inCategory;
	private boolean inPubDate;
	private boolean inDesc;
	
	private ArrayList<News> list = new ArrayList<News>();
	private News currentNews;
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if (inItem && currentNews != null) {
			if (inTitle) {
				String str = new String(ch, start, length);
				if (currentNews.title == null) {
					currentNews.title = "";
				}
				currentNews.title += str;
			} else if (inLink) {
				String str = new String(ch, start, length);
				if (currentNews.link == null) {
					currentNews.link = "";
				}
				currentNews.link += str;
			} else if (inCategory) {
				String category = new String(ch, start, length);
				if (currentNews.category == null) {
					currentNews.category = "";
				}
				currentNews.category += category;
			} else if (inPubDate) {
				String pubDate = new String(ch, start, length);
				if (currentNews.publishDate == null) {
					currentNews.publishDate = "";
				}
				currentNews.publishDate += pubDate;
			} else if (inDesc) {
				String description = new String(ch, start, length);
				if (currentNews.description == null) {
					currentNews.description = "";
				}
				currentNews.description += description;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if (localName.equals("item")) {
			inItem = false;
			list.add(currentNews);
			currentNews = null;
		}
		if (inItem) {
			if (localName.equals("title")) {
				inTitle = false;
			} else if (localName.equals("link")) {
				inLink = false;
			} else if (localName.equals("category")) {
				inCategory = false;
			} else if (localName.equals("pubDate")) {
				inPubDate = false;
			} else if (localName.equals("description")) {
				inDesc = false;
			}
		}
		
		
		//System.out.println("endElement: uri: " + uri + ", localName: " + localName + ", qName " + qName);
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		if (localName.equals("item")) {
			inItem = true;
			currentNews = new News();
		}
		if (inItem) {
			if (localName.equals("title")) {
				inTitle = true;
			} else if (localName.equals("link")) {
				inLink = true;
			} else if (localName.equals("category")) {
				inCategory = true;
			} else if (localName.equals("pubDate")) {
				inPubDate = true;
			} else if (localName.equals("description")) {
				inDesc = true;
			}
		}
		
		
		//System.out.println("startElement: uri: " + uri + ", localName: " + localName + ", qName " + qName);
	}
	
	public void processFeed(URL url) {
        try {
        	
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            xr.setContentHandler(this);
            xr.parse(new InputSource(url.openStream()));
               
        } catch (IOException e) {
            Log.e("", e.toString());
        } catch (SAXException e) {
            Log.e("", e.toString());
        } catch (ParserConfigurationException e) {
            Log.e("", e.toString());
        }
	}
	
	public News[] getNews() {
		if (list != null && list.size() > 0) {
			News []feeds = new News[list.size()];
			list.toArray(feeds);
			return feeds;
		}
		return null;
	}

}
