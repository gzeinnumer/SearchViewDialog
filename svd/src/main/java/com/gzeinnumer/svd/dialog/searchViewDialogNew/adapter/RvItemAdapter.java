package com.gzeinnumer.svd.dialog.searchViewDialogNew.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzeinnumer.svd.R;
import com.gzeinnumer.svd.constant.SelectType;
import com.gzeinnumer.svd.model.BaseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RvItemAdapter<T> extends RecyclerView.Adapter implements Filterable, MyHolderSingle.OnItemSelectedListener {

    public static int textColor = -100;
    public static int textSize = -100;
    public ArrayList<BaseModel<T>> list;
    public ArrayList<BaseModel<T>> listReal;
    public ArrayList<BaseModel<T>> listFilter;
    private final Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<BaseModel<T>> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                Collections.sort(listFilter, new Comparator<BaseModel<T>>() {
                    @Override
                    public int compare(BaseModel<T> o1, BaseModel<T> o2) {
                        return o1.toString().toLowerCase().compareTo(o2.toString().toLowerCase());
                    }
                });
                filteredList.addAll(listFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (BaseModel<T> item : listFilter) {
                    if (item.toString().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    int TYPE;
    MyHolderSingle.OnItemSelectedListener listener;

    public RvItemAdapter(int type, MyHolderSingle.OnItemSelectedListener listener,
                         List<T> items) {
        this.TYPE = type;
        this.listener = listener;

        list = new ArrayList<>();
        for (T item : items) {
            list.add(new BaseModel<T>(item, false));
        }
        listReal = new ArrayList<>(list);
        listFilter = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public MyHolderSingle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_single, parent, false);
        return new MyHolderSingle<T>(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((MyHolderSingle) viewHolder).bind(TYPE, list.get(position));
    }

    public List<T> getSelectedItems() {

        List<T> selectedItems = new ArrayList<>();
        for (BaseModel<T> item : listReal) {
            if (item.isChecked()) {
                selectedItems.add(item.getT());
            }
        }
        return selectedItems;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (TYPE != SelectType.TYPE_SINGLE) {
            return MyHolderSingle.MULTI_SELECTION;
        } else {
            return MyHolderSingle.SINGLE_SELECTION;
        }
    }

    @Override
    public void onItemSelected(BaseModel item) {
        if (TYPE == SelectType.TYPE_SINGLE) {
            for (BaseModel<T> selectableItem : list) {
                if (!selectableItem.equals(item)
                        && selectableItem.isChecked()) {
                    selectableItem.setChecked(false);
                } else if (selectableItem.equals(item)
                        && item.isChecked()) {
                    selectableItem.setChecked(true);
                }
            }
            notifyDataSetChanged();
        }
        listener.onItemSelected(item);
    }

    public void setTextItemColor(int textListColor) {
        textColor = textListColor;
    }

    public void setTextItemSize(int textListSize) {
        textSize = textListSize;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
}
