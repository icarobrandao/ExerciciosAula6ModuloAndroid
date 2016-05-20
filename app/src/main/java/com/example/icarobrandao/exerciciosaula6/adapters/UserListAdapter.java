package com.example.icarobrandao.exerciciosaula6.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icarobrandao.exerciciosaula6.R;
import com.example.icarobrandao.exerciciosaula6.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by icarobrandao on 20/05/16.
 */
public class UserListAdapter extends ArrayAdapter<User> {
    public UserListAdapter(Context context, List<User> users) {
        super(context, R.layout.item_user_list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.item_user_list, null);
            holder = new ViewHolder();
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final  User user = getItem(position);
        holder.image_view.setImageURI(user.getImage());
        holder.text_view_name.setText(user.getName());
        holder.text_view_age.setText(user.getAge());
        return convertView;
    }
}

class ViewHolder{

    @BindView(R.id.image_view)
    ImageView image_view;
    @BindView(R.id.text_view_name)
    TextView text_view_name;
    @BindView(R.id.text_view_age)
    TextView text_view_age;

}
