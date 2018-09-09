package org.cyberbiology.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ProjectProperties extends Properties
{
	private String propertiesFile;

	public ProjectProperties(String propertiesFile)
	{
		this.propertiesFile = propertiesFile;

		// TODO позже убрать
		this.load();
	}

    public void setOutputDirectory(String name)
	{
		if(name==null) {
			name="";
		}

		if(name.length()>0&&!name.endsWith(File.separator)) {
			name+=File.separator;
		}

    	this.setProperty("OutputDirectory", name);

    	this.save();
	}

    public String getOutputDirectory()
	{
    	return this.getProperty("OutputDirectory");
	}

	public void load()
	{
		try {
			this.loadFromXML(new FileInputStream(this.propertiesFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void save()
	{
    	try {
			this.storeToXML(new FileOutputStream(this.propertiesFile), null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}