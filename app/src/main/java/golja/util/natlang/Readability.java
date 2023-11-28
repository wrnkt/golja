package golja.util.natlang;

import java.util.List;

public class Readability {

    static public String toListWithConjunction(List<Integer> numbers, String conjunction)
    {
        int len = numbers.size();
        if ( len == 0 ) return "";
        if ( len == 1 ) return numbers.get(0).toString();
        if ( len == 2 ) return numbers.get(0).toString() + " " + conjunction + " " + numbers.get(1).toString();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; ++i) {
            sb.append(numbers.get(i));
            if ( i == len - 2 ) {
                sb.append(", " + conjunction + " ");
            } else if ( i == len - 1 ) {
                // last value
            } else {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    static public String toListAnd(List<Integer> numbers)
    {
        return toListWithConjunction(numbers, "and");
    }

    static public String toListOr(List<Integer> numbers)
    {
        return toListWithConjunction(numbers, "or");
    }
}
