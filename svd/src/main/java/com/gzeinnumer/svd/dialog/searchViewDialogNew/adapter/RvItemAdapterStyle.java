package com.gzeinnumer.svd.dialog.searchViewDialogNew.adapter;

import android.content.Context;
import android.util.TypedValue;
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

public class RvItemAdapterStyle<T> extends RecyclerView.Adapter implements Filterable, MyHolderSingle.OnItemSelectedListener {

    public static int textColor = -100;
    public static int textSize = -100;
    public ArrayList<BaseModel<T>> list;
    public ArrayList<BaseModel<T>> listReal;
    public ArrayList<BaseModel<T>> listFilter;
    private int TYPE;
    private MyHolderSingle.OnItemSelectedListener listener;
    private Context context;
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

    public RvItemAdapterStyle(int type, MyHolderSingle.OnItemSelectedListener listener,
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
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_single, parent, false);
        return new MyHolderSingle<T>(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyHolderSingle holder = (MyHolderSingle) viewHolder;

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.cardView.getLayoutParams();
        int t = 5; //out
        int h = 5; //out
        int m = 10; //top-bottom
//        int l = m / 2; //center
        int l = 4; //center
        if (position == 0) {
            layoutParams.setMargins(intToDp(h), intToDp(t), intToDp(h), intToDp(l));
            holder.cardView.setLayoutParams(layoutParams);
        } else if (position == list.size() - 1) {
            layoutParams.setMargins(intToDp(h), intToDp(l), intToDp(h), intToDp(t));
            holder.cardView.setLayoutParams(layoutParams);
        } else {
            layoutParams.setMargins(intToDp(h), intToDp(l), intToDp(h), intToDp(l));
            holder.cardView.setLayoutParams(layoutParams);
        }
        holder.bind(TYPE, list.get(position));
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
            for (BaseModel<T> selectableItem : listReal) {
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

    public int intToDp(int sizeInDPH) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDPH, context.getResources().getDisplayMetrics());
    }
}
