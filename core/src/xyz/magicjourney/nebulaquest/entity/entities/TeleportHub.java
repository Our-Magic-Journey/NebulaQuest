package xyz.magicjourney.nebulaquest.entity.entities;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.BigField;
import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.Interactiveable;
import xyz.magicjourney.nebulaquest.player.Player;

public class TeleportHub extends Entity implements Interactiveable {
  public TeleportHub() {
    super("Teleport Hub", "");
  }

  @Override
  public void onEnter(Player player) {
  }

  @Override
  public void onPass(Player player) {
  }

  @Override
  public Field toField(AssetManager assets) {
    return new BigField(this, "images/hiper-hub", assets);
  }

  @Override
  public String getInteractiveablePanelName(Player player) {
    return "Hub";
  }

  @Override
  public boolean isDecisionRequired(Player player) {
    return false;
  }
}