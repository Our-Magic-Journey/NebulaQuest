package xyz.magicjourney.nebulaquest.entity.entities;

import java.util.Optional;
import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;

public class Mine extends Entity implements Buyable {
  protected static final int BASE_PROFIT = 50; 
  protected int value;
  protected Optional<Player> owner;
  protected Random random;

  public Mine() {
    super("Asteroid Mine", "");

    this.value = 500;
    this.owner = Optional.empty();
    this.random = new Random();
  }

  @Override
  public void onEnter(Player player) {
  }

  @Override
  public void onPass(Player player) {
    if (this.isOwner(player)) {
      player.giveMoney((int) (BASE_PROFIT * (random.nextFloat() + 1)));
    }
  }

  @Override
  public Field toField(AssetManager assets) {
    return new Field(this, "images/mine", assets);
  }

  @Override
  public Optional<Player> getOwner() {
    return this.getOwner();
  }

  @Override
  public int getValue() {
    if (this.getOwner().isEmpty()) {
      return this.value;
    }

    return (int) (this.value * 1.5);
  }

  public int setValue(int value) {
    return this.value;
  }

  @Override
  public int getFee() {
    return 0;
  }

  @Override
  public void setOwner(Player player) {
    this.owner = Optional.of(player);
  }

  @Override
  public String getInteractiveablePanelName(Player player) {
    return "Mine";
  }

  @Override
  public boolean isDecisionRequired(Player player) {
    return false;
  }
}
