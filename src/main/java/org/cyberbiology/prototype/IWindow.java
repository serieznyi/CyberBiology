package org.cyberbiology.prototype;

import org.cyberbiology.prototype.view.IView;
import org.cyberbiology.util.ProjectProperties;

public interface IWindow
{

	public void paint();

	public void setView(IView view);

	public ProjectProperties getProperties();

}
