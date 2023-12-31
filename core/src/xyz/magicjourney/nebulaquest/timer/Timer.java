package xyz.magicjourney.nebulaquest.timer;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;

public class Timer {
  private float timeLeft;
  private float waitTime;
  private boolean paused;
  private boolean autoRepeat;
  private Event timeoutEvent;

  public Timer(float waitTime) {
    this(waitTime, false);
  }

  public Timer(float waitTime, boolean autoRepeat) {
    this.waitTime = waitTime;
    this.timeLeft = waitTime;
    this.autoRepeat = autoRepeat;
    this.paused = true;
    this.timeoutEvent = new Event();
  }

  public EventGetter onTimeout() {
    return timeoutEvent;
  }

  public void clear() {
    this.timeoutEvent.unsubscribeAll();
  }

  public void reset() {
    timeLeft = waitTime;
    paused = false;
  }

  public void pause() {
    paused = true;
  }

  public void resume() {
    paused = false;
  }

  public void act(float deltaTime) {
    if (paused) {
      return;
    }

    timeLeft = Math.max(0, timeLeft - deltaTime);

    if (timeLeft == 0) {
      timeoutEvent.emit();

      if (autoRepeat) {
        this.reset();
      }
      else {
        this.pause();
      }
    }
  }
}