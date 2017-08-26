package andrewnguyen.corvus.Tab_Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import andrewnguyen.corvus.R;
import andrewnguyen.corvus.sync.Add_Async;


public class Map_Fragment extends Fragment {
    private static final String ARG_EXAMPLE = "This_is_a_constant";
    private String example_data;
    public static WebView webView;

    public Map_Fragment(){

    }

    public static Map_Fragment newInstance(String example_argument){
        Map_Fragment tabFragmentThree = new Map_Fragment();
        return tabFragmentThree;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        webView = (WebView) getView().findViewById(R.id.map_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
//                Add_Async add_async = new Add_Async(getActivity());
//                add_async.execute("increment");
                return true;
            }
        });
        webView.loadUrl("http://172.16.30.98:8000/maplowfi.html");
    }
    public static void onBackPressed(){
        Map_Fragment mf= new Map_Fragment();
        mf.oBP();
    }
    public void oBP(){
        if(webView!=null) {
            if (webView.canGoBack())
                webView.goBack();
        }
        else{
            System.out.println("Webview is null");
        }
    }
}

