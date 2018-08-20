package cyberbiology.test.gene;

import cyberbiology.test.prototype.IBot;
import cyberbiology.test.prototype.gene.ABotGeneController;

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
public class GenePhotosynthesis extends ABotGeneController
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
