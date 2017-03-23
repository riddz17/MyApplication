package com.zerogravity.myapplication.ui.clans;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zerogravity.myapplication.R;
import com.zerogravity.myapplication.data.clan.ClanLocalDataSourceImpl;
import com.zerogravity.myapplication.data.clan.ClanRemoteDataSourceImpl;
import com.zerogravity.myapplication.data.clan.ClanRepository;
import com.zerogravity.myapplication.data.common.model.Clan;
import com.zerogravity.myapplication.BaseFragment;
import java.util.List;


public class ClanListFragment extends BaseFragment implements ClanListMvpContract.View {

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   */
  public interface OnFragmentInteractionListener {
    void openClanDetails(Clan clan);
    void openChat(Clan clan);
  }

  @BindView(R.id.rv_clan_list)
  RecyclerView rv_clan_list;
  @BindView(R.id.tv_clan_list_no_data)
  TextView tv_clan_list_no_data;
  private OnFragmentInteractionListener activityListener;
  private ClanClanListAdapter clanListAdapter;

  ClanListPresenter clanListPresenter;

  public ClanListFragment() {
    // Required empty public constructor
  }

  public static ClanListFragment newInstance() {
    return new ClanListFragment();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      activityListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_clan_list, container, false);
    ButterKnife.bind(this, view);
    clanListAdapter = new ClanClanListAdapter(this);
    clanListPresenter = new ClanListPresenter(this,new ClanRepository(new ClanRemoteDataSourceImpl(),new ClanLocalDataSourceImpl()));
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    clanListPresenter.start();
  }

  @Override
  public void onDestroy() {
    clanListPresenter.detachView();
    activityListener = null;
    super.onDestroy();

  }


  @Override
  public void onRowClick(Clan clan) {
    clanListPresenter.listItemClicked(clan);
  }

  @Override
  public void onFavouriteClick(Clan clan) {
    clanListPresenter.addToFavouriteClicked(clan);
  }

  @Override
  public void onChatClick(Clan clan) {
    clanListPresenter.chatClicked(clan);
  }

  @Override
  public void showHideContentLoading(boolean show) {

  }

  @Override
  public void showHideEmptyResultView(boolean show) {

  }

  @Override
  public void showError() {

  }

  @Override
  public void showClanListData(List<Clan> list) {

  }

  @Override
  public void showAddToFavouriteSuccess() {

  }

  @Override
  public void showAddToFavouriteFailure() {

  }

  @Override
  public void showChatView(Clan clan) {
    //pass on to activity to open appropriate view
    if (activityListener != null) {
      activityListener.openChat(clan);
    }
  }

  @Override
  public void showFailureToOpenChatView() {

  }

  @Override
  public void showClanDetails(Clan clan) {
    //pass on to activity to open appropriate view
    if (activityListener != null) {
      activityListener.openClanDetails(clan);
    }
  }

  @Override
  public void showFailureToOpenClanDetails() {

  }

  @Override
  public boolean isActive() {
    return isAdded() && getActivity() != null;
  }
}
