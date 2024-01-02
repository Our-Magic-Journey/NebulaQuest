package xyz.magicjourney.nebulaquest.event;

/**
 * Interface for returning an instance of the {@link Event} class without exposing certain methods.
 * 
 * <p><b>NOTE:</b> This interface does not provide access to the {@code emit} and {@code unsubscribeAll} methods 
 * so you can use this interface to safely expose an event in a way that allows only subscribing and unsubscribing from it.</p>
 */
public interface EventGetter {

  /**
   * Subscribes to an Event by setting a callback function that will be invoked later 
   * when an event happens.
   * 
   * <p><b>NOTE:</b> A single callback function can subscribe to a particular {@code Event} instance only once.</p>
   * <p><b>Example:</b></p>
   * <pre>
   * // Define a method to be used as a callback
   * Runnable onEvent = () => {
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
  void subscribe(Runnable callback);
  
  /**
   * Unsubscribes callback function from the subscribers list, so it will not be called by this event in the future.
   *
   * <p><b>NOTE:</b> To unsubscribe from an event, the callback needs to be a the same reference that was used when registered.
   * Anonymous functions like {@code (int x) -> x*x} or {@code this::function} CANNOT be unsubscribed.</p>
   * 
   * <p><b>Example:</b></p>
   * <pre>
   * // Define a method to be used as a callback
   * Runnable onEvent = () => {
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
   * @param callback The callback function to be removed from subscribers.
   */
  void unsubscribe(Runnable callback);
}