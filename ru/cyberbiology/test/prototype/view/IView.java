package cyberbiology.test.prototype.view;

import java.awt.Image;

import javax.swing.JPanel;

import cyberbiology.test.World;

public interface IView
{
	public Image paint(World world,JPanel canvas);

	public String getName();
}
