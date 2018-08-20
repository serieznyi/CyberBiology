package cyberbiology.prototype;

import cyberbiology.prototype.view.IView;
import cyberbiology.util.ProjectProperties;

public interface IWindow
{

	public void paint();

	public void setView(IView view);

	public ProjectProperties getProperties();

}
