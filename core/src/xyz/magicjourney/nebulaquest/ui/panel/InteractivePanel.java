package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.Board;
import xyz.magicjourney.nebulaquest.dice.Dice;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.AuctionInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.BankInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.CasinoInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.DescriptionInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.HubInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.MineInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.PayFeeInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.PlayerInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.PlayersInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.TeleportInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.TeleportPayFeeInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.UnknownJumpInteractiveView;

public class InteractivePanel extends ViewPanel<AbstractInteractiveView> {
  protected Player player;
  protected Entity field;
  protected Board board;

  public InteractivePanel(AssetManager assets, Dice dice, TourPanel tourPanel, Player player, Board board, Entity field) {
    super(207, 466, dice, assets);

    this.player = player;
    this.field = field;
    this.board = board;

    this.views.put("Me", new PlayerInteractiveView(assets, this, tourPanel));
    this.views.put("Bank", new BankInteractiveView(assets, this, tourPanel));
    this.views.put("Players", new PlayersInteractiveView(assets, this, tourPanel));
    this.views.put("Description", new DescriptionInteractiveView(assets, this, tourPanel));
    this.views.put("Auction", new AuctionInteractiveView(assets, this, tourPanel));
    this.views.put("PayFee", new PayFeeInteractiveView(assets, this, tourPanel));
    this.views.put("Casino", new CasinoInteractiveView(assets, this, tourPanel));
    this.views.put("Hub", new HubInteractiveView(assets, this, tourPanel, board));
    this.views.put("Teleport", new TeleportInteractiveView(assets, this, tourPanel, board));
    this.views.put("TeleportPayFee", new TeleportPayFeeInteractiveView(assets, this, tourPanel));
    this.views.put("Mine", new MineInteractiveView(assets, this, tourPanel));
    this.views.put("UnknownJump", new UnknownJumpInteractiveView(assets, this, tourPanel, board));
    
    this.content.pad(4, 4, 4, 4);
    this.select("Bank");
  }

  @Override
  public void select(String view) {
    super.select(view);

    if (this.selected.isPresent()) {
      this.selected.get().select(this.player, this.field);
    }
  }

  public void update(Player player, Entity field) {
    this.player = player;
    this.field = field;
  }
  
  public void select(String view, Player player, Entity field) {
    this.update(player, field);
    this.select(view);
  }

  public void reset() {
    for (AbstractInteractiveView view : this.views.values()) {
      view.prepareForNextTurn();
    }
  }

  public void selectDescription(Entity entity, Player player) {
    this.select("Description", player, entity);
  }
}
