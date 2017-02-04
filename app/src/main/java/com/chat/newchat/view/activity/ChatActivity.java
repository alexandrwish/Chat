package com.chat.newchat.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.chat.newchat.R;
import com.chat.newchat.di.component.ChatComponent;
import com.chat.newchat.di.component.DaggerChatComponent;
import com.chat.newchat.di.module.ChatModule;
import com.chat.newchat.listener.MessageListener;
import com.chat.newchat.listener.UserMessageListener;
import com.chat.newchat.model.loader.TestUserLoader;
import com.chat.newchat.model.service.LocationService;
import com.chat.newchat.presenter.MessagePresenter;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import it.slyce.messaging.SlyceMessagingFragment;
import it.slyce.messaging.message.Message;
import it.slyce.messaging.message.TextMessage;

public class ChatActivity extends AppCompatActivity implements MessageListener {

    @Inject
    MessagePresenter mPresenter;

    private ChatComponent mComponent;
    private SlyceMessagingFragment mMessagingFragment;

    protected void onCreate(Bundle savedInstanceState) {
        initActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initComponent();
        initFragments();
        initLocation();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (checkPermissions()) {
            startService(new Intent(this, LocationService.class));
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_clear) {
            mPresenter.clearMessages();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void initFragments() {
        mMessagingFragment = (SlyceMessagingFragment) getFragmentManager().findFragmentById(R.id.fragment_slyce_messaging);
        mMessagingFragment.setDefaultAvatarUrl(TestUserLoader.MY_AVATAR);
        mMessagingFragment.setOnSendMessageListener(new UserMessageListener(mPresenter));
    }

    private void initComponent() {
        mComponent = DaggerChatComponent.builder().chatModule(new ChatModule()).build();
        mComponent.inject(this);
        mPresenter.setListener(this);
    }

    private void initActionBar() {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayUseLogoEnabled(false);
            bar.setHomeButtonEnabled(false);
            bar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void initLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkPermissions()) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 20417);
        } else {
            startService(new Intent(this, LocationService.class));
        }
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public ChatComponent getComponent() {
        return mComponent;
    }

    public void show(List<Message> messages) {
        mMessagingFragment.addNewMessages(messages);
    }

    public void show(TextMessage message) {
        mMessagingFragment.addNewMessage(message);
    }

    public void clear() {
        mMessagingFragment.replaceMessages(new LinkedList<Message>());
    }
}