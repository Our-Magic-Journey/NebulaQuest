package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractInteractiveView;

public class PlayerInteractiveView extends AbstractInteractiveView {
  protected Label label;

  public PlayerInteractiveView(AssetManager assets, ViewPanel<?> parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);

    this.label = new Label("Player View", skin, "small");
    this.add(label);
  }

  @Override
  public void select(Player player, Entity entity) {
    this.label.setText(player.getName() + " View");
  }
}