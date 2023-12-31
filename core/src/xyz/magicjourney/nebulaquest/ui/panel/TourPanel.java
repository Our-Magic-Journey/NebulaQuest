package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class TourPanel extends Panel {
  TextButton roll;
  Label money;
  Image player;

  public TourPanel(AssetManager assets) {
    super(207, 115, assets);

    this.roll = new TextButton("Roll", this.skin, "orange");
    this.money = new Label("1000$", this.skin);
    this.player = new Image(assets.get("images/player.png", Texture.class));
    
    Table playerDescription = new Table();
    playerDescription.add(player).expand().center();
    playerDescription.add(money).expand().center();

    this.content.add(playerDescription).expand().fill();
    this.content.row();
    this.content.add(roll).fillX();
  }

  @Override
  public void act(float delta) {
    super.act(delta);
  }
}
