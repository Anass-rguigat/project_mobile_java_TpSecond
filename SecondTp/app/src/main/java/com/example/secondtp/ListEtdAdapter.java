package com.example.secondtp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ListEtdAdapter extends BaseAdapter {
    // Change to List<HashMap<String, String>> to match the data you're passing
    List<HashMap<String, String>> eltsEtdListe;
    Context context;
    LayoutInflater layoutInf;

    public ListEtdAdapter(List<HashMap<String, String>> eltsEtdListe, Context context) {
        this.eltsEtdListe = eltsEtdListe;
        this.context = context;
        this.layoutInf = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.eltsEtdListe.size();
    }

    @Override
    public Object getItem(int position) {
        return this.eltsEtdListe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView tn, tp, tt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInf.inflate(R.layout.structetdelt, null);
            holder = new ViewHolder();
            holder.tn = convertView.findViewById(R.id.txtN);
            holder.tp = convertView.findViewById(R.id.txtP);
            holder.tt = convertView.findViewById(R.id.txtT);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tn.setText(eltsEtdListe.get(position).get("nom"));
        holder.tp.setText(eltsEtdListe.get(position).get("prenom"));
        holder.tt.setText(eltsEtdListe.get(position).get("telephone"));

        return convertView;
    }
}
