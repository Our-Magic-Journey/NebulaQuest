package xyz.magicjourney.nebulaquest.entity.entities;

import java.util.HashSet;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.BigField;
import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;

public class Nebula extends Entity {
  protected HashSet<Player> lostPlayers;

  public Nebula() {
    super("Nebula", "");

    this.lostPlayers = new HashSet<>();
  }

  @Override
  public void onEnter(Player player) {
  }

  @Override
  public void onPass(Player player) {
  }

  @Override
  public Field toField(AssetManager assets) {
    return new BigField(this, "images/nebula", assets);
  }

  public void addLostPlayer(Player player) {
    this.lostPlayers.add(player);
  }
}
