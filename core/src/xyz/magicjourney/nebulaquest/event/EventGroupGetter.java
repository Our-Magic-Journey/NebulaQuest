package xyz.magicjourney.nebulaquest.event;

import java.util.function.Consumer;

/**
 * Interface for returning an instance of the {@link EventGroupGetter} class without exposing certain methods.
 * 
 * <p><b>NOTE:</b> This interface does not provide access to the {@code emit} method
 * so you can use this interface to safely expose an event group in a way that allows only subscribing and unsubscribing from it.</p>
 * 
 * @param <T> The type of event identifiers, should be an enum with particular events.
 * @param <N> The type of callback parameter.
 */
public interface EventGroupGetter<T, N> {
  /**
   * Subscribes to an event by setting a callback function that will be invoked later 
   * when an event happens.
   * 
   * <p><b>NOTE:</b> A single callback function can subscribe to a particular event type only once.</p>
   * 
   * @param event    The type of event to subscribe to.
   * @param callback The callback function to be invoked when the event occurs.
   */
  void subscribe(T event, Consumer<N> callback);
  
  /**
   * Unsubscribes a callback function from the subscribers list of the particular event so it will not be called by this event in the future.
   * 
   * <p><b>NOTE:</b> To unsubscribe an event, callback needs to be reference to the same function that was used when registered.
   * So anonymous functions like {@code (int x) -> x*x} CANNOT be unsubscribed.
   * Use {@code this::functionName} instead of {@code () -> this.functionName()}.</p>
   * 
   * @param event    The type of event to unsubscribe from.
   * @param callback The callback function to be removed from subscribers.
   */
  void unsubscribe(T event, Consumer<N> callback);
}