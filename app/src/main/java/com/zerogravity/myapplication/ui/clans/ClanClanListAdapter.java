package com.zerogravity.myapplication.ui.clans;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.zerogravity.myapplication.R;
import com.zerogravity.myapplication.data.common.model.Clan;
import com.zerogravity.myapplication.BaseAdapter;
import com.zerogravity.myapplication.ui.clans.ClanClanListAdapter.FriendListViewHolder;
import de.hdodenhof.circleimageview.CircleImageView;


public class ClanClanListAdapter extends BaseAdapter<Clan, FriendListViewHolder> implements
    ClanListItemClickListener {

  private final ClanListMvpContract.View mvpView;

  public ClanClanListAdapter(@NonNull ClanListMvpContract.View mvpView) {
    this.mvpView = mvpView;

  }

  @Override
  public void didClickRow(int adapterPosition) {
    mvpView.onRowClick(getItem(adapterPosition));
  }

  @Override
  public void didClickFavouriteBtn(int adapterPosition) {
    mvpView.onFavouriteClick(getItem(adapterPosition));
  }

  @Override
  public void didClickChatBtn(int adapterPosition) {
    mvpView.onChatClick(getItem(adapterPosition));
  }


  @Override
  public FriendListViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.adapter_clan_list_item, parent, false);

    return new FriendListViewHolder(itemView, this);
  }

  @Override
  public void onBindViewHolder(FriendListViewHolder holder, int position) {
    holder.setUp(getItem(position));
  }


  static class FriendListViewHolder extends RecyclerView.ViewHolder implements
      View.OnClickListener {

    @BindView(R.id.clan_list_item_iv_clan_badge)
    CircleImageView clan_list_item_iv_clan_badge;
    @BindView(R.id.clan_list_item_iv_friendship_status)
    ImageView clan_list_item_iv_friendship_status;
    @BindView(R.id.clan_list_item_tv_position)
    TextView clan_list_item_tv_position;
    @BindView(R.id.member_list_item_tv_clan_name)
    TextView member_list_item_tv_clan_name;
    @BindView(R.id.member_list_item_tv_clan_member_count)
    TextView member_list_item_tv_clan_member_count;
    @BindView(R.id.member_list_item_tv_clan_trophy_count)
    TextView member_list_item_tv_clan_trophy_count;

    private ClanListItemClickListener clanListItemClickListener;

    public FriendListViewHolder(View itemView,
        @NonNull ClanListItemClickListener clanListItemClickListener) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
      this.clanListItemClickListener = clanListItemClickListener;
    }

    public void setUp(Clan item) {
      member_list_item_tv_clan_name.setText(item.getName());
      member_list_item_tv_clan_member_count.setText(item.getMembers());
      member_list_item_tv_clan_trophy_count.setText(item.getClanPoints());

      Glide.with(itemView.getContext()).load(item.getBadgeUrlMedium()).fitCenter().into(
          clan_list_item_iv_clan_badge);
    }

//    @OnClick(R.id.clan_list_item_rl_click_wrapper)
//    public void onRowClick(View view){
//      clanListItemClickListener.didClickRow(getAdapterPosition());
//    }

    @OnClick(R.id.clan_list_item_rl_friendship_status)
    public void onFavoriteClick(View view) {
      clanListItemClickListener.didClickFavouriteBtn(getAdapterPosition());
    }

    @OnClick(R.id.clan_list_item_rl_chat)
    public void onChatClick(View view) {
      clanListItemClickListener.didClickChatBtn(getAdapterPosition());
    }

    @Override
    public void onClick(View v) {
      clanListItemClickListener.didClickRow(getAdapterPosition());
    }
  }

}
