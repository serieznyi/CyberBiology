package cyberbiology.prototype;

import cyberbiology.Bot;
import cyberbiology.util.ProjectProperties;

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
