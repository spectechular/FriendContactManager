package com.example.mike.friendcontactmanager;

import android.database.sqlite.SQLiteException;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.support.design.R.attr.selectableItemBackground;

public class UpdateActivity extends AppCompatActivity {


    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Friend> friends = dbManager.selectAll();
        if (friends.size() > 0) {

            ScrollView scrollView = new ScrollView(this);

            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(friends.size());
            gridLayout.setColumnCount(5);



            TextView[] ids = new TextView[friends.size()];
            EditText[][] namesAndPrices = new EditText[friends.size()][3];
            Button[] buttons = new Button[friends.size()];

            ButtonHandler buttonHandler = new ButtonHandler();

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);

            int width = size.x;

            int i = 0;

            for (Friend friend : friends) {

                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + friend.getId());


                // TODO new to add another field and add another edit text for email

                Log.d("Updatepull array email:", friend.getEmail());
                Log.d("Updatepull array first:", friend.getFirstName());
                Log.d("Updatepull array last:", friend.getLastName());

                namesAndPrices[i][0] = new EditText(this);
                namesAndPrices[i][1] = new EditText(this);
                namesAndPrices[i][2] = new EditText(this);
                namesAndPrices[i][0].setText(friend.getEmail());
                namesAndPrices[i][1].setText(friend.getFirstName());
                namesAndPrices[i][2].setText(friend.getLastName());

                namesAndPrices[i][0].setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                namesAndPrices[i][0].setId(10 * friend.getId());
                namesAndPrices[i][1].setId(10 * friend.getId() + 1);
                namesAndPrices[i][2].setId(10 * friend.getId() + 2);

                buttons[i] = new Button(this);
                buttons[i].setText("+");
                buttons[i].setId(friend.getId());

                buttons[i].setOnClickListener(buttonHandler);

                gridLayout.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);

                gridLayout.addView(namesAndPrices[i][0], (int) (width * .4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                gridLayout.addView(namesAndPrices[i][1], (int) (width * .2),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(namesAndPrices[i][2], (int) (width * .2),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                gridLayout.addView(buttons[i], (int) (width * .1),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;

            }


            scrollView.addView(gridLayout);
            setContentView(scrollView);

        }

    }


    private class ButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int friendId = v.getId();
            EditText emailET = (EditText) findViewById(10 * friendId);
            EditText firstNameET = (EditText) findViewById(10 * friendId + 1);
            EditText lastNameET = (EditText) findViewById(10 * friendId + 2);
            String emailString = emailET.getText().toString();
            String firstNameString = firstNameET.getText().toString();
            String lastNameString = lastNameET.getText().toString();

            try {
                dbManager.updateByID(friendId, emailString, firstNameString, lastNameString);
                Toast.makeText(UpdateActivity.this, "Friend Updated!", Toast.LENGTH_SHORT).show();
                updateView();
            } catch (SQLiteException SQLe) {
                Toast.makeText(UpdateActivity.this, "Edit Error!", Toast.LENGTH_LONG).show();
            }
        }
    }
}