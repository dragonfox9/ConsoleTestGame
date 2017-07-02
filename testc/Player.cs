using System;
using System.Drawing;
using Game.Utility;
using System.Windows.Forms;

namespace testConsoleApp
{
    class Player
    {

        public string name { get; set; }
        public int health { get; set; }
        public int[] invItems { get; set; }
        public int[] invItemsAmount { get; set; }
        public int vitality { get; set; }
        public int inteligence { get; set; }
        public int strength { get; set; }
        public int dexterity { get; set; }
        public int defence { get; set; }
        public int mana { get; set; }
        public int exp { get; set; }
        public int attack { get; set; }
        public int dodge { get; set; }
        public int critical_Strike { get; set; }
        public int speed { get; set; }
        public int block { get; set; }

        public void build(int MAX_INV_ITEMS)
        {
            this.invItems = new int[MAX_INV_ITEMS];
            this.invItemsAmount = new int[MAX_INV_ITEMS];
            for (int i = 0; i < MAX_INV_ITEMS; i++)
            {
                invItems[i] = -1;
                invItemsAmount[i] = 0;
            }
            name = "Testowy123";
            health = 752;
        }

        public void addInvItems(Window window, Item[] itemDatabase, Room[,] roomDatabase, int x, int y, string itemName, string s, int MAX_ITEMS, int MAX_INV_ITEMS)
        {
            var validItemDatabaseId = Utility.itemExistInRoom(itemDatabase, roomDatabase, itemName, MAX_ITEMS);
            var itemDatabaseId = Utility.getItemDatabaseInformation(itemDatabase,roomDatabase, invItems, itemName, x, y, MAX_ITEMS, MAX_INV_ITEMS, "GET_ITEM_ID_ROOM");
            var emptySlot = Utility.getItemDatabaseInformation(itemDatabase, roomDatabase, invItems, itemName, x, y, MAX_ITEMS, MAX_INV_ITEMS, itemDatabaseId, "GET_INV_SLOT");
            var itemIndexInInventory = Utility.getItemDatabaseInformation(itemDatabase, roomDatabase, invItems, itemName, x, y, MAX_ITEMS, MAX_INV_ITEMS, itemDatabaseId, "GET_INV_INDEX");

            // pętla szukająca pustego slotu
            for (int i = 0; i < MAX_INV_ITEMS; i++)
            {
                if (invItems[i] == -1)
                {
                    emptySlot = i;
                    break;
                }
            }

            //podnoszenie itemu w ilości 1
            if (validItemDatabaseId && roomDatabase[x, y].itemsAmount[itemDatabaseId] != 0)
            {
                if (itemIndexInInventory != -1 && roomDatabase[x, y].itemsAmount[itemDatabaseId] == 1)
                {
                    invItemsAmount[itemIndexInInventory] += 1;
                    roomDatabase[x, y].removeItem(itemDatabaseId, 1);
                    window.writeLine(window.textBox1,"Podnosisz " + itemDatabase[itemDatabaseId].name, Color.Gray);
                }

                //podnoszenie kilku itemów, które istnieją w inventory

                if (itemIndexInInventory != -1 && roomDatabase[x, y].itemsAmount[itemDatabaseId] > 1)
                {
                    if (window.state[1])
                    {
                        int amount = 0;
                        try
                        {
                            amount = Int32.Parse(s);
                        }
                        catch (Exception) { }
                        if (amount <= roomDatabase[x, y].itemsAmount[itemDatabaseId] && amount > 0)
                        {
                            invItemsAmount[itemIndexInInventory] += amount;
                            roomDatabase[x, y].removeItem(itemDatabaseId, amount);
                            window.writeLine(window.textBox1, "Podnosisz " + itemDatabase[itemDatabaseId].name + " w ilości: " + amount, Color.Gray);
                        }
                        else
                            window.writeLine(window.textBox1, "Podnosisz złą liczbę przedmiotów", Color.Gray);
                        window.state[1] = false;
                    }
                    else if (!window.state[1])
                    {
                        window.state[1] = true;
                        window.state[0] = false;
                        window.writeLine(window.textBox1, "Ile przedmiotów chcesz podnieść?", Color.Red);
                    }

                }

                //podnoszenie itemów, które nie istnieją w inventory

                if (itemIndexInInventory == -1 && roomDatabase[x, y].itemsAmount[itemDatabaseId] == 1)
                {
                    invItems[emptySlot] = itemDatabaseId;
                    invItemsAmount[emptySlot] += 1;
                    roomDatabase[x, y].removeItem(itemDatabaseId, 1);
                    window.writeLine(window.textBox1, "Podnosisz " + itemDatabase[itemDatabaseId].name, Color.Gray);
                    Display.DisplayLookAround(window);
                }

                if (itemIndexInInventory == -1 && roomDatabase[x, y].itemsAmount[itemDatabaseId] > 1)
                {
                    if (window.state[1])
                    {
                        int amount = 0;
                        try
                        {
                            amount = Int32.Parse(s);
                        }
                        catch (Exception) { }
                        if (amount > 0)
                        {
                            invItems[emptySlot] = itemDatabaseId;
                            invItemsAmount[emptySlot] += amount;
                            roomDatabase[x, y].removeItem(itemDatabaseId, amount);
                            window.writeLine(window.textBox1, "Podnosisz " + itemDatabase[itemDatabaseId].name + " w ilości: " + amount, Color.Gray);
                            Display.DisplayLookAround(window);
                        }
                        else
                            window.writeLine(window.textBox1, "Podnosisz złą liczbę przedmiotów", Color.Gray);
                        window.state[1] = false;
                    }
                    else if (!window.state[1])
                    {
                        window.state[1] = true;
                        window.state[0] = false;
                        window.writeLine(window.textBox1, "Ile przedmiotów chcesz podnieść?", Color.Red);
                    }

                }
            }
            else
                window.writeLine(window.textBox1, "Nie widzę tutaj takiego przedmiotu...", Color.Gray);
        }

        public void removeInvItems(Window window, Item[] itemDatabase, Room[,] roomDatabase, int x, int y, string itemName, string s, int MAX_INV_ITEMS)
        {

            Boolean validItemDatabaseId = false;
            int itemDatabaseId = -1;
            int itemIndexInInventory = -1;
            //1. pętla sprawdzaja sprawdzająca, czy przedmiot istnieje
            //2. pętla sprawdzająca, czy przedmiot jest w inventory
            //3. warunek (if) sprawdzający i usuwający jeden przedmiot z ekwipunku
            //4. warunek (if) sprawdzający i usuwający kilka przedmiotów z ekwipunku
            for (int i = 0; i < MAX_INV_ITEMS; i++)
            {
                if (itemDatabase[i].name.ToLower().Contains(itemName))
                {
                    validItemDatabaseId = true;
                    itemDatabaseId = itemDatabase[i].id;
                    break;
                }
            }

            for (int i = 0; i < MAX_INV_ITEMS; i++)
            {
                if (invItems[i] == itemDatabaseId)
                {
                    itemIndexInInventory = i;
                    break;
                }
            }

            if (validItemDatabaseId && itemIndexInInventory != -1)
            {
                if (invItemsAmount[itemIndexInInventory] == 1)
                {
                    invItemsAmount[itemIndexInInventory] = 0;
                    invItems[itemIndexInInventory] = -1;
                    roomDatabase[x, y].itemsAmount[itemDatabaseId] += 1;
                    window.writeLine(window.textBox1, "Wyrzuciłeś " + itemDatabase[itemDatabaseId].name, Color.Gray);
                }

                else if (invItemsAmount[itemIndexInInventory] > 1)
                {
                    if (window.state[2])
                    {
                        int amount = 0;
                        try
                        {
                            amount = Int32.Parse(s);
                        }
                        catch (Exception) { }
                        if (amount < invItemsAmount[itemIndexInInventory] && amount > 0)
                        {
                            invItemsAmount[itemIndexInInventory] -= amount;
                            roomDatabase[x, y].itemsAmount[itemDatabaseId] += amount;
                            window.writeLine(window.textBox1, "Wyrzucasz " + itemDatabase[itemDatabaseId].name + " w ilości: " + amount, Color.Gray);
                        }
                        else if (amount == invItemsAmount[itemIndexInInventory] && amount > 0)
                        {
                            invItemsAmount[itemIndexInInventory] = 0;
                            invItems[itemIndexInInventory] = -1;
                            roomDatabase[x, y].itemsAmount[itemDatabaseId] += amount;
                            window.writeLine(window.textBox1, "Wyrzucasz " + itemDatabase[itemDatabaseId].name + " w ilości: " + amount, Color.Gray);
                        }
                        else
                            window.writeLine(window.textBox1, "Nie możesz wyrzucić takiej ilości przedmiotów", Color.Gray);
                        window.state[2] = false;
                    }
                    else if (!window.state[2])
                    {
                        window.state[2] = true;
                        window.state[0] = false;
                        window.writeLine(window.textBox1, "Ile przedmiotów chcesz wyrzucić?", Color.Red);
                    }
                }
                else
                    window.writeLine(window.textBox1, "Nie widzę tutaj takiego przedmiotu", Color.Gray);
            }
        }
    }
}
