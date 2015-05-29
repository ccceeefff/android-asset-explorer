package com.phunware.assetexplorer.adapter;

import android.view.View;
import android.widget.TextView;

import com.phunware.assetexplorer.Assets;

/**
 * Created by cef on 5/28/15.
 */
public class AssetTextViewHolder extends AbstractAssetViewHolder {

    private TextView mTextView;

    public AssetTextViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(android.R.id.text1);
    }

    @Override
    public void bindAsset(Assets.Asset asset) {
        mTextView.setText(asset.name);
    }
}
