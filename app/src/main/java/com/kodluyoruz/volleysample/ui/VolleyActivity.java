package com.kodluyoruz.volleysample.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.kodluyoruz.volleysample.R;
import com.kodluyoruz.volleysample.network.AppController;

import java.io.UnsupportedEncodingException;

public class VolleyActivity extends AppCompatActivity {

    public static final String URL_IMAGE = "https://androidtutorialpoint.com/api/lg_nexus_5x";
    private static final String TAG = VolleyActivity.class
            .getSimpleName();
    String urlObj = "https://api.androidhive.info/volley/person_object.json";
    String arrUrl = "https://api.androidhive.info/volley/person_array.json";
    String strUrl = "https://api.androidhive.info/volley/string_response.html";
    ProgressDialog pDialog;
    NetworkImageView networkImageView;
    private String apiURL = "https://api.myjson.com/bins/1guxr9";
    private Button btnImageReq;
    private NetworkImageView imgNetWorkView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        //JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlObj, null, this, this);
        //JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, arrUrl, null, this, this);
        //StringRequest stringRequest = new StringRequest(Request.Method.GET, strUrl, this, this);


        /*pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();*/
        btnImageReq = (Button) findViewById(R.id.btnImageReq);
        imgNetWorkView = (NetworkImageView) findViewById(R.id.imgNetwork);
        imageView = (ImageView) findViewById(R.id.imgView);

        btnImageReq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                makeImageRequest();
            }
        });

        /*imageLoader.get(URL_IMAGE, ImageLoader.getImageListener(
                imageView, R.drawable.ico_loading, R.drawable.ico_error));*/

        //AppController.getInstance().addToRequestQueue(stringRequest, "Request1");
    }

    /*@Override
    public void onErrorResponse(VolleyError error) {
        VolleyLog.d("Hata", "Error: " + error.getMessage());
        pDialog.hide();
    }

    @Override
    public void onResponse(String response) {
        Log.e("StringRequest: ", response);
        pDialog.hide();

    }*/

    private void makeImageRequest() {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        // If you are using NetworkImageView
        imgNetWorkView.setImageUrl(URL_IMAGE, imageLoader);


        // If you are using normal ImageView
        /*imageLoader.get(Const.URL_IMAGE, new ImageListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, "Image Load Error: " + error.getMessage());
			}

			@Override
			public void onResponse(ImageContainer response, boolean arg1) {
				if (response.getBitmap() != null) {
					// load image into imageview
					imageView.setImageBitmap(response.getBitmap());
				}
			}
		});*/

        // Loading image with placeholder and error image
        imageLoader.get(URL_IMAGE, ImageLoader.getImageListener(
                imageView, R.drawable.ico_loading, R.drawable.ico_error));

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_IMAGE);
        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            // cached response doesn't exists. Make a network call here
        }

    }
}
