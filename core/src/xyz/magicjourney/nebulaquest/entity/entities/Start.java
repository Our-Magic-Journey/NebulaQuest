package xyz.magicjourney.nebulaquest.entity.entities;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.BigField;
import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;

public class Start extends Entity {
  private static final int MONEY_ON_PASS_START = 200;

  public Start() {
    super("Start", "This is where your space adventure has begun. After passing through this field, you will receive " +  MONEY_ON_PASS_START + " credits each time.");
  }

  @Override
  public void onEnter(Player player) {
  }

  @Override
  public void onPass(Player player) {
    player.giveMoney(MONEY_ON_PASS_START);
  }

  @Override
  public Field toField(AssetManager assets) {
    return new BigField(this, "images/start", assets);
  }
}
