package golja;

import golja.util.RandomCollection;

import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.Map;


interface CellStateSupplier<T> {
  State get(T t);
}

@FunctionalInterface
interface SpecifiedCellStateSupplier extends CellStateSupplier<List<State>> {
  State get(List<State> states);
}

@FunctionalInterface
interface WeightedCellStateSupplier extends CellStateSupplier<Map<Double, State>> {
  State get(Map<Double, State> m);
}


public final class CellStateGen {

  public static final SpecifiedCellStateSupplier SPECIFIED_EVEN_PROB_SUPPLIER =
  (states) -> {
    return states.get(ThreadLocalRandom.current().nextInt(states.size()));
  };

  public static final WeightedCellStateSupplier WEIGHTED_PROB_SUPPLIER =
  (map) -> {
    RandomCollection<State> rc = new RandomCollection<>();
    for( Map.Entry<Double, State> entry : map.entrySet() ) {
      rc.add(entry.getKey(), entry.getValue());
    }
    return rc.next();
  };

}

