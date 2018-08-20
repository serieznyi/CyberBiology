package cyberbiology.test.prototype;

import cyberbiology.test.Bot;
import cyberbiology.test.util.ProjectProperties;

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
