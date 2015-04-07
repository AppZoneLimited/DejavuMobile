package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.AccountNumberVerificationListener;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.AuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.PasswordPinModel;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.AccountNumberVerification;
import dejavu.appzonegroup.com.dejavuandroid.ServerRequest.PasswordPinAuthentication;
import dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails.GeneralPreference;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class BankAccountNumber extends Fragment implements AccountNumberVerificationListener {
    EditText accountNumberEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account_number, container, false);
        accountNumberEditText = (EditText) rootView.findViewById(R.id.account_number_field);
        rootView.findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AccountNumberVerification(BankAccountNumber.this, getActivity()).execute(accountNumberEditText.getText().toString());
            }
        });
        return rootView;
    }


    @Override
    public void onAccountVerified() {

    }

    @Override
    public void onAccountVerificationDenied() {
        new FragmentChanger(getFragmentManager(), true, false, true);
    }

    @Override
    public void onRequestFailed() {
        new FragmentChanger(getFragmentManager(), true, false, true);
    }
}
