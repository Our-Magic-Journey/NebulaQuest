package xyz.magicjourney.nebulaquest.listener;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Listener extends ChangeListener {  
  protected Callback callback;

  public Listener(Callback callback) {
    this.callback = callback;
  }

  public Listener(ZeroArgumentsCallback callback) {
    this.callback = (event, item) -> callback.changed();
  }

  @Override
  public void changed(ChangeEvent event, Actor actor) {
    this.callback.changed(event, actor);
  }
}
