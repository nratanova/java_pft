package ru.pft.sandbox;

public class FirstProgram
{
	public static void main (String[] Args) {
    hello("world");
    hello("chel");
    hello("Natali");

    Kvadrat k = new Kvadrat(4);
    System.out.println("Площадь квадрата со стороной " + k.l + " = " + k.kv());

    Pryam pp = new Pryam(8, 10);
    System.out.println("Плащадь прямоугольника со сторонами " + pp.a + ", " + pp.b + " = " + pp.pryam());
  }

	public static void hello (String text) {
	  System.out.println("Hello," + text + "!");
  }

}