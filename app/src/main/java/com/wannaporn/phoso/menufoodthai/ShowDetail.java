package com.wannaporn.phoso.menufoodthai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowDetail extends AppCompatActivity {
    TextView textName,textSoure,textHowto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        bindWidget();
        int intClick =getIntent().getIntExtra("click",0);
        int Id =getIntent().getIntExtra("id",0);
        MyData objMyData=new MyData(Integer.toString(Id+1));
        Log.v("ShowResult",""+Id);
        textName.setText(objMyData.dataMenuName[intClick]);
        textSoure.setText(objMyData.dataRaw[intClick]);
        textHowto.setText(objMyData.dataHowto[intClick]);

    }
    private void bindWidget(){
        textName=(TextView)findViewById(R.id.txtnamefood);
        textSoure=(TextView)findViewById(R.id.txtraw);
        textHowto=(TextView)findViewById(R.id.txthowto);
    }
    public void close(View view){
        finish();
    }
}
