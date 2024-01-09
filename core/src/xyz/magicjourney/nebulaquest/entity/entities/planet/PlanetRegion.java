package xyz.magicjourney.nebulaquest.entity.entities.planet;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

import xyz.magicjourney.nebulaquest.player.Player;

public class PlanetRegion {
  protected Color color;
  protected String name;
  protected ArrayList<Planet> planets;

  public PlanetRegion(int color, String name) {
    this.color = new Color(color);
    this.name = name;
    this.planets = new ArrayList<>();
  }

  public Color getColor() {
    return this.color;
  }

  public String getName() {
    return this.name;
  }

  public void registerPlanet(Planet planet) {
    this.planets.add(planet);
  }

  public boolean ownAll(Player player) {
    for (Planet planet : this.planets) {
      if (!planet.isOwner(player)) {
        return false;
      }
    }

    return true;
  }
}
