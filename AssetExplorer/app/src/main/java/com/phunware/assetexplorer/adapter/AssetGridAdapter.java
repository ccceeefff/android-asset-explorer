package com.phunware.assetexplorer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phunware.assetexplorer.Assets;
import com.phunware.assetexplorer.R;

import java.util.List;

/**
 * Created by cef on 5/28/15.
 */
public class AssetGridAdapter extends AbstractAssetRecyclerAdapter {

    public AssetGridAdapter(Context context, List<Assets.Asset> assets, Callback callback) {
        super(context, assets, callback);
    }

    @Override
    protected AbstractAssetViewHolder createViewHolder(LayoutInflater inflater, ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_asset_thumbnail, viewGroup, false);
        return new AssetThumbnailViewHolder(view);
    }

}
