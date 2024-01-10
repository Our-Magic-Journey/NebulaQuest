package xyz.magicjourney.nebulaquest.screen;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import xyz.magicjourney.nebulaquest.board.Board;
import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.dice.Dice;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.entity.Interactiveable;
import xyz.magicjourney.nebulaquest.entity.entities.Casino;
import xyz.magicjourney.nebulaquest.entity.entities.Mine;
import xyz.magicjourney.nebulaquest.entity.entities.Nebula;
import xyz.magicjourney.nebulaquest.entity.entities.Start;
import xyz.magicjourney.nebulaquest.entity.entities.Teleport;
import xyz.magicjourney.nebulaquest.entity.entities.TeleportHub;
import xyz.magicjourney.nebulaquest.entity.entities.UnknownJump;
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
  protected Entity activeEntity;
  protected ArrayList<Player> players;
  protected ArrayList<PlanetRegion> regions;
  protected ArrayList<Entity> entities;
  protected Board board;
  protected InteractivePanel interactivePanel;
  protected OptionPanel optionsPanel;
  protected TourPanel tourPanel;
  protected MenuPanel menuPanel;
  protected Dice dice;
  protected boolean playerMoved;

  public GameScreen(SpriteBatch batch, AssetManager assets, ScreenManager screenManager, MusicManager musicManager) {
    super(batch, assets, screenManager, musicManager);

    this.players = new ArrayList<>();
    this.regions = new ArrayList<>();
    this.entities = new ArrayList<>();
    this.populatePlayers();
    this.populateRegions();
    this.populateBoard();

    this.activePlayer = this.players.get(0);
    this.activeEntity = this.entities.get(0);
    this.playerMoved = false;
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
    this.entities.add(new Planet("anch-to", 100, regions.get(0)));
    this.entities.add(new Planet("alderaan", 100, regions.get(0)));
    this.entities.add(new Planet("bespin", 100, regions.get(0)));
    this.entities.add(new Planet("kamino", 100, regions.get(0)));
    this.entities.add(new Teleport(25));
    this.entities.add(new Planet("rodia", 200, regions.get(1)));
    this.entities.add(new Casino());
    this.entities.add(new Planet("hoth", 200, regions.get(1)));
    this.entities.add(new Planet("fondor", 250, regions.get(1)));

    this.entities.add(new Nebula());
    this.entities.add(new Planet("dathomir", 350, regions.get(2)));
    this.entities.add(new Planet("paradise", 350, regions.get(2)));
    this.entities.add(new Mine());
    this.entities.add(new Planet("utapau", 400, regions.get(2)));
    this.entities.add(new Teleport(35));
    this.entities.add(new Planet("naboo", 500, regions.get(3)));
    this.entities.add(new Planet("exegol", 500, regions.get(3)));
    this.entities.add(new Planet("coruscant", 500, regions.get(3)));
    this.entities.add(new Planet("teth", 500, regions.get(3)));

    this.entities.add(new TeleportHub());
    this.entities.add(new Planet("mon cala", 600, regions.get(4)));
    this.entities.add(new Planet("tatooine", 600, regions.get(4)));
    this.entities.add(new Planet("moon antar", 600, regions.get(4)));
    this.entities.add(new Planet("antar", 600, regions.get(4)));
    this.entities.add(new Teleport(5));
    this.entities.add(new Planet("prakith", 700, regions.get(5)));
    this.entities.add(new Mine());
    this.entities.add(new Planet("nemtox", 700, regions.get(5)));
    this.entities.add(new Planet("moraband", 750, regions.get(5)));

    this.entities.add(new UnknownJump(5, 15, 25, 35, 0, 10));
    this.entities.add(new Planet("belsavis", 850, regions.get(6)));
    this.entities.add(new Planet("lola sayu", 850, regions.get(6)));
    this.entities.add(new Planet("castilon", 850, regions.get(6)));
    this.entities.add(new Planet("vortex", 950, regions.get(6)));
    this.entities.add(new Teleport(15));
    this.entities.add(new Mine());
    this.entities.add(new Casino());
    this.entities.add(new Planet("lego", 1200, regions.get(7)));
    this.entities.add(new Planet("mustafar", 1500, regions.get(7)));
  }

  @Override
  public void show() {
    if(this.board == null) {
      this.create();
    }

    musicManager.playGameMusic();

    super.show();
  }

  public void create() {
    this.board = new Board(this.entities, this.players, this.assets, this.handleFieldEnter);
    this.dice = new Dice(assets);

    this.tourPanel = new TourPanel(assets, this.players);
    this.tourPanel.setPosition(751, 72);

    this.interactivePanel = new InteractivePanel(assets, this.dice, this.tourPanel, this.activePlayer, this.board, this.entities.get(0));
    this.interactivePanel.setPosition(542, 72);

    this.optionsPanel = new OptionPanel(this.dice, assets);
    this.optionsPanel.setPosition(751, 189);

    this.menuPanel = new MenuPanel(assets);
    this.menuPanel.setPosition(542, 2);

    this.menuPanel.onMenuClick().subscribe(() -> this.screenManager.select("main-menu"));
    this.menuPanel.onSelect().subscribe(this.handlePanelSelect);
    this.menuPanel.onUnselect().subscribe(this.handlePanelUnselect);

    this.board.onFieldSelect().subscribe(this.handleFieldSelect);
    this.board.onFieldUnselect().subscribe(this.handlePanelUnselect);

    this.tourPanel.onRoll().subscribe(this.handleDiceRoll);
    this.tourPanel.onTurnStarted().subscribe(this.handleTurnStarted);

    this.dice.setPosition(0, 0);
    this.stage.addActor(board);
    this.stage.addActor(interactivePanel);
    this.stage.addActor(optionsPanel);
    this.stage.addActor(tourPanel);
    this.stage.addActor(menuPanel);
    this.stage.addActor(dice);
  }

  protected Consumer<Player> handleTurnStarted = (player) -> {
    this.activePlayer = player;
    this.interactivePanel.reset();
    this.playerMoved = false;

    // We first set activeEntity to null ensure that handleFieldSelect method
    // will display entity, regardless of entity being Interactiveable or not.
    this.activeEntity = null;
    // this will fire the board's onFieldSelect event that wil run the handleFieldSelect method
    this.board.selectFieldUnderPlayer(player);
    // now we can safely change the active entity.
    this.activeEntity = this.board.getFieldUnderPlayer(player);
  };

  protected Consumer<TextButton>handlePanelSelect = (button) -> {
    String panelName = button.getText().toString();

    this.board.unselectField();
    this.optionsPanel.select(panelName);
    this.interactivePanel.select(panelName, this.activePlayer, this.activeEntity);
  };

  protected Runnable handlePanelUnselect = () -> {
    this.optionsPanel.unselect();
    this.interactivePanel.unselect();
  };

  protected Consumer<Runnable> handleDiceRoll = (unlock) -> {
    dice.roll((roll) -> {
      if (roll != 12) {
        this.playerMoved = true;
        this.tourPanel.setTurnEndMode();
        this.board.movePlayer(activePlayer, roll, true);
      }
      else {
        this.board.movePlayer(activePlayer, roll, false);
      }

      unlock.run();
    });
  };

  protected Consumer<Field> handleFieldSelect = (field) -> {
    this.menuPanel.unselect();

    if (field.getEntity() == this.activeEntity) {
      this.displayEntityInInteractivePanel(field.getEntity());
    }
    else {
      this.interactivePanel.selectDescription(field.getEntity(), this.activePlayer);
    }
  };

  protected Consumer<Entity> handleFieldEnter = (entity) -> {
    this.activeEntity = entity;

    if (!(entity instanceof Interactiveable interactive) || !interactive.isDecisionRequired(this.activePlayer)) {
      this.tourPanel.unblockTurnButton();
    }

    this.displayEntityInInteractivePanel(entity);
  };

  protected void displayEntityInInteractivePanel(Entity entity) {
    if (entity instanceof Interactiveable interactive && this.playerMoved) {
      this.interactivePanel.select(interactive.getInteractiveablePanelName(this.activePlayer), this.activePlayer, entity);
    }
    else {
      this.interactivePanel.select("Description", this.activePlayer, entity);
    }
  }
}
