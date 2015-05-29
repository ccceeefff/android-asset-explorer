package com.phunware.assetexplorer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phunware.assetexplorer.Assets;

import java.util.List;

/**
 * Created by cef on 5/28/15.
 */
public class AssetListAdapter extends AbstractAssetRecyclerAdapter {
    public AssetListAdapter(Context context, List<Assets.Asset> assets, Callback callback) {
        super(context, assets, callback);
    }

    @Override
    protected AbstractAssetViewHolder createViewHolder(LayoutInflater inflater, ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new AssetTextViewHolder(view);
    }
}
