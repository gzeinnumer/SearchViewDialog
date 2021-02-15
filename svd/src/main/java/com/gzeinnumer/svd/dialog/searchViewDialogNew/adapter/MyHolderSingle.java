package com.gzeinnumer.svd.dialog.searchViewDialogNew.adapter;


import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzeinnumer.svd.R;
import com.gzeinnumer.svd.constant.SelectType;
import com.gzeinnumer.svd.model.BaseModel;

public class MyHolderSingle<T> extends RecyclerView.ViewHolder {

    public static final int MULTI_SELECTION = 2;
    public static final int SINGLE_SELECTION = 1;
    CheckedTextView textView;
    BaseModel<T> mItem;
    LinearLayout parent;
    OnItemSelectedListener itemSelectedListener;

    public MyHolderSingle(@NonNull View view, OnItemSelectedListener listener) {
        super(view);
        itemSelectedListener = listener;

        textView = view.findViewById(R.id.checked_text_item);
        parent = view.findViewById(R.id.parent);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChecked(!mItem.isChecked() || getItemViewType() != MULTI_SELECTION);
                itemSelectedListener.onItemSelected(mItem);
            }
        });
    }

    public void setChecked(boolean value) {
        if (value) {
            textView.setBackgroundColor(Color.LTGRAY);
        } else {
            textView.setBackground(null);
        }
        mItem.setChecked(value);
        textView.setChecked(value);
    }

    public void bind(int TYPE, BaseModel<T> data) {

        if (RvItemAdapter.textColor != -100)
            textView.setTextColor(RvItemAdapter.textColor);

        if (RvItemAdapter.textSize != -100)
            textView.setTextSize((float) RvItemAdapter.textSize);

        textView.setText(data.toString());
        TypedValue value = new TypedValue();

        if (TYPE != SelectType.TYPE_SINGLE) {
            textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true);
        } else {
            textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
        }
        int checkMarkDrawableResId = value.resourceId;
        textView.setCheckMarkDrawable(checkMarkDrawableResId);

        mItem = data;
        setChecked(mItem.isChecked());
    }

    public interface OnItemSelectedListener<T> {
        void onItemSelected(BaseModel<T> item);
    }
}
