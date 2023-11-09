package golja;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CellStateGenTest {

  //@Test
  void standardGameStateEvenSupplier_SuppliesEvenDist() {

    final int numGenerated = 100;
    final List<State> stateList = CellStateGen.STANDARD_GAME_STATES;
    final int numStates = CellStateGen.STANDARD_GAME_STATES.size();

    Stream<State> states = Stream.generate(CellStateGen.STANDARD_GAME_STATE_EVEN_SUPPLIER::get);

    for( State state : stateList ) {
      List<State> theseStates = states
        .filter((cState) -> cState == state).collect(Collectors.toList());

      System.out.println(theseStates.size());

      final int mOE = 5;
      int lowBound = (numGenerated / numStates) - mOE;
      int highBound = (numGenerated / numStates) + mOE;

      assertTrue( theseStates.size() >= lowBound && theseStates.size() <= highBound );
    }
  }

  
}
