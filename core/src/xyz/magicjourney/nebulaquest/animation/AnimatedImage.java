package xyz.magicjourney.nebulaquest.animation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import xyz.magicjourney.nebulaquest.event.Event;
import xyz.magicjourney.nebulaquest.event.EventGetter;

public class AnimatedImage extends Image {
  protected TextureAtlas atlas;
  protected Array<AtlasRegion> animationFrames;
  protected Animation<AtlasRegion> animation;
  protected TextureRegionDrawable texture;
  protected float frameDuration;
  protected float time;
  protected boolean stopped;
  protected boolean loop;
  protected int frameCount;
  protected int displayedFrameId;

  public Event animationEndEvent;

  public AnimatedImage(String atlas, String animation, AssetManager assets) {
    this(assets.get(atlas, TextureAtlas.class), animation, 0.05f, false);
  }

  public AnimatedImage(String atlas, String animation, float frameDuration, AssetManager assets) {
    this(assets.get(atlas, TextureAtlas.class), animation, frameDuration, false);
  }

  public AnimatedImage(String atlas, String animation, float frameDuration, boolean loop, AssetManager assets) {
    this(assets.get(atlas, TextureAtlas.class), animation, frameDuration, loop);
  }
 
  public AnimatedImage(TextureAtlas animations, String animation, float frameDuration, boolean loop) {
    this.atlas = animations;
    this.frameDuration = frameDuration;
    this.loop = loop;
    this.animationEndEvent = new Event();

    this.loadAnimation(animation);
    this.restart();
  }

  public void loadAnimation(String animation) {
    this.animationFrames = atlas.findRegions(animation);
    this.animation = new Animation<>(this.frameDuration, this.animationFrames);
    this.texture = new TextureRegionDrawable(this.animation.getKeyFrame(0));
    this.frameCount = this.animation.getKeyFrames().length;
    this.displayedFrameId = 0;
    this.setDrawable(texture);
  }

  public void stop() {
    this.stopped = true;
  }

  public void restart() {
    this.time = 0;
    this.stopped = false;
  }

  public void play() {
    this.stopped = false;
  }

  public EventGetter onEnd() {
    return this.animationEndEvent;
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    
    if (!stopped) {
      this.time += delta;
      AtlasRegion frame = this.animation.getKeyFrame(this.time);

      this.texture.setRegion(frame);

      if (this.isFinished()) {
        if (this.loop) {
          this.restart();
        }
        else {
          this.stop();
        }

        this.animationEndEvent.emit();
      }
    }
  }

  public boolean isFinished() {
    return this.animation.isAnimationFinished(this.time);
  }
}
