package andrewnguyen.corvus.Tab_Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import andrewnguyen.corvus.R;
import andrewnguyen.corvus.sync.Get_Count_Async;


public class Account_Fragment extends Fragment {

    private static final String ARG_EXAMPLE = "This_is_a_constant";
    private String example_data;
    TextView total_int, total_double, audio_usage;
    double total;
    private Handler myHandler;
    private Runnable myRunnable;


    public Account_Fragment() {

    }

    public static Account_Fragment newInstance(String example_argument) {
        Account_Fragment tabFragmentTwo = new Account_Fragment();
        return tabFragmentTwo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.account_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Timer t = new Timer();
        total_int = (TextView) getActivity().findViewById(R.id.total_int);
        total_double = (TextView) getActivity().findViewById(R.id.total_double);
        audio_usage = (TextView) getActivity().findViewById(R.id.audio_usage);

        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Global global = new Global();
                total = global.getWallet_total();
                //TODO change this for adding device
                Get_Count_Async get_count_async = new Get_Count_Async(getActivity());
                get_count_async.execute("get_count");
                if (getActivity() != null) {
                    getActivity().runOnUiThread(myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            if (total > 0) {
                                int a = (int) (total - (total % 1));
                                DecimalFormat formatter = new DecimalFormat("00");
                                String aFormatted = formatter.format(a);
                                total_int.setText(aFormatted);
                                a = (int) (100 * (total % 1));
                                aFormatted = formatter.format(a);
                                total_double.setText(aFormatted);
                                double audio = Global.getAudio_usage();
                                DecimalFormat df = new DecimalFormat("0.00");
                                aFormatted = df.format(audio);
                                audio_usage.setText("Audio Usage: $" + aFormatted);
                            }

                        }
                    });

                }
            }
        }, 1000, 1000);
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }
}
