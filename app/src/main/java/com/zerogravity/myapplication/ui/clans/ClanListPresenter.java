package com.zerogravity.myapplication.ui.clans;


import com.zerogravity.myapplication.data.clan.ClanRepository;
import com.zerogravity.myapplication.data.common.model.Clan;
import com.zerogravity.myapplication.ui.clans.ClanListMvpContract.View;
import com.zerogravity.myapplication.utils.EspressoIdlingResource;
import hugo.weaving.DebugLog;
import java.util.Collections;
import java.util.List;

public class ClanListPresenter implements ClanListMvpContract.Presenter {

  private View mvpView;
  private ClanRepository clanRepository;
  private boolean isFirstLoad = true;


  ClanListPresenter(View _mvpView, ClanRepository _clanRepository) {
    this.mvpView = _mvpView;
    this.clanRepository = _clanRepository;
  }

  @Override
  public void start() {
    loadData(false);
  }

  @Override
  public void loadData(boolean forceUpdate) {
    loadClanList(forceUpdate || isFirstLoad, true);

  }

  /**
   * @param forceUpdate Pass in true to refresh the data in the Data Repository
   * @param showContentLoading Pass in true to display a loading icon in the UI
   */
  @DebugLog
  private void loadClanList(boolean forceUpdate, final boolean showContentLoading) {
    if (showContentLoading) {
      mvpView.showHideContentLoading(true);
    }

    if (forceUpdate) {
      clanRepository.refreshClans();
    }

    // The network request might be handled in a different thread so make sure Espresso knows
    // that the app is busy until the response is handled.
    EspressoIdlingResource.increment(); // App is busy until further notice

    clanRepository.getClans(new ClanRepository.GetClanListCallback() {
      @Override
      public void onClanListFetched(List<Clan> clans) {
        // This callback may be called twice, once for the cache and once for loading
        // the data from the server API, so we check before decrementing, otherwise
        // it throws "Counter has been corrupted!" exception.
        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
          EspressoIdlingResource.decrement(); // Set app as idle.
        }


        // The view may not be able to handle UI updates anymore
        if (!mvpView.isActive()) {
          return;
        }
        if (showContentLoading) {
          mvpView.showHideContentLoading(false);
        }

        processClanListData(clans);
      }

      @Override
      public void onError(Throwable t) {
        // The view may not be able to handle UI updates anymore
        if (!mvpView.isActive()) {
          return;
        }
        mvpView.showError();
      }


    });
  }

  @DebugLog
  private void processClanListData(List<Clan> data) {

    if (data.isEmpty()) {
      mvpView.showHideEmptyResultView(true);
    } else {
      mvpView.showHideEmptyResultView(false);
      mvpView.showClanListData(data);
    }
  }

  private List<Clan> getData() {
    return Collections.emptyList();
  }

  @Override
  public void addToFavouriteClicked(Clan clan) {
    mvpView.showAddToFavouriteSuccess();
  }

  @Override
  public void chatClicked(Clan clan) {
    mvpView.showChatView(clan);
  }

  @Override
  public void listItemClicked(Clan clan) {
    mvpView.showClanDetails(clan);
  }

  @Override
  public void detachView() {
    mvpView = null;
  }
}
