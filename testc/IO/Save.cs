using System.IO;
using testConsoleApp;

namespace Game.IO
{
    class SavePlayer
    {
        private const string path = "Save/Player.save";

        public static void saveData(Item[] itemDatabase, Room[,] roomDatabase, Player player, Equipment eq,
            int MAX_ITEMS, int MAX_INV_ITEMS, int MAX_WIDTH, int MAX_HEIGHT, int MAX_TYPES)
        {

            if (!Directory.Exists("Save")) { Directory.CreateDirectory("Save"); }

            using (BinaryWriter writer = new BinaryWriter(File.Open(path, FileMode.Create)))
            {
                for (int i = 0; i < MAX_WIDTH; i++)
                {
                    for (int j = 0; j < MAX_HEIGHT; j++)
                    {
                        writer.Write(roomDatabase[i, j].description);
                        writer.Write(roomDatabase[i, j].name);
                        for (int k = 0; k < MAX_ITEMS; k++) { writer.Write(roomDatabase[i, j].itemsAmount[k]); }
                    }
                }

                for (int i = 0; i < MAX_TYPES; i++) { writer.Write(eq.eq[i]); }

                for (int i = 0; i < MAX_INV_ITEMS; i++)
                {
                    writer.Write(player.invItems[i]);
                    writer.Write(player.invItemsAmount[i]);
                }

                for (int i = 0; i < MAX_ITEMS; i++)
                {
                    writer.Write(itemDatabase[i].id);
                    writer.Write(itemDatabase[i].name);
                    writer.Write(itemDatabase[i].type);
                }
            }
        }
    }
}