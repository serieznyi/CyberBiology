package org.cyberbiology.gene;

import org.cyberbiology.prototype.IBot;
import org.cyberbiology.prototype.gene.ABotGeneController;

/**
//**********************************************************************
//.................. преобразовать минералы в энерию ...................
            if (command == 47) {
                botMineral2Energy(this);
                botIncCommandAddress(this, 1);
                break;      // выходим, так как команда - завершающая
            }
 * @author Nickolay
 *
 */
public class GeneMineralToEnergy extends ABotGeneController
{

	@Override
	public boolean onGene(IBot bot)
	{
        bot.mineral2Energy();
        bot.incCommandAddress(1);
        return true;
	}
	@Override
	public String getDescription(IBot bot, int i)
	{
		return "преобразовать минералы в энерию";
	}
}
