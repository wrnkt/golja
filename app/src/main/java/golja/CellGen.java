package golja;

import java.util.Map;
import java.util.function.Supplier;

@FunctionalInterface
interface CellSupplier extends Supplier<Cell> {}
@FunctionalInterface
interface WeightedCellSupplier {
  Cell get(Map <State, Integer> weights);
}

public final class CellGen {
  public static final CellSupplier DEAD_CELL_SUPPLIER = () -> new Cell(State.DEAD);

  public static final CellSupplier LIVE_CELL_SUPPLIER = () -> new Cell(State.ALIVE);

  public static final CellSupplier PSEUDO_EVEN_CELL_SUPPLIER =
  () -> new Cell(CellStateGen.PSEUDO_EVEN_CELL_STATE_SUPPLIER.get());

  public static final WeightedCellSupplier WEIGHTED_CELL_SUPPLIER =
  (Map <State, Integer> weights) -> new Cell(CellStateGen.WEIGHTED_CELL_STATE_SUPPLIER.get(weights));

}
