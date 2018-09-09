package org.cyberbiology_old.prototype.view;

import javafx.scene.canvas.GraphicsContext;
import org.cyberbiology_old.World;

public interface IRenderer
{
	void render(World world, GraphicsContext graphicsContext);

	String getName();
}
