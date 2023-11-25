package golja.console;

public final class Util
{
    public static void clearTerm()
    {
        System.out.print("\033[H\033[2J");
    }
}
