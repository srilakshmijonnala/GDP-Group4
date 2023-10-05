import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a LinearLayout with vertical orientation
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(16, 16, 16, 16);
        linearLayout.setGravity(Gravity.CENTER);

        // EditText for Full Name
        EditText etFullName = new EditText(this);
        etFullName.setId(R.id.etFullName);
        etFullName.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        etFullName.setHint("Full Name");
        etFullName.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
        etFullName.setMarginBottom(16);
        linearLayout.addView(etFullName);

        // EditText for Email
        EditText etEmail = new EditText(this);
        etEmail.setId(R.id.etEmail);
        etEmail.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        etEmail.setHint("Email");
        etEmail.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        etEmail.setMarginBottom(16);
        linearLayout.addView(etEmail);

        // EditText for Password
        EditText etPassword = new EditText(this);
        etPassword.setId(R.id.etPassword);
        etPassword.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        etPassword.setHint("Password");
        etPassword.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPassword.setMarginBottom(16);
        linearLayout.addView(etPassword);

        // EditText for Confirm Password
        EditText etConfirmPassword = new EditText(this);
        etConfirmPassword.setId(R.id.etConfirmPassword);
        etConfirmPassword.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        etConfirmPassword.setHint("Confirm Password");
        etConfirmPassword.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etConfirmPassword.setMarginBottom(16);
        linearLayout.addView(etConfirmPassword);
      // Button for Register
        Button btnRegister = new Button(this);
        btnRegister.setId(R.id.btnRegister);
        btnRegister.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        btnRegister.setText("Register");
        btnRegister.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        btnRegister.setAllCaps(false);
        btnRegister.setTextColor(getResources().getColor(android.R.color.white));
        linearLayout.addView(btnRegister);

        // TextView for Terms and Conditions
        TextView tvTermsAndConditions = new TextView(this);
        tvTermsAndConditions.setId(R.id.tvTermsAndConditions);
        tvTermsAndConditions.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        tvTermsAndConditions.setText("I agree to the Terms and Conditions.");
        tvTermsAndConditions.setMarginTop(16);
        tvTermsAndConditions.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        linearLayout.addView(tvTermsAndConditions);

        // Set the content view to the LinearLayout
        setContentView(linearLayout);
    }
}
