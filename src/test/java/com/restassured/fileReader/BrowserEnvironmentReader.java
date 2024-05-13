package com.restassured.fileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.restassured.utils.Utility;

public class BrowserEnvironmentReader extends XMLReader
{
	private static Map<String, List<String>> enviromentMap;
	
	public BrowserEnvironmentReader()
	{
		readFile();
	}
	
	public Map<String, List<String>> getBrowserEnvironment() {
		return enviromentMap;
	}
	
	private void readFile()
	{
		System.out.println("#### Reading QA Environment ####");
		try {
			XMLEventReader eventReader = getXMLEventReader(
					System.getProperty("user.dir") + "//config//browserconfiguration.xml");
			this.enviromentMap = getEnvironmentList(eventReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Map<String, List<String>> getEnvironmentList(XMLEventReader eventReader) {
		Map<String, List<String>> envMap = new HashMap<String, List<String>>();
		List<String> headers = new ArrayList<String>();
		Boolean executionStatus = false;
		try
		{
			while(eventReader.hasNext())
			{
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) 
				{
					StartElement startElement = event.asStartElement();					
					if(startElement.getName().getLocalPart().equals("Environments"))
					{
						while(eventReader.hasNext())
						{
							event = eventReader.nextEvent();
							if (event.isStartElement()) 
							{
								startElement = event.asStartElement();
								if(startElement.getName().getLocalPart().equals("QAEnvironment"))
								{
									Iterator<Attribute> attributes = startElement.getAttributes();
									
									while(attributes.hasNext()) {
										Attribute attribute = attributes.next();
										if (attribute.getName().toString().equals("testingExecutionStatus")) 
										{
											executionStatus = Boolean.parseBoolean(attribute.getValue());
											if (executionStatus.equals(Boolean.TRUE))
											{
												while(eventReader.hasNext())
												{
													event = eventReader.nextEvent();										
													if(event.isStartElement()) 
													{
														startElement = event.asStartElement();
														if(startElement.getName().getLocalPart().equals("BrowserName"))
														{
															event = eventReader.nextEvent();
															envMap.put("BrowserName", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
															
														}
														if(startElement.getName().getLocalPart().equals("BrowserPath"))
														{
															event = eventReader.nextEvent();
															envMap.put("BrowserPath", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
															
														}
														if(startElement.getName().getLocalPart().equals("WindowMaximize"))
														{
															event = eventReader.nextEvent();
															envMap.put("WindowMaximize", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
															
														}
														if(startElement.getName().getLocalPart().equals("URL"))
														{
															event = eventReader.nextEvent();
															envMap.put("URL", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
														}
														if(startElement.getName().getLocalPart().equals("ImplicitWait"))
														{
															event = eventReader.nextEvent();
															envMap.put("ImplicitWait", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
														}
														if(startElement.getName().getLocalPart().equals("TestDataFilePath"))
														{
															event = eventReader.nextEvent();
															envMap.put("TestDataFilePath", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
															
														}
													}
													if(event.isEndElement())
													{
														EndElement endElement = event.asEndElement();
														if(endElement.getName().getLocalPart().equals("QAEnvironment"))
														{
															break;
														}
													}
												}
											}
												
										}
									}
								}
							}
						}
					}
				}
			}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return envMap;
	}
	
	public static void main(String[] args)
	{
		System.out.println(new BrowserEnvironmentReader().getBrowserEnvironment());
	}
}
