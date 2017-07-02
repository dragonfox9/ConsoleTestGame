using testConsoleApp;
using System.Drawing;

namespace Game.NPC
{
    class NPCs
    {
        public void build(NPC[] npc, int MAX_NPCS, int MAX_ITEMS, int MAX_TYPES)
        {
            for (int i = 0; i < MAX_NPCS; i++)
            {
                npc[i] = new NPC(0, null, false);
                npc[i].initalizeNpc(MAX_ITEMS, MAX_TYPES);
            }

            npc[0].add(0, "Kowal", true);
            //Kowal - sklep
            npc[0].addItemToShop(2, 3);
            //Kowal - sklep



            npc[1].add(1, "Mieszczanin", false);
        }

        public void buy(NPC[] npc, Item[] itemDatabase, Room[,] roomDatabase, Player player)
        {

        }

        public void sell(NPC[] npc, Item[] itemDatabase, Room[,] roomDatabase, Player player)
        {

        }

        public void list(Window window, string name, NPC[] npc, Item[] itemDatabase, Room[,] roomDatabase, Player player)
        {
            for(int i = 0; i < Main.MAX_NPCS; i++)
            {
                if (npc[i].name.ToLower().Contains(name) && roomDatabase[Main.x,Main.y].npcAmount[i] > 0)
                {
                    window.state[6] = true;
                    window.state[0] = false;
                    window.textBox4.Visible = true;
                    window.textBox4.Text = "";
                    window.write(window.textBox4, "[", Color.White);
                    window.write(window.textBox4, "B", Color.Green);
                    window.write(window.textBox4, "]", Color.White);
                    window.write(window.textBox4, " Cofnij", Color.White);
                    window.leftBottomBox.Visible = false;
                    window.rightBottomBox.Visible = false;
                    Display.displayShop(window, i);
                    break;
                }
            }
        }
    }

    class NPC
    {
        public string name { get; set; }
        public int id { get; set; }
        public int[] invItems { get; set; }
        public int[] invItemsAmount { get; set; }
        public int[] eq { get; set; }
        public int[] shopItems { get; set; }
        public int[] shopItemsAmount { get; set; }
        public int money { get; set; }
        public bool shopIsOpen;

        public NPC(int id, string name, bool shopIsOpen)
        {
            this.id = id;
            this.name = name;
            this.shopIsOpen = shopIsOpen;
        }

        public void initalizeNpc(int MAX_ITEMS, int MAX_TYPES)
        {
            this.invItems = new int[MAX_ITEMS];
            this.invItemsAmount = new int[MAX_ITEMS];
            this.eq = new int[MAX_TYPES];
            this.shopItems = new int[MAX_ITEMS];
            this.shopItemsAmount = new int[MAX_ITEMS];

            for (int i = 0; i < MAX_ITEMS; i++)
            {
                this.invItems[i] = i;
                this.invItemsAmount[i] = 0;
                this.shopItems[i] = i;
                this.shopItemsAmount[i] = 0;
                if (i < 9) this.eq[i] = -1;
            }
        }

        public void add(int id, string name, bool shopIsOpen)
        {
            this.id = id;
            this.name = name;
            this.shopIsOpen = shopIsOpen;
        }

        public void addItemToShop(int id, int amount) { this.shopItemsAmount[id] += amount; }

        public void removeItemFromShop(int id, int amount)
        {
            if ((this.shopItemsAmount[id] - amount) <= 0) { this.shopItemsAmount[id] -= amount; }

        }
    }
}