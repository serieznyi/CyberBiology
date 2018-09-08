package org.cyberbiology.v0;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.cyberbiology.Bot;
import org.cyberbiology.prototype.IBot;
import org.cyberbiology.prototype.IWorld;
import org.cyberbiology.prototype.record.IFrame;
import org.cyberbiology.prototype.record.IRecordManager;

/**

* @author Nickolay
*
*/
public class RecordManager  implements IRecordManager
{
	static final int VERSION	= 0;
	private static final int BOT_DATA_LENGHT	= 14+Bot.MIND_SIZE;
	IWorld world;

	private List<IFrame> frames;
	private boolean started;// флаг о том, что запись заканчивается
	private File file;
	private ArrayList<Integer> fameSizes;
	
	public RecordManager(IWorld world)
	{
		this.world = world;
		this.started = false;
		this.fameSizes	= new ArrayList<Integer>();
		frames = Collections.synchronizedList(new ArrayList());
	}
	protected IWorld getWorld()
	{
		return this.world;
	}

	protected static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

	/**
	 * Метод вызывается отдельным потоком 
	 */
	@Override
	public void makeSnapShot()
	{
		try
		{
			String dirName	= getWorld().getProperties().getFileDirectory();

			new File(dirName).mkdirs();
			
			//Создаем временный файл с данными
			String fileName = formatter.format(new Date())+"";
			file	= new File(dirName+fileName+".frame.cb.zip");
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
	class Frame implements IFrame
	{
		List<Item> list;
		public Frame()
		{
			this.list = new ArrayList<Item>();
		}
		public int save(DataOutputStream fileout) throws IOException
		{
			int length	= list.size()*BOT_DATA_LENGHT;
			fileout.writeInt(length);
			for(int i=0;i<list.size();i++)
			{
				list.get(i).save(fileout);
			}
			return length;
		}
		public void addBot(IBot bot, int x, int y)
		{
			this.list.add(new Item((Bot)bot, x, y));
		}
	}
	class Item
	{
		//int[] data;
		//Bot bot;
		
		
		byte bot_adr;
		int bot_x;
		int bot_y;
		int bot_health;
		int bot_mineral;
		byte bot_alive;
		int bot_c_red;
		int bot_c_green;
		int bot_c_blue;
		byte bot_direction;
		int bot_mprev_x;
		int bot_mprev_y;
		int bot_mnext_x;
		int bot_mnext_y;
		byte[] mind;
		
		public Item(Bot bot, int x, int y)
		{
			// жестко сохраняем все зхначения, так как к моменту сохранения кадра данные могут изменится
			bot_adr	= (byte) bot.adr;
			bot_x	= bot.x;
			bot_y	= bot.y;
			bot_health= bot.health;
			bot_mineral= bot.mineral;
			bot_alive= (byte) bot.alive;
			bot_c_red= bot.c_red;
			bot_c_green= bot.c_green;
			bot_c_blue	= bot.c_blue;
			bot_direction= (byte) bot.direction;
			
			if(bot.mprev!=null)
			{
				bot_mprev_x	= bot.mprev.x;
				bot_mprev_y	= bot.mprev.y;
			}else
			{
				bot_mprev_x	= bot_mprev_y = -1;
			}
			if(bot.mnext!=null)
			{
				bot_mnext_x	= bot.mnext.x;
				bot_mnext_y	= bot.mnext.y;
			}else
			{
				bot_mnext_x	= bot_mnext_y = -1;
			}
			mind	= new byte[bot.mind.length];
			for(int i=0;i<bot.mind.length;i++)
			{
				mind[i] = bot.mind[i];
			}
		}
		public void save(DataOutputStream fileout) throws IOException
		{
			fileout.writeByte(bot_adr);
			fileout.writeInt(bot_x);
			fileout.writeInt(bot_y);
			fileout.writeInt(bot_health);
			fileout.writeInt(bot_mineral);
			fileout.writeByte(bot_alive);
			fileout.writeInt(bot_c_red);
			fileout.writeInt(bot_c_green);
			fileout.writeInt(bot_c_blue);
			fileout.writeByte(bot_direction);
			fileout.writeInt(bot_mprev_x);
			fileout.writeInt(bot_mprev_y);
			fileout.writeInt(bot_mnext_x);
			fileout.writeInt(bot_mnext_y);
			for(int i=0;i<mind.length;i++)
			{
				fileout.writeByte(mind[i]);
			}
		}
	}

	private int getVersion()
	{
		return VERSION;
	}
}
