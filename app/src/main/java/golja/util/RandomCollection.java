package golja.util;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<T> {
  private final NavigableMap<Double, T> map = new TreeMap<Double, T>();
  private final Random rand;
  private double total = 0;

  public RandomCollection() {
    this(new Random());
  }

  public RandomCollection(Random rand) {
    this.rand = rand;
  }

  public RandomCollection<T> add(double weight, T res) {
    if( weight <= 0 ) return this;
    total += weight;
    map.put(total, res);
    return this;
  }

  public T next() {
    double value = rand.nextDouble() * total;
    return map.higherEntry(value).getValue();
  }
}
