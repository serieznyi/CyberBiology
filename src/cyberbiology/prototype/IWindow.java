package cyberbiology.prototype;

import cyberbiology.prototype.view.IRenderer;
import cyberbiology.util.ProjectProperties;

/**
 * @deprecated
 */
public interface IWindow
{

	void paint();

	void setRenderer(IRenderer view);

	ProjectProperties getProperties();

}
