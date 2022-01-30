package com.example.mad_assignment_3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_assignment_3.model.Company;

import java.util.ArrayList;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.MyViewHolder> {
    private ArrayList<Company> companyList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, year, location;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.title);
            location = view.findViewById(R.id.locationText);
            year = view.findViewById(R.id.yearText);
        }
    }
    public CompanyAdapter(ArrayList<Company> companyList) {
        this.companyList = companyList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) { View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.company_list_row,parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Company movie = companyList.get(position);
        holder.name.setText(movie.name);
        holder.location.setText(movie.phone);
        holder.year.setText(movie.description);
    }
    @Override
    public int getItemCount() {
        return companyList.size();
    }
}