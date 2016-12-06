package com.wannaporn.phoso.menufoodthai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Phoso on 4/12/2559.
 */
public class ShowMenuAdapter extends BaseAdapter{
    private Context context;
    private String[] iconString , titleString ;

    public ShowMenuAdapter(Context context, String[] iconString, String[] titleString){
        this.context = context;
        this.iconString=iconString;
        this.titleString=titleString;

    }

    @Override
    public int getCount() {
        return iconString.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.show_menu_detail, parent, false);

        TextView titleTextView = (TextView) view1.findViewById(R.id.textView2);
        titleTextView.setText(titleString[position]);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView4);
        Picasso.with(context).load(iconString[position]).resize(150,150).into(iconImageView);
        return view1;
    }
}//MainClass

