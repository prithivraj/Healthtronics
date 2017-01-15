package com.healthtronics.healthtronics;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapLabel;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.anastr.speedviewlib.PointerSpeedometer;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.measureView)
  RelativeLayout measureView;

  @BindView(R.id.historyView)
  RelativeLayout historyView;

  @BindView(R.id.settingsView)
  RelativeLayout settingsView;

  @BindView(R.id.speedView) PointerSpeedometer speedView;

  @BindView(R.id.date)
  AwesomeTextView dateView;

  @BindView(R.id.status)
  BootstrapLabel statusView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "This button is available and can be mapped to a health device/feature.", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
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

  @SuppressWarnings("StatementWithEmptyBody") @Override public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.measure) {
      historyView.setVisibility(View.GONE);
      settingsView.setVisibility(View.GONE);
      measureView.setVisibility(View.VISIBLE);
      speedView.speedTo(70);
      YoYo.with(Techniques.Shake)
          .duration(20700)
          .playOn(statusView);
      dateView.setText(DateFormat.getDateTimeInstance().format(new Date()));
    } else if (id == R.id.history) {
      measureView.setVisibility(View.GONE);
      settingsView.setVisibility(View.GONE);
      historyView.setVisibility(View.VISIBLE);
    }
    else if (id == R.id.settings) {
      historyView.setVisibility(View.GONE);
      measureView.setVisibility(View.GONE);
      settingsView.setVisibility(View.VISIBLE);
    } else if (id == R.id.emergency) {
      new MaterialDialog.Builder(this)
          .title("Emergency")
          .content("Emergency contacts will appear here.")
          .positiveText("Call")
          .negativeText("Cancel")
          .show();
    } else if (id == R.id.feedback) {
      new MaterialDialog.Builder(this)
          .title("Feedback")
          .inputType(InputType.TYPE_CLASS_TEXT)
          .input("Your feedback", "", new MaterialDialog.InputCallback() {
            @Override
            public void onInput(MaterialDialog dialog, CharSequence input) {
              // Do something
            }
          }).show();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
