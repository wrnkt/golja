package golja.model.cell;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface CellStateSupplier<T> {
  State get(T t);
}

