package xyz.magicjourney.nebulaquest.board;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import xyz.magicjourney.nebulaquest.player.Player;

public class Pawn extends Group {
  protected Player player;
  protected Image image;
  protected int field;

  public Pawn(Player player, AssetManager assets) {
    this.player = player;
    this.image = new Image(player.getShip(assets));
    this.field = 0;
    this.setSize(40, 40);
    
    this.image.setSize(40, 40);
    this.image.setRotation(180);
    this.image.moveBy(40, 40);

    this.setOrigin(20, 20);

    this.addActor(image);
  }


  public int getField() {
    return this.field;
  }

  public void setField(int field) {
    this.field = field;
  }

}
