package golja;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.ArrayList;


@FunctionalInterface
interface CellSupplier extends Supplier<Cell> {}
@FunctionalInterface
interface CellStateSupplier extends Supplier<State> {}


public final class CellGen {

  private static final int FULL_RANGE_SIZE = 100;

  public static final CellStateSupplier PSEUDO_EVEN_CELL_STATE_SUPPLIER =
  () -> {
    ArrayList<RangeAssociation> ranges = evenedSubRanges(State.values());
    int rand = (int)(Math.random() * FULL_RANGE_SIZE + 1);

    State state = State.DEAD;

    for( int i = 0; i < ranges.size(); ++i ) {
      if( rand >= ranges.get(i).low && rand < ranges.get(i).high ) {
          state = (State) ranges.get(i).o;
          return state;
      }
    }
    return state;
  };


  public static final CellSupplier DEAD_CELL_SUPPLIER =
  () -> {
    return new Cell(State.DEAD);
  };

  public static final CellSupplier LIVE_CELL_SUPPLIER =
  () -> {
    return new Cell(State.ALIVE);
  };

  public static final CellSupplier PSEUDO_EVEN_CELL_SUPPLIER =
  () -> {
    return new Cell(PSEUDO_EVEN_CELL_STATE_SUPPLIER.get());
  };

  // static private ArrayList<RangeAssociation> weightedSubRanges(int numRanges, Object[] objects, int[] weights)
  // {
  //   ArrayList<RangeAssociation> result = new ArrayList<>();
  //
  // }

  static private ArrayList<RangeAssociation> evenedSubRanges(Object[] objects)
  {
    ArrayList<RangeAssociation> result = new ArrayList<>();

    int remaining = FULL_RANGE_SIZE;

    int numRanges = objects.length;
    int batchSize = FULL_RANGE_SIZE / numRanges;
    int batchesProcessed = 0;

    int start = 0;
    int end = 0;

    while(true) {
      if( batchesProcessed < numRanges ) {
        end = start + batchSize;
        result.add(new RangeAssociation(start, end, objects[batchesProcessed]));

        remaining -= batchSize;
        ++batchesProcessed;
        start = end;
      } else {
        if( remaining > 0 ) {
          result.get(batchesProcessed-1).high += remaining;
        }
        result.forEach((range) -> {
          System.out.println("Range:");
          System.out.println("\tlow = " + range.low);
          System.out.println("\thigh = " + range.high);
          System.out.println("\tobj = " + range.o.toString());
        });
        break;
      }
    }
    return result;
  }


}


class RangeAssociation {
  int low;
  int high;
  Object o;

  public RangeAssociation(int l, int h, Object o) {
    this.low = l; this.high = h; this.o = o;
  }
}


