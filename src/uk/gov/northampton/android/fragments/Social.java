package uk.gov.northampton.android.fragments;

import java.util.ArrayList;
import uk.gov.northampton.android.R;
import uk.gov.northampton.android.SocialEntry;
import uk.gov.northampton.android.SocialFeedAdapter;
import uk.gov.northampton.android.lib.CustomWebViewActivity;
import uk.gov.northampton.android.lib.SocialFeedRetriever;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("ParserError")
public class Social extends ListFragment {
	
	 private ArrayList<SocialEntry> feedList = new ArrayList<SocialEntry>();
	 private SocialFeedRetriever sfr = new SocialFeedRetriever();
	 private ProgressDialog progressDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		progressDialog = ProgressDialog.show(getActivity(), "Please wait...", "Retrieving data.", true, true);
		String socialFeedUrl = getString(R.string.social_feed_url);
		RetrieveSocialFeedTask sf = new RetrieveSocialFeedTask();
		sf.execute(socialFeedUrl);  		
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.social, container, false);
        return view;		
    }
	
	@Override
	 public void onListItemClick(ListView l, View v, int position, long id) {

		SocialEntry current = (SocialEntry) getListView().getItemAtPosition(position);
		String uriString = current.getUrl();
		Intent seIntent = new Intent(l.getContext(), CustomWebViewActivity.class);
		seIntent.putExtra("url", uriString);
		startActivity(seIntent);
		
		/*Toast.makeText(
	    getActivity(), 
	    current.getUrl(), 
	    Toast.LENGTH_LONG).show();
	 }*/
		
	}
	
	private class RetrieveSocialFeedTask extends AsyncTask<String, Void, ArrayList<SocialEntry>>{

		@Override
		protected ArrayList<SocialEntry> doInBackground(String... params) {
			String url = getString(R.string.social_feed_url);
			return 	sfr.retrieveSocialFeed(url);
		}
		
		@Override
		protected void onPostExecute(final ArrayList<SocialEntry> result){

				if(progressDialog != null){
						progressDialog.dismiss();
						progressDialog = null;
				}
				
				Toast.makeText(getActivity(),"Result " + result.size(),Toast.LENGTH_LONG).show();
				
				ListAdapter myListAdapter = new SocialFeedAdapter(getActivity(),R.layout.social_feed_row, result);
				setListAdapter(myListAdapter);
				
				/*for(int i = 0; i < 5; i++){
					
					SocialEntry s = new SocialEntry();
					s.date = "today";
					s.text = "message " + i;
					s.type = "tweet";
					s.url = "http://www.northampton.gov.uk/"+i;
					feedList.add(s);
					Log.d("Northampton", "message added, number: "+ i);
				}*/
		}

	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("Social Feed", "Resumed!!");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("Social Feed", "Stopped!!");
	}
		

}
