package xyz.magicjourney.nebulaquest.entity.entities.planet;

import com.badlogic.gdx.graphics.Color;

public class PlanetRegion {
  protected Color color;
  protected String name;

  public PlanetRegion(int color, String name) {
    this.color = new Color(color);
    this.name = name;
  }

  public Color getColor() {
    return this.color;
  }

  public String getName() {
    return this.name;
  }
}
