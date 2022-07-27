package me.giverplay.grape.core.screen;

import me.giverplay.grape.core.GrapeDesktop;
import me.giverplay.grape.core.GrapeOS;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputHandler implements MouseListener {

  private final GrapeOS grape;

  public InputHandler(GrapeOS grape) {
    this.grape = grape;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    ((GrapeDesktop) grape.getDesktop()).onMouseClick(e.getX(), e.getY(), e.getButton());
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
