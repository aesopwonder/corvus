package andrewnguyen.corvus.Tab_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andrewnguyen.corvus.R;


public class TabFragmentTwo extends Fragment {

    private static final String ARG_EXAMPLE = "This_is_a_constant";
    private String example_data;

    public TabFragmentTwo(){

    }

    public static TabFragmentTwo newInstance(String example_argument){
        TabFragmentTwo tabFragmentTwo = new TabFragmentTwo();
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
}
