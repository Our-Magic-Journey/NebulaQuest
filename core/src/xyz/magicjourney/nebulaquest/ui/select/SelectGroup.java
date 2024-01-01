package xyz.magicjourney.nebulaquest.ui.select;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.event.ParameterizedEvent;
import xyz.magicjourney.nebulaquest.event.ParameterizedEventGetter;

public class SelectGroup<T extends Button> extends ButtonGroup<T> {
  protected ParameterizedEvent<T> selectEvent;
  protected Event unselectEvent;

  public SelectGroup() {
    this(0, 1);
  }

  public SelectGroup(int min, int max) {
    super();

    this.setMinCheckCount(min);
    this.setMaxCheckCount(max);

    this.selectEvent = new ParameterizedEvent<>();
    this.unselectEvent = new Event();
  }

  @Override
  protected boolean canCheck(T button, boolean newState) {
    boolean result = super.canCheck(button, newState);

    if (this.getChecked() != null) {
      this.selectEvent.emit(this.getChecked()); 
    }
    else {
      this.unselectEvent.emit();
    }

    return result;
  }

  public T get(int index) {
    return this.getButtons().get(index);
  }

  public int length() {
    return this.getButtons().size;
  }

  public ParameterizedEventGetter<T> onSelect() {
    return this.selectEvent;
  }

  public EventGetter onUnselect() {
    return this.unselectEvent;
  }
}