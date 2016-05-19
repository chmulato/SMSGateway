package csonline.net.br.smsgateway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import csonline.net.br.smsgateway.R;

/**
 * @author Christian Mulato
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button startButton = (Button) findViewById(R.id.start_button);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Your service is starting now!", Toast.LENGTH_LONG).show();
            }
        });
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
        if (id == R.id.action_pop_settings) {
            Intent intent = new Intent(MainActivity.this, PopActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_imap_settings) {
            Intent intent = new Intent(MainActivity.this, ImapActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_smtp_settings) {
            Intent intent = new Intent(MainActivity.this, SmtpActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_send_email) {
            Intent intent = new Intent(MainActivity.this, EmailActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_send_sms) {
            Intent intent = new Intent(MainActivity.this, SmsActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_exit_app) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
