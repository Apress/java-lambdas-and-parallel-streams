package de.muellerbruehl.parallelstreams;

/**
 *
 * @author mmueller
 */
public interface Condition<T> {
  boolean test(T t);
}