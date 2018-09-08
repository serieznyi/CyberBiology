package org.cyberbiology_old.prototype;

import org.cyberbiology_old.Bot;

public interface IWorld
{
	int getWidth();

	int getHeight();

	void setSize(int width, int height);

	void addBot(Bot bot);

	Bot[][] getWorldArray();

	void restoreLinks();

}
