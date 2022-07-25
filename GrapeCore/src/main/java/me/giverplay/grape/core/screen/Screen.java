package me.giverplay.grape.core.screen;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Screen {

  private final BufferStrategy bufferStrategy;
  private final Canvas canvas;
  private final JFrame frame;

  private final int width;
  private final int height;

  private Graphics renderGraphics;

  public Screen(String title, int width, int height) {
    this.width = width;
    this.height = height;

    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(width, height));

    frame = new JFrame(title);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(canvas);
    frame.pack();
    frame.setLocationRelativeTo(null);

    canvas.createBufferStrategy(3);
    bufferStrategy = canvas.getBufferStrategy();
  }

  public void beginRender() {
    renderGraphics = bufferStrategy.getDrawGraphics();
    renderGraphics.setColor(Color.WHITE);
    renderGraphics.fillRect(0, 0, width, height);
  }

  public void endRender() {
    bufferStrategy.show();
    renderGraphics.dispose();
  }

  public Graphics getRenderGraphics() {
    return renderGraphics;
  }

  public BufferStrategy getBufferStrategy() {
    return bufferStrategy;
  }

  public JFrame getFrame() {
    return frame;
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
