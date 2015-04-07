package dejavu.appzonegroup.com.dejavuandroid.ShellFramework.Authetication;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.Activities.MainActivity;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

public class PasswordAuth extends FragmentActivity {
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_auth);
        passwordEditText = (EditText) findViewById(R.id.password_field);
        findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
    }

    public void verify() {
        if (passwordEditText.getText().toString().equalsIgnoreCase(new UserDetailsSharePreferences(this).getPassword())) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            new ShowMessage(this, "Invalid password", Toast.LENGTH_LONG);
        }
    }
}
