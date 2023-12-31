package xyz.magicjourney.nebulaquest.entity.entities.planet;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.board.field.PlanetField;
import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;

public class Planet extends Entity implements Buyable {
  protected int value;
  protected PlanetReqion reqion;

  public Planet(String name, int value, PlanetReqion reqion) {
    super(name, "Planet field");

    this.value = value;
    this.reqion = reqion;
  }

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
  public Player getOwner() {
    return null;
  }

  @Override
  public boolean canBeBought() {
    return true;
  }

  public PlanetReqion getRegion() {
    return this.reqion;
  }

  @Override
  public Field toField(AssetManager assets) {
    return new PlanetField(this, assets);
  }
}
