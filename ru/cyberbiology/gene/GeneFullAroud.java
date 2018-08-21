package cyberbiology.gene;

import cyberbiology.prototype.IBot;
import cyberbiology.prototype.gene.ABotGene;

/**
//****************************************************
//...............  окружен ли бот    ................
            if (command == 43) {   // функция full_aroud() возвращает  1, если бот окружен и 2, если нет
                // увеличиваем значение указателя текущей команды
                // на значение следующего байта после команды или 2-го байта после команды
                // в зависимости от того, окружен бот или нет
                botIndirectIncCmdAddress(this, fullAround(this));
            }

 * @author Nickolay
 *
 */
public class GeneFullAroud extends ABotGene
{

	@Override
	public boolean onGene(IBot bot)
	{
		// функция full_aroud() возвращает  1, если бот окружен и 2, если нет
        // увеличиваем значение указателя текущей команды
        // на значение следующего байта после команды или 2-го байта после команды
        // в зависимости от того, окружен бот или нет
        bot.indirectIncCmdAddress(bot.fullAround());
        return false;
	}
	public String getDescription(IBot bot, int i)
	{
		return "окружен ли бот";
	}
}
