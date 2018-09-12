package org.cyberbiology.render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.cyberbiology.Bot;
import org.cyberbiology.World;
import org.cyberbiology.prototype.view.IRenderer;

public class BasicRenderer implements IRenderer
{
	public String getName()
	{
		return "Базовое";
	}

    public void render(World world, GraphicsContext graphicsContext) {
        graphicsContext.fillRect(0, 0, world.width * World.BOT_WIDTH + 1, world.height * World.BOT_HEIGHT + 1);

        for (int y = 0; y < world.height; y++) {
            for (int x = 0; x < world.width; x++) {
                Bot bot = world.matrix[x][y];

                if (bot == null) {
                    graphicsContext.setFill(Color.WHITE);
                    graphicsContext.fillRect(x * World.BOT_WIDTH,y * World.BOT_HEIGHT, World.BOT_WIDTH, World.BOT_HEIGHT);
                } else if (bot.alive == 1 || bot.alive == 2) {
                    graphicsContext.setFill(Color.rgb(200, 200, 200));
                    graphicsContext.fillRect(x * World.BOT_WIDTH, y * World.BOT_HEIGHT, World.BOT_WIDTH, World.BOT_HEIGHT);
                    world.organic = world.organic + 1;
                } else if (bot.alive == 3) {
                    graphicsContext.setFill(Color.BLACK);
                    graphicsContext.fillRect(x * World.BOT_WIDTH, y * World.BOT_HEIGHT, World.BOT_WIDTH, World.BOT_HEIGHT);

                    Color botColor = this.calculateBotColor(bot);

                    graphicsContext.setFill(botColor);

                    graphicsContext.fillRect(x * World.BOT_WIDTH + 1, y * World.BOT_HEIGHT + 1,World.BOT_WIDTH -1, World.BOT_HEIGHT -1);
                }
            }
        }
    }

    private Color calculateBotColor(Bot bot) {
        int green = bot.c_green - ((bot.c_green * bot.health) / 2000);

        if (green < 0)  {
            green = 0;
        }

        if (green > 255) {
            green = 255;
        }

        int blue = (int) (bot.c_blue * 0.8 - ((bot.c_blue * bot.mineral) / 2000));

        if (blue < 0)  {
            blue = 0;
        }

        if (blue > 255) {
            blue = 255;
        }

        return Color.rgb(bot.c_red, green, blue);
    }
}
