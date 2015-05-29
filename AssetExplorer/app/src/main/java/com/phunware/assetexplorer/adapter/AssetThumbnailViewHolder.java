package com.phunware.assetexplorer.adapter;

import android.view.View;
import android.widget.ImageView;

import com.phunware.assetexplorer.Assets;
import com.phunware.assetexplorer.R;

/**
 * Created by cef on 5/28/15.
 */
public class AssetThumbnailViewHolder extends AssetTextViewHolder {

    private ImageView imageView;

    public AssetThumbnailViewHolder(View itemView) {
        super(itemView);
        this.imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    @Override
    public void bindAsset(Assets.Asset asset) {
        super.bindAsset(asset);
        imageView.setImageResource(asset.resId);
    }

}
