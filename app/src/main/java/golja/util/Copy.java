package golja.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import golja.model.cell.Cell;

public final class Copy
{
  public static <K extends Serializable, V extends Serializable> HashMap<K, V> deepCopyMap(Map<K, V> original, Class<? extends K> clazzk, Class<? extends V> clazzv) {
    try {
      HashMap<K, V> copy = new HashMap<>();

      ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(byteOut);

      for( Map.Entry<K, V> entry : original.entrySet() ) {
        out.writeObject(entry.getValue());
        out.flush();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
        copy.put(clazzk.cast(entry.getKey()), clazzv.cast(in.readObject()));
      }
      return copy;
    } catch (Exception e) {
      System.err.println("[ERROR]: Failed to deep copy map.");
      e.printStackTrace();
      return null;
    }
  }

  public static <T> ArrayList<T> deepCopyUsingCopyConstructor(ArrayList<T> original, Class<T> clazz) {
    List<T> list = original.stream().map(element -> {
      try {
        return clazz.getConstructor(clazz).newInstance(element);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }).collect(Collectors.toList());

    return new ArrayList<>(list);
  }

  public static ArrayList<Cell> deepCopyUsingCopyConstructor(ArrayList<Cell> original) {
    return deepCopyUsingCopyConstructor(original, Cell.class);
  }

}
