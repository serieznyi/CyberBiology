package org.cyberbiology_old.snapshot;

import org.cyberbiology_old.Bot;
import org.cyberbiology_old.prototype.IBot;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Frame implements IFrame {
    private static final int BOT_DATA_LENGTH = 14+Bot.MIND_SIZE;

    private List<Item> list;

    Frame()
    {
        this.list = new ArrayList<Item>();
    }

    public void save(DataOutputStream fileout) throws IOException
    {
        int length	= list.size()* BOT_DATA_LENGTH;
        fileout.writeInt(length);

        for (Item aList : list) {
            aList.save(fileout);
        }
    }

    public void addBot(IBot bot, int x, int y)
    {
        this.list.add(new Item((Bot)bot, x, y));
    }
}
