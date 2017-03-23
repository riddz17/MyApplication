package com.zerogravity.myapplication.data.common.mapper;


import com.zerogravity.myapplication.data.common.model.User;
import com.zerogravity.myapplication.data.common.model.UserEntity;

public class UserEntityMapper {
  public static User getUserFromUserEntity(UserEntity userEntity){
    return User.create(userEntity.userId(),userEntity.userName(),userEntity.email());
  }

}
