package org.cyberbiology_old.prototype.view;

import org.cyberbiology_old.World;

import javax.swing.*;
import java.awt.*;

public interface IRenderer
{
	Image render(World world, JPanel canvas);

	String getName();
}
