package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ewaybill.nectar.com.ewaybill.presenter.presenterImpl.LoginPresenterImpl;
import ewaybill.nectar.com.ewaybill.viewstate.LoginView;

import static ewaybill.nectar.com.ewaybill.localDB.Constants.*;

/**
 * Created by Abhishek on 4/6/2018.
 */

public class LoginActivity extends Activity implements LoginView{

    private EditText ePassword;
    private EditText eUserName;
    private EditText eGstin;
    private Button eLogin;
    private Button btnRegister;
    private String name;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
         initXMLResources();

    }

    private void initXMLResources(){
        eUserName=(EditText)findViewById(R.id.userEditText);
        ePassword=(EditText)findViewById(R.id.passwordEditText);
        eGstin=(EditText)findViewById(R.id.gstinEditText);
        eLogin=(Button)findViewById(R.id.btnLogin);
        btnRegister=(Button)findViewById(R.id.btnRegister);

        /*eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // name.equalsIgnoreCase(userName) || password.equalsIgnoreCase(password)
                name = eUserName.getText().toString();
                password = ePassword.getText().toString();

                if(name.equalsIgnoreCase("Admin") || password.equalsIgnoreCase("admin")) {
                    Toast.makeText(LoginActivity.this, "Login Sussessfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, EwayBillDashboardActivity.class);
                    intent.putExtra("USERNAME", eUserName.getText().toString());
                    intent.putExtra("GSTIN", eGstin.getText().toString());
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), "User name and Password not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luserName=eUserName.getText().toString();
                String lpassword=ePassword.getText().toString();

                if((luserName == null || luserName.isEmpty()) && (lpassword == null || lpassword.isEmpty())){
                    Toast.makeText(LoginActivity.this, "Please enter the username and password", Toast.LENGTH_LONG).show();
                }
                if(luserName == null || luserName.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter the username", Toast.LENGTH_LONG).show();
                }
                 if(lpassword == null || lpassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter the password", Toast.LENGTH_LONG).show();
                }

                else{
                    String where = COL_RUSER_NAME + "=?";
                    String[] whereclause = new String[]{luserName};
                    String projection[] = {COL_REGISTRATION_ID, COL_RUSER_NAME, COL_RUSER_PASSWORD};
                    Cursor cursor = getContentResolver().query(CONTENT_URI_REGISTER_EWAYBILL, projection, where, whereclause, null);
                    if(cursor.getCount()<1) // UserName Not Exist
                    {
                        cursor.close();
                        Toast.makeText(LoginActivity.this, "Please enter the correct User Name and Passward", Toast.LENGTH_LONG).show();

                        return;
                    }
                    cursor.moveToFirst();

                    String username = cursor.getString(cursor.getColumnIndex(COL_RUSER_NAME));
                    String password = cursor.getString(cursor.getColumnIndex(COL_RUSER_PASSWORD));
                    cursor.close();

                    if(lpassword.equalsIgnoreCase(password)){
                        Intent i = new Intent(LoginActivity.this,ClientActivity.class);
                        startActivity(i);
                        Toast.makeText(LoginActivity.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
                    }
                    else if(!lpassword.equalsIgnoreCase(password)){
                        Toast.makeText(LoginActivity.this, "your password is wrong. Please enter the correct password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });




        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterPageActivity.class);

                startActivity(intent);
            }
        });
    }


    /*private void initUI(){

        mLoginBtn = (ImageButton) rootView.findViewById(R.id.loginBtn);
        forgotLoginBtn = (ImageButton) rootView.findViewById(R.id.forgotLoginBtn);

        //usernameEditText = (EditText) rootView.findViewById(R.id.usernameEditText);
        //passwordEditText = (EditText) rootView.findViewById(R.id.passwordEditText);

        usernameEditText = (InputTextBox) rootView.findViewById(R.id.usernameEditText);
        passwordEditText = (InputTextBox) rootView.findViewById(R.id.passwordEditText);
        passwordEditText.setOnClickListener(this);

        usernameEditText.setOnClickListener(this);
        mLoginCheckboxRemember = (CheckBox) rootView.findViewById(R.id.loginCheckboxRemember);

        mLoginCheckboxRemember.setOnCheckedChangeListener(this);

        mLoginBtn.setOnClickListener(this);
        forgotLoginBtn.setOnClickListener(this);

    }
*/



    private void initAPIResources(){

        LoginPresenterImpl loginPresenter= new LoginPresenterImpl(this);
        loginPresenter.callApi(eUserName.getText().toString(),ePassword.getText().toString(),eGstin.getText().toString());
    }


    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure() {

    }
}
