package com.zerogravity.myapplication.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.zerogravity.myapplication.R;
import com.zerogravity.myapplication.data.common.model.User;
import com.zerogravity.myapplication.ui.home.HomeActivity;
import com.zerogravity.myapplication.utils.ActivityUtils;

public class LoginActivity extends AppCompatActivity implements
    LoginFragment.OnFragmentInteractionListener {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
        .findFragmentByTag(LoginFragment.class.getName());

    if (loginFragment == null) {
      loginFragment = LoginFragment.newInstance();
    }
    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
        loginFragment, R.id.activity_login_container);

  }


  @Override
  public void onLoginFinished(User user) {
    startActivity(HomeActivity.newIntent(this, user));
    finish();
  }

  @Override
  public void onLoginFailed(Throwable t) {

  }
}
