package xyz.magicjourney.nebulaquest.entity.entities;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;

public class Teleport extends Entity implements Buyable {
  public Teleport() {
    super("Teleport", "Teleport field description");
  }

  @Override
  public Player getOwner() {
    return null;
  }

  @Override
  public boolean canBeBought() {
    return false;
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
}
