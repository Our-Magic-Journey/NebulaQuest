package xyz.magicjourney.nebulaquest.board;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Entity;
import xyz.magicjourney.nebulaquest.event.EventGetter;
import xyz.magicjourney.nebulaquest.event.ParameterizedEventGetter;
import xyz.magicjourney.nebulaquest.player.Player;
import xyz.magicjourney.nebulaquest.ui.select.SelectGroup;

public class Board extends Group {
  public final int WIDTH = 540;
  public final int HEIGHT = 540;

  protected int[] lastElementInRows;
  protected Table topRow;
  protected Table rightRow;
  protected Table leftRow;
  protected Table bottomRow;
  protected Image background;
  protected SelectGroup<Field> fields;
  protected HashMap<Player, Pawn> players;

  public Board(ArrayList<Entity> entities, ArrayList<Player> players, AssetManager assets) {
    this.setWidth(540);
    this.setHeight(540);

    this.background = new Image(new TextureRegionDrawable(assets.get("images/board-background.png",  Texture.class)));
    this.addActor(background);

    this.players = new HashMap<>();
    this.fields = new SelectGroup<>();
    this.fields.add(entities.get(0).toField(assets));
    this.fields.get(0).setPosition(WIDTH - 68, 4);
    this.addActor(this.fields.get(0));

    for (int i = 1; i < 40; i++) {
      Field field = entities.get(i).toField(assets);
      
      this.setFieldPositionByIndex(field, i);

      this.fields.add(field);
      this.addActor(field);
    }

    players.forEach((player) -> {
      Pawn ship = new Pawn(player, assets);

      this.players.put(player, ship);
      this.fields.get(0).addPawn(ship);
    });
  }

  protected void setFieldPositionByIndex(Field field, int index) {
    float margin = (index % 10 <= 1 ? 10 : 12);
    Field previous = this.fields.get(index - 1);

    field.setPosition(previous.getX(), previous.getY());

    if (index < 11) {
      field.moveBy(-field.getWidth() - margin, 0);
    }

    else if (index == 11) {
      field.moveBy(0, previous.getWidth() + field.getWidth() + margin);
      field.rotateBy(270);
    }

    else if (index < 20) {
      field.moveBy(0, field.getWidth() + margin);
      field.rotateBy(270);
    }

    else if (index == 20) {
      field.moveBy(0, margin);
      field.correctPawnRotationBy(180);
    }

    else if (index < 30) {
      field.moveBy(previous.getWidth() + margin, 0);
      field.correctPawnRotationBy(180);
    }

    else if (index == 30) {
      field.moveBy(previous.getWidth() + margin, 0);
      field.correctPawnRotationBy(90);
    }

    else if (index == 31) {
      field.rotateBy(90);
      field.moveBy(field.getHeight(), -field.getWidth() - margin);
    }

    else if (index < 40) {
      field.rotateBy(90);
      field.moveBy(0, -field.getWidth() - margin);
    }
  }

  public void unselectField() {
    this.fields.uncheckAll();
  }

  public ParameterizedEventGetter<Field> onFieldSelect() {
    return this.fields.onSelect();
  }

  public EventGetter onFieldUnselect() {
    return this.fields.onUnselect();
  }

  public void setPlayerPosition(Player player, int field, boolean isFinalMove) {    
    field = field % this.fields.length();

    this.players.get(player).remove();
    this.players.get(player).setField(field);
    this.fields.get(field).addPawn(this.players.get(player));

    if (isFinalMove) {
      this.fields.get(field).getEntity().onEnter(player);
    }
  }

  public void movePlayer(Player player, int moveBy, boolean isFinalMove) {
    Pawn pawn = players.get(player);
    int finalPos = (pawn.getField() + moveBy) % this.fields.length();

    for (int i = pawn.getField(); i <= finalPos; i++) {
      this.fields.get(i).getEntity().onPass(player);
    }

    this.setPlayerPosition(player, finalPos, isFinalMove);
  }
}
