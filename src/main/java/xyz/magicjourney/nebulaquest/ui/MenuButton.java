package xyz.magicjourney.nebulaquest.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import xyz.magicjourney.odyssey.input.InputManager;
import xyz.magicjourney.odyssey.renderer.Canvas;
import xyz.magicjourney.odyssey.ui.AbstractButton;

/** Hamburger like Menu Button.
 * 
 * @see AbstractButton
*/
public class MenuButton extends AbstractButton {

  /**
   * Constructs a new instance of the MenuButton.
   * 
   * @param x The x-coordinate of the button.
   * @param y The y-coordinate of the button.
   * @param inputManager The input manager for handling user input.
   * 
   */
  public MenuButton(int x, int y,InputManager inputManager, Canvas canvas) {
    super(x, y, 25, 40, new Color(0xff7b00), inputManager, canvas);
  }

  @Override
  public void draw(Graphics2D context, Canvas canvas) {
    super.draw(context, canvas);
    
    context.fillRect(x, y, 5, 40);  
    context.fillRect(x+10, y, 5, 40); 
    context.fillRect(x+20, y, 5, 40); 
  }
}