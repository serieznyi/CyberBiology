package org.cyberbiology_old.gene;

import org.cyberbiology_old.prototype.IBot;
import org.cyberbiology_old.prototype.gene.ABotGene;

/********************************************************************
//............... минералы прибавляются? ............................
            if (command == 45) {  // если глубина больше половины, то он начинает накапливать минералы
                if (y > (World.simulation.height / 2)) {
                    botIndirectIncCmdAddress(this, 1);
                } else {
                    botIndirectIncCmdAddress(this, 2);
                }
            }

 * @author Nickolay
 *
 */
public class GeneIsMineralGrow extends ABotGene
{

	@Override
	public boolean onGene(IBot bot)
	{
        if (bot.getY() > (bot.getWorld().getHeight() / 2)) {
            bot.indirectIncCmdAddress(1);
        } else {
            bot.indirectIncCmdAddress(2);
        }
        return false;
	}
	@Override
	public String getDescription(IBot bot, int i)
	{
		return "минералы прибавляются?";
	}
}
