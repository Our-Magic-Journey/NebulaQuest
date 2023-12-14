package xyz.magicjourney.nebulaquest.music;

import java.util.Optional;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Queue;

public class MusicManager {
  protected Optional<Music> menu;
  protected Queue<Music> tracks;
  protected float volume;

  public MusicManager() {
    this.tracks = new Queue<>();
    this.menu = Optional.empty();
    this.volume = 1f;
  }

  public void setMenuMusic(Music menu) {
    this.menu = Optional.of(menu);
  } 

  public void playMenuMusic() {
    this.tracks.first().stop();
    
    if (this.menu.isPresent() && !this.menu.get().isPlaying()) {
      this.playFromBeginning(this.menu.get(), true);
    }
  }

  protected void playFromBeginning(Music music, boolean loop) {
    music.setVolume(this.volume);
    music.setLooping(loop);
    music.setPosition(0);
    music.play();
  }

  public void playGameMusic() {
    if (this.menu.isPresent()) {
      this.menu.get().stop();
    }

    if (!this.tracks.first().isPlaying()) {
      this.playFromBeginning(this.tracks.first(), false);
    }
  }

  public void register(Music music) {
    music.setOnCompletionListener((m) -> {
      tracks.addLast(tracks.removeFirst());
      playFromBeginning(tracks.first(), false);
    });

    tracks.addLast(music);
  }
}
