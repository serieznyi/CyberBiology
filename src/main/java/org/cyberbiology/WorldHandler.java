package org.cyberbiology;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import org.cyberbiology_old.World;
import org.cyberbiology_old.prototype.view.IRenderer;

public class WorldHandler extends AnimationTimer {
    private World world;
    private IRenderer renderer;
    private GraphicsContext graphicsContext;

    WorldHandler(World world, IRenderer renderer, GraphicsContext graphicsContext) {
        this.world = world;
        this.renderer = renderer;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void handle(long l) {
        this.world.makeStep();

        if (this.world.getGeneration() % 10 == 0) {
            this.renderer.render(world, this.graphicsContext);
        }
    }
}
