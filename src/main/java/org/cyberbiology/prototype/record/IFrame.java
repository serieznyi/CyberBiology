package org.cyberbiology.prototype.record;

import java.io.DataOutputStream;
import java.io.IOException;

import org.cyberbiology.prototype.IBot;

public interface IFrame
{

	void addBot(IBot bot, int x, int y);

	int save(DataOutputStream fileout) throws IOException;

}
