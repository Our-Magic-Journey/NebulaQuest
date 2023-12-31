package xyz.magicjourney.nebulaquest.ui.panel;

import java.util.HashMap;
import java.util.Optional;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;

public class ViewPanel extends Panel {
  protected HashMap<String, AbstractView> views;
  protected Optional<AbstractView> selected;

  public ViewPanel(int width, int height, AssetManager assets) {
    super(width, height, assets);

    this.views = new HashMap<>();
    this.selected = Optional.empty();
  }

  public void select(String view) {
    this.unselect();

    this.selected = Optional.ofNullable(this.views.get(view));
    
    if (this.selected.isPresent()) {
      this.content.add(this.selected.get());
      this.selected.get().show();
      this.content.pack();
    }
  }

  public void unselect() {
    if (this.selected.isPresent()) {
      this.selected.get().hide();
      this.selected.get().remove();
      this.content.pack();
    }
  }
}