package org.cyberbiology_old.gene;

import org.cyberbiology_old.prototype.gene.ABotGene;
import org.cyberbiology_old.prototype.IBot;

/**
//*********************************************************************
//...................сколько  минералов ...............................
            if (command == 39) {
                int param = botGetParam(this) * 1000 / MIND_SIZE;
                if (mineral < param) {
                    botIndirectIncCmdAddress(this, 2);
                } else {
                    botIndirectIncCmdAddress(this, 3);
                }
            }
 * @author Nickolay
 *
 */
public class GeneMyMineral extends ABotGene
{

	@Override
	public boolean onGene(IBot bot)
	{
        int param = bot.getParam() * 1000 / bot.MIND_SIZE;
        if (bot.getMineral() < param) {
            bot.indirectIncCmdAddress(2);
        } else {
            bot.indirectIncCmdAddress(3);
        }
        return false;
	}
	public String getDescription(IBot bot, int i)
	{
		return "сколько  минералов";
	}
}
