package xyz.magicjourney.nebulaquest.entity.entities;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.Interactiveable;
import xyz.magicjourney.nebulaquest.player.Player;

public class Casino extends Entity implements Interactiveable {
  
  public Casino() {
    super("Suspicious\nSpace Station", "According to the local Republic administration, this space station should be abandoned. However, it appears freshly built and teeming with life. Will you dare to investigate its mysteries?");
  }

  @Override
  public void onEnter(Player player) {
  }

  @Override
  public void onPass(Player player) {
  }

  @Override
  public Field toField(AssetManager assets) {
    return new Field(this, assets);
  }

  @Override
  public String getInteractiveablePanelName(Player player) {
    return "Casino";
  }
}
