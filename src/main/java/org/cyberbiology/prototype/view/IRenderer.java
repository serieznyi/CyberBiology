package org.cyberbiology.prototype.view;

import org.cyberbiology.World;

import javax.swing.*;
import java.awt.*;

public interface IRenderer
{
	Image render(World world, JPanel canvas);

	String getName();
}
