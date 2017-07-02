namespace testConsoleApp
{
    class Items
    {
        public void build(Item[] item, int MAX_ITEMS)
        {
            for (int i = 0; i < MAX_ITEMS; i++)
            {
                item[i] = new Item(-1, "", -1, 0);
            }
            /*Typy:
            *0 - weapon
            *1 - helmet
            *2 - chestplate
            *3 - gloves
            *4 - pants
            *5 - boots
            *6 - shield
            *7 - necklace
            *8 - ring
            *9 - potion
            *10 - throwpotion
            *11 - food
            *12 - arrow
            *13 - misc
             */
            createItem(item, 0, "Chestplate", 2, 15);
            createItem(item, 1, "Helmet", 1, 15);
            createItem(item, 2, "Sword", 0, 15);
            createItem(item, 3, "Pants", 4, 15);
            createItem(item, 4, "Boots", 5, 15);
            createItem(item, 5, "Gloves", 3, 15);
            createItem(item, 6, "Bow", 0, 15);
            createItem(item, 7, "Shield", 6, 15);
            createItem(item, 8, "Helmet2", 1, 15);
            createItem(item, 9, "Helmet3", 1, 15);
        }

        public void createItem(Item[] item, int id, string name, int type, int price)
        {
            item[id].set(id, name, type, price);
        }
    }
}

class Item
{
    public int id { get; set; }
    public string name { get; set; }
    public int type { get; set; }
    public int price { get; set; }


    public Item(int id, string name, int type, int price)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public void set(int id, string name, int type, int price)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;

    }

}
