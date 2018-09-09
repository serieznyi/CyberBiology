package org.cyberbiology_old.event.listener;

import org.cyberbiology_old.World;

@FunctionalInterface
public interface AfterStepEventListener {
    public void run(World world);
}
