package com.restassured.fileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.restassured.utils.Utility;

public class RequestEnvironmentReader extends XMLReader
{
	private static Map<String, List<String>> listEnvironment;
	
	public RequestEnvironmentReader(String requestName)
	{
		readFile(requestName);
	}
	
	public Map<String, List<String>> getRequestEnvironment() {
		return listEnvironment;
	}
	
	private void readFile(String requestName)
	{
		System.out.println("#### Reading QA Environment ####");
		try {
			XMLEventReader eventReader = getXMLEventReader(
					System.getProperty("user.dir") + "//config//requestconfiguration.xml");
			this.listEnvironment = getEnvironmentList(requestName, eventReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Map<String, List<String>> getEnvironmentList(String requestName, XMLEventReader eventReader) {
		Map<String, List<String>> envMap = new HashMap<String, List<String>>();
		List<String> headers = new ArrayList<String>();
		List<String> params = new ArrayList<String>();
		try
		{
			while(eventReader.hasNext())
			{
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) 
				{
					StartElement startElement = event.asStartElement();					
					if(startElement.getName().getLocalPart().equals("request"))
					{
						while(eventReader.hasNext())
						{
							event = eventReader.nextEvent();
							if (event.isStartElement()) 
							{
								startElement = event.asStartElement();
								if(startElement.getName().getLocalPart().equals(requestName))
								{
									envMap.put("requestName", Utility.getListData(requestName));
									while(eventReader.hasNext())
									{
										event = eventReader.nextEvent();										
										if(event.isStartElement()) 
										{
											startElement = event.asStartElement();
											if(startElement.getName().getLocalPart().equals("baseUrl"))
											{
												event = eventReader.nextEvent();
												envMap.put("baseUrl", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
												
											}
											if(startElement.getName().getLocalPart().equals("endpoint"))
											{
												event = eventReader.nextEvent();
												envMap.put("endpoint", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
												
											}
											if(startElement.getName().getLocalPart().equals("method"))
											{
												event = eventReader.nextEvent();
												envMap.put("method", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
												
											}
											if(startElement.getName().getLocalPart().equals("header"))
											{
												event = eventReader.nextEvent();
												headers.add(event.isCharacters() ? event.asCharacters().getData().trim() : null);
												
											}
											if(startElement.getName().getLocalPart().equals("contentType"))
											{
												event = eventReader.nextEvent();
												envMap.put("contentType", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
											}
											if(startElement.getName().getLocalPart().equals("params"))
											{
												event = eventReader.nextEvent();
												params.add(event.isCharacters() ? event.asCharacters().getData().trim() : null);
											}
											if(startElement.getName().getLocalPart().equals("bodyContentType"))
											{
												event = eventReader.nextEvent();
												envMap.put("bodyContentType", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
											}
											if(startElement.getName().getLocalPart().equals("basicAuthUname"))
											{
												event = eventReader.nextEvent();
												envMap.put("basicAuthUname", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
											}
											if(startElement.getName().getLocalPart().equals("basicAuthPwd"))
											{
												event = eventReader.nextEvent();
												envMap.put("basicAuthPwd", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
											}
											if(startElement.getName().getLocalPart().equals("TestDataFilePath"))
											{
												event = eventReader.nextEvent();
												envMap.put("TestDataFilePath", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : null));
											}
											if(startElement.getName().getLocalPart().equals("SheetName"))
											{
												event = eventReader.nextEvent();
												envMap.put("SheetName", Utility.getListData(event.isCharacters() ? event.asCharacters().getData().trim() : ""));
											}
										}
										if(event.isEndElement())
										{
											EndElement endElement = event.asEndElement();
											if(endElement.getName().getLocalPart().equals(requestName))
											{
												break;
											}
										}
									}
									envMap.put("headers", headers);
									envMap.put("params", params);
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
		System.out.println(new RequestEnvironmentReader("ping").getRequestEnvironment());
	}
}
