package xyz.magicjourney.nebulaquest.ui.panel;

import java.util.HashMap;
import java.util.Optional;

import com.badlogic.gdx.assets.AssetManager;

import xyz.magicjourney.nebulaquest.ui.panel.views.AbstractView;

public class ViewPanel<T extends AbstractView> extends Panel {
  protected HashMap<String, T> views;
  protected Optional<T> selected;

  public ViewPanel(int width, int height, AssetManager assets) {
    super(width, height, assets);

    this.views = new HashMap<>();
    this.selected = Optional.empty();
  }

  public void select(String view) {
    this.unselect();

    this.selected = Optional.ofNullable(this.views.get(view));
    
    if (this.selected.isPresent()) {
      this.content.add(this.selected.get()).fillX().expand().top();
      this.content.pack();
    }
  }

  public void unselect() {
    this.content.clear();
    this.content.pack();
  }
}