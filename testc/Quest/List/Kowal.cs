using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using testConsoleApp;

namespace Game.Quest.List
{
    class Kowal
    {
        static bool[] state = new bool[3];
        public bool active;
        public void initalize()
        {
            state[0] = true;
        }
        public void basicInfo(Window window, string s)
        {
            if (state[0] && active)
            {
                window.state[3] = true;
                window.state[0] = false;
                window.writeLine(window.leftBottomBox, "1. Jak idą prace w kuźni?", Color.White);
                window.writeLine(window.leftBottomBox, "2. Co masz na sprzedaż?", Color.Green);
                state[0] = false;
                active = false;
                changeState(true);
            }
            if (state[1] && s == "1")
            {
                window.roboText = "Jakoś idzie, dziś nie miałem zbyt dużo klientów...";
                window.timer2Start = true;
                window.state[3] = false;
                window.state[0] = true;
                state[0] = true;
                changeState(false);
            }
            else if (state[2] && s == "2")
            {
                window.roboText = "Sklep jest aktualnie zamknięty";
                window.timer2Start = true;
                window.state[3] = false;
                window.state[0] = true;
                state[0] = true;
                changeState(false);
            }
        }

        private void changeState(bool status)
        {
            if (!status)
            {
                for (int i = 1; i < state.Length; i++)
                {
                    state[i] = false;
                }
            }
            if (status)
            {
                for (int i = 1; i < state.Length; i++)
                {
                    state[i] = true;
                }
            }
        }
    }
}
