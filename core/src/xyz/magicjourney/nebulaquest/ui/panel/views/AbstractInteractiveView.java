package xyz.magicjourney.nebulaquest.ui.panel.views;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;

public abstract class AbstractInteractiveView extends AbstractView {
  protected TourPanel tourPanel;

  public AbstractInteractiveView(AssetManager assets, ViewPanel<?> parent, TourPanel tourPanel) {
    super(assets, parent);

    this.tourPanel = tourPanel;
  }

  public void select(Player player, Entity field) {}
}
