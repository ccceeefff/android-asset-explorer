package com.phunware.assetexplorer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.phunware.assetexplorer.adapter.AbstractAssetRecyclerAdapter;
import com.phunware.assetexplorer.adapter.AssetGridAdapter;
import com.phunware.assetexplorer.adapter.AssetListAdapter;


public class AssetListActivity extends ActionBarActivity implements AbstractAssetRecyclerAdapter.Callback {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.grid);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(layoutManager);
        mLayoutManager = layoutManager;

        if(savedInstanceState != null && savedInstanceState.getInt("spanCount") != 1){
            setGridMode();
        } else {
            setListMode();
        }

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
                toggleGrid(mLayoutManager.getSpanCount());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("spanCount", mLayoutManager.getSpanCount());
        super.onSaveInstanceState(outState);
    }

    private void toggleGrid(int span){
        if(span == 1){
            setGridMode();
        } else {
            setListMode();
        }
    }

    private void setListMode(){
        mLayoutManager.setSpanCount(1);
        mRecyclerView.setAdapter(new AssetListAdapter(this, Assets.list, this));
    }

    private void setGridMode(){
        mLayoutManager.setSpanCount(getResources().getInteger(R.integer.grid_column_count));
        mRecyclerView.setAdapter(new AssetGridAdapter(this, Assets.list, this));
    }

    @Override
    public void onItemSelected(Assets.Asset asset) {
        AssetDetailActivity.startActivity(this, asset);
    }
}
