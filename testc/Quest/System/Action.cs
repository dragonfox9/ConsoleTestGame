using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using testConsoleApp;
using Game.Quest.List;

namespace Game.Quest.System
{
    class Action
    {
        public void Talk(Window window,string name, string s)
        {
            bool validNpc = false;
            int npcId = -1;
            for(int i = 0; i < Main.MAX_NPCS; i++)
            {
                if(Main.npc[i].name.ToLower().Contains(name))
                {
                    validNpc = true;
                    npcId = Main.npc[i].id;
                }
            }

            if (validNpc && Main.roomDatabase[Main.x, Main.y].npcAmount[npcId] >= 1)
            {
                talkWith(window, npcId, s);
            }
        }

        public void talkWith(Window window,int id, string s)
        {
            if(id == 0)
            {
                Main._Kowal.active = true;
                Main._Kowal.basicInfo(window, s);
            }
        }
    }
}
