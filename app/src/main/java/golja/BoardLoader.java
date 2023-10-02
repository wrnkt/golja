package golja;

import java.util.stream.Stream;
import java.nio.file.*;
import java.io.*;
import java.lang.StringBuilder;

public class BoardLoader
{
    public static char[] aliveChars = {'1','x'};

    public static Cell[][] loadFromFile(Path path) throws IOException
    {
        int rows = 0;
        int columns;
        if(Files.exists(path))
        {
            try(BufferedReader br = new BufferedReader(new FileReader(path.toString()))){

                StringBuilder fileContent = new StringBuilder();
                int c = 0;
                while((c = br.read()) != -1)
                {
                    char character = (char) c;
                    if(character != '\n')
                        fileContent.append(character);
                    else
                        rows++;
                }
                // System.out.println(fileContent.length());

                columns = fileContent.length()/rows;

                System.out.println(columns);
                System.out.println(rows);

                Cell[][] board = new Cell[columns][rows];
                int location = 0;

                for(int row = 0; row < board[0].length; row++)
                {
                    for(int col = 0; col < board.length; col++)
                    {
                        if(new String(aliveChars).indexOf(fileContent.charAt(location)) == -1)
                            board[col][row] = new Cell(State.DEAD);
                        else
                            board[col][row] = new Cell(State.ALIVE);

                        location++;
                    }
                }
                return board;
            }
        }
        else
        {
            throw new IOException();
        }
    }

    public static void animateFromPath(Path path)
    {
        try {
            Cell[][] board = loadFromFile(path);
            try {
                BoardPrinter.animateBoard(board);

            } catch(Exception e) {
                System.out.println("Failed to animate board.");
            }
        } catch(IOException e) {
            System.out.println("Couldn't load from path.");
        }
    }
}
