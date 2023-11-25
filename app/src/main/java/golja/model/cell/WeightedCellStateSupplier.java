package golja.model.cell;

import java.util.Map;

@FunctionalInterface
public interface WeightedCellStateSupplier extends CellStateSupplier<Map<Double, State>> {
  State get(Map<Double, State> m);
}
