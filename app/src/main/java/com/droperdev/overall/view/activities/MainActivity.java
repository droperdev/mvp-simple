package com.droperdev.overall.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.droperdev.overall.R;
import com.droperdev.overall.interfaces.group.GroupPresenter;
import com.droperdev.overall.interfaces.group.GroupView;
import com.droperdev.overall.model.entities.Group;
import com.droperdev.overall.presenter.GroupPresenterImpl;
import com.droperdev.overall.view.adapters.GroupAdapter;

import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements GroupView, View.OnClickListener, GroupAdapter.OnItemClickListener {

    private GroupPresenter mGroupPresenter;
    private EditText mGroupNameText;
    private TextView mMessageText;
    private RecyclerView mGroupRecycler;
    private GroupAdapter mGroupAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        initUI();

    }

    private void initPresenter() {
        mGroupPresenter = new GroupPresenterImpl(this, this);
    }

    private void initUI() {
        mGroupNameText = findViewById(R.id.et_group);
        mMessageText = findViewById(R.id.tv_message);
        Button mSaveButton = findViewById(R.id.btn_save);
        Button mDeleteButton = findViewById(R.id.btn_delete);
        mSaveButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
        mGroupRecycler = findViewById(R.id.rv_groups);
        mGroupRecycler.setLayoutManager(new LinearLayoutManager(this));
        mGroupPresenter.getGroups();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                mGroupPresenter.save(getGroup());
                break;
            case R.id.btn_delete:
                mGroupPresenter.delete();
                break;
                default:

        }

    }

    @Override
    public void showProgress(String message) {
        mMessageText.setText(message);
    }

    @Override
    public void hideProgress() {
        mMessageText.setText("");
    }

    @Override
    public void saveSuccessful() {
        mGroupAdapter.onChanges();
        mMessageText.setText(getString(R.string.stored_correctly));
        mGroupNameText.setText("");
    }

    @Override
    public void saveFailure(Throwable error) {
        mMessageText.setText(error.toString());
    }

    @Override
    public void showGroups(RealmResults<Group> groups) {
        mGroupAdapter = new GroupAdapter(groups, this);
        mGroupRecycler.setAdapter(mGroupAdapter);
    }

    @Override
    public void deleteSuccessful() {
        mGroupAdapter.onChanges();
        mMessageText.setText(getString(R.string.groups_successful_deleted));
    }

    private String getGroup() {
        return mGroupNameText.getText().toString().trim();
    }

    @Override
    public void onDelete(Object object) {
        Group group = (Group) object;
       mGroupPresenter.deleteForId(group.getId());
    }

    @Override
    public void onShow(Object object) {
        Group group = (Group) object;
        Toast.makeText(this, group.getName(), Toast.LENGTH_SHORT).show();
    }
}
