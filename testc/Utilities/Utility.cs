using testConsoleApp;
using System.Windows.Forms;

namespace Game.Utility
{
    class Utility
    {
        public static bool itemExistInRoom(Item[] itemDatabase, Room[,] roomDatabase, string itemName, int MAX_ITEMS)
        {
            var exist = false;
            for (int i = 0; i < MAX_ITEMS; i++)
            {
                if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()) && roomDatabase[Main.x, Main.y].itemsAmount[i] != 0)
                {
                    exist = true;
                    break;
                }
            }
            if (exist) return true;
            else return false;
        }

        public static bool itemExistInInv(Item[] itemDatabase, Room[,] roomDatabase, string itemName, int MAX_ITEMS)
        {
            var exist = false;
            for (int i = 0; i < MAX_ITEMS; i++)
            {
                if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()))
                {
                    exist = true;
                    break;
                }
            }
            if (exist) return true;
            else return false;
        }

        public static int getItemDatabaseInformation(Item[] itemDatabase, Room[,] roomDatabase, int[] invItems, string itemName, int x, int y, int MAX_ITEMS, int MAX_INV_ITEMS, int itemDatabaseId, string informationType)
        {
            if (informationType == "GET_ITEM_ID_ROOM")
            {
                int itemId = -1;
                for (int i = 0; i < MAX_ITEMS; i++)
                {
                    if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()) && roomDatabase[x,y].itemsAmount[i] != 0)
                    {
                        itemId = itemDatabase[i].id;
                        break;
                    }
                }
                return itemId;
            }
            else if(informationType == "GET_ITEM_ID_INV")
            {
                int itemId = -1;
                for (int i = 0; i < MAX_ITEMS; i++)
                {
                    if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()))
                    {
                        itemId = itemDatabase[i].id;
                        break;
                    }
                }
                return itemId;
            }
            else if (informationType == "GET_ITEM_TYPE_ROOM")
            {
                var itemType = -1;
                for (int i = 0; i < MAX_ITEMS; i++)
                {
                    if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()) && roomDatabase[Main.x, Main.y].itemsAmount[i] != 0)
                    {
                        itemType = itemDatabase[i].type;
                        break;
                    }
                }
                return itemType;
            }
            else if(informationType == "GET_ITEM_TYPE_INV")
            {
                var itemType = -1;
                for (int i = 0; i < MAX_ITEMS; i++)
                {
                    if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()))
                    {
                        itemType = itemDatabase[i].type;
                        break;
                    }
                }
                return itemType;
            }
            else if (informationType == "GET_INV_INDEX")
            {
                var itemIndexInInventory = -1;
                for (int i = 0; i < MAX_INV_ITEMS; i++)
                {
                    if (invItems[i] == itemDatabaseId) { itemIndexInInventory = i; break; }
                }
                return itemIndexInInventory;
            }
            else if (informationType == "GET_INV_SLOT")
            {
                var emptySlot = -1;
                for (int i = 0; i < MAX_INV_ITEMS; i++)
                {
                    if (invItems[i] == -1) { emptySlot = i; break; }
                }
                return emptySlot;
            }
            else return -1;
        }

        public static int getItemDatabaseInformation(Item[] itemDatabase,Room[,] roomDatabase, int[] invItems, string itemName,int x, int y, int MAX_ITEMS, int MAX_INV_ITEMS, string informationType)
        {
            if (informationType == "GET_ITEM_ID_ROOM")
            {
                int itemId = -1;
                for (int i = 0; i < MAX_ITEMS; i++)
                {
                    if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()) && roomDatabase[x, y].itemsAmount[i] != 0)
                    {
                        itemId = itemDatabase[i].id;
                        break;
                    }
                }
                return itemId;
            }
            else if (informationType == "GET_ITEM_ID_INV")
            {
                int itemId = -1;
                for (int i = 0; i < MAX_ITEMS; i++)
                {
                    if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()))
                    {
                        itemId = itemDatabase[i].id;
                        break;
                    }
                }
                return itemId;
            }
            else if (informationType == "GET_ITEM_TYPE_ROOM")
            {
                var itemType = -1;
                for (int i = 0; i < MAX_ITEMS; i++)
                {
                    if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()) && roomDatabase[Main.x, Main.y].itemsAmount[i] != 0)
                    {
                        itemType = itemDatabase[i].type;
                        break;
                    }
                }
                return itemType;
            }
            else if (informationType == "GET_ITEM_TYPE_INV")
            {
                var itemType = -1;
                for (int i = 0; i < MAX_ITEMS; i++)
                {
                    if (itemDatabase[i].name.ToLower().Contains(itemName.ToLower()))
                    {
                        for (int j = 0; j < Main.MAX_TYPES; j++)
                        {
                            if (Main.eq.eq[j] == itemDatabase[i].type)
                            {
                                itemType = itemDatabase[i].type;
                                break;
                            }
                        }
                    }
                }
                return itemType;
            }
            else if (informationType == "GET_INV_SLOT")
            {
                var emptySlot = -1;
                for (int i = 0; i < MAX_INV_ITEMS; i++)
                {
                    if (invItems[i] == -1) { emptySlot = i; break; }
                }
                return emptySlot;
            }
            else return -1;
        }
    }
}
