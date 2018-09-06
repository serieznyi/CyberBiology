package org.cyberbiology_old.prototype;

import org.cyberbiology_old.Bot;
import org.cyberbiology_old.util.ProjectProperties;

public interface IWorld
{
	int getWidth();

	int getHeight();

	void setSize(int width, int height);

	void addBot(Bot bot);

	void paint();

	ProjectProperties getProperties();

	Bot[][] getWorldArray();

	void restoreLinks();

}
