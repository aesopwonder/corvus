package andrewnguyen.corvus;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import andrewnguyen.corvus.Tab_Fragments.Global;


public class New_Device extends AppCompatActivity {

    Global global;
    private static int TIME_OUT = 5000; //Time to launch the another activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_device);
        global = new Global();
        ImageView hypergraph_animation = (ImageView) findViewById(R.id.animation_hypergraph);
        hypergraph_animation.setImageDrawable(getResources().getDrawable(R.drawable.hypergraph_source));
        AnimationDrawable hypergraph_animation_drawable = (AnimationDrawable) hypergraph_animation.getDrawable();
        hypergraph_animation_drawable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(New_Device.this, Device_Found.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}