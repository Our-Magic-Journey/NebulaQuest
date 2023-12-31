package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.BankInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.CardView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.PlayerInteractiveView;
import xyz.magicjourney.nebulaquest.ui.panel.views.interactive.PlayersInteractiveView;

public class InteractivePanel extends ViewPanel {
  
  public InteractivePanel(AssetManager assets) {
    super(207, 466, assets);

    this.views.put("Me", new PlayerInteractiveView(assets));
    this.views.put("Bank", new BankInteractiveView(assets));
    this.views.put("Players", new PlayersInteractiveView(assets));
    this.views.put("Card", new CardView(assets));

    this.select("bank");
  }

  public void selectCardView(Field field) {
    this.select("Card");
    
    CardView view = (CardView) this.views.get("Card");
    view.setEntity(field.getEntity());
  }
}
