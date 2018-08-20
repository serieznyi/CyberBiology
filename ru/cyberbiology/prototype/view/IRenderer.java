package cyberbiology.prototype.view;

import java.awt.Image;

import javax.swing.JPanel;

import cyberbiology.World;

public interface IRenderer
{
	public Image render(World world, JPanel canvas);

	public String getName();
}
