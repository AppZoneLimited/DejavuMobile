package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.PhoneSubmissionCallback;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.onPinRequest;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.pinVerificationListener;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.PhoneSubmission;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.PinRequest;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.VerifyPin;
import dejavu.appzonegroup.com.dejavuandroid.SharePreferences.UserDetailsSharePreferences;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails.UserDetailsFromPhone;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

public class PhoneRegistration extends Fragment implements onPinRequest, pinVerificationListener, PhoneSubmissionCallback {
    private EditText phoneEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.phone_number_fragment, container, false);
        phoneEditText = (EditText) rootView.findViewById(R.id.phone_number_edit_view);
        phoneEditText.setText(new UserDetailsFromPhone(getActivity()).getPhoneNumber());
        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PinRequest(getActivity(), PhoneRegistration.this, "+234" + phoneEditText.getText().toString());
            }
        });
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_generic_flow, menu);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            new VerifyPin(intent.getStringExtra("pin"), new UserDetailsSharePreferences(getActivity()).getKey(), new UserDetailsSharePreferences(getActivity()).getUserPhoneNumber(), PhoneRegistration.this);
        }
    };


    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiver, new IntentFilter("dejavu.BroadcastReceivers"));

    }


    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
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
    public void onPinRequested(String key) {
        new UserDetailsSharePreferences(getActivity()).setKey(key);
        new UserDetailsSharePreferences(getActivity()).setUserPhoneNumber(phoneEditText.getText().toString());
        new VerifyPin("1234", new UserDetailsSharePreferences(getActivity()).getKey(), new UserDetailsSharePreferences(getActivity()).getUserPhoneNumber(), PhoneRegistration.this);

    }

    @Override
    public void onPinRequestDenied() {
        // do something
        new ShowMessage(getActivity(), "denied", Toast.LENGTH_LONG);
    }

    @Override
    public void onRequestFailed() {
        new ShowMessage(getActivity(), "failed", Toast.LENGTH_LONG);
    }


    @Override
    public void onPinValid() {
        new PhoneSubmission(getActivity(),PhoneRegistration.this).execute(new UserDetailsSharePreferences(getActivity()).getUserPhoneNumber());
//        if (GeneralPreference.getFlowType(getActivity()) == FlowConstant.BANK_FLOW) {
//            new FragmentChanger(getFragmentManager(), new PasswordPinAuth());
//        } else if (GeneralPreference.getFlowType(getActivity()) == FlowConstant.GENERIC_FLOW) {
//            new FragmentChanger(getFragmentManager(), new ProfileDetails());
//        } else {
//            new ShowMessage(getActivity(), "Wrong flow", Toast.LENGTH_LONG);
//        }
    }

    @Override
    public void onInvalidPin() {
        new ShowMessage(getActivity(), "invalid pin", Toast.LENGTH_LONG);
    }

    @Override
    public void onPinVerificationFailed(String failure) {
        new ShowMessage(getActivity(), failure, Toast.LENGTH_LONG);
    }

    @Override
    public void onPhoneSubmitted() {
        new FragmentChanger(getFragmentManager(), new ProfileDetails());
    }

    @Override
    public void onSubmissionFailed(String failure) {
        new FragmentChanger(getFragmentManager(), new ProfileDetails());
        new ShowMessage(getActivity(), failure, Toast.LENGTH_LONG);
    }
}
