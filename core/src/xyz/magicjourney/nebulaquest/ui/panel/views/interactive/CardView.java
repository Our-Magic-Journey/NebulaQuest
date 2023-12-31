package xyz.magicjourney.nebulaquest.ui.panel.views.interactive;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.ray3k.tenpatch.TenPatchDrawable;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Buyable;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.entities.planet.Planet;
import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;

public class CardView extends AbstractView {
  protected AssetManager assets;
  protected Entity entity;
  protected Field field;
  protected Label title;
  protected Label description;
  protected Label price;
  protected Label owner;
  protected Label region;

  protected TenPatchDrawable spacer;

  public CardView(AssetManager assets) {
    super(assets);
    this.assets = assets;
    this.title = new Label("", this.skin);
    this.description = new Label("", this.skin, "small");
    this.description.setAlignment(Align.center);
    this.region = new Label("", this.skin, "small");
    this.price = new Label("", this.skin, "small");
    this.owner = new Label("", this.skin, "small");

    this.spacer = this.skin.get("panel-margin10", TenPatchDrawable.class);
  }

  public void setField(Field field) {
    this.field = field.clone(this.assets);
    this.entity = field.getEntity();

    if (entity instanceof Planet planet) {
      this.region.setText("Region: " + planet.getRegion().getName());
    }

    if (entity instanceof Buyable buyable) {
      this.price.setText("Value: " + buyable.getValue());
      this.owner.setText("Owner: " + buyable.getOwner().map((p) -> p.getName()).orElse("Free"));
    }

    this.title.setText(this.entity.getName());
    this.description.setText(this.splitText(23, this.entity.getDescription()));
    this.clear();
    this.field.makeStatic();
    this.createLayout();
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

  protected void createLayout() {
    this.add(this.title).expandX().top().row();
    this.add(this.field).row();
    
    if (this.entity instanceof Planet) {
      this.add(region).row();
      this.add(new Image(this.spacer)).fillX().height(4).space(0, 0, 2, 0).row();
    }
    else {
      this.add(new Image(this.spacer)).fillX().height(4).space(10, 0, 2, 0).row();
    }

    this.add(this.description).row();

    if (this.entity instanceof Buyable) {
      this.add(new Image(this.spacer)).fillX().height(4).space(2, 0, 2, 0).row();
      this.add(price).row();
      this.add(owner).row();
    }
  }
}
