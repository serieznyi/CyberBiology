package org.cyberbiology.gene;

import org.cyberbiology.prototype.gene.ABotGene;
import org.cyberbiology.prototype.IBot;

/**
//..............   съесть в относительном напралении       ...............
            if (command == 28) {
                int drct = botGetParam(this) % 8;       // вычисляем направление из следующего за командой байта
                botIndirectIncCmdAddress(this, botEat(this, drct, 0)); // меняем адрес текущей команды
                // в зависимости от того, что было в этом направлении
                //смещение условного перехода  стена - 2 пусто - 3 органика - 4 живой - 5
                break;  // выходим, так как команда шагнуть - завершающая
            }
 * @author Nickolay
 *
 */
public class GeneEatRelativeDirection extends ABotGene
{

	@Override
	public boolean onGene(IBot bot)
	{
        int drct = bot.getParam() % 8;       // вычисляем направление из следующего за командой байта
        bot.indirectIncCmdAddress(bot.eat(drct, 0)); // меняем адрес текущей команды
        // в зависимости от того, что было в этом направлении
        //смещение условного перехода  стена - 2 пусто - 3 органика - 4 живой - 5
        return true;// выходим, так как команда шагнуть - завершающая
	}
	public String getDescription(IBot bot, int i)
	{
		return "съесть в относительном напралении";
	}
}