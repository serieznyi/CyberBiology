package cyberbiology.gene;

import cyberbiology.prototype.IBot;
import cyberbiology.prototype.gene.ABotGene;

/**
//**********************************************
//...............  фотосинтез ................
            if (command == 25) {
                botEatSun(this);                            // выполняем команду фотосинтеза
                botIncCommandAddress(this, 1);              // адрес текущей команды увеличивается на 1
                break;         // выходим, так как команда шагнуть - завершающая
            }
 * @author Nickolay
 *
 */
public class GenePhotosynthesis extends ABotGene
{

	@Override
	public boolean onGene(IBot bot)
	{
		bot.eatSun(); // выполняем команду фотосинтеза
        bot.incCommandAddress(1);                              // адрес текущей команды увеличивается на 1,
        
        return true;// выходим, так как команда фотосинтез - завершающая
	}
	@Override
	public String getDescription(IBot bot, int i)
	{
		return "фотосинтез";
	}
}
