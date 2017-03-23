package com.zerogravity.myapplication.ui.clans;


import com.zerogravity.myapplication.data.common.model.Clan;
import com.zerogravity.myapplication.BasePresenter;
import com.zerogravity.myapplication.BaseView;
import java.util.List;

public class ClanListMvpContract {

  interface View extends BaseView {
    void onRowClick(Clan clan);
    void onFavouriteClick(Clan clan);
    void onChatClick(Clan clan);
    void showHideContentLoading(boolean show);
    void showHideEmptyResultView(boolean show);
    void showError();
    void showClanListData(List<Clan> list);
    void showAddToFavouriteSuccess();
    void showAddToFavouriteFailure();
    void showChatView(Clan clan);
    void showFailureToOpenChatView();
    void showClanDetails(Clan clan);
    void showFailureToOpenClanDetails();
  }

  interface Presenter extends BasePresenter {

    void loadData(boolean forceUpdate);
    void addToFavouriteClicked(Clan clan);
    void chatClicked(Clan clan);
    void listItemClicked(Clan clan);

  }

}
