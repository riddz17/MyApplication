package com.zerogravity.myapplication.data.common.model;


import android.os.Parcelable;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User implements Parcelable {


  public static User create(String newId, String newName, String newEmail) {
    return builder()
        .setId(newId)
        .setName(newName)
        .setEmail(newEmail)
        .build();
  }

  public static Builder builder() {
    return new AutoValue_User.Builder();
  }

  abstract String getId();

  abstract String getName();

  abstract String getEmail();

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setId(String newId);

    public abstract Builder setName(String newName);

    public abstract Builder setEmail(String newEmail);

    public abstract User build();
  }

}