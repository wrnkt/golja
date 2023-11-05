package golja;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class BoardStats
{
  private AtomicInteger totalCells = new AtomicInteger(0);
  private AtomicInteger generation = new AtomicInteger(0);
  private Map<State, AtomicInteger> statMap;

  private Board board;

  public BoardStats(Board board) {
    this.totalCells = new AtomicInteger(0);
    this.board = board;
    this.statMap = new HashMap<State, AtomicInteger>();
    update();
  }

  public void update() {
    for( State state : State.values() ) this.cellsWithState(state);
    totalCells();
  }

  public int updateGeneration() {
    return generation.incrementAndGet();
  }

  public int generation() {
    return generation.intValue();
  }

  public int aliveCells() {
    return cellsWithState(State.ALIVE);
  }

  public int cellsWithState(State state) {
    int count = 0;
    for(int y = 0; y < this.board.getHeight(); ++y) {
      for(int x = 0; x < this.board.getWidth(); ++x) {
        if( state.equals(this.board.at(x, y).getState()) ) count++;
      }
    }
    statMap.putIfAbsent(state, new AtomicInteger(count));
    return count;
  }
  
  public int totalCells() {
    totalCells = new AtomicInteger(board.getWidth() * this.board.getHeight());
    return totalCells.intValue();
  }

}
