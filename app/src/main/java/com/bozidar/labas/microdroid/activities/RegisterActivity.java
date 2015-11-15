package com.bozidar.labas.microdroid.activities;

import android.util.Log;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.presenter.RegisterPresenter;
import com.bozidar.labas.microdroid.mvp.presenter.impl.RegisterPresenterImpl;
import com.bozidar.labas.microdroid.mvp.view.RegisterView;
import com.bozidar.microdroid.base.MicroActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends MicroActivity implements RegisterView, DatePickerDialog.OnDateSetListener {

    @InjectView(R.id.username)
    TextView tvUsername;

    @InjectView(R.id.email)
    TextView tvEmail;

    @InjectView(R.id.password)
    TextView tvPassword;

    @InjectView(R.id.first_name)
    TextView tvFirstName;

    @InjectView(R.id.last_name)
    TextView tvLastName;

    @InjectView(R.id.birth_date)
    TextView tvBirthDate;

    @InjectView(R.id.city)
    TextView tvCity;

    private String birthDate = "";

    DatePickerDialog dpd;

    RegisterPresenter presenter;

    //name mora bit unesen i email mora biti unesen, pass min 6


    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        presenter = new RegisterPresenterImpl(this);

        Calendar now = Calendar.getInstance();
        dpd = DatePickerDialog.newInstance(
                RegisterActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {}

    @OnClick(R.id.email_sign_in_button)
    public void register() {
        presenter.register(tvUsername.getText().toString(), tvPassword.getText().toString(), tvEmail.getText().toString(), tvFirstName.getText().toString(), tvLastName.getText().toString(), tvCity.getText().toString(),
                birthDate);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        birthDate = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;

        Log.d("DATE", birthDate);
    }

    @OnClick(R.id.birth_date)
    public void getDate(){
        dpd.show(getFragmentManager(), "Datepickerdialog");
        dpd.showYearPickerFirst(true);
    }
}

