package cyberbiology.prototype.view;

import cyberbiology.World;

import javax.swing.*;
import java.awt.*;

public interface IRenderer
{
	Image render(World world, JPanel canvas);

	String getName();
}
