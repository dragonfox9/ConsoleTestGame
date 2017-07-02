using System.Drawing;
using Game.NPC;

namespace testConsoleApp
{
    class Rooms
    {
        public void build(Room[,] room, int MAX_WIDTH, int MAX_HEIGHT, int MAX_ITEMS, int MAX_NPCS)
        {
            for (int i = 0; i < MAX_WIDTH; i++)
            {
                for (int j = 0; j < MAX_HEIGHT; j++)
                {
                    room[i, j] = new Room(i, "", "");
                    room[i, j].initalizeItems(MAX_ITEMS, MAX_NPCS);
                }
            }

            room[0, 0].number = 1;
            room[0, 0].name = "Pokój 1";
            room[0, 0].description = "Krążowniki typu Dresden miały 117,9 m długości na linii wodnej i 118,3 m długości całkowitej. Szerokość po pokładzie\r\nwynosiła 13,5 m, a zanurzenie 5,53 m. Miały 3664 ton wyporności standardowej i do 4268 ton w pełnym obciążeniu bojowym.\r\nKonstrukcja kadłubów opierała się na szkielecie ze stalowych wręg i wzdłużników. Były podzielone na trzynaście przedziałów\r\nwodoszczelnych, a na 47% długości kilu miały podwójne dno[1]. Okręty wykazywały się dobrą dzielnością morską, ale – m.in.\r\nze względu na małe zanurzenie[2] – osiągały przy większej fali przechyły do 20 stopni. Ponadto przy dużej prędkości stawały się bardzo zawilgocone i ";
            room[0, 0].addItem(0, 1);
            room[0, 0].addItem(1, 5);
            room[0, 0].addItem(8, 1);
            room[0, 0].addNpc(0, 1);
            room[0, 0].aPoint = new Point(0,338);
            room[0, 0].bPoint = new Point(492,338);

            room[0, 1].number = 2;
            room[0, 1].name = "Pokój 2";
            room[0, 1].description = "Opis pokoju nr. 2";
            room[0, 1].aPoint = new Point(0, 270);
            room[0, 1].bPoint = new Point(492, 270);


            room[1, 0].number = 3;
            room[1, 0].name = "Pokój 3";
            room[1, 0].description = "Opis pokoju nr. 3";
            room[1, 0].aPoint = new Point(0, 273);
            room[1, 0].bPoint = new Point(492, 273);

            room[1, 1].number = 4;
            room[1, 1].name = "Pokój 4";
            room[1, 1].description = "Opis pokoju nr. 4";
            room[1, 1].aPoint = new Point(0, 270);
            room[1, 1].bPoint = new Point(492, 270);
        }
    }
    class Room
    {
        public int number { get; set; }
        public string name, description;
        public int[] itemsId { get; set; }
        public int[] itemsAmount { get; set; }
        public int[] npcId { get; set; }
        public int[] npcAmount { get; set; }
        public Point aPoint;
        public Point bPoint;

        public Room(int number, string name, string description)
        {
            this.number = number;
            this.name = name;
            this.description = description;
        }

        public void initalizeItems(int MAX_ITEMS, int MAX_NPCS)
        {
            this.itemsId = new int[MAX_ITEMS];
            this.itemsAmount = new int[MAX_ITEMS];
            this.npcId = new int[MAX_NPCS];
            this.npcAmount = new int[MAX_NPCS];
            for (int i = 0; i < MAX_ITEMS; i++)
            {
                this.itemsId[i] = i;
                this.itemsAmount[i] = 0;
            }
        }

        public void addItem(int id, int amount)
        {
            this.itemsAmount[id] = amount;
        }
        public void removeItem(int id, int amount)
        {
            if ((itemsAmount[id] - amount) >= 0)
            {
                this.itemsAmount[id] -= amount;
            }

        }
        public void addNpc(int id, int amount)
        {
            this.npcAmount[id] += amount;
        }

        public void printItems(Window window, Item[] items, int MAX_ITEMS)
        {
            int counter = 0;
            window.writeLine(window.textBox1, "Przedmioty w pokoju: ", Color.White);
            for (int i = 0; i < MAX_ITEMS; i++)
            {
                if (itemsAmount[i] >= 1)
                {
                    counter++;
                    window.write(window.textBox1, counter + ". ", Color.White);
                    window.write(window.textBox1, items[i].name, Color.Tomato);
                    window.write(window.textBox1, "[", Color.White);
                    window.write(window.textBox1, "" + itemsAmount[i], Color.Green);
                    window.write(window.textBox1, "]", Color.White);
                    window.writeLine(window.textBox1, "", Color.White);
                }
            }
        }

        public void printNpcs(Window window, NPC[] npc, int MAX_NPCS)
        {
            for(int i = 0; i < MAX_NPCS; i++)
            {
                if(npcAmount[i] >= 1)
                {
                    window.writeLine(window.leftBottomBox, npc[i].name + " jest tutaj!", Color.Red);
                }
            }
        }



    }
}
