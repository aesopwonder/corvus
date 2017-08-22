package andrewnguyen.corvus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Device_Found extends AppCompatActivity {

    private static int TIME_OUT = 4000; //Time to launch the another activity
    private final String EXTRA_NAME = "NAME",
            EXTRA_STATUS = "STATUS",
            EXTRA_RADIO="RADIO",
            EXTRA_AUDIO = "AUDIO",
            EXTRA_VIDEO = "VIDEO",
            EXTRA_AMOUNT_AUDIO = "AMOUNT_AUDIO",
            EXTRA_AMOUNT_VIDEO = "AMOUNT_VIDEO",
            EXTRA_AMOUNT_RADIO = "AMOUNT_RADIO",
            EXTRA_PRICE_AUDIO = "PRICE_AUDIO",
            EXTRA_PRICE_VIDEO = "PRICE_VIDEO",
            EXTRA_PRICE_RADIO = "PRICE_RADIO",
            BUNDLE_EXTRAS ="DEVICE_DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.device_found);
    }
    public void yes_click(View view){
        default_item();
    }
    public void no_click(View view){
        Intent i = new Intent(this, Main_Activity.class);
        startActivity(i);
        finish();
    }
    public void default_item() {
        Intent i = new Intent(this, View_Device.class);
        Bundle extras = new Bundle();
        extras.putString(EXTRA_NAME, "Enter Device Name");
        extras.putString(EXTRA_STATUS, "OK");
        extras.putString(EXTRA_RADIO, "OFF");
        extras.putString(EXTRA_AUDIO, "OFF");
        extras.putString(EXTRA_VIDEO, "OFF");
        extras.putString(EXTRA_AMOUNT_AUDIO, "0");
        extras.putString(EXTRA_AMOUNT_VIDEO, "0");
        extras.putString(EXTRA_AMOUNT_RADIO, "0");
        extras.putString(EXTRA_PRICE_AUDIO, "0");
        extras.putString(EXTRA_PRICE_VIDEO, "0");
        extras.putString(EXTRA_PRICE_RADIO, "0");
        i.putExtra(BUNDLE_EXTRAS, extras);
        startActivity(i);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
