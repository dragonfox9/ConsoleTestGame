using System.Text;
using System.IO;

namespace Game.IO
{
    class LoadTxt
    {

        public static string[] Load(string path)
        {
            string[] str = File.ReadAllLines(path, Encoding.UTF8);
            return str;
        }
    }
}
