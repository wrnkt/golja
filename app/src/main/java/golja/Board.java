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
    setupBoard();
    this.stats = new BoardStats();
  }

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    setupBoard();
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

  public Cell at(int x, int y) {
    return this.board.get(y).get(x);
  }

  public boolean contains(int x, int y) {
    return (
      ( (y >= 0) && (y < this.board.size()) )
       &&
      ( (x >= 0) && (x < this.board.get(y).size()) )
    );
  }

  public boolean update(Board board) {
    if( !isCongruent(board) ) {
      return false;
    }
    
    for(int y = 0; y < this.getHeight(); ++y) {
      for(int x = 0; x < this.getWidth(); ++x) {
        this.at(x, y).setState(board.at(x, y).getState());
      }
    }

    return true;
  }

  private boolean isCongruent(Board board) {
    return ( this.getWidth() == board.getWidth() && this.getHeight() == board.getHeight() );
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
