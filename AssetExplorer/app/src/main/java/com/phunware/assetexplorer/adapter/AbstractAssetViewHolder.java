package com.phunware.assetexplorer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.phunware.assetexplorer.Assets;

/**
 * Created by cef on 5/28/15.
 */
public abstract class AbstractAssetViewHolder extends RecyclerView.ViewHolder {

    public AbstractAssetViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindAsset(Assets.Asset asset);
}
