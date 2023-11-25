package golja.model.cell;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import golja.util.RandomCollection;

public final class CellStateGen
{
  public static final List<State> STANDARD_GAME_STATES = List.of(State.ALIVE, State.DEAD);

  public static final SpecifiedCellStateSupplier SPECIFIED_EVEN_PROB_SUPPLIER =
  (states) -> {
    return states.get(ThreadLocalRandom.current().nextInt(states.size()));
  };

  public static final Supplier<State> STANDARD_GAME_STATE_EVEN_SUPPLIER =
  () -> {
      return SPECIFIED_EVEN_PROB_SUPPLIER.get(STANDARD_GAME_STATES);
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

