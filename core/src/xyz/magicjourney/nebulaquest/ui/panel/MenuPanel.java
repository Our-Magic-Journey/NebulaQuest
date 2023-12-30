package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.event.ParameterizedEvent;
import xyz.magicjourney.nebulaquest.event.ParameterizedEventGetter;
import xyz.magicjourney.nebulaquest.listener.Listener;

public class MenuPanel extends Panel {
  protected TextButton menu;
  protected ButtonGroup<TextButton> buttons;
  protected String[] options;
  protected Event menuClickEvent;
  protected ParameterizedEvent<String> selectEvent; 

  public MenuPanel(AssetManager assets) {
    super(416, 68, assets);

    this.menuClickEvent = new Event();
    this.selectEvent = new ParameterizedEvent<>();

    this.options = new String[] {"Me", "Bank", "Players"};
    this.menu = new TextButton("menu", this.skin, "text");
    this.menu.addListener(new Listener(this.menuClickEvent::emit));
    this.buttons = new ButtonGroup<>();

    for (String text : this.options) {
      TextButton button = new TextButton(text, this.skin, "select");
      button.addListener(new Listener((event, actor) -> selectEvent.emit(actor.getName())));

      this.buttons.add(button);
      this.content.add(button).expandX().center();
    }

    this.buttons.setMaxCheckCount(1);
    this.buttons.setMinCheckCount(1);
    this.buttons.setChecked("Bank");
    
    this.content.add(this.menu).expandX().center();
    this.content.center();
    this.content.pad(6, 6, 6, 6);
  }

  public EventGetter onMenuClick() {
    return menuClickEvent;
  }

  public ParameterizedEventGetter<String> onSelect() {
    return selectEvent;
  }
}
