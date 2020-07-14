package com.restassured.fileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

public abstract class XMLReader{
	private String filePath;
	private static XMLReader instance;
	

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public static XMLReader getInstance() {
		return instance;
	}
	public static void setInstance(XMLReader instance) {
		XMLReader.instance = instance;
	}
	
	public XMLEventReader getXMLEventReader(String filePath) throws FileNotFoundException, XMLStreamException
	{
		
		try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(filePath);
           return inputFactory.createXMLEventReader(in);
            
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
            e.toString();
            throw new FileNotFoundException(" file path is incorrect");
        } catch (XMLStreamException e) {
        	  e.printStackTrace();
              e.toString();
              throw new XMLStreamException(" XMLStreamException");
        }
		
		
	}
}
