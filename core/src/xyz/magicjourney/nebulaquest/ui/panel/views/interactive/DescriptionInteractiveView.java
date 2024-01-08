package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.ray3k.tenpatch.TenPatchDrawable;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Describable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.entities.planet.Planet;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.panel.InteractivePanel;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractInteractiveView;

public class DescriptionInteractiveView extends AbstractInteractiveView {
  protected AssetManager assets;
  protected Actor icon;
  protected Label title;
  protected Label description;
  protected Label price;
  protected Label fee;
  protected Label owner;
  protected Label region;

  protected TenPatchDrawable spacer;

  public DescriptionInteractiveView(AssetManager assets, InteractivePanel parent, TourPanel tourPanel) {
    super(assets, parent, tourPanel);
    this.assets = assets;
    this.title = new Label("", this.skin, "small-title");
    this.description = new Label("", this.skin, "small");
    this.description.setAlignment(Align.center);
    this.region = new Label("", this.skin, "small");
    this.price = new Label("", this.skin, "small");
    this.fee = new Label("", this.skin, "small");
    this.owner = new Label("", this.skin, "small");

    this.spacer = this.skin.get("panel-margin10", TenPatchDrawable.class);
  }

  @Override
  public void select(Player player, Entity field) {
    this.display(field);
  }
  
  public void display(Describable entity) {
    this.icon = entity.getIcon(assets); 

    if (entity instanceof Planet planet) {
      this.region.setText("Region: " + planet.getRegion().getName());
    }

    if (entity instanceof Buyable buyable) {
      this.price.setText("Value: " + buyable.getValue());
      this.fee.setText("Residence fee: " + buyable.getFee());
      this.owner.setText("Owner: " + buyable.getOwner().map((p) -> p.getName()).orElse("Free"));
    }

    if (this.icon instanceof Field field) {
      field.makeStatic();
    }

    this.title.setAlignment(Align.center);
    this.title.setText(entity.getName());
    this.description.setText(this.splitText(23, entity.getDescription()));
    
    this.clear();
    this.createLayout(entity, true);
  }

  protected String splitText(int lineWidth, String text) {
    String[] words = text.split(" ");
    String result = "";

    int line = 0;

    for (String word : words) {
      if ((line + word.length() + 1) < lineWidth) {
        line += word.length() + 1;
        result += " " + word;
      }
      else {
        line = word.length();
        result += "\n" + word;
      }
    }

    return result;
  }

  protected void createLayout(Describable entity, boolean displayDescription) {
    this.add(this.title).expandX().top().row();
    this.add(this.icon).row();
    
    if (entity instanceof Planet) {
      this.add(region).row();
    }

    if (displayDescription) {
      this.addSpacer(entity instanceof Planet ? 2 : 10, 2);
      this.add(this.description).row();
    }

    if (entity instanceof Buyable) {
      this.addSpacer(2, 2);
      this.add(price).row();
      this.add(fee).row();
      this.add(owner).row();
    }
  }

  protected void addSpacer() {
    this.addSpacer(0, 0, 2, 0);
  }

  protected void addSpacer(int spaceTop, int spaceBottom) {
    this.addSpacer(spaceTop, 0, spaceBottom, 0);
  }

  protected void addSpacer(int spaceTop, int spaceLeft, int spaceBottom, int spaceRight) {
    this.add(new Image(this.spacer)).fillX().height(4).space(spaceTop, spaceLeft, spaceBottom, spaceRight).row();
  }
}
