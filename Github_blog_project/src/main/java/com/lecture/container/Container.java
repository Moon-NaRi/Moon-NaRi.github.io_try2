package com.lecture.container;

import java.util.Scanner;

public class Container {
  public static Scanner scanner;

  //프로그램 실행되자마자 만들어짐
  static {
    scanner = new Scanner(System.in);
  }
}
