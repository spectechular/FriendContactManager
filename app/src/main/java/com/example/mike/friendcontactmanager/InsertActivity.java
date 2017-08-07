package com.example.mike.friendcontactmanager;

import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


//TODO Insert potentially does not insert correctly. When going to delete activity NUll shows


public class InsertActivity extends AppCompatActivity {


        private DatabaseManager dbManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            dbManager = new DatabaseManager(this);
            setContentView(R.layout.activity_insert);

        }

        public void insert(View v) {
            EditText firstNameEditText = (EditText) findViewById(R.id.insert_first_name_edit_text);
            EditText lastNameEditText = (EditText) findViewById(R.id.insert_last_name_edit_text);
            EditText emailEditText = (EditText) findViewById(R.id.insert_email_edit_text);
            String firstNameString = firstNameEditText.getText().toString();
            String lastNameString = lastNameEditText.getText().toString();
            String emailString = emailEditText.getText().toString();

            try {
                Friend friend = new Friend(0, emailString, firstNameString, lastNameString);
                dbManager.insert(friend);
                Toast.makeText(this, "A Friend Added!", Toast.LENGTH_LONG).show();
            } catch (SQLException SQLe) {
                Toast.makeText(this, "Friend Add Error", Toast.LENGTH_LONG).show();
            }

            firstNameEditText.setText("");
            lastNameEditText.setText("");
            emailEditText.setText("");
        }

        public void goBack(View v) {
            this.finish();
        }

    }