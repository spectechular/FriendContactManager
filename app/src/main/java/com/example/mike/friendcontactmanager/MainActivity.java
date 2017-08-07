package com.example.mike.friendcontactmanager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final since accessed from onClickListener
        final EditText emailQueryEditText = (EditText) findViewById(R.id.email_query_edit_text);
        final TextView returnValueFirstNameTextView = (TextView) findViewById(R.id.first_name_search_result_text_view);
        final TextView returnValueLastNameTextView = (TextView) findViewById(R.id.last_name_search_result_text_view);
        final TableLayout queryResultDisplayTableLayout = (TableLayout) findViewById(R.id.email_search_display_table_layout);

        ImageButton startEmailQueryButton = (ImageButton) findViewById(R.id.query_start_image_button);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startEmailQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());

                String emailToQuery = emailQueryEditText.getText().toString();
                Log.d("Email from edit text", emailToQuery);
               /* if (databaseManager.selectByEmail(emailToQuery)) {
                    Toast.makeText(getApplicationContext(), "Frien foiund",  Toast.LENGTH_LONG).show();
                }*/
                Friend friend = databaseManager.selectByEmail(emailToQuery);

                if (friend == null) {

                    Toast.makeText(getApplicationContext(), "No friend found!", Toast.LENGTH_LONG).show();
                }
                else {

                    queryResultDisplayTableLayout.setVisibility(View.VISIBLE);
                    returnValueFirstNameTextView.setText(friend.getFirstName());
                    returnValueLastNameTextView.setText(friend.getLastName());
                    Toast.makeText(getApplicationContext(), "Friend found!", Toast.LENGTH_LONG).show();
                }


            }
        }) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Log.w("MainActivity", "Add Selected");
            Intent insertIntent = new Intent(this, InsertActivity.class);
            this.startActivity(insertIntent);
            return true;
        }
        if (id == R.id.action_delete) {
            Log.w("MainActivity", "Delete Selected");
            Intent deleteIntent = new Intent(this, DeleteActivity.class);
            this.startActivity(deleteIntent);
            return true;
        }
        if (id == R.id.action_update) {
            Log.w("MainActivity", "Update Selected");
            Intent updateIntent = new Intent(this, UpdateActivity.class);
            this.startActivity(updateIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}