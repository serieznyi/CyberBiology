package org.cyberbiology.prototype;

import org.cyberbiology.Bot;
import org.cyberbiology.util.ProjectProperties;

public interface IWorld
{

	public int getWidth();

	public int getHeight();

	public void setSize(int width, int height);

	public void setBot(Bot bot);

	public void paint();

	public ProjectProperties getProperties();

	public Bot[][] getWorldArray();

	public void restoreLinks();

}
