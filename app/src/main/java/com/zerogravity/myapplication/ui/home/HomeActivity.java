package com.zerogravity.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.zerogravity.myapplication.R;
import com.zerogravity.myapplication.data.common.model.Clan;
import com.zerogravity.myapplication.data.common.model.User;
import com.zerogravity.myapplication.ui.clans.ClanListFragment;
import com.zerogravity.myapplication.utils.ActivityUtils;

public class HomeActivity extends AppCompatActivity implements ClanListFragment.OnFragmentInteractionListener{

  public static final String BUNDLE_KEY_USER_MODEL = "b_key_user_model";

  /**
   * Creates an {@link Intent} which is to be used to start this activity with
   * all the required bundle data.
   *
   *
   * @param context The context of the calling class
   * @param user  A {@link User} object which contains the details of the user.
   *              This object will be put inside the bundle under the Key: {@link #BUNDLE_KEY_USER_MODEL}
   * @return The Intent with all the required bundle data.
   */
  public static Intent newIntent(Context context, User user) {
    Intent intent = new Intent(context, HomeActivity.class);
    Bundle bundle = new Bundle();
    bundle.putParcelable(BUNDLE_KEY_USER_MODEL, user);
    intent.putExtras(bundle);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    ClanListFragment friendListFragment = (ClanListFragment) getSupportFragmentManager()
        .findFragmentByTag(ClanListFragment.class.getName());

    if (friendListFragment == null) {
      friendListFragment = ClanListFragment.newInstance();
    }

    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
        friendListFragment, R.id.activity_home_container);
  }


  @Override
  public void openClanDetails(Clan clan) {

  }

  @Override
  public void openChat(Clan clan) {

  }
}
