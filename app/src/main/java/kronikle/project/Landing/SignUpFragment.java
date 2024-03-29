package kronikle.project.Landing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.Objects;

import kronikle.project.Main.MainActivity;
import kronikle.project.R;

public class SignUpFragment extends Fragment {

    private View view;
    private ConstraintLayout constraintLayout;
    private TextInputLayout textInputLayoutFirstName;
    private TextInputEditText editTextFirstName;
    private TextInputLayout textInputLayoutLastName;
    private TextInputEditText editTextLastName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputEditText editTextEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText editTextPassword;
    private Button buttonSignUp;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public SignUpFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_up_fragment, container, false);

        initializer();
        layoutFocus();
        buttonSignUpListener();

        return view;
    }

    private void initializer() {
        constraintLayout = view.findViewById(R.id.constraint_layout_SUF);
        textInputLayoutFirstName = view.findViewById(R.id.text_input_layout_first_name_SUF);
        editTextFirstName = view.findViewById(R.id.edit_text_first_name_SUF);
        textInputLayoutLastName = view.findViewById(R.id.text_input_layout_last_name_SUF);
        editTextLastName = view.findViewById(R.id.edit_text_last_name_SUF);
        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_SUF);
        editTextEmail = view.findViewById(R.id.edit_text_email_SUF);
        textInputLayoutPassword = view.findViewById(R.id.text_input_layout_password_SUF);
        editTextPassword = view.findViewById(R.id.edit_text_password_SUF);
        buttonSignUp = view.findViewById(R.id.button_sign_up_SUF);
    }

    // Hides Keyboard when switching fragments when open
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethod != null;
                inputMethod.hideSoftInputFromWindow(Objects.requireNonNull(getView()).getWindowToken(), 0);
                inputMethod.hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
            } catch (Exception e) {
                Log.e("Keyboard fragment", "Changing fragment causes crash");
            }
        }
    }

    // Hides Keyboard when user clicks outside EditText
    @SuppressLint("ClickableViewAccessibility")
    private void layoutFocus() {
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethod != null;
                Objects.requireNonNull(inputMethod).hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
                editTextFirstName.clearFocus();
                editTextLastName.clearFocus();
                editTextEmail.clearFocus();
                editTextPassword.clearFocus();
                return true;
            }
        });
    }

    private void buttonSignUpListener() {
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFirstName()) {
                    focusEditText(editTextFirstName);
                }

                else if (!validateLastName()) {
                    focusEditText(editTextLastName);
                }

                else if (!validateEmail()) {
                    focusEditText(editTextEmail);
                }

                else if (!validatePassword()) {
                    focusEditText(editTextPassword);
                }

                else {
                    //Code to check if the given email is already in the database
                    new StyleableToast
                            .Builder(Objects.requireNonNull(getContext()))
                            .text(getString(R.string.welcome))
                            .textColor(getResources().getColor(R.color.colorTextLight))
                            .backgroundColor(getResources().getColor(R.color.colorBackground))
                            .iconStart(R.drawable.icon_user_created)
                            .cornerRadius(2)
                            .length(6000)
                            .show();

                    Intent MainActivityIntent = new Intent(getActivity(), MainActivity.class);
                    startActivity(MainActivityIntent);
                    Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.enter_in_up, R.anim.exit_out_up);
                    getActivity().finish();
                }
            }
        });
    }

    private boolean validateFirstName() {
        firstName = editTextFirstName.getText().toString().trim();

        if (firstName.isEmpty()) {
            textInputLayoutFirstName.setError(getString(R.string.error_empty_field));
            return false;
        }

        else if (!firstName.matches("^[ A-Za-z'-]+$")) {
            textInputLayoutFirstName.setError(getString(R.string.error_first_name));
            return false;
        }

        else {
            textInputLayoutFirstName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateLastName() {
        lastName = editTextLastName.getText().toString().trim();

        if (lastName.isEmpty()) {
            textInputLayoutLastName.setError(getString(R.string.error_empty_field));
            return false;
        }

        else if (!lastName.matches("^[ A-Za-z'-]+$")) {
            textInputLayoutLastName.setError(getString(R.string.error_last_name));
            return false;
        }

        else {
            textInputLayoutLastName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            textInputLayoutEmail.setError(getString(R.string.error_empty_field));
            return false;
        }

        else if (!(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            textInputLayoutEmail.setError(getString(R.string.error_email));
            return false;
        }

        else {
            textInputLayoutEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        password = editTextPassword.getText().toString();

        if (password.isEmpty()) {
            textInputLayoutPassword.setError(getString(R.string.error_empty_field));
            return false;
        }

        else if (password.length() < 8) {
            textInputLayoutPassword.setError(getString(R.string.error_password));
            return false;
        }

        else {
            textInputLayoutPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void focusEditText(View view) {
        view.requestFocus();
        InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputMethod != null;
        Objects.requireNonNull(inputMethod).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
