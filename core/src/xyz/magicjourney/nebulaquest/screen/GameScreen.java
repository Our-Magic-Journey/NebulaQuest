package xyz.magicjourney.nebulaquest.screen;

import java.util.ArrayList;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import xyz.magicjourney.nebulaquest.board.Board;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.entities.Start;
import xyz.magicjourney.nebulaquest.entity.entities.Teleport;
import xyz.magicjourney.nebulaquest.entity.entities.planet.Planet;
import xyz.magicjourney.nebulaquest.entity.entities.planet.PlanetReqion;
import xyz.magicjourney.nebulaquest.music.MusicManager;

public class GameScreen extends AbstractScreen {
  ArrayList<PlanetReqion> regions;
  ArrayList<Entity> entities;
  Board board;

  public GameScreen(SpriteBatch batch, AssetManager assets, ScreenManager screenManager, MusicManager musicManager) {
    super(batch, assets, screenManager, musicManager);

    this.regions = new ArrayList<>();
    this.regions.add(new PlanetReqion(0xfbe300ff));
    this.regions.add(new PlanetReqion(0xfba500ff));
    this.regions.add(new PlanetReqion(0xfb4a00ff));
    this.regions.add(new PlanetReqion(0x52c2cfff));
    this.regions.add(new PlanetReqion(0x991277ff));
    this.regions.add(new PlanetReqion(0xf041c4ff));
    this.regions.add(new PlanetReqion(0x218525ff));
    this.regions.add(new PlanetReqion(0x52eacfff));

    this.entities = new ArrayList<>();
    this.entities.add(new Start());
    this.entities.add(new Planet("ne59", 100, regions.get(0)));
    this.entities.add(new Planet("pluto", 150, regions.get(0)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("paradise", 200, regions.get(0)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("nabu", 150, regions.get(1)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("tatuine", 300, regions.get(1)));
    this.entities.add(new Planet("centre", 350, regions.get(1)));
    
    this.entities.add(new Start());
    this.entities.add(new Planet("ne59", 100, regions.get(2)));
    this.entities.add(new Planet("pluto", 150, regions.get(2)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("paradise", 200, regions.get(2)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("nabu", 150, regions.get(3)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("tatuine", 300, regions.get(3)));
    this.entities.add(new Planet("centre", 350, regions.get(3)));

    this.entities.add(new Start());
    this.entities.add(new Planet("ne59", 100, regions.get(4)));
    this.entities.add(new Planet("pluto", 150, regions.get(4)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("paradise", 200, regions.get(4)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("nabu", 150, regions.get(5)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("tatuine", 300, regions.get(5)));
    this.entities.add(new Planet("centre", 350, regions.get(5)));

    this.entities.add(new Start());
    this.entities.add(new Planet("pluto", 100, regions.get(6)));
    this.entities.add(new Planet("ne59", 150, regions.get(6)));
    this.entities.add(new Teleport());
    this.entities.add(new Planet("pluto", 200, regions.get(6)));
    this.entities.add(new Teleport());
    this.entities.add(new Teleport());
    this.entities.add(new Teleport());
    this.entities.add(new Planet("paradise", 300, regions.get(7)));
    this.entities.add(new Planet("paradise", 350, regions.get(7)));
  }

  @Override
  public void show() {
    create();
    musicManager.playGameMusic();

    super.show();    
  }

  public void create() {
    Board board = new Board(this.entities, assets);

    Table table = new Table();
    table.setFillParent(true);
    stage.addActor(table);
    table.add(board).left().expand();
  }
}
