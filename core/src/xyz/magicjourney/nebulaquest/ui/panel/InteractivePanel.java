package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.AuctionInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.BankInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.DescriptionInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.PayFeeInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.PlayerInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.PlayersInteractiveView;

public class InteractivePanel extends ViewPanel<AbstractInteractiveView> {
  protected Player player;
  protected Entity field;

  public InteractivePanel(AssetManager assets, TourPanel tourPanel, Player player, Entity field) {
    super(207, 466, assets);

    this.player = player;
    this.field = field;

    this.views.put("Me", new PlayerInteractiveView(assets, this, tourPanel));
    this.views.put("Bank", new BankInteractiveView(assets, this, tourPanel));
    this.views.put("Players", new PlayersInteractiveView(assets, this, tourPanel));
    this.views.put("Description", new DescriptionInteractiveView(assets, this, tourPanel));
    this.views.put("Auction", new AuctionInteractiveView(assets, this, tourPanel));
    this.views.put("PayFee", new PayFeeInteractiveView(assets, this, tourPanel));

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

  public void selectDescription(Describable entity) {
    this.select("Description");
    
    var view = (DescriptionInteractiveView) this.views.get("Description");
    view.display(entity);
  }
}
