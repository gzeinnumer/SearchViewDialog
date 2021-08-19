package com.gzeinnumer.svd.dialog.searchViewDialogNew;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.gzeinnumer.svd.R;
import com.gzeinnumer.svd.constant.ButtonStyle;
import com.gzeinnumer.svd.constant.SelectType;
import com.gzeinnumer.svd.dialog.BaseDialog;
import com.gzeinnumer.svd.dialog.searchViewDialogNew.adapter.MyHolderSingle;
import com.gzeinnumer.svd.dialog.searchViewDialogNew.adapter.RvItemAdapter;
import com.gzeinnumer.svd.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class SearchViewDialogSetting<T> extends BaseDialog implements MyHolderSingle.OnItemSelectedListener<T> {

    private static final String TAG = "SearchViewDialogSetting";
    protected SearchViewDialog.OnCancelPressed onCancelPressed;
    protected SearchViewDialog.OnOkPressedSingle onOkPressedSingle;
    protected SearchViewDialog.OnOkPressedMulti onOkPressedMulti;
    protected int buttonColor = 0;
    protected ButtonStyle btnStyle;
    protected int type = SelectType.TYPE_SINGLE;
    protected String tvTitleValue;
    protected String tvContentValue;
    protected String dBtnCancelValue;
    protected String dBtnOkValue;
    protected Drawable shapeCanvas = null;
    protected double tvTitleSize = 0;
    protected double tvContentSize = 0;
    protected double dBtnTextSize = 0;
    protected int tvTitleColor = 0;
    protected int tvContentColor = 0;
    protected int btnTextColorOk = 0;
    protected int btnTextColorCancel = 0;
    protected int okIconL = 0;
    protected int okIconT = 0;
    protected int okIconR = 0;
    protected int okIconB = 0;
    protected int cancelIconL = 0;
    protected int cancelIconT = 0;
    protected int cancelIconR = 0;
    protected int cancelIconB = 0;
    protected int buttonGravity = -100;
    protected int tvTitleAlignment = View.TEXT_ALIGNMENT_CENTER; //default from view
    protected int tvContentAlignment = View.TEXT_ALIGNMENT_TEXT_START; //default from view
    protected int listHeight = 0;
    protected List<T> listFromUser = new ArrayList<>();
    protected List<T> list = new ArrayList<>();
    protected int textListColor = -100;
    protected int textSearchColor = -100;
    protected int textListSize = -100;
    protected int textSearchSize = -100;
    List<BaseModel<T>> selectedItems;
    private View _view;
    private LinearLayout _dialogCanvas;
    private LinearLayout _parentButton;
    private TextView _tvTitle;
    private TextView _tvContent;
    private Button _dBtnCancelMBT;
    private Button _dBtnCancelMBO;
    private Button _dBtnCancelMBC;
    private Button _dBtnOkMBT;
    private Button _dBtnOkMBO;
    private Button _dBtnOkMBC;
    private RecyclerView _rv;
    private TextInputEditText _edSearch;
    private RvItemAdapter _adapter;

    private void initView() {
        _dialogCanvas = _view.findViewById(R.id.dialog_canvas);
        _parentButton = _view.findViewById(R.id.parent_button);
        _tvTitle = _view.findViewById(R.id.tv_title);
        _tvContent = _view.findViewById(R.id.tv_content);
        _dBtnCancelMBT = _view.findViewById(R.id.d_btn_cancel_MBT);
        _dBtnCancelMBO = _view.findViewById(R.id.d_btn_cancel_MBO);
        _dBtnCancelMBC = _view.findViewById(R.id.d_btn_cancel_MBC);
        _dBtnOkMBT = _view.findViewById(R.id.d_btn_ok_MBT);
        _dBtnOkMBO = _view.findViewById(R.id.d_btn_ok_MBO);
        _dBtnOkMBC = _view.findViewById(R.id.d_btn_ok_MBC);
        _rv = _view.findViewById(R.id.rv_item);
        _edSearch = _view.findViewById(R.id.ed_search);
    }

    @Override
    protected int contentView() {
        if (isFullScreen) {
            return R.layout.select_dialog_fullscreen;
        } else {
            return R.layout.select_dialog;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._view = view;
        initView();
        initDesign();
        initOnClick();
        initTextView();
    }

    private void initDesign() {
        if (shapeCanvas != null)
            _dialogCanvas.setBackground(shapeCanvas);

        if (tvTitleValue != null)
            _tvTitle.setText(tvTitleValue);
        else
            _tvTitle.setVisibility(View.GONE);

        if (tvContentValue != null)
            _tvContent.setText(tvContentValue);
        else
            _tvContent.setVisibility(View.GONE);

        if (dBtnCancelValue != null) {
            if (btnStyle == ButtonStyle.ButtonText) _dBtnCancelMBT.setText(dBtnCancelValue);
            if (btnStyle == ButtonStyle.ButtonOutlined) _dBtnCancelMBO.setText(dBtnCancelValue);
            if (btnStyle == ButtonStyle.ButtonContained) _dBtnCancelMBC.setText(dBtnCancelValue);
        }

        if (dBtnOkValue != null) {
            if (btnStyle == ButtonStyle.ButtonText) _dBtnOkMBT.setText(dBtnOkValue);
            if (btnStyle == ButtonStyle.ButtonOutlined) _dBtnOkMBO.setText(dBtnOkValue);
            if (btnStyle == ButtonStyle.ButtonContained) _dBtnOkMBC.setText(dBtnOkValue);
        }

        if (btnStyle != null) {
            if (btnStyle == ButtonStyle.ButtonText) {
                btnVisibleCancel(_dBtnCancelMBT, View.VISIBLE);
                btnVisibleOk(_dBtnOkMBT, View.VISIBLE);
            }
            if (btnStyle == ButtonStyle.ButtonOutlined) {
                btnVisibleCancel(_dBtnCancelMBO, View.VISIBLE);
                btnVisibleOk(_dBtnOkMBO, View.VISIBLE);
            }
            if (btnStyle == ButtonStyle.ButtonContained) {
                btnVisibleCancel(_dBtnCancelMBC, View.VISIBLE);
                btnVisibleOk(_dBtnOkMBC, View.VISIBLE);
            }
        } else {
            btnVisibleOk(_dBtnCancelMBT, View.VISIBLE);
            btnVisibleOk(_dBtnOkMBT, View.VISIBLE);
        }

        if (tvTitleSize != 0)
            _tvTitle.setTextSize((float) tvTitleSize);

        if (tvContentSize != 0)
            _tvContent.setTextSize((float) tvContentSize);

        if (dBtnTextSize != 0) {
            if (btnStyle == ButtonStyle.ButtonText)
                _dBtnCancelMBT.setTextSize((float) dBtnTextSize);
            if (btnStyle == ButtonStyle.ButtonOutlined)
                _dBtnCancelMBO.setTextSize((float) dBtnTextSize);
            if (btnStyle == ButtonStyle.ButtonContained)
                _dBtnCancelMBC.setTextSize((float) dBtnTextSize);
            if (btnStyle == ButtonStyle.ButtonText) _dBtnOkMBT.setTextSize((float) dBtnTextSize);
            if (btnStyle == ButtonStyle.ButtonOutlined)
                _dBtnOkMBO.setTextSize((float) dBtnTextSize);
            if (btnStyle == ButtonStyle.ButtonContained)
                _dBtnOkMBC.setTextSize((float) dBtnTextSize);
        }

        if (tvTitleColor != 0)
            _tvTitle.setTextColor(tvTitleColor);

        if (tvContentColor != 0)
            _tvContent.setTextColor(tvContentColor);

        if (btnTextColorCancel != 0) {
            if (btnStyle == ButtonStyle.ButtonText) _dBtnCancelMBT.setTextColor(btnTextColorCancel);
            if (btnStyle == ButtonStyle.ButtonOutlined)
                _dBtnCancelMBO.setTextColor(btnTextColorCancel);
            if (btnStyle == ButtonStyle.ButtonContained)
                _dBtnCancelMBC.setTextColor(btnTextColorCancel);
        }

        if (btnTextColorOk != 0) {
            if (btnStyle == ButtonStyle.ButtonText) _dBtnOkMBT.setTextColor(btnTextColorOk);
            if (btnStyle == ButtonStyle.ButtonOutlined) _dBtnOkMBO.setTextColor(btnTextColorOk);
            if (btnStyle == ButtonStyle.ButtonContained) _dBtnOkMBC.setTextColor(btnTextColorOk);
        }

        if (buttonGravity != -100) {
            _parentButton.setGravity(buttonGravity);
        }

        if (tvTitleAlignment != View.TEXT_ALIGNMENT_CENTER) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                _tvTitle.setTextAlignment(tvTitleAlignment);
            } else {
                _tvTitle.setGravity(tvTitleAlignment);
            }
        }

        if (tvContentAlignment != View.TEXT_ALIGNMENT_TEXT_START) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                _tvContent.setTextAlignment(tvContentAlignment);
            } else {
                _tvContent.setGravity(tvContentAlignment);
            }
        }

        if (buttonColor != 0 && btnStyle == ButtonStyle.ButtonContained) {
            _dBtnCancelMBC.setBackgroundColor(buttonColor);
            _dBtnOkMBC.setBackgroundColor(buttonColor);
        }

        if (listFromUser.size() > 0) {
            for (int i = 0; i < listFromUser.size(); i++) {
                list.add(listFromUser.get(i));
            }
            _adapter = new RvItemAdapter<T>(type, this, list);

            if (textListColor != -100)
                _adapter.setTextItemColor(textListColor);

            if (textListSize != -100)
                _adapter.setTextItemSize(textListSize);

            if (textSearchColor != -100)
                _edSearch.setTextColor(textSearchColor);

            if (textSearchSize != -100)
                _edSearch.setTextSize((float) textSearchSize);

            _rv.setAdapter(_adapter);
            _rv.setLayoutManager(new LinearLayoutManager(requireContext()));
            _rv.setHasFixedSize(true);
        }

        if (listHeight > 0 && !isFullScreen) {
            ViewGroup.LayoutParams params = _rv.getLayoutParams();
            params.height = listHeight;
            _rv.setLayoutParams(params);
        }
    }

    private void initOnClick() {
        _dBtnCancelMBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelPressed != null)
                    onCancelPressed.onCancelPressed();
                getDialog().dismiss();
            }
        });

        _dBtnOkMBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOkPressedSingle != null || onOkPressedMulti != null)
                    actionOk();
            }
        });

        _dBtnCancelMBO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelPressed != null)
                    onCancelPressed.onCancelPressed();
                getDialog().dismiss();
            }
        });

        _dBtnOkMBO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOkPressedSingle != null || onOkPressedMulti != null)
                    actionOk();
            }
        });

        _dBtnCancelMBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelPressed != null)
                    onCancelPressed.onCancelPressed();
                getDialog().dismiss();
            }
        });

        _dBtnOkMBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOkPressedSingle != null || onOkPressedMulti != null)
                    actionOk();
            }
        });
    }

    private void initTextView() {
        _edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (_adapter!=null)
                    _adapter.getFilter().filter(s.toString());
            }
        });
    }

    private void actionOk() {
        if (_adapter!=null){
            List<T> selectedItems = _adapter.getSelectedItems();
            if (selectedItems.size() > 0) {
                if (type == SelectType.TYPE_SINGLE) {
                    onOkPressedSingle.onOkSingle(selectedItems.get(0));
                } else {
                    onOkPressedMulti.onOkMulti(selectedItems);
                }
            }
        }
        getDialog().dismiss();
    }

    private void btnVisibleOk(Button button, int visible) {
        button.setVisibility(visible);
        button.setVisibility(visible);
        if (okIconL != 0 || okIconT != 0 || okIconR != 0 || okIconB != 0) {
            if (okIconT != 0 || okIconB != 0) {
                ViewGroup.LayoutParams lp = button.getLayoutParams();
                lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                button.setLayoutParams(lp);
            }
            button.setCompoundDrawablesWithIntrinsicBounds(okIconL, okIconT, okIconR, okIconB);
        }
    }

    private void btnVisibleCancel(Button button, int visible) {
        button.setVisibility(visible);
        button.setVisibility(visible);
        if (cancelIconL != 0 || cancelIconT != 0 || cancelIconR != 0 || cancelIconB != 0) {
            if (cancelIconT != 0 || cancelIconB != 0) {
                ViewGroup.LayoutParams lp = button.getLayoutParams();
                lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                button.setLayoutParams(lp);
            }
            button.setCompoundDrawablesWithIntrinsicBounds(cancelIconL, cancelIconT, cancelIconR, cancelIconB);
        }
    }

    @Override
    public void onItemSelected(BaseModel item) {
        List<BaseModel<T>> selectedItems = _adapter.getSelectedItems();
    }
}
