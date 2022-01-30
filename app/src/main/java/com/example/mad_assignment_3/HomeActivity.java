package com.example.mad_assignment_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_assignment_3.API.BackendImpl;
import com.example.mad_assignment_3.model.Company;
import com.example.mad_assignment_3.model.CompanyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends Fragment {
    private ArrayList<Company> companyList;
    private CompanyAdapter companyAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View V;
        V= inflater.inflate(R.layout.activity_home, container, false);
        RecyclerView recyclerView = V.findViewById(R.id.recycler_view);
        companyList = new ArrayList<>();
        companyAdapter = new CompanyAdapter(companyList);
        RecyclerView.LayoutManager mLayoutManager = new
                LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(companyAdapter);
        recyclerView.addOnItemTouchListener(new
                RecyclerTouchListener(getActivity().getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Company company = companyList.get(position);
                Fragment fr=new CompanyDetails();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("name",
                        companyList.get(position).name);
                args.putString("location",
                        companyList.get(position).phone);
                args.putString("year",
                        companyList.get(position).description);
                args.putString("address",
                        companyList.get(position).address);
                args.putDouble("longitude",
                        companyList.get(position).location.getLongitude());
                args.putDouble("latitude",
                        companyList.get(position).location.getLatitude());
                args.putString("logo",
                        companyList.get(position).logo);
                fr.setArguments(args);
                fragmentTransaction.replace(R.id.FragmentHolder, fr);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        getCompaniesList();
        return V;
    }
    private void getCompaniesList() {
            Call<CompanyResponse> call = new BackendImpl(getActivity().getApplicationContext()).companies();
            call.enqueue(new Callback<CompanyResponse>() {
                @Override
                public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                    CompanyResponse data = response.body();
                    if (data.status) {
                        companyList.addAll(data.data);
                        companyAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<CompanyResponse> call, Throwable t) {
                    Toast.makeText(
                            getActivity().getApplicationContext(),
                            "Could not register",
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
    }
}