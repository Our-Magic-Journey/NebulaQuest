package xyz.magicjourney.nebulaquest.dice;

import java.util.Random;
import java.util.function.Consumer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import xyz.magicjourney.nebulaquest.animation.AnimatedImage;
import xyz.magicjourney.nebulaquest.timer.Timer;

public class Dice extends Group {
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


  }

  @Override
  public void act(float delta) {
    super.act(delta);

    this.viabilityTimer.act(delta);
  }

  public void roll(Consumer<Integer> callback) {
    int result = this.random.nextInt(11) + 1;

    if (this.isRolling) {
      return;
    }

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
      callback.accept(result);
    });
  }
}
