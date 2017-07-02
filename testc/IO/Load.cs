using System.IO;
using System;
using System.Drawing;
using testConsoleApp;

namespace Game.IO
{
    class LoadPlayer
    {
        private const string path = "Save/player.save";

        public static void LoadData(Window window, Item[] itemDatabase, Room[,] roomDatabase, Player player, Equipment eq,
            int MAX_ITEMS, int MAX_INV_ITEMS, int MAX_WIDTH, int MAX_HEIGHT, int MAX_TYPES)
        {
            if (File.Exists(path))
            {
                using (BinaryReader reader = new BinaryReader(File.Open(path, FileMode.Open)))
                {
                    try
                    {
                        for (int i = 0; i < MAX_WIDTH; i++)
                        {
                            for (int j = 0; j < MAX_HEIGHT; j++)
                            {
                                roomDatabase[i, j].description = reader.ReadString();
                                roomDatabase[i, j].name = reader.ReadString();
                                for (int k = 0; k < MAX_ITEMS; k++) { roomDatabase[i, j].itemsAmount[k] = reader.ReadInt32(); }
                            }
                        }

                        for (int i = 0; i < MAX_TYPES; i++) { eq.eq[i] = reader.ReadInt32(); }

                        for (int i = 0; i < MAX_INV_ITEMS; i++)
                        {
                            player.invItems[i] = reader.ReadInt32();
                            player.invItemsAmount[i] = reader.ReadInt32();
                        }

                        for (int i = 0; i < MAX_ITEMS; i++)
                        {
                            itemDatabase[i].id = reader.ReadInt32();
                            itemDatabase[i].name = reader.ReadString();
                            itemDatabase[i].type = reader.ReadInt32();
                        }
                    }
                    catch (Exception) { window.writeLine(window.textBox1, "Plik zapisu jest uszkodzony!", Color.Red); }
                }
            }
        }

    }
}
