package org.cyberbiology.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import org.cyberbiology.World;
import org.cyberbiology.prototype.view.IRenderer;

public class BasicRenderer implements IRenderer
{
	public String getName()
	{
		return "Базовое";
	}

    public Image render(World world, JPanel canvas) {
    	int w = canvas.getWidth();
    	int h = canvas.getHeight();
    	//Создаем временный буфер для рисования
    	Image buf = canvas.createImage(w, h);
    	//подеменяем графику на временный буфер
    	Graphics g = buf.getGraphics();
    	
        g.drawRect(0, 0, world.width * World.BOT_WIDTH + 1, world.height * World.BOT_HEIGHT + 1);

        world.population = 0;
        world.organic = 0;
        for (int y = 0; y < world.height; y++) {
            for (int x = 0; x < world.width; x++) {
                if (world.matrix[x][y] == null) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x * World.BOT_WIDTH,y * World.BOT_HEIGHT, World.BOT_WIDTH, World.BOT_HEIGHT);
                } else if ((world.matrix[x][y].alive == 1) || (world.matrix[x][y].alive == 2)) {
                    g.setColor(new Color(200, 200, 200));
                    g.fillRect(x * World.BOT_WIDTH, y * World.BOT_HEIGHT, World.BOT_WIDTH, World.BOT_HEIGHT);
                    world.organic = world.organic + 1;
                } else if (world.matrix[x][y].alive == 3) {
                    g.setColor(Color.BLACK);
                    g.drawRect(x * World.BOT_WIDTH, y * World.BOT_HEIGHT, World.BOT_WIDTH, World.BOT_HEIGHT);

//                    g.setColor(new Color(matrix[x][y].c_red, matrix[x][y].c_green, matrix[x][y].c_blue));
                    int green = (int) (world.matrix[x][y].c_green - ((world.matrix[x][y].c_green * world.matrix[x][y].health) / 2000));
                    if (green < 0) green = 0;
                    if (green > 255) green = 255;
                    int blue = (int) (world.matrix[x][y].c_blue * 0.8 - ((world.matrix[x][y].c_blue * world.matrix[x][y].mineral) / 2000));
                    g.setColor(new Color(world.matrix[x][y].c_red, green, blue));
//                    g.setColor(new Color(matrix[x][y].c_red, matrix[x][y].c_green, matrix[x][y].c_blue));
                    g.fillRect(x * World.BOT_WIDTH + 1, y * World.BOT_HEIGHT + 1,World.BOT_WIDTH -1, World.BOT_HEIGHT -1);
                    world.population = world.population + 1;
                }
            }
        }
        return buf;
    }
}
