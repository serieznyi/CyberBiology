package org.cyberbiology.listener;

import org.cyberbiology.World;

@FunctionalInterface
public interface AfterStepEventListener {
    public void run(World world);
}
