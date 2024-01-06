package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.Board;
import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.entities.UnknownJump;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.ViewPanel;

public class UnknownJumpInteractiveView extends DescriptionInteractiveView  {
  protected ActionButton jump;
  protected Player player;
  protected UnknownJump entity;
  protected Board board;

  public UnknownJumpInteractiveView(AssetManager assets, ViewPanel<?> parent, TourPanel tourPanel, Board board) {
    super(assets, parent, tourPanel);
    
    this.board = board;
    this.jump = new ActionButton("Jump", false, assets);

    this.jump.onClick().subscribe(this.handleJumpClick);
  }

  @Override
  public void prepareForNextTurn() {    
    this.player = null;
    this.entity = null;
    this.jump.setDisabled(false);
  }

  @Override
  public void select(Player player, Entity field) {
    super.select(player, field);
    this.player = player;

    if (field instanceof UnknownJump entity) {
      this.entity = entity;
    }

    this.description.setText(this.splitText(23, "When you visited the space station, a mechanic sold you an 'amazing' self-assembly hyperdrive upgrade kit. Now, when the installation is completed, it's time to see if your ship can truly jump unbelievably far! After all, what could go wrong, right?"));
  }

  @Override
  public void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, displayDescription);

    this.addSpacer();
    this.add(this.jump).height(20).pad(2, 4, 0, 4).fillX().row();
  }

  protected Consumer<Runnable> handleJumpClick = (unblock) -> {
    this.parent.getDice().roll((rollResult) -> {
      int field = this.entity.jump(rollResult);

      if (field == this.entity.getNebulaField()) {
        this.board.movePlayerToNebula(this.player, field);
      }
      else {
        this.board.setPlayerPosition(this.player, field, false);
        this.tourPanel.unblockTurnButton();
      }
    });
  };
}
