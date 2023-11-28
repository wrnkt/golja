package golja.console;

import java.util.List;

public class Readability {

    static public String toReadableList(List<Integer> numbers)
    {
        int len = numbers.size();
        if ( len == 0 ) return "";
        if ( len == 1 ) return numbers.get(0).toString();
        if ( len == 2 ) return numbers.get(0).toString() + " and " + numbers.get(1).toString();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; ++i) {
            sb.append(numbers.get(i));
            if ( i == len - 2 ) {
                sb.append(", and ");
            } else if ( i == len - 1 ) {
                // last value
            } else {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

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
}
