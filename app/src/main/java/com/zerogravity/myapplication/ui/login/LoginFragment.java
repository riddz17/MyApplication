package com.zerogravity.myapplication.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.zerogravity.myapplication.BaseFragment;
import com.zerogravity.myapplication.R;
import com.zerogravity.myapplication.data.common.mapper.UserEntityMapper;
import com.zerogravity.myapplication.data.common.model.GoogleAccountUser;
import com.zerogravity.myapplication.data.common.model.User;
import com.zerogravity.myapplication.data.common.model.UserEntity;
import timber.log.Timber;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragment implements OnConnectionFailedListener {

  private static final int RC_SIGN_IN = 9001;
  @BindView(R.id.google_sign_in_button)
  SignInButton googleSignInButton;
  @BindView(R.id.tv_user_name)
  TextView tvUserName;

  private ProgressDialog progressDialog;

  private OnFragmentInteractionListener mListener;
  private GoogleApiClient googleApiClient;

  public LoginFragment() {
    // Required empty public constructor
  }

  public static LoginFragment newInstance() {
    return new LoginFragment();
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_login, container, false);

    ButterKnife.bind(this, view);
    configureGoogleSignIn();
    return view;
  }


  private void configureGoogleSignIn() {

    googleSignInButton.setSize(SignInButton.SIZE_WIDE);
    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();

    // Build a GoogleApiClient with access to the Google Sign-In API and the
    // options specified by gso.
    googleApiClient = new GoogleApiClient.Builder(getActivity())
        .enableAutoManage(getActivity(), this)
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
        .build();
  }

  @Override
  public void onResume() {
    super.onResume();

    checkIfPreviouslyLoggedIn();
  }

  private void checkIfPreviouslyLoggedIn() {
    OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi
        .silentSignIn(googleApiClient);
    if (opr.isDone()) {
      // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
      // and the GoogleSignInResult will be available instantly.
      Timber.d("Got cached sign-in data");
      GoogleSignInResult result = opr.get();
      handleSignInResult(result);
    } else {
      // If the user has not previously signed in on this device or the sign-in has expired,
      // this asynchronous branch will attempt to sign in the user silently.  Cross-device
      // single sign-on will occur in this branch.
      showProgressDialog(getActivity());
      opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
        @Override
        public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
          hideProgressDialog(progressDialog);
          handleSignInResult(googleSignInResult);
        }
      });
    }
  }


  private ProgressDialog createProgressDialog(@NonNull Activity activity) {
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(activity);
      progressDialog.setMessage(getString(R.string.loading_wait));
      progressDialog.setIndeterminate(true);
    }
    return progressDialog;
  }

  private void showProgressDialog(@NonNull Activity activity) {
    createProgressDialog(activity).show();
  }

  private void hideProgressDialog(@NonNull ProgressDialog progressDialog) {
    if (progressDialog.isShowing()) {
      progressDialog.hide();
    }

  }

  @OnClick(R.id.google_sign_in_button)
  public void onSignInClick(SignInButton signInButton) {
    googleSignIn();
  }

  private void googleSignIn() {
    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      handleSignInResult(result);
    }
  }

  private void handleSignInResult(GoogleSignInResult result) {
    Timber.d("handleSignInResult:" + result.isSuccess());
    if (result.isSuccess()) {
      // Signed in successfully, show authenticated UI.
      GoogleSignInAccount acct = result.getSignInAccount();
      if (acct == null) {
        updateUI(false);
//        showAlert("");
        onLoginFailed(new Exception("Login failed"));
        return;
      }
      UserEntity userEntity = new GoogleAccountUser(acct.getId(), acct.getDisplayName(),
          acct.getEmail());
      User user = UserEntityMapper.getUserFromUserEntity(userEntity);

      tvUserName.setText(getString(R.string.signed_in_welcome_format, acct.getDisplayName()));
      updateUI(true);
      onLoginFinished(user);
    } else {
      // Signed out, show unauthenticated UI.
      updateUI(false);
    }
  }

  private void onLoginFailed(Throwable t) {
    mListener.onLoginFailed(t);
  }

  private void updateUI(final boolean signedIn) {
    int shortAnimTime = getResources().getInteger(android.R.integer.config_longAnimTime);

    googleSignInButton.setVisibility(signedIn ? View.GONE : View.VISIBLE);
    googleSignInButton.animate().setDuration(shortAnimTime).alpha(
        signedIn ? 0 : 1).setListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        googleSignInButton.setVisibility(signedIn ? View.GONE : View.VISIBLE);
      }
    });

    tvUserName.setVisibility(signedIn ? View.VISIBLE : View.GONE);
    tvUserName.animate().setDuration(shortAnimTime).alpha(
        signedIn ? 1 : 0).setListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        tvUserName.setVisibility(signedIn ? View.VISIBLE : View.GONE);
      }
    });
  }


  public void onLoginFinished(User user) {
    if (mListener != null) {
      mListener.onLoginFinished(user);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   */
  public interface OnFragmentInteractionListener {

    void onLoginFinished(User user);
    void onLoginFailed(Throwable t);
  }

}
