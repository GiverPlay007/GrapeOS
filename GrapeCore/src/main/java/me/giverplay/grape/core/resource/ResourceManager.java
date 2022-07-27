package me.giverplay.grape.core.resource;

import me.giverplay.grape.core.GrapeOS;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;

public class ResourceManager {

  private final GrapeOS grape;

  public ResourceManager(GrapeOS grape) {
    this.grape = grape;
  }

  public Image getImage(String path) {
    try {
      return ImageIO.read(grape.getClass().getResource(path));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
