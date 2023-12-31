package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.event.ParameterizedEventGetter;
import xyz.magicjourney.nebulaquest.listener.Listener;
import xyz.magicjourney.nebulaquest.ui.select.SelectGroup;

public class MenuPanel extends Panel {
  protected TextButton menu;
  protected SelectGroup<TextButton> buttons;
  protected String[] options;
  protected Event menuClickEvent;

  public MenuPanel(AssetManager assets) {
    super(416, 68, assets);

    this.menuClickEvent = new Event();

    this.options = new String[] {"Me", "Bank", "Players"};
    this.menu = new TextButton("menu", this.skin, "text");
    this.menu.addListener(new Listener(this.menuClickEvent::emit));
    
    this.buttons = new SelectGroup<>();

    for (String text : this.options) {
      TextButton button = new TextButton(text, this.skin, "select");
      this.buttons.add(button);
      this.content.add(button).expandX().center();
    }

    this.buttons.setChecked("Bank");
    this.content.add(this.menu).expandX().center();
    this.content.center();
  }

  public void unselect() {
    this.buttons.uncheckAll();
  }

  public EventGetter onMenuClick() {
    return this.menuClickEvent;
  }

  public ParameterizedEventGetter<TextButton> onSelect() {
    return this.buttons.onSelect();
  }

  public EventGetter onUnselect() {
    return this.buttons.onUnselect();
  }
}
