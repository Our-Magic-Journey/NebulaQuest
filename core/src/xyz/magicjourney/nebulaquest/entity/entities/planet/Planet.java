package xyz.magicjourney.nebulaquest.entity.entities.planet;

import java.util.Optional;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.board.field.PlanetField;
import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;

public class Planet extends Entity implements Buyable {
  protected int value;
  protected PlanetRegion region;
  protected Optional<Player> owner;

  public Planet(String name, int value, PlanetRegion region) {
    super(name, "This planet is owned by the Republic, and there is no entrance fee. You have the chance to acquire this planet as your own by being the first to step onto it. Beware, though, if someone else buys it, you'll need to pay a fee.");
    this.value = value;
    this.region = region;
    this.owner = Optional.empty();
  }
  
  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public int getFee() {
    return 100;
  }

  @Override
  public void onEnter(Player player) {
  }

  @Override
  public void onPass(Player player) {
  }

  @Override
  public Optional<Player> getOwner() {
    return this.owner;
  }

  @Override
  public void setOwner(Player player) {
    this.description = "Attention, Explorer! This planet has already been claimed by " + player.getName() + ". If you wish to visit or stay on this celestial body, a fee must be paid to " + player.getName();
    this.owner = Optional.of(player);
  }

  public PlanetRegion getRegion() {
    return this.region;
  }

  @Override
  public Field toField(AssetManager assets) {
    return new PlanetField(this, assets);
  }
}
