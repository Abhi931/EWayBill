package ewaybill.nectar.com.ewaybill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ewaybill.nectar.com.ewaybill.fragment.ProductsFragment;
import ewaybill.nectar.com.ewaybill.fragment.TestFragment;

public class EwayBillDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private Fragment fragment = null;
    private String uName;
    private String nGSTIN;
    private TextView txtUserName;
    private TextView txtGSTIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eway_bill_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Products List");
        setSupportActionBar(toolbar);
       //txtUserName=(TextView)findViewById(R.id.txtUserName);
         txtGSTIN=(TextView)findViewById(R.id.txtGSTIN);
         String nameFromIntent = getIntent().getStringExtra("NAME");
       //txtUserName.setText(nameFromIntent);


        Intent intent =getIntent();
        uName=intent.getStringExtra("USERNAME");
        nGSTIN=intent.getStringExtra("GSTIN");


        fragment = new ProductsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        TextView txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtUserName);
        txtProfileName.setText("Name: " + nameFromIntent);

        TextView txtGSTIN= (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtGSTIN);
        txtGSTIN.setText("GSTIN NO: " + nGSTIN);
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
        getMenuInflater().inflate(R.menu.eway_bill_dashboard, menu);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_products) {
            // Handle the camera action
            fragment = new ProductsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

            toolbar.setTitle("Products List");
        } else if (id == R.id.nav_client) {
            fragment = new ClientSupplierFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

            toolbar.setTitle("Client Registration");
        }else if (id == R.id.nav_supplier) {
            fragment = new ClientSupplierFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

            toolbar.setTitle("Supplier Registration");
        }
        else if (id == R.id.nav_transport) {
            fragment = new TransportersFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

            toolbar.setTitle("Transporter Registration");
        } else if (id == R.id.nav_ewaybill) {
            fragment = new EWayBillFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

            toolbar.setTitle("Generate EWay Bill");
        } else if (id == R.id.nav_share) {
            fragment = new ShareFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

            toolbar.setTitle("Share");
        } else if (id == R.id.nav_send) {
            fragment = new SendFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

            toolbar.setTitle("Send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
