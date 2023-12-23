package xyz.magicjourney.nebulaquest.board;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import xyz.magicjourney.nebulaquest.board.field.Field;
import xyz.magicjourney.nebulaquest.entity.Entity;

public class Board extends Group {
  public final int WIDTH = 540;
  public final int HEIGHT = 540;

  protected int[] lastElementInRows;
  protected Table topRow;
  protected Table rightRow;
  protected Table leftRow;
  protected Table bottomRow;
  protected Image background;
  protected ArrayList<Field> fields;

  public Board(ArrayList<Entity> entities, AssetManager assets) {
    this.setWidth(540);
    this.setHeight(540);

    this.background = new Image(new TextureRegionDrawable(assets.get("images/board-background.png",  Texture.class)));
    this.addActor(background);

    this.fields = new ArrayList<>();
    this.fields.add(entities.get(0).toField(assets));
    this.fields.get(0).setPosition(WIDTH - 68, 4);
    this.addActor(this.fields.get(0));

    for (int i = 1; i < 40; i++) {
      Field field = entities.get(i).toField(assets);
      
      this.setFieldPositionByIndex(field, i);

      this.fields.add(field);
      this.addActor(field);
    }
  }

  protected void setFieldPositionByIndex(Field field, int index) {
    float margin = (index % 10 <= 1 ? 10 : 12);
    Field previus = this.fields.get(index - 1);

    field.setPosition(previus.getX(), previus.getY());

    if (index < 11) {
      field.moveBy(-field.getWidth() - margin, 0);
    }

    else if (index == 11) {
      field.moveBy(0, previus.getWidth() + field.getWidth() + margin);
      field.rotateBy(270);
    }

    else if (index < 20) {
      field.moveBy(0, field.getWidth() + margin);
      field.rotateBy(270);
    }

    else if (index == 20) {
      field.moveBy(0, margin);
    }

    else if (index < 31) {
      field.moveBy(previus.getWidth() + margin, 0);
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
}
