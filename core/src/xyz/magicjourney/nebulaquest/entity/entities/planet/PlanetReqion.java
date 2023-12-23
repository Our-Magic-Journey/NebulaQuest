package xyz.magicjourney.nebulaquest.entity.entities.planet;

import com.badlogic.gdx.graphics.Color;

public class PlanetReqion {
  protected Color color;

  public PlanetReqion(int color) {
    this.color = new Color(color);
  }

  public Color getColor() {
    return this.color;
  }
}
