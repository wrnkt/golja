package golja.rule;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import golja.util.natlang.Readability;
import golja.model.cell.State;

public class Rule {

    public Map<State, List<State>> stateMap() {
        return null;
    }

    public String title() {
        return null;
    }
    
    public String description() {
        List<State> nextStateWhenAlive = stateMap().get(State.ALIVE);
        List<State> nextStateWhenDead = stateMap().get(State.DEAD);
        List<Integer> neighborsToKeepAlive = IntStream.range(0, nextStateWhenAlive.size())
            .filter(
                (idx) -> nextStateWhenAlive.get(idx).equals(State.ALIVE)
            )
            .boxed().toList();
        List<Integer> neighborsToResurrect = IntStream.range(0, nextStateWhenDead.size())
            .filter(
                (idx) -> nextStateWhenDead.get(idx).equals(State.ALIVE)
            )
            .boxed().toList();

        StringBuilder sb = new StringBuilder();
        sb.append("A dead cell will become alive if "
            + Readability.toListWithConjunction(neighborsToResurrect, "or")
            + " neighbors are present.\n");
        sb.append("A cell will stay alive if "
            + Readability.toListWithConjunction(neighborsToKeepAlive, "or")
            + " neighbors are present.\n");

        return sb.toString();
    }

    public AppliableRule rule() {
        return (col, row, board) -> {
            int aliveNeighbors = board.neighborsWithState(col, row, State.ALIVE);
            Map<State, List<State>> stateMap = stateMap();
            List<State> nextStates = stateMap.get(board.at(col, row).getState());
            return nextStates.get(aliveNeighbors);
        };
    }
}
