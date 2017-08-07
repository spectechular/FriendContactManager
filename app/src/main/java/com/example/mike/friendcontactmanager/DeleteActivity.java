package com.example.mike.friendcontactmanager;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {

        ArrayList<Friend> friends = dbManager.selectAll();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this);

        for (Friend friend : friends) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(friend.getId());
            radioButton.setText(friend.toString());
            radioGroup.addView(radioButton);
        }

        RadioButtonHandler radioButtonHandler = new RadioButtonHandler();
        radioGroup.setOnCheckedChangeListener(radioButtonHandler);

        Button backButton = new Button(this);
        backButton.setText(R.string.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteActivity.this.finish();
            }
        });

        scrollView.addView(radioGroup);
        relativeLayout.addView(scrollView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        relativeLayout.addView(backButton, params);
        setContentView(relativeLayout);

    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            dbManager.deleteByID(checkedId);
            Toast.makeText(DeleteActivity.this, "Friend Deleted", Toast.LENGTH_SHORT).show();
            updateView();
        }
    }


}