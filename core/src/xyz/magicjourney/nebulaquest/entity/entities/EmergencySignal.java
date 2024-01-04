package xyz.magicjourney.nebulaquest.entity.entities;

import java.util.Optional;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;

public class EmergencySignal extends Entity {
  protected int value;
  protected Optional<Player> owner;

  public EmergencySignal() {
    super("Emergency\nsignal", "");
  }
  
  @Override
  public void onEnter(Player player) {
  }

  @Override
  public void onPass(Player player) {
  }

  @Override
  public Field toField(AssetManager assets) {
    return new Field(this, "images/event", assets);
  }
}
