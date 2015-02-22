package belu.www.codigosxboxlive;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.zip.Inflater;

/**
 * Created by Luis on 26/12/2014.
 */
public class Frag_page_web extends Fragment {

    //Declaro variables
    private WebView pag_web;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View vista = inflater.inflate(R.layout.frag_page_web, container, false);
        if (vista != null){
            pag_web = (WebView)vista.findViewById(R.id.webView);
        }
        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Enable JavaScript
        WebSettings webSettings = pag_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Provide a WebViewClient for your WebView
        pag_web.setWebViewClient(new WebViewClient());
        //Carga Pag WEB
        pag_web.loadUrl("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=12&ct=1419628568&rver=6.2.6289.0&wp=MBI_SSL&wreply=https:%2F%2Faccount.xbox.com:443%2Fpassport%2FsetCookies.ashx%3Frru%3Dhttps%253a%252f%252faccount.xbox.com%252fes-MX%252fPaymentAndBilling%252fRedeemCode%253fdoRedeem%253d1%2526token%253d%2526returnUrl%253d&lc=2058&id=292543&cbcxt=0");
    }

}
