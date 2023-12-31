package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.ui.panel.views.options.BankOptionView;
import xyz.magicjourney.nebulaquest.ui.panel.views.options.PlayerOptionView;
import xyz.magicjourney.nebulaquest.ui.panel.views.options.PlayersOptionView;

public class OptionPanel extends ViewPanel {
  public OptionPanel(AssetManager assets) {
    super(207, 349, assets);

    this.views.put("Me", new PlayerOptionView(assets));
    this.views.put("Bank", new BankOptionView(assets));
    this.views.put("Players", new PlayersOptionView(assets));

    this.select("bank");
  }
}
