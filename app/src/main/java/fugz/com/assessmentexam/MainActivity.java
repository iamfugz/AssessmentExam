package fugz.com.assessmentexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView, View.OnClickListener{

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ListAdapter mAdapter;

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.listView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        presenter = new MainActivityPresenter(MainActivity.this, this);
        presenter.addDataToArray();
    }

    @Override
    public void setItems(ArrayList<String> data) {
        mAdapter = new ListAdapter(MainActivity.this, data);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void refreshListView(){
        mAdapter.notifyDataSetChanged();
    }

    public void navigateToAddItemView(){
        startActivityForResult(new Intent(MainActivity.this, AddItem.class), 1);
    }

    @Override
    public void onClick(View v) {
        presenter.navigateToAddItemsView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String result = data.getStringExtra("DATA");
                presenter.addDataToDB(result);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
