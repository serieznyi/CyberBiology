package org.cyberbiology.prototype.view;

import java.awt.Image;

import javax.swing.JPanel;

import org.cyberbiology.World;

public interface IView
{
	Image paint(World world, JPanel canvas);

	String getName();
}
