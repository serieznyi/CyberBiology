package org.cyberbiology_old.prototype;

import org.cyberbiology_old.Bot;
import org.cyberbiology_old.event.listener.AfterStepEventListener;

public interface IWorld
{
	int getWidth();

	int getHeight();

	public void makeStep();

	void setSize(int width, int height);

	void addBot(Bot bot);

	Bot[][] getWorldArray();

	void restoreLinks();

	void addListener(AfterStepEventListener runnable);
}
