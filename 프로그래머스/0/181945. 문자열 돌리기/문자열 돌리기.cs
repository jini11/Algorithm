using System;

public class Example
{
    public static void Main()
    {
        char[] s;

        Console.Clear();
        s = Console.ReadLine().ToCharArray();
        
        for (int i = 0; i < s.Length; i++) {
            Console.WriteLine(s[i]);
        }
    }
}