package org.cyberbiology.snapshot;

import org.cyberbiology.Bot;
import org.cyberbiology.prototype.IBot;

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

    public int save(DataOutputStream fileout) throws IOException
    {
        int length	= list.size()* BOT_DATA_LENGTH;
        fileout.writeInt(length);

        for (Item aList : list) {
            aList.save(fileout);
        }

        return length;
    }

    public void addBot(IBot bot, int x, int y)
    {
        this.list.add(new Item((Bot)bot, x, y));
    }
}
