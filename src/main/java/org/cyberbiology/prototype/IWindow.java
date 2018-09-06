package org.cyberbiology.prototype;

import org.cyberbiology.prototype.view.IRenderer;
import org.cyberbiology.util.ProjectProperties;

/**
 * @deprecated
 */
public interface IWindow
{

	void paint();

	void setRenderer(IRenderer view);

	ProjectProperties getProperties();

}
