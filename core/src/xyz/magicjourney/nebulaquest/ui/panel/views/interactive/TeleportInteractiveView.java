package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.Board;
import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.entities.Teleport;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;

public class TeleportInteractiveView extends DescriptionInteractiveView  {
  protected ActionButton militarize;
  protected ActionButton teleport;
  protected Player player;
  protected Teleport entity;
  protected Board board;

  public TeleportInteractiveView(AssetManager assets, ViewPanel<?> parent, TourPanel tourPanel, Board board) {
    super(assets, parent, tourPanel);
    
    this.board = board;
    this.militarize = new ActionButton("Militarize", true, assets);
    this.teleport = new ActionButton("Teleport", false, assets);

    this.militarize.onClick().subscribe(this.handleMilitarizeClick);
    this.teleport.onClick().subscribe(this.handleTeleportClick);
  }

  @Override
  public void prepareForNextTurn() {
    if (this.player != null) {
      this.player.onChange().unsubscribe(this.update);
    }
    
    this.player = null;
    this.entity = null;
    this.militarize.setDisabled(true);
    this.teleport.setDisabled(false);
  }

  @Override
  public void select(Player player, Entity field) {
    super.select(player, field);
    
    if (field instanceof Teleport teleport) {
      this.entity = teleport;
    }

    if (this.player == null) {
      player.onChange().subscribe(this.update);
      this.militarize.setDisabled(!this.canByBought(player));
    }        

    this.player = player;
  }

  @Override
  public void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, displayDescription);

    this.addSpacer();
    this.add(this.militarize).height(20).pad(2, 4, 0, 4).fillX().row();
    this.add(this.teleport).height(20).pad(2, 4, 0, 4).fillX().row();
  }

  protected Consumer<Runnable> handleMilitarizeClick = (unblock) -> {
    this.player.buyProperty(this.entity);
  };

  protected Consumer<Runnable> handleTeleportClick = (unblock) -> {
    this.militarize.setDisabled(true);
    this.board.setPlayerPosition(this.player, this.entity.getExit(), true);
  };

  protected Consumer<Player> update = (player) -> {
    this.militarize.setDisabled(!this.canByBought(player));
  };

  protected boolean canByBought(Player player) {
    return this.entity.getOwner().isEmpty() && player.getMoney() >= this.entity.getValue();
  }
}
