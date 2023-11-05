package golja;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Map;


@FunctionalInterface
interface CellStateSupplier extends Supplier<State> {}
@FunctionalInterface
interface WeightedCellStateSupplier {
  State get(Map<State, Integer> m);
}

class RangeAssociation {
  int low;
  int high;
  Object o;

  public RangeAssociation(int l, int h, Object o) {
    this.low = l; this.high = h; this.o = o;
  }
}


public final class CellStateGen {

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

  public static final WeightedCellStateSupplier WEIGHTED_CELL_STATE_SUPPLIER =
  (weightMap) -> {
    ArrayList<RangeAssociation> ranges = weightedSubRanges(weightMap);
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


  static private ArrayList<RangeAssociation> weightedSubRanges(Map<State, Integer> weightMap)
  {
    ArrayList<RangeAssociation> result = new ArrayList<>();

    Integer totalWeight = weightMap.entrySet().stream()
                            .map(Map.Entry::getValue)
                            .reduce(0, Integer::sum);

    Map<Object, Double> adjustedWeights = weightMap.entrySet().stream()
                                            .collect(Collectors.toMap(
                                              entry -> entry.getKey(),
                                              entry -> (entry.getValue().doubleValue() / totalWeight.doubleValue())));

    int numEntries = weightMap.size();
    int remaining = FULL_RANGE_SIZE;
    int used = 0;

    int end = 0;
    int start = end;

    for( Map.Entry<Object, Double> entry : adjustedWeights.entrySet() ) {
        end = start + (int) (entry.getValue() * FULL_RANGE_SIZE);
        result.add(new RangeAssociation(start, end, entry.getKey()));

        used += end - start;
        start = end;
    }

    if( remaining > used ) {
      // NOTE: introduces slight bias weighting last range higher
      result.get(numEntries-1).high += remaining = used;
    }

    /*
    int i = 0;
    Set<Object> weightSet = weightMap.keySet();
    Iterator<Object> it = weightSet.iterator();
    while (it.hasNext())
    {
      Object obj = (Object) it.next();

      // do something
      if( remaining > used ) {
        RangeAssociation ra = result.stream()
          .filter((association) -> association.o.equals(obj))
          .findFirst().orElseGet(() -> result.get(i));
        ra.

      } else break;

      if (!it.hasNext())
        it = weightSet.iterator();
    }
    */
    return result;
  }

  static private ArrayList<RangeAssociation> evenedSubRanges(Object[] objects)
  {
    ArrayList<RangeAssociation> result = new ArrayList<>();

    int remaining = FULL_RANGE_SIZE;

    int numRanges = objects.length;
    int batchSize = FULL_RANGE_SIZE / numRanges;
    int batchesProcessed = 0;

    int end = 0;
    int start = end;

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

