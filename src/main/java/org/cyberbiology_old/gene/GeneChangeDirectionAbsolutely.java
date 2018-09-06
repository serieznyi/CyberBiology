package org.cyberbiology_old.gene;

import org.cyberbiology_old.prototype.IBot;
import org.cyberbiology_old.prototype.gene.ABotGene;

/**
//...............  сменить направление абсолютно   ....
            if (command == 24) {                // записываем новое значение направления
                direction = botGetParam(this) % 8;  // берем следующий байт и вычисляем остаток от деления на 8
                botIncCommandAddress(this, 2);                  // адрес текущей команды увеличивается на 2,
            }
 * @author Nickolay
 *
 */
public class GeneChangeDirectionAbsolutely extends ABotGene
{

	@Override
	public boolean onGene(IBot bot)
	{
		// записываем новое значение направления
		bot.setDirection(bot.getParam() % 8);  // берем следующий байт и вычисляем остаток от деления на 8
        bot.incCommandAddress(2);                  // адрес текущей команды увеличивается на 2,
        
        return false;
	}
	public String getDescription(IBot bot, int i)
	{
		return "сменить направление абсолютно";
	}
}
