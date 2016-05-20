package com.example.icarobrandao.exerciciosaula6.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import com.example.icarobrandao.exerciciosaula6.R;
import com.example.icarobrandao.exerciciosaula6.adapters.UserListAdapter;
import com.example.icarobrandao.exerciciosaula6.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by icarobrandao on 20/05/16.
 */
public class UserListActivity extends AppCompatActivity{

    @BindView(R.id.list_view)
    ListView list_view;

    @BindView(R.id.btn_back)
    Button btn_back;
    List<User> usersList;
    UserListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);

        adapter = new UserListAdapter(this,usersList);
        list_view.setAdapter(adapter);
    }
}
