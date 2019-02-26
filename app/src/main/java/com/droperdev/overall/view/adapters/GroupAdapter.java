package com.droperdev.overall.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.droperdev.overall.R;
import com.droperdev.overall.model.entities.Group;

import io.realm.RealmResults;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private final RealmResults<Group> mGroupList;
    private OnItemClickListener mListener;

    public GroupAdapter(RealmResults<Group> groupList, OnItemClickListener listener) {
        this.mGroupList = groupList;
        this.mListener = listener;
    }

    public void onChanges() {
       notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mGroupText;
        private Button mDeleteButton;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mGroupText = itemView.findViewById(R.id.tv_group);
            mDeleteButton = itemView.findViewById(R.id.btn_delete);
        }

        void bind(Group group, OnItemClickListener mListener) {

            mGroupText.setText(group.getName());
            mDeleteButton.setOnClickListener(v -> mListener.onDelete(group));

            itemView.setOnClickListener(v -> mListener.onShow(group));
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(mGroupList.get(i), mListener);
    }

    @Override
    public int getItemCount() {
        return mGroupList == null? 0 : mGroupList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface OnItemClickListener {
        void onDelete(Object object);
        void onShow(Object object);
    }
}
