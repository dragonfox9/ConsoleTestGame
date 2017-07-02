using Game.NPC;
using Game.Quest.List;
using Game.Quest.System;
namespace testConsoleApp
{
    partial class Main
    {
        public const int MAX_WIDTH = 2;
        public const int MAX_HEIGHT = 2;
        public const int MAX_ITEMS = 200;
        public const int MAX_INV_ITEMS = 200;
        public const int MAX_NPCS = 2;
        public const int MAX_TYPES = 9;
        public static int x = 0, y = 0;
        public static Item[] itemDatabase = new Item[MAX_ITEMS];
        public static Items items = new Items();

        public static Room[,] roomDatabase = new Room[MAX_WIDTH, MAX_HEIGHT];
        public static Rooms rooms = new Rooms();

        public static Player player = new Player();
        public static Equipment eq = new Equipment();

        public static NPC[] npc = new NPC[MAX_NPCS];
        public static NPCs npcs = new NPCs();

        //quest section
        public static Action _Action = new Action();
        public static Kowal _Kowal = new Kowal();

        public static void build()
        {
            rooms.build(roomDatabase, MAX_WIDTH, MAX_HEIGHT, MAX_ITEMS, MAX_NPCS);
            items.build(itemDatabase, MAX_ITEMS);
            npcs.build(npc, MAX_NPCS, MAX_ITEMS, MAX_TYPES);
            eq.build(MAX_TYPES);
            player.build(MAX_INV_ITEMS);

            //npc Quest section
            _Kowal.initalize();
        }
    }
}
