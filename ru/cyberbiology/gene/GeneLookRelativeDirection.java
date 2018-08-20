package cyberbiology.gene;

import cyberbiology.prototype.IBot;
import cyberbiology.prototype.gene.ABotGene;

/**
//.............   посмотреть  в относительном напралении ...................................
            if (command == 30) {
                int drct = botGetParam(this) % 8;    // вычисляем направление из следующего за командой байта
                botIndirectIncCmdAddress(this, botSeeBots(this, drct, 0)); // меняем адрес текущей команды
                // в зависимости от того, что было в этом направлении
                // пусто - 2 стена - 3 органик - 4 бот -5 родня -  6
            }
 * @author Nickolay
 *
 */
public class GeneLookRelativeDirection extends ABotGene
{

	@Override
	public boolean onGene(IBot bot)
	{
        int drct = bot.getParam() % 8;       // вычисляем направление из следующего за командой байта
        bot.indirectIncCmdAddress(bot.seeBots(drct, 0)); // меняем адрес текущей команды
        // в зависимости от того, что было в этом направлении
        // пусто - 2 стена - 3 органик - 4 бот -5 родня -  6
        return false;
	}
	@Override
	public String getDescription(IBot bot, int i)
	{
		return "посмотреть  в относительном напралении";
	}
}
