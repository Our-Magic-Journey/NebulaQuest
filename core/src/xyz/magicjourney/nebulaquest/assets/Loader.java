package xyz.magicjourney.nebulaquest.assets;

import java.util.stream.Stream;

import com.badlogic.gdx.assets.AssetManager;

public class Loader {
  protected String[] extensions;
  protected Class<?> loader;

  public Loader(String[] extensions, Class<?> loader) {
    this.extensions = extensions;
    this.loader = loader;
  }

  /**
   * Checks if a resource at the specified path is supported by loader based on its file extension.
   *
   * @param path The path of the resource.
   * @return {@code true} if the resource can be loaded, {@code false} otherwise.
   */
  public boolean canBeLoaded(String path) {
    String extension = getFileExtension(path);

    return Stream.of(extensions).anyMatch(ext -> extension.equals(ext));
  }

  /**
   * Retrieves the file extension from the specified path.
   *
   *  <pre>Examples:
   *    getFileExtension("/data/file.json") returns ".json"
   *    getFileExtension("./data/file.animation.json") returns ".animation.json"
   *    getFileExtension("./data/file") returns "file"
   *    getFileExtension(".gitignore") returns ".gitignore"
   *    getFileExtension("Dockerfile") returns "Dockerfile"
   *  </pre>
   * 
   * @param path The path of the resource.
   * @return The file extension.
   */
  protected String getFileExtension(String path) {
    String filename = getFileName(path);
    int firstDot = filename.indexOf(".", 0);

    if (firstDot > 0) {
      return filename.substring(firstDot);
    }

    return filename;
  }

  /**
   * Retrieves the file name from the specified path.
   *
   * @param path The path of the resource.
   * @return The file name.
   */
  protected String getFileName(String path) {
    return path.substring(Math.max(0, path.indexOf("/")));
  }

  /**
   * Attempts to load a resource from the specified path.
   *
   * @param path The path of the resource to be loaded.
   * @param assets The asset manager that will try to load a resource.
   * @return {@code true} if the resource is loaded successfully, {@code false} otherwise.
   */
  public boolean load(AssetManager assets, String path) {
    if (!this.canBeLoaded(path)) {
      return false;
    }

    assets.load(path, this.loader);

    return true;
  }
}
