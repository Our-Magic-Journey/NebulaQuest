package xyz.magicjourney.nebulaquest.entity;

import java.util.Optional;

import xyz.magicjourney.nebulaquest.player.Player;

public interface Buyable extends Describable {
  Optional<Player> getOwner();
  int getValue();
  boolean canBeBought();
}
