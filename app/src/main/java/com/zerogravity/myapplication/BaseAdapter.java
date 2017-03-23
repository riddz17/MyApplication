package com.zerogravity.myapplication;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T,VH extends ViewHolder> extends RecyclerView.Adapter<VH> {

  protected List<T> data;

  public void setData(ArrayList<T> _data) {
    if(data!=null){
      data.clear();
    }
    data = _data;
    notifyDataSetChanged();
  }

  public void addData(T item) {
    if (data == null) {
      data = new ArrayList<T>();
    }

    data.add(item);
    notifyItemInserted(data.size() - 1);
  }

  public void removeData(int position) {
    if (data != null && position < data.size()) {
      data.remove(position);
      notifyItemRemoved(position);
    }
  }

  public void removeData(T item) {
    if (data != null && data.contains(item)) {
      int position = data.indexOf(item);
      data.remove(item);
      notifyItemRemoved(position);
    }
  }

  public T getItem(int position) {
    if (data != null && position < data.size()) {
      return data.get(position);
    }
    return null;
  }

  @Override
  public int getItemCount() {
    return data == null ? 0 : data.size();
  }
}
