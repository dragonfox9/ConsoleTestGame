using System;
using System.Drawing;
using Game.IO;

namespace testConsoleApp
{
    class Display
    {
        static Color lifeColor = Color.FromArgb(47, 247, 80);
        static Color basicText = Color.FromArgb(255, 255, 255);
        static String[] s1 = LoadTxt.Load("Data/Location Pic/0.txt");
        static string[] s2 = LoadTxt.Load("Data/Location Pic/1.txt");
        static string lineDisplay = "--------------------------------------------------------------------------------------------------------------------------";
        public static void DisplayMainLoc(Window window)
        {
            window.rightBottomBox.Text = "";
            window.leftBottomBox.Text = "";
            window.textBox1.Text = "";
            displayTopBar(window);
            displayRightBottomBar(window);
            Location(window, 0);
            //form.roboText = Main.roomDatabase[Main.x, Main.y].description;
            //form.timer2Start = true;
            window.writeLine(window.textBox1, Main.roomDatabase[Main.x, Main.y].description, basicText);
            window.writeLine(window.textBox1, lineDisplay, basicText);
            Main.roomDatabase[Main.x, Main.y].printNpcs(window, Main.npc, Main.MAX_NPCS);
        }

        public static void DisplayLookAround(Window window)
        {
            window.leftBottomBox.Text = "";
            window.textBox1.Text = "";
            displayTopBar(window);
            Location(window, 0);
            Main.roomDatabase[Main.x, Main.y].printItems(window, Main.itemDatabase, Main.MAX_ITEMS);
            window.writeLine(window.leftBottomBox, ""+lineDisplay.Length, Color.Black);
        }

        private static void Location(Window window, int loc)
        {
            if (loc == 0)
            {
                foreach (string str in s1)
                {
                    window.writeLine(window.textBox1, str, Color.White);
                }
                window.writeLine(window.textBox1, lineDisplay, basicText);
            }
            else if (loc == 1)
            {
                foreach (string str in s2)
                    window.writeLine(window.textBox1, str, basicText);
                window.writeLine(window.textBox1, lineDisplay, basicText);
            }
        }

        private static void displayTopBar(Window window)
        {
            string heroName = Main.player.name;
            string locName = Main.roomDatabase[Main.x, Main.y].name;
            window.writeLine(window.textBox1, lineDisplay, basicText);
            window.write(window.textBox1, "  Imię: ", basicText);
            window.write(window.textBox1, heroName, Color.Green);
            for (int i = 0; i < 43 - heroName.Length; i++)
            {
                window.write(window.textBox1, " ", basicText);
            }
            window.write(window.textBox1, "Lokacja: ", basicText);
            window.write(window.textBox1, locName, Color.Green);
            for (int i = 0; i < 43 - locName.Length; i++)
            {
                window.write(window.textBox1, " ", basicText);
            }
            window.write(window.textBox1, "Życie: ", Color.White);
            window.write(window.textBox1, "" + Main.player.health, lifeColor);
            window.write(window.textBox1, "/", Color.White);
            window.writeLine(window.textBox1, "" + Main.player.health, lifeColor);
            window.writeLine(window.textBox1, lineDisplay, basicText);
        }

        private static void displayRightBottomBar(Window window)
        {
            window.write(window.rightBottomBox, "[", Color.White);
            window.write(window.rightBottomBox, "I", Color.Green);
            window.write(window.rightBottomBox, "]", Color.White);
            window.writeLine(window.rightBottomBox, " Inventarz", Color.White);
            window.write(window.rightBottomBox, "[", Color.White);
            window.write(window.rightBottomBox, "O", Color.Green);
            window.write(window.rightBottomBox, "]", Color.White);
            window.write(window.rightBottomBox, " Wyposażenie", Color.White);
        }

        public static void displayInventory(Window window)
        {
            window.textBox1.Text = "";
            displayTopBar(window);
            for(int i = 0; i < Main.MAX_INV_ITEMS;i++)
            {
                if(Main.player.invItemsAmount[i] != 0)
                {
                    window.writeLine(window.textBox1, Main.itemDatabase[Main.player.invItems[i]].name + ", w ilości: " + Main.player.invItemsAmount[i], basicText);
                }
            }
        }

        public static void displayShop(Window window, int shopId)
        {
            window.textBox1.Text = "";
            displayTopBar(window);
            for(int i = 0; i < Main.MAX_ITEMS;i++)
            {
                if(Main.npc[shopId].shopItemsAmount[i] != 0)
                {
                    window.writeLine(window.textBox1, "Przedmioty w sklepie: ", basicText);
                    window.writeLine(window.textBox1, Main.itemDatabase[i].name + ", ilość: " + Main.npc[shopId].shopItemsAmount[i], basicText);
                }
            }
        }
    }
}
