using System;
using System.Drawing;
using System.Windows.Forms;
using System.Threading.Tasks;
using System.Linq;
using Game.IO;

namespace testConsoleApp
{
    public partial class Window : Form
    {
        public bool textScroll;
        public bool timer2Start;
        public bool[] state = new bool[8];
        public bool[] menu = new bool[2];
        public string itemName = "";
        public string roboText;
        public int counter = 0;
        public bool gameLoaded;



        public Window()
        {
            InitializeComponent();
            textBox2.Dock = DockStyle.Bottom;
            textBox2.Anchor = AnchorStyles.Bottom & AnchorStyles.Left;
            textBox2.Font = new Font(textBox2.Font.FontFamily, 10);
            textBox1.Cursor = Cursors.Arrow;
            textBox1.Font = new Font(textBox2.Font.FontFamily, 10);
            state[0] = true;
            menu[0] = true;
        }



        private void Form1_Load(object sender, EventArgs e)
        {
            writeLine(textBox1, "New game [N]", Color.Gray);
            writeLine(textBox1, "Load game [L]", Color.Gray);
        }


        private void timer1_Tick(object sender, EventArgs e)
        {
            textBox2.Focus();
            if (menu[1])
            {
                textBox2.Text = "";
                menu[1] = false;
            }
            if (textScroll)
            {
                leftBottomBox.SelectionStart = leftBottomBox.Text.Length;
                leftBottomBox.ScrollToCaret();
                textScroll = false;
            }
            if (gameLoaded)
            {
                rightBottomBox.Location = Main.roomDatabase[Main.x, Main.y].bPoint;
                leftBottomBox.Location = Main.roomDatabase[Main.x, Main.y].aPoint;
            }
        }


        private void textBox2_KeyDown(object sender, KeyEventArgs e)
        {
            if (!menu[0])
            {
                if (e.KeyData == Keys.Enter)
                {
                    string s = textBox2.Text.ToLower();
                    textBox2.Text = "";
                    textBox2.SelectionStart = textBox2.Text.Length;
                    textBox2.ScrollToCaret();
                    textScroll = true;
                    e.Handled = true;
                    e.SuppressKeyPress = true;
                    if (state[0])
                    {
                        SavePlayer.saveData(Main.itemDatabase, Main.roomDatabase, Main.player, Main.eq, Main.MAX_ITEMS, Main.MAX_INV_ITEMS, Main.MAX_WIDTH, Main.MAX_HEIGHT, Main.MAX_TYPES);
                        if (s == "w")
                        {
                            if (Main.x < Main.MAX_WIDTH - 1)
                            {
                                Main.x++;
                                Display.DisplayMainLoc(this);

                            }
                            else
                                writeLine(leftBottomBox, "Nie możesz iść w tę stronę", Color.Red);
                        }
                        else if (s == "e")
                        {
                            if (Main.x > 0)
                            {
                                Main.x--;
                                Display.DisplayMainLoc(this);
                            }
                            else
                                writeLine(leftBottomBox, "Nie możesz iść w tę stronę", Color.Red);
                        }
                        else if (s == "s")
                        {
                            if (Main.y < Main.MAX_HEIGHT - 1)
                            {
                                Main.y++;
                                Display.DisplayMainLoc(this);
                            }
                            else
                                writeLine(leftBottomBox, "Nie możesz iść w tę stronę", Color.Red);
                        }
                        else if (s == "n")
                        {
                            if (Main.y > 0)
                            {
                                Main.y--;
                                Display.DisplayMainLoc(this);
                            }
                            else
                                writeLine(leftBottomBox, "Nie możesz iść w tę stronę", Color.Red);
                        }
                        else if (s.Length > 8 && s.Substring(0, 8).Equals("zagadaj "))
                        {
                            if (s.Substring(0, s.IndexOf(' ')).Equals("zagadaj"))
                            {
                                if (s.Substring(s.IndexOf(' ')).Length > 1)
                                {
                                    string name = s.Substring(s.IndexOf(' ') + 1);
                                    Main._Action.Talk(this, name, s);
                                }
                            }
                        }
                        else if (s.Length > 6 && s.Substring(0, 6).Equals("sklep "))
                        {
                            if (s.Substring(0, s.IndexOf(' ')).Equals("sklep"))
                            {
                                if (s.Substring(s.IndexOf(' ')).Length > 1)
                                {
                                    string npcName = s.Substring(s.IndexOf(' ') + 1);
                                    Main.npcs.list(this, npcName, Main.npc, Main.itemDatabase, Main.roomDatabase, Main.player);
                                }
                            }
                        }
                        else if (s == "o")
                        {
                            for (int i = 0; i < Main.MAX_TYPES; i++)
                            {
                                if (Main.eq.eq[i] != -1) { writeLine(leftBottomBox, "Przedmiot założony na slocie " + i + ", to: " + Main.itemDatabase[Main.eq.eq[i]].name, Color.Gray); }
                            }
                        }
                        else if (s == "i")
                        {
                            state[5] = true;
                            state[0] = false;
                            textBox4.Visible = true;
                            textBox4.Text = "";
                            write(textBox4, "[", Color.White);
                            write(textBox4, "B", Color.Green);
                            write(textBox4, "]", Color.White);
                            write(textBox4, " Cofnij", Color.White);
                            leftBottomBox.Visible = false;
                            rightBottomBox.Visible = false;
                            Display.displayInventory(this);
                        }
                        else if (s == "rozejrzyj sie")
                        {
                            state[4] = true;
                            state[0] = false;
                            Display.DisplayLookAround(this);
                            textBox4.Visible = true;
                            textBox4.Text = "";
                            write(textBox4, "[", Color.White);
                            write(textBox4, "B", Color.Green);
                            write(textBox4, "]", Color.White);
                            write(textBox4, " Cofnij", Color.White);
                            leftBottomBox.Visible = false;
                            rightBottomBox.Visible = false;
                        }
                        else writeLine(leftBottomBox, "Nie rozumiem co robię...", Color.Gray);
                    }
                    else
                        changeAction(s);
                }
            }
            else
            {
                if (e.KeyData == Keys.N)
                {
                    Main.build();
                    Display.DisplayMainLoc(this);
                    menu[0] = false;
                    menu[1] = true;
                    gameLoaded = true;
                }
                else if (e.KeyData == Keys.L)
                {
                    Main.build();
                    Display.DisplayMainLoc(this);
                    LoadPlayer.LoadData(this, Main.itemDatabase, Main.roomDatabase, Main.player, Main.eq, Main.MAX_ITEMS, Main.MAX_INV_ITEMS, Main.MAX_WIDTH, Main.MAX_HEIGHT, Main.MAX_TYPES);
                    menu[0] = false;
                    menu[1] = true;
                    gameLoaded = true;
                }
            }
        }

        private void changeAction(string s)
        {
            if (state[1])
            {
                if (s != "") { Main.player.addInvItems(this, Main.itemDatabase, Main.roomDatabase, Main.x, Main.y, itemName, s, Main.MAX_ITEMS, Main.MAX_INV_ITEMS); }
            }
            else if (state[2])
            {
                if (s != "") { Main.player.removeInvItems(this, Main.itemDatabase, Main.roomDatabase, Main.x, Main.y, itemName, s, Main.MAX_INV_ITEMS); }
            }
            else if (state[3])
            {
                if (s != "") { Main._Kowal.basicInfo(this, s); }
            }
            else if(state[4])
            {
                if (s.Length > 8 && s.Substring(0, 8).Equals("podnies "))
                {
                    if (s.Substring(0, s.IndexOf(' ')).Equals("podnies"))
                    {
                        if (s.Substring(s.IndexOf(' ')).Length > 1)
                        {
                            itemName = s.Substring(s.IndexOf(' ') + 1);
                            Main.player.addInvItems(this, Main.itemDatabase, Main.roomDatabase, Main.x, Main.y, itemName, s, Main.MAX_ITEMS, Main.MAX_INV_ITEMS);
                        }
                    }
                }
                else if(s == "b")
                {
                    state[4] = false;
                    state[0] = true;
                    Display.DisplayMainLoc(this);
                    textBox4.Visible = false;
                    leftBottomBox.Visible = true;
                    rightBottomBox.Visible = true;
                }
            }
            else if(state[5])
            {
                if (s.Length > 7 && s.Substring(0, 7).Equals("wyrzuc "))
                {
                    if (s.Substring(0, s.IndexOf(' ')).Equals("wyrzuc"))
                    {
                        if (s.Substring(s.IndexOf(' ')).Length > 1)
                        {
                            string itemName = s.Substring(s.IndexOf(' ') + 1);
                            Main.player.removeInvItems(this, Main.itemDatabase, Main.roomDatabase, Main.x, Main.y, itemName, s, Main.MAX_INV_ITEMS);
                        }
                    }
                }
                else if (s.Length > 6 && s.Substring(0, 6).Equals("zaloz "))
                {
                    if (s.Substring(0, s.IndexOf(' ')).Equals("zaloz"))
                    {
                        if (s.Substring(s.IndexOf(' ')).Length > 1)
                        {
                            string itemName = s.Substring(s.IndexOf(' ') + 1);
                            Main.eq.set(this, Main.itemDatabase, Main.roomDatabase, Main.player, itemName, Main.x, Main.y, Main.MAX_INV_ITEMS, Main.MAX_ITEMS, Main.MAX_TYPES);
                        }
                    }
                }
                else if (s.Length > 8 && s.Substring(0, 8).Equals("zdejmij "))
                {
                    if (s.Substring(0, s.IndexOf(' ')).Equals("zdejmij"))
                    {
                        if (s.Substring(s.IndexOf(' ')).Length > 1)
                        {
                            string itemName = s.Substring(s.IndexOf(' ') + 1);
                            Main.eq.remove(this, Main.itemDatabase, Main.roomDatabase, Main.player, itemName, Main.x, Main.y, Main.MAX_INV_ITEMS, Main.MAX_ITEMS, Main.MAX_TYPES);
                        }
                    }
                }
                else if (s == "b")
                {
                    state[5] = false;
                    state[0] = true;
                    Display.DisplayMainLoc(this);
                    textBox4.Visible = false;
                    leftBottomBox.Visible = true;
                    rightBottomBox.Visible = true;
                }
            }
            else if(state[6])
            {
                if(s == "b")
                {
                    state[6] = false;
                    state[0] = true;
                    Display.DisplayMainLoc(this);
                    textBox4.Visible = false;
                    leftBottomBox.Visible = true;
                    rightBottomBox.Visible = true;
                }
            }
        }

        public void write(RichTextBox box,string text, Color color)
        {
            box.SelectionStart = box.TextLength;
            box.SelectionLength = 0;

            box.SelectionColor = color;
            box.AppendText(text);
            box.SelectionColor = box.ForeColor;
        }

        public void writeLine(RichTextBox box, string text, Color color)
        {
            box.SelectionStart = box.TextLength;
            box.SelectionLength = 0;

            box.SelectionColor = color;
            box.AppendText(text + "\r\n");
            box.SelectionColor = box.ForeColor;
        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void timer2_Tick(object sender, EventArgs e)
        {
            if (timer2Start)
            {
                char[] ch = roboText.ToCharArray();
                write(leftBottomBox,"" + ch[counter], Color.White);
                counter++;
                if(counter == 1)
                {
                    textScroll = true;
                }
                if (counter == ch.Length)
                {
                    textScroll = true;
                    writeLine(leftBottomBox,"", Color.White);
                    timer2Start = false;
                    counter = 0;

                }
            }
        }
    }
}
