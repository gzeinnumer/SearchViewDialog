package com.gzeinnumer.searchviewdialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.svd.constant.ButtonStyle;
import com.gzeinnumer.svd.dialog.searchViewDialogNew.SearchViewDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    ArrayList<String> listString = new ArrayList<>();
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        listString.add("Lorem ipsum dolor");
//        listString.add("sit amet, consectetur");
//        listString.add("adipiscing elit, sed do");
//        listString.add("eiusmod tempor");
//        listString.add("incididunt ut labore et dolore");
//        listString.add("magna aliqua");
//        listString.add("Ut enim ad minim veniam, quis nostrud exercitation");
//        listString.add("ullamco laboris nisi ut aliquip");
//        listString.add("ex ea commodo consequat.");
//        listString.add("Duis aute irure dolor");
//        listString.add("in reprehenderit");
//        listString.add("in voluptate");
//        listString.add("velit esse cillum dolor");

        initOnClick();
    }

    private void initOnClick() {
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv_title);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //choise 1
//                initDialogSingleGZeinNumer();
                initDialogSingleGZeinNumer2();
            }
        });
    }

    private void initDialogSingleGZeinNumer() {
        ArrayList<ExampleModel> listObject = new ArrayList<>();
        listObject.add(new ExampleModel(1, "Zein", "Balbar"));
        listObject.add(new ExampleModel(2, "Zein2", "Balbar2"));
        listObject.add(new ExampleModel(3, "Zein3", "Balbar3"));
        listObject.add(new ExampleModel(4, "Zein4", "Balbar4"));

//        String[] arrayString = {"M", "Fadli", "Zein"};
        new SearchViewDialog<ExampleModel>(getSupportFragmentManager())
                .setItems(listObject)
                .setTitle("ini title")
                .setContent("ini content")
                .setButtonStyle(ButtonStyle.ButtonContained)
                .onOkPressedCallBackSingle(new SearchViewDialog.OnOkPressedSingle<ExampleModel>() {
                    @Override
                    public void onOkSingle(ExampleModel data) {
                        String temp = "Single Select : \n" + data.toString();
                        tv.setText(temp);
                    }
                })
//                .onOkPressedCallBackMulti(new SearchViewDialog.OnOkPressedMulti<String>() {
//                    @Override
//                    public void onOkMulti(List<String> data) {
//                        String temp = "Multi Select :\n";
//                        temp = temp + "Total Data => " + data.size() + "\n\n";
//                        for (String d : data) {
//                            temp = temp + "Value => " + d + "\n";
//                        }
//                        tv.setText(temp);
//                    }
//                })
                .onCancelPressedCallBack(new SearchViewDialog.OnCancelPressed() {
                    @Override
                    public void onCancelPressed() {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


    private void initDialogSingleGZeinNumer2() {
        ArrayList<ExampleModel> listObject = new ArrayList<>();
        listObject.add(new ExampleModel(1, "Zein", "Balbar"));
        listObject.add(new ExampleModel(2, "Zein2", "Balbar2"));
        listObject.add(new ExampleModel(3, "Zein3", "Balbar3"));
        listObject.add(new ExampleModel(4, "Zein4", "Balbar4"));
        listObject.add(new ExampleModel(4, "Zein4", "Balbar4"));
        listObject.add(new ExampleModel(4, "Zein4", "Balbar4"));
        listObject.add(new ExampleModel(4, "Zein4", "Balbar4"));
        listObject.add(new ExampleModel(4, "Zein4", "Balbar4"));
        listObject.add(new ExampleModel(4, "Zein4", "Balbar4"));

        new SearchViewDialog<ExampleModel>(getSupportFragmentManager())
                .setAnimationStyle(R.style.DialogStyle_In)
                .setItems(listObject)
                .setTitle("Version 3.1.+")
//                .setContent("ini content")
                .setButtonStyle(ButtonStyle.ButtonContained)
//                .enableFullScreen()
                .onOkPressedCallBackSingle(new SearchViewDialog.OnOkPressedSingle<ExampleModel>() {
                    @Override
                    public void onOkSingle(ExampleModel data) {
                        String temp = "Single Select : \n" + data.toString();
                        tv.setText(temp);
                    }
                })
                .onOkPressedCallBackMulti(new SearchViewDialog.OnOkPressedMulti<ExampleModel>() {
                    @Override
                    public void onOkMulti(List<ExampleModel> data) {
                        String temp = "Multi Select :\n";
                        temp = temp + "Total Data => " + data.size() + "\n\n";
                        for (ExampleModel d : data) {
                            temp = temp + "Value => " + d.getName() + "\n";
                            temp = temp + "Value => " + d.getAddress() + "\n";
                        }
                        tv.setText(temp);
                    }
                })
                .onCancelPressedCallBack(new SearchViewDialog.OnCancelPressed() {
                    @Override
                    public void onCancelPressed() {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}