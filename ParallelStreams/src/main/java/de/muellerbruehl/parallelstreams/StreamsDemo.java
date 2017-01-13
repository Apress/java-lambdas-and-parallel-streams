/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.parallelstreams;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 *
 * @author mmueller
 */
public class StreamsDemo {

  public static void main(String[] args) {

    showParallelProblem();
    System.out.println("started");
    Persons persons = Persons.getInstance();
    System.out.println("created " + persons.getPersons().size() + " persons.");
    countVendors(persons.getPersons());
  }

  private static void showParallelProblem() {
    long[] result = new long[1];

    for (int i = 0; i < 10; i++) {
      result[0] = 0;
      LongStream.range(0, 1000).forEach(n -> result[0] = (result[0] + n) * n);
      System.out.println("serial: " + result[0]);
    }

    for (int i = 0; i < 10; i++) {
      result[0] = 0;
      LongStream.range(0, 1000).parallel().forEach(n -> result[0] = (result[0] + n) * n);
      System.out.println("parallel: " + result[0]);
    }

    for (int i = 0; i < 10; i++) {
      result[0] = 0;
      LongStream.range(0, 1000).parallel().forEachOrdered(n -> result[0] = (result[0] + n) * n);
      System.out.println("parallel ordered: " + result[0]);
    }

    long reduce = LongStream.range(0, 1000).reduce(0, (a, c) -> (a + c) * c);
    System.out.println("reduce: " + reduce);
  }

  private static void countVendors(List<Person> persons) {
    invokeMethod("Vendors by counting list", () -> getVendorCount(persons));
    
    Supplier<Long> countByStream = () -> persons.stream().filter(p -> p.isVendor()).collect(Collectors.counting());
    invokeMethod("Vendors by Stream", countByStream);
    Supplier<Long> countByParallelStream = () -> persons.parallelStream().filter(p -> p.isVendor()).collect(Collectors.counting());
    invokeMethod("Vendors by ParallelStream", countByParallelStream);
  }

  private static long getVendorCount(List<Person> persons) {
    int counter = 0;
    for (Person person : persons) {
      if (person.isVendor()) {
        counter++;
      }
    }
    return counter;
  }

  private static <T> T invokeMethod(String info, Supplier<T> method) {
    long start = System.nanoTime();
    T result = method.get();
    long elapsedTime = System.nanoTime() - start;
    System.out.println(info + ": " + elapsedTime / 1000000);
    return result;
  }

}
