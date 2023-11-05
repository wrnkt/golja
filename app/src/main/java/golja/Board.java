package golja;

import java.util.ArrayList;



@FunctionalInterface
interface CellOp {
  void execute(Cell cell);
}


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
    this.stats = new BoardStats(this);
  }

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    setupBoard();
    this.stats = new BoardStats(this);
  }

  public Board(int width, int height, State state) {
    this(width, height);
    setAll(state);
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

  public boolean update(AppliableRule rule) {
    int rows = getHeight();
    int cols = getWidth();

    Board tempBoard = new Board(cols, rows);

    for(int row = 0; row < rows; ++row) {
      for(int col = 0; col < cols; ++col) {
          tempBoard.at(col, row).setState(rule.apply(col, row, this));
      }
    }
    if (copyStateFrom(tempBoard)) {
      this.stats.updateGeneration();
      return true;
    }
    return false;
  }

  public boolean copyStateFrom(Board board) {
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

  public int neighborsWithState(int x, int y, State state) {
    int neighbors = 0;
    
    for(int ny = (y - 1); ny <= (y + 1); ++ny) {
      for(int nx = (x - 1); nx <= (x + 1); ++nx) {
        if( !this.contains(nx, ny) ) continue;
        if( nx == x && ny == y ) continue;
        if( state.equals(this.at(nx, ny).getState()) ) neighbors++;
      }
    }
    return neighbors;
  }

  private boolean isCongruent(Board board) {
    return ( this.getWidth() == board.getWidth() && this.getHeight() == board.getHeight() );
  }

  public void setDead() {
    setAll(State.DEAD);
  }

  public void setAlive() {
    setAll(State.ALIVE);
  }

  public void setAll(State state) {
    operateAll((cell) -> {
      cell.setState(state);
    });
  }

  public void operateAll(CellOp op) {
    for(int y = 0; y < this.getHeight(); ++y) {
      for(int x = 0; x < this.getWidth(); ++x) {
        op.execute(this.at(x, y));
      }
    }
    stats.update();
  }

  public void setWidth(int w) {
    this.width = w;
  }

  public void setHeight(int h) {
    this.height = h;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public BoardStats getStats() {
    return this.stats;
  }

}
