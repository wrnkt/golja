package golja.model.cell;

@FunctionalInterface
public interface CellOp {
  void execute(Cell cell);
}

