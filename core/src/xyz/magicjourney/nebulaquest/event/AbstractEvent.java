package xyz.magicjourney.nebulaquest.event;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An abstract class providing a base implementation of an event system.
 * It's based on HashSet, so a single callback function can subscribe to a particular {@code AbstractEvent} instance only once.
 * 
 * <p><b>NOTE:</b> AbstractEvent doesn't contain an emit function because its declaration depends on {@code T} type.</p>
 * 
 * @param <T> The type of callback function. This should be one of Java's functional interfaces
 * such as {@link Runnable}, {@link Consumer}, {@link Supplier}, etc.
 */
public abstract class AbstractEvent<T> {
  protected HashSet<T> callbacks;

  public AbstractEvent() {
    this.callbacks = new HashSet<>();
  }

  /**
   * Subscribes to Event by setting a callback function that will be invoked later 
   * when an event happens.
   * 
   * <p><b>NOTE:</b> A single callback function can subscribe to a particular {@code Event} instance only once.</p>
   * 
   * @param callback A callback function to be invoked later.
   */
  public void subscribe(T callback) {
    callbacks.add(callback);
  }

  /**
   * Unsubscribes callback function from the subscribers list, so it will not be called by this event in the future.
   *
   * <p><b>NOTE:</b> To unsubscribe from an event, the callback needs to be a the same reference that was used when registered.
   * Anonymous functions like {@code (int x) -> x*x} or {@code this::function} CANNOT be unsubscribed.</p>
   * 
   * @param callback The callback function to be removed from subscribers.
   */
  public void unsubscribe(T callback) {
    callbacks.remove(callback);
  }


  /**
   * Unsubscribes all callback functions from the event.
   */
  public void unsubscribeAll() {
    callbacks.clear();
  }

  /**
   * Checks if there are any subscribing callback functions.
   *
   * @return {@code true} if there is at least one subscriber, {@code false} otherwise.
   */
  public boolean hasSubscribers() {
    return this.callbacks.size() >= 1;
  }

  /**
   * Gets the count of subscribing callback functions.
   *
   * @return The number of subscribing callback functions.
   */
  public int subscribersCount() {
    return this.callbacks.size();
  }
}
