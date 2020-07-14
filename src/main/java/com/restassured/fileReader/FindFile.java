package com.restassured.fileReader;

import java.io.File;

public class FindFile 
{
	private static String filepath;
	
	public FindFile(String filename, File file)
	{
		FindFilePath(filename, file);
	}
	
	public String getFilePath()
	{
		return filepath;
	}
	
	protected void FindFilePath(String filename, File file)
	{
		File[] list = file.listFiles();
		if(list!=null)
		{
			for(File fil : list)
			{
				if(fil.isDirectory())
				{
					FindFilePath(filename, fil);
				}
				else if(filename.equalsIgnoreCase(fil.getName()))
				{
					filepath = fil.getPath();
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) 
	{
		System.out.println(System.getProperty("user.dir"));
		System.out.println(new FindFile("AllDeleteMethodScenarios.feature", new File(System.getProperty("user.dir"))).getFilePath());
	}
}
