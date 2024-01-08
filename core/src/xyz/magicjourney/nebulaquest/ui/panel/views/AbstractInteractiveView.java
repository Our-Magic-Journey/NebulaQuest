package xyz.magicjourney.nebulaquest.ui.panel.views;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.panel.InteractivePanel;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;

public abstract class AbstractInteractiveView extends AbstractView {
  protected TourPanel tourPanel;
  protected InteractivePanel parent;

  public AbstractInteractiveView(AssetManager assets, InteractivePanel parent, TourPanel tourPanel) {
    super(assets, parent);

    this.parent = parent;
    this.tourPanel = tourPanel;
  }

  public void prepareForNextTurn() {};
  public void select(Player player, Entity field) {}
}
