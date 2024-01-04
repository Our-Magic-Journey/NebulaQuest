package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;

public class TeleportInteractiveView extends DescriptionInteractiveView  {
  protected ActionButton teleport;

  public TeleportInteractiveView(AssetManager assets, ViewPanel<?> parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);
    
    this.teleport = new ActionButton("Teleport", true, assets);
    this.description.setText("");
  }

  @Override
  public void select(Player player, Entity field) {
    super.select(player, field);
  }

  @Override
  public void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, displayDescription);

    this.addSpacer();
    this.add(this.teleport).height(20).pad(2, 4, 0, 4).fillX().row();
  }
}
