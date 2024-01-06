package xyz.magicjourney.nebulaquest.entity.entities;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.BigField;
import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.Interactiveable;
import xyz.magicjourney.nebulaquest.player.Player;

public class UnknownJump extends Entity implements Interactiveable {
  protected int[] teleportFields;
  protected int startField;
  protected int nebulaField;

  public UnknownJump(int teleportBottom, int teleportLeft, int teleportTop, int teleportRight, int start, int nebula) {
    super("Galactic\nBargain Hub", "Welcome to the Galactic Bargain Hub, the self-proclaimed 'Best All-in-One Station in the Galaxy.' We offer unbeatable prices and an equally unbeatable low quality of products. Step right in and explore our vast array of curious upgrade kits – where every purchase is an adventure in itself!");

    this.teleportFields = new int[] { teleportBottom, teleportLeft, teleportTop, teleportRight };
    this.startField = start;
    this.nebulaField = nebula;
  }

  @Override
  public void onEnter(Player player) {
  }

  @Override
  public void onPass(Player player) {
  }

  @Override
  public Field toField(AssetManager assets) {
    return new BigField(this, "images/unknown-jump", assets);
  }

  @Override
  public String getInteractiveablePanelName(Player player) {
    return "UnknownJump";
  }

  public int jump(int rollResult) {
    if (rollResult <= 4 ) {
      return this.teleportFields[rollResult - 1];
    }

    if (rollResult <= 6) {
      return this.startField;
    }

    return this.nebulaField;
  }

  public int getNebulaField() {
    return this.nebulaField;
  }
}
