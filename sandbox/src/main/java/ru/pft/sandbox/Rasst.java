package ru.pft.sandbox;

/**
 * Created by Наташа on 26.01.2017.
 */
public class Rasst {

  public static void main (String[] Args){

    Point p1 = new Point(1,2);
    Point p2 = new Point(4, 7);
    Point p3 = new Point(1,0);
    //Point p2 = new Point(4,6);
    //p1.x = 1;
    //p1.y = 2;
    //p2.x = 4;
    //p2.y = 6;
    System.out.println("Расстояние между точками " + "P1 (x=" + p1.x + ", y=" + p1.y + ")" + " и " + "PN (x=4, y=6) = "
            + p1.distance(4,6));

    System.out.println("Расстояние между точками " + "P2 (x=" + p2.x + ", y=" + p2.y + ")" + " и " + "PN (x=8, y=10) = "
            + p2.distance(8,10));

    System.out.println("Расстояние между точками " + "P3 (x=" + p3.x + ", y=" + p3.y + ")" + " и " + "PN (x=1, y=2) = "
            + p3.distance(1,2));

  }



  /*public static double distance(Point p1, Point p2) {
    return Math.sqrt((Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2)));
  }*/

}
