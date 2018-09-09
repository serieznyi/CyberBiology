package org.cyberbiology_old.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.cyberbiology_old.World;
import org.cyberbiology_old.prototype.view.IRenderer;

public class BasicRenderer implements IRenderer
{
	public String getName()
	{
		return "Базовое";
	}

    public void render(World world, GraphicsContext graphicsContext) {
        graphicsContext.rect(0, 0, world.width * World.BOT_WIDTH + 1, world.height * World.BOT_HEIGHT + 1);

        world.population = 0;
        world.organic = 0;
        for (int y = 0; y < world.height; y++) {
            for (int x = 0; x < world.width; x++) {
                if (world.matrix[x][y] == null) {
                    graphicsContext.setFill(Color.WHITE);
                    graphicsContext.fillRect(x * World.BOT_WIDTH,y * World.BOT_HEIGHT, World.BOT_WIDTH, World.BOT_HEIGHT);
                } else if ((world.matrix[x][y].alive == 1) || (world.matrix[x][y].alive == 2)) {
                    graphicsContext.setFill(Color.rgb(200, 200, 200));
                    graphicsContext.fillRect(x * World.BOT_WIDTH, y * World.BOT_HEIGHT, World.BOT_WIDTH, World.BOT_HEIGHT);
                    world.organic = world.organic + 1;
                } else if (world.matrix[x][y].alive == 3) {
                    graphicsContext.setFill(Color.BLACK);
                    graphicsContext.rect(x * World.BOT_WIDTH, y * World.BOT_HEIGHT, World.BOT_WIDTH, World.BOT_HEIGHT);

                    int green = world.matrix[x][y].c_green - ((world.matrix[x][y].c_green * world.matrix[x][y].health) / 2000);
                    if (green < 0) green = 0;
                    if (green > 255) green = 255;
                    int blue = (int) (world.matrix[x][y].c_blue * 0.8 - ((world.matrix[x][y].c_blue * world.matrix[x][y].mineral) / 2000));
                    graphicsContext.setFill(Color.rgb(world.matrix[x][y].c_red, green, blue));
                    graphicsContext.fillRect(x * World.BOT_WIDTH + 1, y * World.BOT_HEIGHT + 1,World.BOT_WIDTH -1, World.BOT_HEIGHT -1);
                    world.population = world.population + 1;
                }
            }
        }
    }
}
