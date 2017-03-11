package com.codegod.alctrack.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codegod.alctrack.R;
import com.codegod.alctrack.activity.model.User;

import java.util.ArrayList;

/**
 * Created by alamzdayveed on 10/03/2017.
 */

public class UserProfileDialogFragment extends DialogFragment {
    private ArrayList<User> users;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;

    private int selectedPosition = 0;

    static UserProfileDialogFragment newInstance() {
        UserProfileDialogFragment f = new UserProfileDialogFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        users = (ArrayList<User>) getArguments().getSerializable("users");
        selectedPosition = getArguments().getInt("position");

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);

        setCurrentItem(selectedPosition);

        Toast.makeText(getContext(), "Swipe to the left or right to view other profiles.", Toast.LENGTH_SHORT).show();

        return v;
    }

    private void setCurrentItem(int position) {
        viewPager.setCurrentItem(position, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    // adapter
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.user_profile_detail, container, false);

            ImageView imageViewAvatar = (ImageView) view.findViewById(R.id.imageView_avatar);
            TextView textViewLogin = (TextView) view.findViewById(R.id.textView_login);
            TextView textViewGithubUrl = (TextView) view.findViewById(R.id.textView_github_url);
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

            final User user = users.get(position);

            textViewLogin.setText("@" + user.getLogin());
            textViewGithubUrl.setText(user.getHtml_url());

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            String.format("Check out this awesome developer @%s, %s", user.getLogin(), user.getHtml_url())
                    );
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            });

            Glide.with(getActivity()).load(user.getAvatar_url())
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewAvatar);

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
