package cyberbiology.prototype;

import cyberbiology.Bot;
import cyberbiology.util.ProjectProperties;

public interface IWorld
{

	public int getWidth();

	public int getHeight();

	public void setSize(int width, int height);

	public void addBot(Bot bot);

	public void paint();

	public ProjectProperties getProperties();

	public Bot[][] getWorldArray();

	public void restoreLinks();

}
