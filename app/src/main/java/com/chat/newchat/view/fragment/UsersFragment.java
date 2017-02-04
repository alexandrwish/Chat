package com.chat.newchat.view.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.chat.newchat.R;
import com.chat.newchat.listener.UserListener;
import com.chat.newchat.model.record.UserRecord;
import com.chat.newchat.presenter.UserPresenter;
import com.chat.newchat.view.activity.ChatActivity;
import com.chat.newchat.view.adapter.UsersAdapter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class UsersFragment extends ListFragment implements UserListener {

    private final List<UserRecord> users = new LinkedList<>();
    @Inject
    UserPresenter mPresenter;
    private ArrayAdapter<UserRecord> adapter;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ChatActivity) getActivity()).getComponent().inject(this);
        mPresenter.setListener(this);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        adapter = new UsersAdapter(getActivity(), users);
        setListAdapter(adapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, null);
    }

    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    public void onLoad(final Collection<UserRecord> users) {
        adapter.clear();
        adapter.addAll(users);
        adapter.notifyDataSetChanged();
    }

    public void onConnect(final UserRecord user) {
        adapter.add(user);
        adapter.notifyDataSetChanged();
    }

    public void onDisconnect(final UserRecord user) {
        adapter.remove(user);
        adapter.notifyDataSetChanged();
    }
}