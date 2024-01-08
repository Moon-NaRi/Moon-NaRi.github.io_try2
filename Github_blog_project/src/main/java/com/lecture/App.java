package com.lecture;

import com.lecture.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  List<User> users;
  String position;

  public App() {
    users = new ArrayList<>();
    position = "bronze";
  }

  public void run() {
    Scanner sc = Container.scanner;

    while (true) {
      System.out.printf("명령어) ");
      // \n을 포함한 한 라인을 읽고 \n을 버린 나머지를 Return
      String cmd = sc.nextLine();

      if (cmd.equals("/usr/user/list")) {
        System.out.println("==회원 리스트==");

        if (users.isEmpty()) {
          System.out.println("회원이 존재하지 않습니다.");
          continue;
        }

        System.out.println("이메일 / 이름 / 별명");
        for (User user : users) {
          System.out.printf("%s / %s / %s\n", user.email, user.username, user.nickname);
        }//for

        /*역순 출력하기
        for (int i= users.size() -1; i >=0; i--) {
          User user = users.get(i);
          System.out.printf("%s / %s / %s\n", user.email, user.username, user.nickname);
        }*/

      }//if
      else if (cmd.equals("/usr/user/write")) {
        System.out.println("==회원 등록==");
        System.out.printf("이메일 : ");
        String email = sc.nextLine();
        System.out.printf("비밀번호 : ");
        String password = sc.nextLine();
        System.out.printf("이름 : ");
        String username = sc.nextLine();
        System.out.printf("별명 : ");
        String nickname = sc.nextLine();

        //Create User
        User user = new User(email, password, position, username, nickname);
        //Add ArrayList
        users.add(user);

        System.out.println("회원이 등록되었습니다.");
      } else if (cmd.equals("exit")) {
        System.out.println("프로그램 종료");
        break;
      }
    }//while
    
    sc.close(); //메모리 누수 방지
  }//run()
}
