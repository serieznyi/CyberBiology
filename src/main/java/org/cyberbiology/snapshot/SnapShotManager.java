package org.cyberbiology.snapshot;

import org.cyberbiology.Bot;
import org.cyberbiology.prototype.IWorld;
import org.cyberbiology.util.ProjectProperties;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**

* @author Nickolay
*
*/
public class SnapShotManager implements ISnapShotManager
{
	private static final int VERSION = 0;
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
	private final String outputDirectory;
	private IWorld world;

	public SnapShotManager(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	/**
	 * Метод вызывается отдельным потоком 
	 */
	@Override
	public void makeSnapShot(IWorld world)
	{
		this.world = world;

		try
		{
			new File(this.outputDirectory).mkdirs();
			
			//Создаем временный файл с данными
			String fileName = formatter.format(new Date())+"";
			File file = new File(this.outputDirectory + fileName + ".frame.cb.zip");
			ZipOutputStream fileout	= new ZipOutputStream(new FileOutputStream(file));
			fileout.putNextEntry(new ZipEntry("data"));
			
			DataOutputStream out = new DataOutputStream(fileout);;
			
			// Версия 
			out.writeInt(getVersion());
			int width	= getWorld().getWidth();

			// Ширина мира
			out.writeInt(width);
			int height	= getWorld().getHeight();
			// Высота мира
			out.writeInt(height);
			
			Frame frame = new Frame();
			Bot[][] w	= this.getWorld().getWorldArray();
	        for (int y = 0; y < height; y++)
	        {
	            for (int x = 0; x < width; x++)
	            {
	            	if (w[x][y] != null)
	            	{
	            		frame.addBot(w[x][y], x, y);
	            	}
	            }
	        }
			frame.save(out);
			out.writeInt(0);// Следующий фрейм размером 0 - больше данных нет, конец записи
			
			fileout.closeEntry();
			out.close();
			
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private IWorld getWorld()
	{
		return this.world;
	}

	private int getVersion()
	{
		return VERSION;
	}
}
