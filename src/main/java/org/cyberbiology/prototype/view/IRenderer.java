package org.cyberbiology.prototype.view;

import javafx.scene.canvas.GraphicsContext;
import org.cyberbiology.World;

public interface IRenderer
{
	void render(World world, GraphicsContext graphicsContext);

	String getName();
}
