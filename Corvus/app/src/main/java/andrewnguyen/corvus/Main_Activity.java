package andrewnguyen.corvus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import andrewnguyen.corvus.Adapters.FragmentAdapter;
import andrewnguyen.corvus.Tab_Fragments.TabFragmentOne;
import andrewnguyen.corvus.Tab_Fragments.Map_Fragment;
import andrewnguyen.corvus.Tab_Fragments.Account_Fragment;


public class Main_Activity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    public WebView webView;
    public int p = 0;
    private int[] tabIcons = {R.drawable.my_icon, R.drawable.my_icon2, R.drawable.my_icons3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager_setup();
    }
    private void viewpager_setup(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[2]);
        tabLayout.getTabAt(2).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(TabFragmentOne.newInstance("Frag: Fragment 1"), "Frag 1 Created");
        adapter.addFragment(Map_Fragment.newInstance("Frag: Fragment 3"), "Frag 3 Created");
        adapter.addFragment(Account_Fragment.newInstance("Frag: Fragment 2"), "Frag 2 Created");
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                p= position;
                if(position == 1){

                }
                if(position == 2){

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (p ==1){
            Map_Fragment.onBackPressed();
        }
    }

    public void addNewDevice(View view){
        Intent i = new Intent(this, New_Device.class);
        startActivity(i);
        finish();
    }
}
