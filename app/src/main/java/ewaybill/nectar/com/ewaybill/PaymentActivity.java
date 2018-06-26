package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PaymentActivity extends Activity {
 TextView totalamount;
 EditText textInputEditTextamount;
    AppCompatButton add,pay;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);


    }
}
