package golja;

import java.util.ArrayList;
import java.util.stream.IntStream;


public class Board {

  protected int DEFAULT_WIDTH = 50;
  protected int DEFAULT_HEIGHT = 50;

  private int width = 0;
  private int height = 0;

  private ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();
  private BoardStats stats;

  public Board() {
    this.width = DEFAULT_WIDTH;
    this.height = DEFAULT_HEIGHT;
    this.stats = new BoardStats();
  }

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    this.stats = new BoardStats();
  }

  private void setupBoard() {
    for(int i = 0; i < this.height; ++i) {
      this.board.add(new ArrayList<Cell>(this.width));
      for( int j = 0; j < this.width; ++j) {
        this.board.get(i).add(new Cell());
      }
    }
  }

  public void setWidth(int w) {
    this.width = w;
  }

  public int getWidth() {
    return this.width;
  }

  public void setHeight(int h) {
    this.height = h;
  }

  public int getHeight() {
    return this.height;
  }

}
