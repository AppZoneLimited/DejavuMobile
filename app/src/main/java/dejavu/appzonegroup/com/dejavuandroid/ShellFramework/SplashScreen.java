package dejavu.appzonegroup.com.dejavuandroid.ShellFramework;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Random;

import dejavu.appzonegroup.com.dejavuandroid.Activities.MainActivity;
import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.FragmentChanger;
import dejavu.appzonegroup.com.dejavuandroid.Fragment.PhoneRegistration;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.GoogleCloudMessagingListener;
import dejavu.appzonegroup.com.dejavuandroid.PushNotification.PushRegistration;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.ConfigurationRequest;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.Authetication.PasswordAuth;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails.GeneralPreference;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;


public class SplashScreen extends FragmentActivity implements ConfigurationRequest.onConfigurationRequest, GoogleCloudMessagingListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new ConfigurationRequest(this, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationRequestSuccessful(int flow, boolean debit, boolean password, boolean hardToken, boolean softToken) {

    }

    @Override
    public void onConfigurationRequestFailed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (new UserDetailsSharePreferences(SplashScreen.this).isRegistered()) {
                    if (new UserDetailsSharePreferences(SplashScreen.this).isFullyAuth()) {
                        startActivity(new Intent(SplashScreen.this, PasswordAuth.class));
                        finish();
                    } else {
                        new PushRegistration(SplashScreen.this, SplashScreen.this);
                        new ShowMessage(SplashScreen.this, "fully auth plz", Toast.LENGTH_LONG);
                    }
                } else {
                    int flow = new Random().nextInt(2);
                    GeneralPreference.setFlowType(SplashScreen.this, flow);
                    if (flow == FlowConstant.GENERIC_FLOW) {
                        new ShowMessage(SplashScreen.this, "Generic flow", Toast.LENGTH_LONG);
                        //new FragmentChanger(getSupportFragmentManager(), new DebitCard().newInstance(FlowConstant.GENERIC_FLOW));
                        new FragmentChanger(getSupportFragmentManager(), new PhoneRegistration());
                    } else if (flow == FlowConstant.BANK_FLOW) {
                        //new FragmentChanger(getSupportFragmentManager(), true, false, true);
                        new FragmentChanger(getSupportFragmentManager(), new PhoneRegistration());
                        new ShowMessage(SplashScreen.this, "Bank flow", Toast.LENGTH_LONG);
                    } else {
                        // what should i do?
                    }
                }
            }
        }, 1000);

    }

    @Override
    public void onRegistered() {
        new UserDetailsSharePreferences(SplashScreen.this).setFullyAuth(true);
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRegistrationFailed() {
        new PushRegistration(SplashScreen.this, SplashScreen.this);
    }
}
