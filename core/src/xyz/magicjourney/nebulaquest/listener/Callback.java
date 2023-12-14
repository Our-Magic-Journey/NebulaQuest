package xyz.magicjourney.nebulaquest.listener;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

@FunctionalInterface
public interface Callback {
  public void changed(ChangeEvent event, Actor actor);
}
