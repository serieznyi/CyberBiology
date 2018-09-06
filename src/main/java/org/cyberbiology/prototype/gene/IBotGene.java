package org.cyberbiology.prototype.gene;

import org.cyberbiology.prototype.IBot;

/**
 * Интерфейс обработчика гена бота
 * @author Nickolay
 *
 */
public interface IBotGene
{
	/**
	 * Реализация одного шага интерпетации гена
	 * @param bot бот, над которыым проводится процедура
	 * @return возвращает true если обработка в данной серии этому боту больше не требуется ?
	 */
	boolean onGene(IBot bot);

	String getDescription(IBot bot, int i);
}
