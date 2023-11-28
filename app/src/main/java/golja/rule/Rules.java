package golja.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import golja.model.cell.State;


public final class Rules {

    static Rule DEFAULT_RULE_W = new Rule() {

        @Override
        public String title() {
            return "Default Rule";
        }

        @Override
        public Map<State, List<State>> stateMap() {
            Map<State, List<State>> sm = new HashMap<>();
            sm.put(
                State.ALIVE,
                List.of(
                    State.DEAD,
                    State.DEAD, State.ALIVE, State.ALIVE, State.DEAD, 
                    State.DEAD, State.DEAD, State.DEAD, State.DEAD
                )
            );
            sm.put(
                State.DEAD,
                List.of(
                    State.DEAD,
                    State.DEAD, State.DEAD, State.ALIVE, State.DEAD,
                    State.DEAD, State.DEAD, State.DEAD, State.DEAD
                )
            );
            return sm;
        }
    };

    public static final List<Rule> AVAILABLE_RULES = new ArrayList<>();

    static {
        AVAILABLE_RULES.add(DEFAULT_RULE_W);
    };

    public static AppliableRule DEFAULT_RULE = (c, r, b) -> {
        int aliveNeighbors = b.neighborsWithState(c, r, State.ALIVE);
        if(b.at(c, r).isAlive())
        {
            if(aliveNeighbors == 2 || aliveNeighbors == 3)
                return State.ALIVE;
            else
                return State.DEAD;
        }
        else
        {
            if(aliveNeighbors == 3)
                return State.ALIVE;
            else
                return State.DEAD;
        }
    };
  
}
