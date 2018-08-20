package cyberbiology.test.prototype;

import cyberbiology.test.prototype.view.IView;
import cyberbiology.test.util.ProjectProperties;

public interface IWindow
{

	public void paint();

	public void setView(IView view);

	public ProjectProperties getProperties();

}
