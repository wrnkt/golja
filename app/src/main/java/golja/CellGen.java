package golja;

import java.util.function.Supplier;
import golja.CellStateGen;

@FunctionalInterface
interface CellSupplier extends Supplier<Cell> {}

public final class CellGen {
  public static final CellSupplier DEAD_CELL_SUPPLIER = () -> new Cell(State.DEAD);

  public static final CellSupplier LIVE_CELL_SUPPLIER = () -> new Cell(State.ALIVE);

  public static final CellSupplier PSEUDO_EVEN_CELL_SUPPLIER =
  () -> new Cell(CellStateGen.PSEUDO_EVEN_CELL_STATE_SUPPLIER.get());

  public static final CellSupplier WEIGHTED_CELL_SUPPLIER =
  () -> new Cell(CellStateGen.PSEUDO_EVEN_CELL_STATE_SUPPLIER.get());

}
