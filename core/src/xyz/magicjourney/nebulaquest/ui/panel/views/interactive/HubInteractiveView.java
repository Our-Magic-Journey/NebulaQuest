package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.Board;
import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.entities.Teleport;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.button.ActionButton;
import xyz.magicjourney.nebulaquest.ui.panel.InteractivePanel;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;

public class HubInteractiveView extends DescriptionInteractiveView {
  protected ActionButton[] teleportButtons;
  protected Player player;
  protected Board board;

  public HubInteractiveView(AssetManager assets, InteractivePanel parent, TourPanel tourPanel, Board board) {
    super(assets, parent, tourPanel);
    this.board = board;
    this.teleportButtons = new ActionButton[] {
      new ActionButton("Teleport South", false, assets),
      new ActionButton("Teleport West", false, assets),
      new ActionButton("Teleport North", false, assets),
      new ActionButton("Teleport East", false, assets)
    }; 

    for (int i = 0; i < this.teleportButtons.length ; i++) {
      this.teleportButtons[i].onClick().subscribe(this.handleTeleportClick(5 + i * 10));
    }
  }

  @Override
  public void select(Player player, Entity field) {
    super.select(player, field);

    if (this.player != player) {
      this.player = player;
    }
    this.description.setText(this.splitText(23, "HUB HUB HUB HUB"));
  }

  @Override
  protected void createLayout(Describable entity, boolean displayDescription) {
    super.createLayout(entity, displayDescription);

    this.addSpacer(0, 0, 4, 0);

    for (var button : this.teleportButtons) {
      this.add(button).height(20).pad(0, 4, 4, 4).fillX().row();
    }
  }

  protected Consumer<Runnable> handleTeleportClick(int field) {
    return (unblock) -> {
      this.board.setPlayerPosition(player, field, false);

      if(this.board.getFieldUnderPlayer(player) instanceof Teleport teleport) {
        
        if (teleport.mustPayFee(player)) {
          this.tourPanel.blockTurnButton();
          this.parent.select("PayFee", player, teleport);
        }
        else {
          this.parent.select("Description", player, teleport);
        }
      }

      for (var button : this.teleportButtons) {
        button.setDisabled(true);
      }
    };
  }

  @Override
  public void prepareForNextTurn() {
    super.prepareForNextTurn();

    for (var button : this.teleportButtons) {
      button.setDisabled(false);
    }
  }
}
