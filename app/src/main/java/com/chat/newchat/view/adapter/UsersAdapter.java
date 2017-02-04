package com.chat.newchat.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chat.newchat.R;
import com.chat.newchat.model.record.UserRecord;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersAdapter extends ArrayAdapter<UserRecord> {

    public UsersAdapter(Context context, List<UserRecord> users) {
        super(context, R.layout.item_user, users);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_user, null);
            view.setTag(new ViewHolder(view));
        } else {
            view = convertView;
        }
        UserRecord record = getItem(position);
        if (record != null) {
            ViewHolder holder = (ViewHolder) view.getTag();
            if (record.getAvatar() != null && !record.getAvatar().trim().isEmpty()) {
                Glide.with(getContext()).load(record.getAvatar()).into(holder.avatar);
                holder.avatar.setVisibility(View.VISIBLE);
            } else {
                holder.avatar.setVisibility(View.GONE);
            }
            holder.name.setText(record.getName());
        }
        return view;
    }

    static class ViewHolder {

        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.name)
        TextView name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}