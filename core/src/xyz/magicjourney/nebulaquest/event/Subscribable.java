package xyz.magicjourney.nebulaquest.event;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An interface for objects that can be subscribed.
 *
 * @param <T> The type of callback function. This should be one of Java's functional interfaces
 * such as {@link Runnable}, {@link Consumer}, {@link Supplier}, etc.
 */
public interface Subscribable<T> {
  
  /**
   * Subscribes a Subscribable by setting a callback function that will be invoked later 
   * by the object implementing Subscribable when something happens, for example, an event or update.
   * 
   * @param callback A callback function to be invoked later.
   */
  void subscribe(T callback);
  
  /**
   * Unsubscribes a previously subscribed callback function from the object implementing Subscribable.
   *
   * @param callback The callback function to be unsubscribed.
   */
  void unsubscribe(T callback);
}
