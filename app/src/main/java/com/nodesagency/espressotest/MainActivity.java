package com.nodesagency.espressotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nodesagency.espressotest.util.DialogUtils;
import com.nodesagency.espressotest.util.PasswordValidator;

import java.util.regex.Pattern;

import br.com.bloder.blormlib.Blorm;
import br.com.bloder.blormlib.validation.Validate;
import br.com.bloder.blormlib.validation.Validation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    /**
     *
     * Activity with your typical username and password fields (and a monkey)
     *
     * Espresso tests will try to log in with various invalid usernames and passwords
     * and await the correct error message
     *
     * The real magic happens in LoginValidationTest.java
     *
     */

    /**
     * Validate the username using this pattern
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @BindView(R.id.userNameInput)
    EditText userNameInput;
    @BindView(R.id.passwordInput)
    EditText passwordInput;
    @BindView(R.id.submitButton)
    Button submitButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.showMeTheMonkey)
    TextView showMeTheMonkey;

    @OnClick(R.id.showMeTheMonkey)
    public void setShowMeTheMonkey() {
        startMonkeyActivity();
    }

    private PasswordValidator passwordValidator;

    private PasswordValidator getPasswordValidator() {
        if (passwordValidator == null) {
            passwordValidator = new PasswordValidator.Builder()
                    .setMinimumLength(8)
                    .requireLowerCase(true)
                    .requireUpperCase(true)
                    .requireNumber(true)
                    .requireSpecialCharacter(false)
                    .build();
        }
        return passwordValidator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setValidator();                                     // set up validation for these fields
    }

    private void startMonkeyActivity() {
        Intent i = new Intent(this, MonkeyActivity.class);  // SHOW ME THE MONKEY
        startActivity(i);
        finish();
    }

    private void setValidator() {
        new Blorm.Builder().field(userNameInput).is(new Validation() {          // Experimental use of the Blorm library - A handy way to setup validation on a field. Worth a try
            @Override                                                           // https://github.com/daniel-martins-IR/Blorm
            public Validate validate() {
                return new Validate() {
                    @Override
                    public boolean validate() {
                        if (userNameInput.getText() != null && userNameInput.getText().length() > 0) {
                            final String input = userNameInput.getText().toString();
                            return VALID_EMAIL_ADDRESS_REGEX.matcher(input).find();
                        } else {
                            return false;
                        }
                    }

                    @Override
                    public void onError() {
                        final String errorString = getString(R.string.email_invalid);
                        DialogUtils.showGenericDialog(MainActivity.this, "Error ", errorString);
                    }

                    @Override
                    public void onSuccess() {
                        boolean isValid = getPasswordValidator().validate(passwordInput.getText().toString());
                        if (isValid) {
                            startMonkeyActivity();
                        } else {
                            showPasswordErrorDialog();
                        }
                    }
                };
            }
        }).submitOn(submitButton);
    }

    private void showPasswordErrorDialog() {
        String errorString;                                                         // Map the enum constants to a user-friendly error message
        Log.e("Espresso", "Input Error: " + passwordValidator.getError().name());
        switch (passwordValidator.getError()) {
            case TOO_SHORT:
                errorString = getString(R.string.password_error_too_short);
                break;
            case LOWERCASE_REQUIRED:
            case UPPERCASE_REQUIRED:
            case NUMBER_REQUIRED:
                errorString = getString(R.string.password_error_case);
                break;
            default:
                errorString = getString(R.string.unknown_error);
                break;
        }
        DialogUtils.showGenericDialog(MainActivity.this, "Error ", errorString);
    }

}
