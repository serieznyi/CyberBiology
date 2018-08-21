package cyberbiology.prototype;

import cyberbiology.prototype.view.IRenderer;
import cyberbiology.util.ProjectProperties;

/**
 * @deprecated
 */
public interface IWindow
{

	public void paint();

	public void setRenderer(IRenderer view);

	public ProjectProperties getProperties();

}
