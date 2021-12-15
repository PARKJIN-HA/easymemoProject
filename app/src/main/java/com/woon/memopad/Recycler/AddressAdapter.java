package com.woon.memopad.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woon.memopad.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private List<ItemList> data;

    public AddressAdapter(List<ItemList> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.memorecycler_itemview_address, parent, false);
        AddressAdapter.ViewHolder vh = new AddressAdapter.ViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemList itemList = data.get(position);
        holder.cityT.setText(itemList.city);
        holder.districtT.setText(itemList.district);
        holder.buildingT.setText(itemList.building);
        holder.roadNameT.setText(itemList.roadName);
        holder.detailT.setText(itemList.detail);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityT, districtT, buildingT, roadNameT, detailT;

        ViewHolder(View itemView) {
            super(itemView);

            cityT    = itemView.findViewById(R.id.textView1);
            districtT= itemView.findViewById(R.id.textView2);
            buildingT= itemView.findViewById(R.id.textView3);
            roadNameT= itemView.findViewById(R.id.roadName);
            detailT  = itemView.findViewById(R.id.detailAddr);

        }
    }

    public static class ItemList {
        public String city, district, building, roadName, detail;

        public ItemList(String city, String district, String building, String roadName, String detail) {
            this.city = city;
            this.district = district;
            this.building = building;
            this.roadName = roadName;
            this.detail = detail;
        }
    }
}
