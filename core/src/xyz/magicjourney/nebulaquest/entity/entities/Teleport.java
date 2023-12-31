package xyz.magicjourney.nebulaquest.entity.entities;

import java.util.Optional;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;

public class Teleport extends Entity implements Buyable {
  protected int value;

  public Teleport() {
    super("Teleport", "This is a well-known hyperspace path. It allows you to traverse to the opposite edge of the galaxy. You can fornicate the start and finish fields, then jumping players need to pay you a fee. Jump doesn't count as passing the starting field.");

    this.value = 350;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public Optional<Player> getOwner() {
    return Optional.empty();
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
