package com.zerogravity.myapplication.utils;//package com.zerogravity.myapplication.presentation.utils;
//
//import android.support.design.widget.Snackbar;
//import android.text.TextUtils;
//import android.view.View;
//
//
//public class SneakySnacks {
//
//
//    private final int duration;
//    private final int backgroundColor;
//    private final Snackbar.Callback callback;
//    private final String message;
//    private final View anchorView;
//    private Snackbar snackbar;
//
//    private SneakySnacks(Builder builder){
//        this.duration = builder.duration;
//        this.backgroundColor = builder.backgroundColor;
//        this.callback = builder.callback;
//        this.message = builder.message;
//        this.anchorView = builder.anchorView;
//
//
//    }
//
//    private void make(){
//        snackbar = Snackbar.make(anchorView, message, duration);
//        View sbView = snackbar.getView();
//        //set background color
//        sbView.setBackgroundColor(backgroundColor);
//        if (callback != null) {
//            snackbar.setCallback(callback);
//        }
//    }
//
//    public void show(){
//
//        snackbar.show();
//    }
//
//    public static class Builder{
//        private int duration = Snackbar.LENGTH_LONG;
//        private int backgroundColor = ResourceUtils.instance().getColorResId(R.color.colorAccent);
//        private Snackbar.Callback callback;
//        private String message;
//        private View anchorView;
//
//        public Builder(int ){
//            this.anchorView = anchorView;
//            this.message = message;
//            this.backgroundColor = resources.getColo
//        }
//
//        public Builder(final View anchorView,final String message){
//            this.anchorView = anchorView;
//            this.message = message;
//        }
//
//        public Builder anchorView(View anchorView){
//            this.anchorView = anchorView;
//            return this;
//        }
//
//        public Builder message(String message){
//            this.message = message;
//            return this;
//        }
//        public Builder duration(int duration){
//            this.duration = duration;
//            return this;
//        }
//
//        public Builder backgroundColor(int backgroundColor){
//            this.backgroundColor = backgroundColor;
//            return this;
//        }
//
//        public Builder callBack(Snackbar.Callback callback){
//            this.callback = callback;
//            return this;
//        }
//
//       public SneakySnacks build(){
//           if(duration!=Snackbar.LENGTH_SHORT
//                   ||duration!=Snackbar.LENGTH_LONG
//                   ||duration!=Snackbar.LENGTH_INDEFINITE){
//               duration =Snackbar.LENGTH_LONG;
//           }
//
//           if(TextUtils.isEmpty(message)){
//               throw new IllegalArgumentException("Must provide a message for Snackbar");
//           }
//           if(anchorView==null){
//               throw new IllegalArgumentException("Anchorview must not be null");
//           }
//
//           SneakySnacks sneakySnacks = new SneakySnacks(this);
//           sneakySnacks.make();
//           return sneakySnacks;
//       }
//
//    }
//}
