package com.zerogravity.myapplication.ui.clans;

public interface ClanListItemClickListener {

    void didClickRow(int adapterPosition);

    void didClickFavouriteBtn(int adapterPosition);

    void didClickChatBtn(int adapterPosition);
  }