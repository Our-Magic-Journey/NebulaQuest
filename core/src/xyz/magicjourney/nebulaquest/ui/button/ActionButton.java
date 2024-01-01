package xyz.magicjourney.nebulaquest.ui.button;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import xyz.magicjourney.nebulaquest.event.ParameterizedEvent;
import xyz.magicjourney.nebulaquest.event.ParameterizedEventGetter;
import xyz.magicjourney.nebulaquest.listener.Listener;

public class ActionButton extends Group {
  protected TextButton button;
  protected TextButton disabled;
  protected ParameterizedEvent<Runnable> clickEvent;
  protected Skin skin;
  protected Boolean isDisabled;
  protected Listener clickListener;

  public ActionButton(String text, AssetManager assets) {
    this(text, false, assets);
  }

  public ActionButton(String text, boolean disabled, AssetManager assets) {
    this.skin = assets.get("skin/ui.skin.json", Skin.class);
    this.button = new TextButton(text, skin, "orange");
    this.disabled = new TextButton(text, skin, "disabled");
    this.clickEvent = new ParameterizedEvent<>();
    this.clickListener = new Listener(this::clickHandler);
    
    this.setDisabled(disabled);
  }

  @Override
  protected void sizeChanged() {
    super.sizeChanged();

    this.button.setSize(this.getWidth(), this.getHeight());
    this.disabled.setSize(this.getWidth(), this.getHeight());
  }

  public ParameterizedEventGetter<Runnable> onClick() {
    return this.clickEvent;
  }

  public boolean isDisabled() {
    return this.isDisabled;
  }

  public void setDisabled(boolean value) {
    this.clear();
    this.isDisabled = value;

    if (this.isDisabled) {
      this.addActor(this.disabled);
      this.button.removeListener(this.clickListener);
    }
    else {
      this.addActor(this.button);
      this.button.addListener(this.clickListener);
    }
  }

  protected void clickHandler() {
    setDisabled(true);
    this.clickEvent.emit(() -> this.setDisabled(false));
  }
}
