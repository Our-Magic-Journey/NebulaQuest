package xyz.magicjourney.nebulaquest.entity;

import xyz.magicjourney.nebulaquest.player.Player;

public interface Buyable {
  Player getOwner();
  boolean canBeBought();
}
