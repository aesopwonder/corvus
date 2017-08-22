package andrewnguyen.corvus;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import andrewnguyen.corvus.Data.List_Item;
import andrewnguyen.corvus.Tab_Fragments.Global;

public class Splash extends Permissions {

    List_Item fake_device_1, fake_device_2;
    Global global;
    private static int TIME_OUT = 5000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        global = new Global();
        requestAppPermissions(new String[] {
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION},
                0,10);
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        ImageView hypergraph_animation = (ImageView) findViewById(R.id.animation_hypergraph);
        hypergraph_animation.setImageDrawable(getResources().getDrawable(R.drawable.hypergraph_source));
        AnimationDrawable hypergraph_animation_drawable = (AnimationDrawable) hypergraph_animation.getDrawable();
        hypergraph_animation_drawable.start();

        fake_device_1 = new List_Item();
        fake_device_2 = new List_Item();

        fake_device_1.setName("Model A");
        fake_device_1.setStatus("OK");
        fake_device_1.setPrice_audio("0.6");
        fake_device_1.setPrice_video("1.8");
        fake_device_1.setPrice_radio("1.2");
        fake_device_1.setAmount_audio((".80"));
        fake_device_1.setAmount_radio(("1.0"));
        fake_device_1.setAmount_video(("1.2"));
        fake_device_1.setAudio("ON");
        fake_device_1.setVideo("ON");
        fake_device_1.setRadio("ON");

        fake_device_2.setName("Model B");
        fake_device_2.setStatus("BAD");
        fake_device_2.setPrice_audio("0");
        fake_device_2.setPrice_video("0");
        fake_device_2.setPrice_radio("1.2");
        fake_device_2.setAmount_audio(("0"));
        fake_device_2.setAmount_radio(("1.4"));
        fake_device_2.setAmount_video(("0"));
        fake_device_2.setAudio("0");
        fake_device_2.setVideo("0");
        fake_device_2.setRadio("ON");

        global.addDevice_toList(fake_device_2);
        global.addDevice_toList(fake_device_1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Main_Activity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }

}
