package com.example.secondtp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

        // Initialize views
        TextView name = convertView.findViewById(R.id.txtN);
        TextView prenom = convertView.findViewById(R.id.txtP);
        TextView telephone = convertView.findViewById(R.id.txtT);
        ImageView imageView = convertView.findViewById(R.id.imgEmployee); // ImageView for employee picture

        // Get the current employee object
        Employe employe = employeList.get(position);

        // Set the employee details
        name.setText(employe.getNomEmploye());
        prenom.setText(employe.getPrenomEmploye());
        telephone.setText(employe.getTelephoneEmploye());

        // Set image if available (assuming you have a method to get the image resource ID)
        if (employe.getImageResource() != 0) {  // Check if an image resource exists for this employee
            imageView.setImageResource(employe.getImageResource());
        } else {
            imageView.setImageResource(R.drawable.pic);  // Default image if no resource available
        }

        return convertView;
    }
}
