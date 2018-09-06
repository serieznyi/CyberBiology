package org.cyberbiology;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.cyberbiology.prototype.IWindow;
import org.cyberbiology.prototype.view.IRenderer;

public class ViewMenuActionListener implements ActionListener
{
	IWindow window;
	IRenderer renderer;
	public ViewMenuActionListener(IWindow window, IRenderer rend)
	{
		this.window		= window;
		this.renderer = rend;
	}
    public void actionPerformed(ActionEvent e)
    {
    	this.window.setRenderer(this.renderer);
    }    
}
