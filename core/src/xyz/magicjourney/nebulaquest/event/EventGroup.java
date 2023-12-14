package xyz.magicjourney.nebulaquest.event;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * A utility class that simplifies the handling of multiple events of different types.
 * Each event type is associated with its own {@link ParameterizedEvent}.
 * 
 * @param <T> The type of event identifiers, should be an enum with particular events.
 * @param <N> The type of callback parameter.
 */
public class EventGroup<T, N> implements EventGroupGetter<T, N> {
  private HashMap<T, ParameterizedEvent<N>> events;

  public EventGroup() {
    this.events = new HashMap<>();
  }

  /**
   * Subscribes to an event by setting a callback function that will be invoked later 
   * when an event happens.
   * 
   * <p><b>NOTE:</b> A single callback function can subscribe to a particular event type only once.</p>
   * 
   * @param event    The type of event to subscribe to.
   * @param callback The callback function to be invoked when the event occurs.
   */
  public void subscribe(T event, Consumer<N> callback) {
    prepareEvent(event);
    events.get(event).subscribe(callback);
  }

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
  public void unsubscribe(T event, Consumer<N> callback) {
    if (hasAnySubscribersForEvent(event)) {
      events.get(event).unsubscribe(callback);
    }
  }

  /**
   * Emits the event, invoking all callback functions that are subscribing to the particular event with a specific value.
   *    
   * @param event The type of event to emit.
   * @param value The value to pass to the subscribing callback functions.
   */
  public void emit(T event, N value) {
    if (hasAnySubscribersForEvent(event)) {
      this.events.get(event).emit(value);
    }
  }

  // Checks if there are any subscribers for a specific event type.
  private boolean hasAnySubscribersForEvent(T event) {
    return events.containsKey(event);
  }

  // Creates a ParameterizedEvent if it does not exist.
  private void prepareEvent(T event) {
    if (!hasAnySubscribersForEvent(event)) {
      events.put(event, new ParameterizedEvent<>());
    }
  }
}
