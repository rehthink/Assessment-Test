package com.rehthinkdev.gramoday;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rehthinkdev.gramoday.API.RetrofitClient;
import com.rehthinkdev.gramoday.Model.GramModel;
import com.rehthinkdev.gramoday.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BusinessFragment extends Fragment {

    TextView mandi, firm, shop;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    Product[] product;

    public BusinessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mandi = view.findViewById(R.id.mandiName);
        firm = view.findViewById(R.id.firmName);
        shop = view.findViewById(R.id.shopNumber);
        recyclerView = view.findViewById(R.id.recyclerView);

        SharedPreferences preferences=getActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String firmName=preferences.getString("Firm","");
        String shopNumb = preferences.getString("Shop Number","");
        String mandiName = preferences.getString("Mandi","");

        mandi.setText(mandiName);
        firm.setText(" "+ firmName);
        shop.setText(shopNumb);

        getProducts();
      //  setUpRecyclerView();
    }
//
//    private void setUpRecyclerView() {
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
//        recyclerView.setAdapter(adapter);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business, container, false);
    }

    private void getProducts() {
        Call<List<Product>> call = RetrofitClient.getInstance().getMyApi().getProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                String[] oneProduct = new String[productList.size()];
                String[] twoProduct = new String[productList.size()];

                for (int i = 0; i < productList.size(); i++) {
                    oneProduct[i] = productList.get(i).getCmdtyStdName();
                    //twoProduct[i] = productList.get(i).getCmdtyID();
                    Log.d("TAG", oneProduct[i]);
                    product[i] = new Product(productList.get(i).getCmdtyStdName());
                    Log.d("TAG", String.valueOf(product[i]));



                    //Log.d("TAG", twoProduct[i]);
                }

               RecyclerAdapter adapter = new RecyclerAdapter(product);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerView.setAdapter(adapter);
                //superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

}