package com.example.jamal.mobilecodinchallengejmarouf.adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jamal.mobilecodinchallengejmarouf.R;
import com.example.jamal.mobilecodinchallengejmarouf.model.Item;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {

    private Context mCtx;

    public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_repos, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = getItem(position);

        if (item != null) {
            holder.repoName.setText(item.getName());
            holder.repoDesc.setText(item.getDescription());
            holder.ownerName.setText(item.owner.login);
            if(item.stargazers_count>1000) {
                holder.numberOfStars.setText(String.valueOf((Double.valueOf(item.stargazers_count )/ 1000)) + "K");
            }else{
                holder.numberOfStars.setText(String.valueOf(item.stargazers_count));
            }
            Glide.with(mCtx)
                    .load(item.owner.avatar_url)
                    .into(holder.imageView);
        }else{
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(Item oldItem, Item newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Item oldItem, Item newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView repoName,ownerName,repoDesc,numberOfStars;
        ImageView imageView;


        public ItemViewHolder(View itemView) {
            super(itemView);
            repoName=itemView.findViewById(R.id.repoName);
            repoDesc=itemView.findViewById(R.id.repoDesc);
            ownerName = itemView.findViewById(R.id.ownerName);
            imageView = itemView.findViewById(R.id.ownerAvatar);
            numberOfStars=itemView.findViewById(R.id.numberOfStars);
        }
    }
}
