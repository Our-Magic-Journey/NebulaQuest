package xyz.magicjourney.nebulaquest.event;

/**
 * Simple event system. It collects subscribing callback functions and calls them when the {@code emit} function is called.
 * It uses {@link Runnable} as a callback type. If you need callbacks with arguments, consider using {@link ParameterizedEvent}.
 */
public class Event extends AbstractEvent<Runnable> implements EventGetter {
  /**
   * Emits the event, invoking all subscribing callback functions.
   */
  public void emit() {
    this.callbacks.forEach(Runnable::run);
  }
}