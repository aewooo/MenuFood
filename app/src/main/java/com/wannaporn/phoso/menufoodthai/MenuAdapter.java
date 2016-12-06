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
 * Created by Phoso on 3/12/2559.
 */
public class MenuAdapter extends BaseAdapter {

    private Context context;
    private String[] iconString;

    public MenuAdapter(Context context, String[] iconString){
        this.context = context;
        this.iconString=iconString;
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
        View view1 = layoutInflater.inflate(R.layout.menu_show_detail, parent, false);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView2);
        Picasso.with(context).load(iconString[position]).into(iconImageView);
        return view1;
    }
}//MainClass