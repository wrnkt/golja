package golja;

import java.util.stream.Stream;
import java.nio.file.*;
import java.io.*;
import java.lang.StringBuilder;

public class BoardLoader
{
    public static char[] aliveChars = {'1','x'};

    public static Board loadFromFile(Path path) throws IOException
    {
        int rows = 0;
        int columns = 0;
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

                columns = fileContent.length()/rows;

                System.out.println(columns);
                System.out.println(rows);

                Board board = new Board(columns, rows);
                int location = 0;

                for(int row = 0; row < board.getHeight(); row++)
                {
                    for(int col = 0; col < board.getWidth(); col++)
                    {
                        if(new String(aliveChars).indexOf(fileContent.charAt(location)) == -1)
                            board.at(col, row).setCellState(State.DEAD);
                        else
                            board.at(col, row).setCellState(State.ALIVE);

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
