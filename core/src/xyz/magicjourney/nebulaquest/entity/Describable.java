package xyz.magicjourney.nebulaquest.entity;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;

public interface Describable {
  String getName();
  String getDescription();
  Actor getIcon(AssetManager assets);
}
