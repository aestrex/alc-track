package com.codegod.alctrack.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codegod.alctrack.R;
import com.codegod.alctrack.activity.helper.CircleTransform;
import com.codegod.alctrack.activity.model.User;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private ArrayList<User> users;
    private ArrayList<User> filteredUsers = new ArrayList<>();

    private Context context;

    public DataAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        filteredUsers.addAll(users);
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
        viewHolder.name.setText(users.get(i).getLogin());

        Glide.with(context).load(users.get(i).getAvatar_url())
                .thumbnail(0.5f)
                .crossFade()
                .transform(new CircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.previewImage);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                users.clear();
                final FilterResults results = new FilterResults();

                if (constraint.length() == 0) {
                    users.addAll(filteredUsers);
                } else {
                    final String filterPattern = constraint.toString().toLowerCase().trim();

                    for (final User user :
                            filteredUsers) {
                        if (user.getLogin().toLowerCase().contains(filterPattern)) {
                            users.add(user);
                        }
                    }
                }

                results.values = users;
                results.count = users.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView previewImage;

        public ViewHolder(View view) {
            super(view);

            name = (TextView)view.findViewById(R.id.name);
            previewImage = (ImageView) view.findViewById(R.id.image_preview);
        }
    }

}