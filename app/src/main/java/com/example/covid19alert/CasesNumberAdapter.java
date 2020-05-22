package com.example.covid19alert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CasesNumberAdapter extends ArrayAdapter<CasesNumber> {


    public CasesNumberAdapter(@NonNull Context context, List<CasesNumber> casesNumbers) {
        super(context, 0, casesNumbers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_row, parent, false);

        }
        CasesNumber currentcasesNumber = getItem(position);

        TextView countryView = listItemView.findViewById(R.id.country);
        countryView.setText(currentcasesNumber.getmCountry());

        TextView recoveredView = listItemView.findViewById(R.id.recovered);
        recoveredView.setText(currentcasesNumber.getmRecovered());

        TextView deathView = listItemView.findViewById(R.id.death);
        deathView.setText(currentcasesNumber.getmDeath());

        TextView activeView = listItemView.findViewById(R.id.active);
        activeView.setText(currentcasesNumber.getmActivecases() );

        return listItemView;

    }

}
