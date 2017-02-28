package ru.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Наташа on 28.02.2017.
 */
public class Collections {

  public static void main (String[] args) {

    String[] langs = {"Java","C#","Python","PHP"};

    List languages = Arrays.asList("Java","C#","Python","PHP");

    for (String l : langs)   {
      System.out.println("Я хочу выучить "+l);
    }

    for (Object l : languages)   {
      System.out.println("Я хочу знать "+l);
    }

    for (int i = 0; i < languages.size(); i++) {
      System.out.println("Хочу выучить " + languages.get(i));
    }
  }
}
