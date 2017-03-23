package com.zerogravity.myapplication.data.common.model;


import android.support.annotation.NonNull;

public class GoogleAccountUser implements UserEntity {


  private String userId;
  private String userName;
  private String email;

  private GoogleAccountUser(){}

  public GoogleAccountUser(@NonNull String id, @NonNull String name, @NonNull String email) {
    this.userId = id;
    this.userName = name;
    this.email = email;
  }

  @Override
  public String userName() {
    return this.userName;
  }

  @Override
  public String email() {
    return this.email;
  }

  @Override
  public String userId() {
    return this.userId;
  }
}
