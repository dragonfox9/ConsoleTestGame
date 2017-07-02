using System;
using System.Drawing;
using Game.Utility;
using System.Windows.Forms;

namespace testConsoleApp
{
    class Equipment : Window
    {
        public int[] eq;

        public void build(int MAX_TYPES)
        {
            this.eq = new int[MAX_TYPES];
            for (int i = 0; i < eq.Length; i++)
            {
                this.eq[i] = -1;
            }
        }

        public void set(Window window, Item[] itemDatabase,Room[,] roomDatabase, Player player, string itemName, int x,int y, int MAX_INV_ITEMS, int MAX_ITEMS, int MAX_TYPES)
        {
            var validItemDatabaseId = Utility.itemExistInInv(itemDatabase, roomDatabase, itemName, MAX_ITEMS);
            var itemDatabaseId = Utility.getItemDatabaseInformation(itemDatabase,roomDatabase, player.invItems, itemName,x,y, MAX_ITEMS, MAX_INV_ITEMS, "GET_ITEM_ID_INV");
            var itemIndexInInventory = Utility.getItemDatabaseInformation(itemDatabase, roomDatabase, player.invItems, itemName,x,y, MAX_ITEMS, MAX_INV_ITEMS, itemDatabaseId, "GET_INV_INDEX");
            var itemType = Utility.getItemDatabaseInformation(itemDatabase, roomDatabase, player.invItems, itemName, x, y, MAX_ITEMS, MAX_INV_ITEMS, itemDatabaseId, "GET_ITEM_TYPE_INV");

            var oldItemIndexInInventory = -1;
            var emptySlot = -1;

            for (int i = 0; i < MAX_INV_ITEMS; i++)
            {
                if (player.invItems[i] == eq[itemType])
                {
                    oldItemIndexInInventory = i;
                    break;
                }
            }

            for (int i = 0; i < MAX_INV_ITEMS; i++)
            {
                if (player.invItems[i] == -1)
                {
                    emptySlot = i;
                    break;
                }
            }

            var validItem = false;
            if (player.invItems[itemIndexInInventory] != eq[itemType])
                validItem = true;

            if (validItem && validItemDatabaseId && eq[itemType] == -1 && itemIndexInInventory != -1)
            {
                eq[itemType] = itemDatabaseId;
                if (player.invItemsAmount[itemIndexInInventory] == 1)
                {
                    player.invItems[itemIndexInInventory] = -1;
                    player.invItemsAmount[itemIndexInInventory] = 0;
                    window.writeLine(window.textBox1, "Zakładasz " + itemDatabase[itemDatabaseId].name, Color.Gray);
                }
                else if (player.invItemsAmount[itemIndexInInventory] > 1)
                {
                    player.invItemsAmount[itemIndexInInventory] -= 1;
                    window.writeLine(window.textBox1, "Zakładasz " + itemDatabase[itemDatabaseId].name, Color.Gray);
                }
            }
            else if (validItem && validItemDatabaseId && eq[itemType] != -1 && itemIndexInInventory != -1)
            {
                if (oldItemIndexInInventory == -1)
                {
                    if (player.invItemsAmount[itemIndexInInventory] == 1)
                    {
                        var oldId = player.invItems[itemIndexInInventory];
                        player.invItems[itemIndexInInventory] = eq[itemType];
                        eq[itemType] = oldId;
                        window.writeLine(window.textBox1, "Zakładasz " + itemDatabase[itemDatabaseId].name, Color.Gray);
                    }
                    else if (player.invItemsAmount[itemIndexInInventory] > 1)
                    {
                        var oldId = player.invItems[itemIndexInInventory];
                        player.invItems[emptySlot] = eq[itemType];
                        player.invItemsAmount[emptySlot] = 1;
                        player.invItemsAmount[itemIndexInInventory] -= 1;
                        eq[itemType] = oldId;
                        window.writeLine(window.textBox1, "Zakładasz " + itemDatabase[itemDatabaseId].name, Color.Gray);
                    }
                }
                else if (oldItemIndexInInventory != -1)
                {
                    player.invItemsAmount[oldItemIndexInInventory] += 1;
                    eq[itemType] = player.invItems[itemIndexInInventory];
                    player.invItemsAmount[itemIndexInInventory] -= 1;
                    player.invItems[itemIndexInInventory] = -1;
                    window.writeLine(window.textBox1, "Zakładasz " + itemDatabase[itemDatabaseId].name, Color.Gray);

                }
            }
            else if(!validItem && validItemDatabaseId && eq[itemType] == -1 && itemIndexInInventory != -1) { window.writeLine(window.textBox1, "Mam już na sobie taki sam przedmiot", Color.Gray); }
            else if(!validItem && validItemDatabaseId && eq[itemType] != -1 && itemIndexInInventory != -1) { window.writeLine(window.textBox1, "Mam już na sobie taki sam przedmiot", Color.Gray); }
            else
                window.writeLine(window.textBox1, "Nie posiadam takiego przedmiotu", Color.Gray);
        }

        public void remove(Window window, Item[] itemDatabase, Room[,] roomDatabase, Player player, string itemName, int x, int y, int MAX_INV_ITEMS, int MAX_ITEMS, int MAX_TYPES)
        {
            var validItemDatabaseId = Utility.itemExistInInv(itemDatabase, roomDatabase, itemName, MAX_ITEMS);
            var itemDatabaseId = Utility.getItemDatabaseInformation(itemDatabase, roomDatabase, player.invItems, itemName, x, y, MAX_ITEMS, MAX_INV_ITEMS, "GET_ITEM_ID");
            var itemType = Utility.getItemDatabaseInformation(itemDatabase, roomDatabase, player.invItems, itemName, x, y, MAX_ITEMS, MAX_INV_ITEMS, itemDatabaseId, "GET_ITEM_TYPE_INV");
            var itemIndexInInventory = Utility.getItemDatabaseInformation(itemDatabase, roomDatabase, player.invItems, itemName, x, y, MAX_ITEMS, MAX_INV_ITEMS, itemDatabaseId, "GET_INV_INDEX");
            var emptySlot = -1;

            //szukanie pustego slotu w player.invItems
            for (int i = 0; i < MAX_INV_ITEMS; i++)
            {
                if (player.invItems[i] == -1)
                {
                    emptySlot = i;
                    break;
                }
            }

            //jeśli item istnieje
            if (validItemDatabaseId && eq[itemType] == itemDatabaseId)
            {
                if (itemIndexInInventory != -1)
                {
                    eq[itemType] = -1;
                    player.invItemsAmount[itemIndexInInventory] += 1;
                    window.writeLine(window.textBox1, "Zdejmujesz " + itemDatabase[itemDatabaseId].name, Color.Gray);
                }
                //jeśli item nie istnieje
                else if (itemIndexInInventory == -1)
                {
                    eq[itemType] = -1;
                    player.invItems[emptySlot] = itemDatabaseId;
                    player.invItemsAmount[emptySlot] += 1;
                    window.writeLine(window.textBox1, "Zdejmujesz " + itemDatabase[itemDatabaseId].name, Color.Gray);
                }
            }
            else
                window.writeLine(window.textBox1, "Nie posiadam takiego przedmiotu", Color.Gray);
        }
    }
}