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

  public Planet(String name, int value, PlanetRegion region) {
    super(name, "This is a planet field. If unclaimed, you have  the opportunity to acquire it as your property. If another explorer has already claimed the planet, be prepared to pay a fee for your stay.");
    this.value = value;
    this.region = region;
  }
  
  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public void onEnter(Player player) {
  }

  @Override
  public void onPass(Player player) {
  }

  @Override
  public Optional<Player> getOwner() {
    return Optional.empty();
  }

  @Override
  public boolean canBeBought() {
    return true;
  }

  public PlanetRegion getRegion() {
    return this.region;
  }

  @Override
  public Field toField(AssetManager assets) {
    return new PlanetField(this, assets);
  }
}
