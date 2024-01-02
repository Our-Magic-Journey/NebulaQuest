package xyz.magicjourney.nebulaquest.event;

import java.util.function.Consumer;

/**
 * Interface for returning an instance of the {@link ParameterizedEvent} class without exposing certain methods.
 * 
 * <p><b>NOTE:</b> This interface does not provide access to the {@code emit} and {@code unsubscribeAll} methods 
 * so you can use this interface to safely expose an event in a way that allows only subscribing and unsubscribing from it.</p>
 *
 * @param <T> The type of the event's parameter.
 */
public interface ParameterizedEventGetter<T> {
  
  /**
   * Subscribes to an Event by setting a callback function that will be invoked later 
   * when an event happens.
   * 
   * <p><b>NOTE:</b> A single callback function can subscribe to a particular {@code Event} instance only once.</p>
   * <p><b>Example:</b></p>
   * <pre>
   * // Define a method to be used as a callback
   * Consumer<T> onEvent = (param) => {
   *     System.out.println("Event occurred!");
   * }
   * 
   * 
   * // Subscribe the method for the event
   * event.subscribe(this.onEvent);
   * 
   * // Unsubscribe the method from the event
   * event.unsubscribe(this.onEvent);
   * </pre>
   * 
   * @param callback A callback function to be invoked later.
   */
  void subscribe(Consumer<T> callback);
  
  /**
   * Subscribes to an Event by setting a callback function that will be invoked later 
   * when an event happens.
   * 
   * <p><b>NOTE:</b> A single callback function can subscribe to a particular {@code Event} instance only once.</p>
   * <p><b>Example:</b></p>
   * <pre>
   * // Define a method to be used as a callback
   * Consumer<T> onEvent = (param) => {
   *     System.out.println("Event occurred!");
   * }
   * 
   * // Subscribe the method for the event
   * event.subscribe(this.onEvent);
   * 
   * // Unsubscribe the method from the event
   * event.unsubscribe(this.onEvent);
   * </pre>
   * 
   * @param callback A callback function to be invoked later.
   */
  void unsubscribe(Consumer<T> callback);
}
