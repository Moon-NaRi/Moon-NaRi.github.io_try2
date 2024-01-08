package com.lecture;

public class User {
  String email;
  String password;
  String position;
  String username;
  String nickname;

  public User(String email, String password, String position, String username, String nickname) {
    this.email = email;
    this.password = password;
    this.position = position;
    this.username = username;
    this.nickname = nickname;
  }
  @Override
  public String toString() {
    return "User{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", position='" + position + '\'' +
        ", username='" + username + '\'' +
        ", nickname='" + nickname + '\'' +
        '}';
  }
}
