package xyz.magicjourney.nebulaquest.logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssetsLoadingLogger extends StringLogger {
  protected String lastLoaded;
  protected Pattern regex;

  public AssetsLoadingLogger(int level) {
    super(level);

    this.lastLoaded = "";

    /*
      regex: \.\/[^ ,\s]+
      matches anything that starts with 'Loading:'' 
      and doesn't contain white spaces or ','.

      [^] - negated set
      + - match one or more of the preceding token

     */
    this.regex = Pattern.compile("Loading: [^,]+");
  }

  public AssetsLoadingLogger() {
    this(DEBUG);
  }
  
  public String getLastLoaded() {
    return lastLoaded;
  }

  protected void setText(String message) {
    super.setText(message);
    
    Matcher matcher = regex.matcher(this.text);

    if (matcher.find()) {
      lastLoaded = matcher.group(0).replace("Loading: ", "");
    }
  }
}
