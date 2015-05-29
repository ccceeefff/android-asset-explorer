package com.phunware.assetexplorer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phunware.assetexplorer.Assets;

import java.util.List;

/**
 * Created by cef on 5/28/15.
 */
public abstract class AbstractAssetRecyclerAdapter extends RecyclerView.Adapter<AbstractAssetViewHolder> {

    protected LayoutInflater inflater;
    protected List<Assets.Asset> assets;
    protected Callback callback;

    protected AbstractAssetRecyclerAdapter(Context context, List<Assets.Asset> assets, Callback callback){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.assets = assets;
        this.callback = callback;
    }

    @Override
    public AbstractAssetViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return createViewHolder(inflater, viewGroup, i);
    }

    protected abstract AbstractAssetViewHolder createViewHolder(LayoutInflater inflater, ViewGroup viewGroup, int viewType);

    @Override
    public void onBindViewHolder(AbstractAssetViewHolder viewHolder, int i) {
        final Assets.Asset item = getItem(i);
        viewHolder.bindAsset(getItem(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null){
                    callback.onItemSelected(item);
                }
            }
        });
    }

    public Assets.Asset getItem(int position){
        return assets.get(position);
    }

    @Override
    public int getItemCount() {
        return assets.size();
    }

    public interface Callback {
        void onItemSelected(Assets.Asset asset);
    }
}
