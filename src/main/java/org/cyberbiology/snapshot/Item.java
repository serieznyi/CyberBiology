package org.cyberbiology.snapshot;

import org.cyberbiology.Bot;

import java.io.DataOutputStream;
import java.io.IOException;

class Item {
    private byte bot_adr;
    private int bot_x;
    private int bot_y;
    private int bot_health;
    private int bot_mineral;
    private byte bot_alive;
    private int bot_c_red;
    private int bot_c_green;
    private int bot_c_blue;
    private byte bot_direction;
    private int bot_mprev_x;
    private int bot_mprev_y;
    private int bot_mnext_x;
    private int bot_mnext_y;
    private byte[] mind;

    Item(Bot bot, int x, int y)
    {
        // жестко сохраняем все зхначения, так как к моменту сохранения кадра данные могут изменится
        bot_adr	= (byte) bot.adr;
        bot_x	= bot.x;
        bot_y	= bot.y;
        bot_health= bot.health;
        bot_mineral= bot.mineral;
        bot_alive= (byte) bot.alive;
        bot_c_red= bot.c_red;
        bot_c_green= bot.c_green;
        bot_c_blue	= bot.c_blue;
        bot_direction= (byte) bot.direction;

        if(bot.mprev!=null) {
            bot_mprev_x	= bot.mprev.x;
            bot_mprev_y	= bot.mprev.y;
        } else {
            bot_mprev_x	= bot_mprev_y = -1;
        }

        if(bot.mnext!=null) {
            bot_mnext_x	= bot.mnext.x;
            bot_mnext_y	= bot.mnext.y;
        } else {
            bot_mnext_x	= bot_mnext_y = -1;
        }
        mind	= new byte[bot.mind.length];

        for(int i=0;i<bot.mind.length;i++)
        {
            mind[i] = bot.mind[i];
        }
    }

    void save(DataOutputStream fileOut) throws IOException
    {
        fileOut.writeByte(bot_adr);
        fileOut.writeInt(bot_x);
        fileOut.writeInt(bot_y);
        fileOut.writeInt(bot_health);
        fileOut.writeInt(bot_mineral);
        fileOut.writeByte(bot_alive);
        fileOut.writeInt(bot_c_red);
        fileOut.writeInt(bot_c_green);
        fileOut.writeInt(bot_c_blue);
        fileOut.writeByte(bot_direction);
        fileOut.writeInt(bot_mprev_x);
        fileOut.writeInt(bot_mprev_y);
        fileOut.writeInt(bot_mnext_x);
        fileOut.writeInt(bot_mnext_y);

        for (byte aMind : mind) {
            fileOut.writeByte(aMind);
        }
    }
}
