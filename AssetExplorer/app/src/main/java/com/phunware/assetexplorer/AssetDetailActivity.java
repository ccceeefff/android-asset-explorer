package com.phunware.assetexplorer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by cef on 5/28/15.
 */
public class AssetDetailActivity extends ActionBarActivity {

    private static final String EXTRA_ASSET = "extra.Asset";

    private ViewPager mViewPager;
    private boolean mBackgroundState = false;

    public static void startActivity(Context context, Assets.Asset asset){
        Intent intent = new Intent(context, AssetDetailActivity.class);
        intent.putExtra(EXTRA_ASSET, asset);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_asset_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getAsset().name);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), getAsset()));
    }

    protected Assets.Asset getAsset(){
        return getIntent().getExtras().getParcelable(EXTRA_ASSET);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toggle_bg:
                toggleBackground();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleBackground(){
        View view = findViewById(R.id.content);
        mBackgroundState = !mBackgroundState;
        if(mBackgroundState){
            view.setBackgroundResource(R.color.light_bg);
        } else {
            view.setBackgroundResource(R.color.dark_bg);
        }
    }

    private static class PagerAdapter extends FragmentPagerAdapter {

        Assets.Asset asset;

        public PagerAdapter(FragmentManager fm, Assets.Asset asset) {
            super(fm);
            this.asset = asset;
        }

        @Override
        public Fragment getItem(int position) {
            return AssetDetailFragment.newInstance(asset, Assets.densities.get(position));
        }

        @Override
        public int getCount() {
            return Assets.densities.size();
        }
    }
}
