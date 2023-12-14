package xyz.magicjourney.nebulaquest.event;

import java.util.function.Consumer;

/**
 * Simple event system for handling callbacks with a parameter.
 * It collects subscribing callback functions and calls them when the {@code emit} function is called with a specific value.
 * It uses {@link Consumer} as a callback type. If you need callbacks with arguments, consider using {@link Event}.
 * 
 * @param <T> The type of the callbacks' argument.
 */
public class ParameterizedEvent<T> extends AbstractEvent<Consumer<T>> implements ParameterizedEventGetter<T> {
  /**
   * Emits the event, invoking all subscribing callback functions with a specific value.
   *    
   * @param value The value to pass to the subscribing callback functions.
   */
  public void emit(T value) {
    this.callbacks.forEach((callback) -> callback.accept(value));
  }
}