package org.cyberbiology.prototype;

import org.cyberbiology.Bot;

public interface IWorld
{
	int getWidth();

	int getHeight();

	void setSize(int width, int height);

	void addBot(Bot bot);

	Bot[][] getWorldArray();

	void restoreLinks();

}
