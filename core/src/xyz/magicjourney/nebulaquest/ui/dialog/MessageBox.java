package xyz.magicjourney.nebulaquest.ui.dialog;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.listener.Listener;

public class MessageBox extends Window {
  protected Skin skin;
  protected Label message;
  protected TextButton okBtn;
  protected Event acceptedEvent;
  protected boolean singleEventEmit;

  public MessageBox(String text, AssetManager assets) {
    super("",  assets.get("skin/ui.skin.json", Skin.class));
    
    this.skin = super.getSkin();
    this.acceptedEvent = new Event();
    this.message = new Label(text, this.skin, "small");
    this.okBtn = new TextButton("Ok", this.skin, "orange");
    this.singleEventEmit = false;

    this.setWidth(300);
    this.setHeight(100);

    this.clear();
    this.pad(6);
    this.add(this.message).expandX().center().row();
    this.add(okBtn).minWidth(100).expandX().center().pad(10);

    this.okBtn.addListener(new Listener(this::handleOkButtonClick));
  }

  public boolean getEventEmitMode() {
    return this.singleEventEmit;
  }

  public void setEventEmitMode(boolean singleEmit) {
    this.singleEventEmit = singleEmit;
  }

  public EventGetter onAccepted() {
    return this.acceptedEvent;
  }

  public void show(Stage stage) {
    stage.addActor(this);
    
    this.center(stage.getWidth(), stage.getHeight());
  }

  protected void center(float width, float height) {
    this.setX((width - this.getWidth()) / 2);
    this.setY((height - this.getHeight()) / 2);
  }

  protected void handleOkButtonClick() {
    this.remove();
    this.acceptedEvent.emit();
    
    if (this.singleEventEmit) {
      this.acceptedEvent.unsubscribeAll();
    }
  }

  public void setText(String text) {
    this.message.setText(text);
  }
}
