package org.cyberbiology;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import org.cyberbiology.prototype.view.IRenderer;

public class WorldHandler extends AnimationTimer {
    private World world;
    private IRenderer renderer;
    private GraphicsContext graphicsContext;
    private boolean worked = false;

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

    @Override
    public void start() {
        this.worked = true;

        super.start();
    }

    @Override
    public void stop() {
        this.worked = false;

        super.stop();
    }

    public boolean isWorked() {
        return worked;
    }
}
