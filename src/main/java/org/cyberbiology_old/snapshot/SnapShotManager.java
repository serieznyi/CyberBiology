package org.cyberbiology_old.snapshot;

import org.cyberbiology_old.Bot;
import org.cyberbiology_old.prototype.IWorld;

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
	private IWorld world;

	/**
	 * Метод вызывается отдельным потоком 
	 */
	@Override
	public void makeSnapShot(IWorld world)
	{
		this.world = world;

		try
		{
			String dirName	= getWorld().getProperties().getFileDirectory();

			new File(dirName).mkdirs();
			
			//Создаем временный файл с данными
			String fileName = formatter.format(new Date())+"";
			File file = new File(dirName + fileName + ".frame.cb.zip");
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
