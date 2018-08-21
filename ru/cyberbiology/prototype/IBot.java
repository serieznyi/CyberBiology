package cyberbiology.prototype;

public interface IBot
{
	int MIND_SIZE	= 64;

    /**
     * получение параметра для команды
     * 
     * @return возвращает число из днк, следующее за выполняемой командой
     */
	int getParam();

    /**
     * получение направления движения
     * 
     * @return возвращает число от 0 до 8 (0 вверх, 5 вниз)
     */
	int getDirection();

    /**
     * устанавливает направления движения
     * 
     * @param newDirection число от 0 до 8 (0 вверх, 5 вниз)
     */
	void setDirection(int newDirection);

    /**
     * увеличение адреса команды
     * @param increment насколько прибавить адрес
     */
	void incCommandAddress(int increment);

	/**
	 * фотосинтез
	 */
	void eatSun();
	
	/**
	 * Многоклеточный ли бот?
	 */
	int isMulti();
	
	int move(int direction, int i);
	
	void indirectIncCmdAddress(int move);
	
	int eat(int direction, int i);
	
	int seeBots(int direction, int i);
	
	int care(int direction, int i);
	
	int give(int direction, int i);
	
	int getY();
	
	int getHealth();
	
	int getMineral();
	
	void Double();
	
	void multi();
	
	int fullAround();

	int isHealthGrow();

	void mineral2Energy();

	void setMind(byte ma, byte mc);

	void genAttack();

	IWorld getWorld();

}
