package ru.pft.sandbox;

/**
 * Created by Наташа on 26.01.2017.
 */
public class Rasst {

  public static void main (String[] Args){

    Point p1 = new Point();
    Point p2 = new Point();
    p1.x = 1;
    p1.y = 2;
    p2.x = 4;
    p2.y = 6;
    System.out.println("Расстояние между точками " + "P1 ("+ "x = " + p1.x + ", " + "y = " + p1.y +")" + " и " +
            "P2 (" + "x =" + p2.x + ", " + "y = " + p2.y + ")" + " = " + distance(p1, p2));
  }


  public static double distance(Point p1, Point p2) {
    return Math.sqrt((Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2)));
  }

}
