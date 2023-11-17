package golja.model.cell;

import java.util.List;

@FunctionalInterface
public interface SpecifiedCellStateSupplier extends CellStateSupplier<List<State>> {
  State get(List<State> states);
}
