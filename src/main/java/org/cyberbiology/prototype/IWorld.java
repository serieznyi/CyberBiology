package org.cyberbiology.prototype;

import org.cyberbiology.Bot;
import org.cyberbiology.listener.AfterStepEventListener;

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