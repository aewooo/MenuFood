package com.wannaporn.phoso.menufoodthai;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowMenu extends AppCompatActivity {
    private ListView listView;
    int intClickk ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);
        bindWidget();
        SynJSON synJSON = new SynJSON();
        synJSON.execute();
    }
    private void bindWidget() {
        listView = (ListView) findViewById(R.id.listView2);
        intClickk =getIntent().getIntExtra("click",0);
    }//Bind Widget
    private class SynJSON extends AsyncTask<Void , Void ,String> {
        @Override
        protected String doInBackground(Void... params) {

            try {
                String strURL = "http://csclub.ssru.ac.th/s56122201096/menu/MenuDatail.php?id="+Integer.toString(intClickk+1);
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("23/11/59", "doIn ==> " + e.toString());
            }

            return null;

        }//do Inback

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.d("23/11/59", "SucesOnpost ==> " + s);
                JSONArray jsonArray = new JSONArray(s);

                String[] iconString = new String[jsonArray.length()];
                String[] titleString = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    iconString[i] = jsonObject.getString("menu_img");
                    titleString[i] = jsonObject.getString("menu_name");

                }

                ShowMenuAdapter menuAdapter = new ShowMenuAdapter(ShowMenu.this,iconString,titleString);
                listView.setAdapter(menuAdapter);

                listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                        myIntenToDetail(i);
                    }
                });




            } catch (Exception e) {
                Log.d("23/11/59", "onPost ==> " + e.toString());
            }

        }//onPost

    }//SynJson
    private void myIntenToDetail(int intClick) {
        Intent objIntent =new Intent(ShowMenu.this,ShowDetail.class);
        objIntent.putExtra("click",intClick);
        objIntent.putExtra("id",intClickk);
        Log.v("gg",""+intClickk);
        startActivity(objIntent);
    }
    public void close(View view){
        finish();
    }
}



