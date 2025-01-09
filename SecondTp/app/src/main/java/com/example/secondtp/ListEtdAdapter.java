package com.example.secondtp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListEtdAdapter extends BaseAdapter {
    private List<Employe> employeList;
    private Context context;

    public ListEtdAdapter(List<Employe> employeList, Context context) {
        this.employeList = employeList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return employeList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.structetdelt, parent, false);
        }
        TextView name = convertView.findViewById(R.id.txtN);
        TextView prenom = convertView.findViewById(R.id.txtP);
        TextView telephone = convertView.findViewById(R.id.txtT);

        Employe employe = employeList.get(position);
        name.setText(employe.getNomEmploye());
        prenom.setText(employe.getPrenomEmploye());
        telephone.setText(employe.getTelephoneEmploye());

        return convertView;
    }
}
