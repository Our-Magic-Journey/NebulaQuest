package xyz.magicjourney.nebulaquest.logger;

import com.badlogic.gdx.utils.Logger;

public class StringLogger extends Logger {
  protected String text;

  public StringLogger(int level) {
    super("", level);

    this.text = "";
  }

  public String getText() {
    return text;
  }
  
  public StringLogger() {
    this(DEBUG);
  }
  
  protected void setText(String message) {
    this.text = message;
  }

  public void debug (String message) {
		if (getLevel() >= DEBUG){
      this.setText(message);
    }
	}

	public void debug (String message, Exception exception) {
		if (getLevel() >= DEBUG){
      this.setText(message);
    }
	}

	public void info (String message) {
		if (getLevel() >= INFO){
      this.setText(message);
    }
	}

	public void info (String message, Exception exception) {
		if (getLevel() >= INFO){
      this.setText(message);
    }
	}

	public void error (String message) {
		if (getLevel() >= ERROR){
      this.setText(message);
    }
	}

	public void error (String message, Throwable exception) {
		if (getLevel() >= ERROR){
      this.setText(message);
    }
	}
}
