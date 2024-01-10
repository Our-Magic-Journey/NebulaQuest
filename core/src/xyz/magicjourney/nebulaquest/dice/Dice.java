package xyz.magicjourney.nebulaquest.dice;

import java.util.Random;
import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import xyz.magicjourney.nebulaquest.animation.AnimatedImage;
import xyz.magicjourney.nebulaquest.timer.Timer;

public class Dice extends Group {
  protected int rollNumber;
  protected int[] debugRolls;

  protected Random random;
  protected AnimatedImage animation;
  protected Table table;
  protected Timer viabilityTimer;
  protected boolean isRolling;

  public Dice(AssetManager assets) {
    this.random = new Random();
    this.viabilityTimer = new Timer(0.5f);
    this.animation = new AnimatedImage("animations/dice/dice.atlas", "roll1", 0.04f, false, assets);
    this.animation.stop();
    this.table = new Table();
    this.table.setFillParent(true);
    this.addActor(table);
    this.setWidth(400);
    this.setHeight(200);
    this.setVisible(false);
    this.rollNumber = 0;

    this.debugRolls = new int[] {
      12, 12, 6, 3,
      12, 12, 6, 6,
      12, 12, 6, 10,
    };
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    this.viabilityTimer.act(delta);
  }

  protected int debugRoll() {
    return debugRolls[Math.max(0, Math.min(this.debugRolls.length - 1, this.rollNumber++))];
  }

  public void roll(Consumer<Integer> callback) {
    int result = 3;// this.random.nextInt(11) + 1; 
    //int result = this.debugRoll(); 

    if (this.isRolling) {
      return;
    }

    this.setVisible(true);
    this.table.add(animation);
    this.animation.loadAnimation("roll" + result);
    this.viabilityTimer.clear();
    this.animation.restart();
    this.isRolling = true;
    
    this.animation.onEnd().subscribe(() -> {
      this.animation.clear();
      this.viabilityTimer.reset();
    });

    this.viabilityTimer.onTimeout().subscribe(() -> {
      this.table.clear();
      this.table.pack();
      this.isRolling = false;
      this.setVisible(false);
      callback.accept(result);
    });
  }
}
