package xyz.phyoekhant.padc.mealordering.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import xyz.phyoekhant.padc.mealordering.R;
import xyz.phyoekhant.padc.mealordering.data.vos.MealVO;
import xyz.phyoekhant.padc.mealordering.fragments.ListViewMealListFragment;
import xyz.phyoekhant.padc.mealordering.views.holders.MealViewHolder;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MealViewHolder.ControllerMealItem{

    public String currentstyle="1";
    private FrameLayout flContainer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        flContainer = (FrameLayout) findViewById(R.id.fl_container);
        navigateToListView();

        if (savedInstanceState == null) {
            navigateToListView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        final int id = menuItem.getItemId();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            if (id == R.id.meal_ordering_list_view) {
                toolbar.setTitle(R.string.str_listview);
                navigateToListView();
            } else if (id == R.id.meal_ordering_grid_view) {
                toolbar.setTitle(R.string.str_gridview);
                navigateToGrideView();
            }
            }
        }, 100); //to close drawer smoothly.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigateToListView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ListViewMealListFragment.newInstance("1"))
                .commit();
    }

    private void navigateToGrideView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ListViewMealListFragment.newInstance("2"))
                .commit();
    }

    @Override
    public void onTapMealItem(MealVO meal, ImageView ivMeal) {
        Intent intent = MealDetailActivity.newIntent(meal.getId());
        startActivity(intent);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair(ivMeal, getString(R.string.meal_list_detail_transition_name)));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }
}
