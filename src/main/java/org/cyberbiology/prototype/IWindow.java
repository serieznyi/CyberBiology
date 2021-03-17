package org.cyberbiology.prototype;

import org.cyberbiology.prototype.view.IView;
import org.cyberbiology.util.ProjectProperties;

public interface IWindow
{

	void paint();

	void setView(IView view);

	ProjectProperties getProperties();

}
