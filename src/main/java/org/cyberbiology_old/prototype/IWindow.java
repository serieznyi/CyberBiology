package org.cyberbiology_old.prototype;

import org.cyberbiology_old.prototype.view.IRenderer;
import org.cyberbiology_old.util.ProjectProperties;

/**
 * @deprecated
 */
public interface IWindow
{

	void paint();

	void setRenderer(IRenderer view);

	ProjectProperties getProperties();

}
