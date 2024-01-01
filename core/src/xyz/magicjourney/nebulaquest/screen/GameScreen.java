package xyz.magicjourney.nebulaquest.screen;

import java.util.ArrayList;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import xyz.magicjourney.nebulaquest.board.Board;
import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.dice.Dice;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.entities.Start;
import xyz.magicjourney.nebulaquest.entity.entities.Teleport;
import xyz.magicjourney.nebulaquest.entity.entities.planet.Planet;
import xyz.magicjourney.nebulaquest.entity.entities.planet.PlanetRegion;
import xyz.magicjourney.nebulaquest.music.MusicManager;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.panel.InteractivePanel;
import xyz.magicjourney.nebulaquest.ui.panel.MenuPanel;
import xyz.magicjourney.nebulaquest.ui.panel.OptionPanel;
import xyz.magicjourney.nebulaquest.ui.panel.TourPanel;

public class GameScreen extends AbstractScreen {
  protected Player activePlayer;
  protected ArrayList<Player> players;
  protected ArrayList<PlanetRegion> regions;
  protected ArrayList<Entity> entities;
  protected Board board;
  protected InteractivePanel interactivePanel;
  protected OptionPanel optionsPanel;
  protected TourPanel tourPanel;
  protected MenuPanel menuPanel;
  protected Dice dice;

  public GameScreen(SpriteBatch batch, AssetManager assets, ScreenManager screenManager, MusicManager musicManager) {
    super(batch, assets, screenManager, musicManager);
    
    this.players = new ArrayList<>();
    this.regions = new ArrayList<>();
    this.entities = new ArrayList<>();
    this.populatePlayers();
    this.populateRegions();
    this.populateBoard();

    this.activePlayer = this.players.get(0);
  }

  public void populatePlayers() {
    this.players.add(new Player("Player 1"));
    this.players.add(new Player("Player 2"));
    this.players.add(new Player("Player 3"));
  }

  protected void populateRegions() {
    this.regions.add(new PlanetRegion(0xfbe300ff, "A"));
    this.regions.add(new PlanetRegion(0xfba500ff, "B"));
    this.regions.add(new PlanetRegion(0xfb4a00ff, "C"));
    this.regions.add(new PlanetRegion(0x52c2cfff, "D"));
    this.regions.add(new PlanetRegion(0x991277ff, "E"));
    this.regions.add(new PlanetRegion(0xf041c4ff, "F"));
    this.regions.add(new PlanetRegion(0x218525ff, "G"));
    this.regions.add(new PlanetRegion(0x52eacfff, "H"));
  }

  protected void populateBoard() {
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
    this.board = new Board(this.entities, this.players, this.assets);
    this.dice = new Dice(assets);

    this.interactivePanel = new InteractivePanel(assets);
    this.interactivePanel.setPosition(542, 72);

    this.optionsPanel = new OptionPanel(assets);
    this.optionsPanel.setPosition(751, 189);

    this.tourPanel = new TourPanel(assets, this.players);
    this.tourPanel.setPosition(751, 72);

    this.menuPanel = new MenuPanel(assets);
    this.menuPanel.setPosition(542, 2);

    this.menuPanel.onMenuClick().subscribe(() -> this.screenManager.select("main-menu"));
    this.menuPanel.onSelect().subscribe(this::handlePanelSelect);
    this.menuPanel.onUnselect().subscribe(this::handlePanelUnselect);

    this.board.onFieldSelect().subscribe(this::handleFieldSelect);
    this.board.onFieldUnselect().subscribe(this::handlePanelUnselect);
    
    this.tourPanel.onRoll().subscribe(this::handleDiceRoll);
    this.tourPanel.onTurnStarted().subscribe((player) -> this.activePlayer = player);

    this.dice.setPosition(0, 0);
    this.stage.addActor(board);
    this.stage.addActor(interactivePanel);
    this.stage.addActor(optionsPanel);
    this.stage.addActor(tourPanel);
    this.stage.addActor(menuPanel);
    this.stage.addActor(dice);
  }

  protected void handlePanelSelect(TextButton button) {
    String panelName = button.getText().toString();

    this.board.unselectField();
    this.optionsPanel.select(panelName);
    this.interactivePanel.select(panelName);
  }
  
  protected void handleFieldSelect(Field field) {
    this.menuPanel.unselect();
    this.interactivePanel.selectCardView(field);
  }

  protected void handlePanelUnselect() {
    this.optionsPanel.unselect();
    this.interactivePanel.unselect();
  }

  protected void handleDiceRoll(Runnable unlock) {
    dice.roll((roll) -> {
      if (roll != 12) {
        this.board.movePlayer(activePlayer, roll, true);
        this.tourPanel.setTurnEndMode();
      }
      else {
        this.board.movePlayer(activePlayer, roll, false);
      }

      unlock.run();
    });
  }
}