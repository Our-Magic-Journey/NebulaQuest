package xyz.magicjourney.nebulaquest.entity;

import xyz.magicjourney.nebulaquest.player.Player;

public interface Interactiveable {
  default boolean isDecisionRequired(Player player) {
    return true;
  }

  String getInteractiveablePanelName(Player player);
}