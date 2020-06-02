package com.example.covid19alert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CasesNumberAdapter extends ArrayAdapter<CasesNumber>   {


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

        //This is to put commas inbetween the numbers gotten from the api
        //the value has na and null from the api, so hence the if statement
        String NA = "NA";
        String nulll = "null";
        if (currentcasesNumber.getmRecovered().equals(NA)) {
            recoveredView.setText(currentcasesNumber.getmRecovered());

        } else if (currentcasesNumber.getmRecovered().equals(nulll)) {
            recoveredView.setText(currentcasesNumber.getmRecovered());

        }
        else{
        String mRecoveredd = new DecimalFormat("#,###,###").format(Double.parseDouble(currentcasesNumber.getmRecovered()));
        recoveredView.setText(mRecoveredd);}

        TextView deathView = listItemView.findViewById(R.id.death);
        if (currentcasesNumber.getmDeath().equals(NA)){
            deathView.setText(currentcasesNumber.getmDeath());
        }else if (currentcasesNumber.getmDeath().equals(nulll)){
            deathView.setText(currentcasesNumber.getmDeath());
        }else{String mDeathh = new DecimalFormat("#,###,###").format(Double.parseDouble(currentcasesNumber.getmDeath()));
            deathView.setText(mDeathh);}


        TextView activeView = listItemView.findViewById(R.id.active);
        if (currentcasesNumber.getmActivecases().equals(NA)){
             activeView.setText(currentcasesNumber.getmActivecases() );}
        else if (currentcasesNumber.getmActivecases().equals(nulll)){
            activeView.setText(currentcasesNumber.getmActivecases() );}
        else{String mActivee = new DecimalFormat("#,###,###").format(Double.parseDouble(currentcasesNumber.getmActivecases()));
            activeView.setText(mActivee);}

        return listItemView;

    }




}
