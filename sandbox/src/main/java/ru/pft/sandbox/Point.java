package ru.pft.sandbox;

/**
 * Created by Наташа on 25.01.2017.
 */
public class Point {
  public double x, y;

  public Point (double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(double x2, double y2) {
    return Math.sqrt((Math.pow((x2 - this.x), 2) + Math.pow((y2 - this.y), 2)));
  }
}