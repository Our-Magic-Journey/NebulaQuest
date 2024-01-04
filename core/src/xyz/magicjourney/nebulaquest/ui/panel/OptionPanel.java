package xyz.magicjourney.nebulaquest.ui.panel;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.dice.Dice;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;
import xyz.magicjourney.nebulaquest.ui.panel.views.options.BankOptionView;
import xyz.magicjourney.nebulaquest.ui.panel.views.options.PlayerOptionView;
import xyz.magicjourney.nebulaquest.ui.panel.views.options.PlayersOptionView;

public class OptionPanel extends ViewPanel<AbstractView> {
  public OptionPanel(Dice dice, AssetManager assets) {
    super(207, 349, dice, assets);

    this.views.put("Me", new PlayerOptionView(assets, this));
    this.views.put("Bank", new BankOptionView(assets, this));
    this.views.put("Players", new PlayersOptionView(assets, this));

    this.select("Bank");
  }
}
