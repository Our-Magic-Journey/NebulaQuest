package xyz.magicjourney.nebulaquest.entity;

import java.util.Optional;

import xyz.magicjourney.nebulaquest.player.Player;

public interface Buyable extends Describable, Interactiveable {
  Optional<Player> getOwner();
  int getValue();
  int getFee();

  default boolean isOwner(Player player) {
    return this.getOwner().isPresent() && this.getOwner().get() == player;
  }

  void setOwner(Player player);

  default boolean mustPayFee(Player player) {
    return this.getOwner().isPresent() && !this.isOwner(player);
  }

  default boolean canByBought() {
    return this.getOwner().isEmpty();
  }

  @Override
  default String getInteractiveablePanelName(Player player) {
    if (this.getOwner().isPresent()) {
      if (this.getOwner().get() == player) {
        return "Description";
      }
      
      return "PayFee";
    }
    
    return  "Auction";
  }
  
  @Override
  default boolean isDecisionRequired(Player player) {
    return this.mustPayFee(player) || this.canByBought();
  }
}
