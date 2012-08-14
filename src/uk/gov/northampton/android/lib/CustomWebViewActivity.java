package uk.gov.northampton.android.lib;

import uk.gov.northampton.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class CustomWebViewActivity extends Activity {

	private WebView wv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.web_view);

		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		wv = (WebView) findViewById(R.id.webview);
		wv.setWebViewClient(new WebViewClientNoRedirect());
		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setDomStorageEnabled(true);
		wv.loadUrl(url);
	}
	
	private class WebViewClientNoRedirect extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            return false;

	    }
		
	}

}
