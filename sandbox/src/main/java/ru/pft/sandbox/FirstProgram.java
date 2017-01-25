package ru.pft.sandbox;

public class FirstProgram
{
	public static void main (String[] Args) {
		hello("world");
    hello("chel");
    hello("Natali");

    double l = 5;
    System.out.println("Площадь квадрата со стороной " + l + " = " + kv(l));

    double a1 = 2, b1=4;
    System.out.println("Плащадь прямоугольника со сторонами " + a1 + ", "+ b1 + " = " + pryam(a1,b1));

	}

	public static void hello (String text) {
	  System.out.println("Hello," + text + "!");
  }

  public static double kv (double len){
	  return len*len;
  }

  public static double pryam (double a, double b) {
    return a*b;
  }
}