package org.cyberbiology_old.snapshot;

import java.io.DataOutputStream;
import java.io.IOException;

import org.cyberbiology_old.prototype.IBot;

public interface IFrame
{
	void addBot(IBot bot, int x, int y);

	void save(DataOutputStream fileout) throws IOException;
}
