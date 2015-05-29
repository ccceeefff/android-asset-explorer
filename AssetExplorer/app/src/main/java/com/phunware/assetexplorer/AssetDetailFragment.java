package com.phunware.assetexplorer;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by cef on 5/28/15.
 */
public class AssetDetailFragment extends Fragment {

    private static final String ARG_ASSET = "arg.Asset";
    private static final String ARG_DENSITY = "arg.Density";

    private ImageView imageView;
    private TextView densityLabel;
    private TextView widthLabel;
    private TextView heightLabel;

    public static AssetDetailFragment newInstance(Assets.Asset asset, Assets.Density density){
        AssetDetailFragment fragment = new AssetDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ASSET, asset);
        args.putParcelable(ARG_DENSITY, density);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asset_detail, container, false);

        imageView = (ImageView) view.findViewById(R.id.image);
        densityLabel = (TextView) view.findViewById(R.id.density);
        widthLabel = (TextView) view.findViewById(R.id.width);
        heightLabel = (TextView) view.findViewById(R.id.height);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Assets.Asset asset = getAsset();
        Assets.Density dens = getDensity();

        if(asset != null && dens != null){
            Drawable d = null;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                d = getResources().getDrawableForDensity(asset.resId, dens.code, null);
            } else {
                d = getResources().getDrawableForDensity(asset.resId, dens.code);
            }
            imageView.setImageDrawable(d);
            densityLabel.setText("Density: " + dens.name);
            if(d != null){
                widthLabel.setText("Intrinsic Width: " + d.getIntrinsicWidth());
                heightLabel.setText("Intrinsic Height: " + d.getIntrinsicHeight());
            } else {
                widthLabel.setText("Intrinsic Width: Unavailable");
                heightLabel.setText("Intrinsic Height: Unavailable");
            }
        }
    }

    protected Assets.Asset getAsset(){
        return getArguments().getParcelable(ARG_ASSET);
    }

    protected Assets.Density getDensity(){
        return getArguments().getParcelable(ARG_DENSITY);
    }
}
