package com.example.s1;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by 重夢 on 2016/2/3.
 */
public class DataBase extends AppCompatActivity{
    public static String[]
            skill = {"鉛筆","原子筆","鋼筆","毛筆","彩色筆","鬼團子","貓屁股","刀座子"},
            monster = {
            "1:5:160:70:","2:5:160:70:","3:5:160:70:",
            "4:5:160:70:","5:5:160:70:","6:5:160:70:",
            "7:5:160:70:","8:5:160:70:","9:5:160:70:",
            "10:5:160:70","11:5:160:70","12:5:160:70:","13:5:160:70:",
            "14:5:160:70","15:5:160:70","16:5:160:70",
            "17:5:160:70","18:5:160:70","19:5:160:70",
            "20:5:160:70","21:5:160:70","22:5:160:70","23:5:160:70","24:5:160:70","25:5:160:70","26:5:160:70",
            "27:5:160:70",
            "28:5:160:70","29:5:160:70","30:5:160:70",
            "31:5:160:70",
            "32:5:160:70",
            "33:5:160:70","34:5:160:70",
            "35:5:160:70","36:5:160:70",
            "37:5:160:70","38:5:160:70","39:5:160:70",
            "40:5:160:70","41:5:160:70",
            "42:5:160:70","43:5:160:70","44:5:160:70","45:5:160:70","46:5:160:70","47:5:160:70","48:5:160:70","49:5:160:70","50:5:160:70",
            "51:5:160:70",
            "52:5:160:70","53:5:160:70","54:5:160:70","55:5:160:70",
            "56:5:160:70",
    };
    public static int[] picture = {
            R.drawable.angel,R.drawable.assassin,R.drawable.asura,
            R.drawable.bandit,R.drawable.bat,R.drawable.behemoth,
            R.drawable.captain,R.drawable.chimera,R.drawable.cockatrice,
            R.drawable.darklord,R.drawable.death,R.drawable.demon,R.drawable.dragon,
            R.drawable.earthspirit,R.drawable.evilgod,R.drawable.evilking,
            R.drawable.fairy,R.drawable.fanatic,R.drawable.firespirit,
            R.drawable.gargoyle,R.drawable.garuda,R.drawable.gayzer,R.drawable.general,R.drawable.ghost,R.drawable.god,R.drawable.goddess,
            R.drawable.hornet,
            R.drawable.icelady,R.drawable.ifrit,R.drawable.imp,
            R.drawable.jellyfish,
            R.drawable.lamia,
            R.drawable.mage,R.drawable.mimic,
            R.drawable.ogre,R.drawable.orc,
            R.drawable.plant,R.drawable.priest,R.drawable.puppet,
            R.drawable.rat,R.drawable.rogue,
            R.drawable.sahagin,R.drawable.scorpion,R.drawable.skeleton,R.drawable.slime,R.drawable.snake,R.drawable.soldier,R.drawable.spider,R.drawable.succubus,R.drawable.swordman,
            R.drawable.vampire,
            R.drawable.waterspirit,R.drawable.werewolf,R.drawable.willowisp,R.drawable.windspirit,
            R.drawable.zombie
    };
}

class picture{//沒有用到
    int[] picture = new int[]{
            R.drawable.angel,R.drawable.assassin,R.drawable.asura,
            R.drawable.bandit,R.drawable.bat,R.drawable.behemoth,
            R.drawable.captain,R.drawable.chimera,R.drawable.cockatrice,
            R.drawable.darklord,R.drawable.death,R.drawable.demon,R.drawable.dragon,
            R.drawable.earthspirit,R.drawable.evilgod,R.drawable.evilking,
            R.drawable.fairy,R.drawable.fanatic,R.drawable.firespirit,
            R.drawable.gargoyle,R.drawable.garuda,R.drawable.gayzer,R.drawable.general,R.drawable.ghost,R.drawable.god,R.drawable.goddess,
            R.drawable.hornet,
            R.drawable.icelady,R.drawable.ifrit,R.drawable.imp,
            R.drawable.jellyfish,
            R.drawable.lamia,
            R.drawable.mage,R.drawable.mimic,
            R.drawable.ogre,R.drawable.orc,
            R.drawable.plant,R.drawable.priest,R.drawable.puppet,
            R.drawable.rat,R.drawable.rogue,
            R.drawable.sahagin,R.drawable.scorpion,R.drawable.skeleton,R.drawable.slime,R.drawable.snake,R.drawable.soldier,R.drawable.spider,R.drawable.succubus,R.drawable.swordman,
            R.drawable.vampire,
            R.drawable.waterspirit,R.drawable.werewolf,R.drawable.willowisp,R.drawable.windspirit,
            R.drawable.zombie
    };
    int pic = picture.length;
}
