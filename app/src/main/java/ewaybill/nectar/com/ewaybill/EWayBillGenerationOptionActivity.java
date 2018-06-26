package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class EWayBillGenerationOptionActivity extends Activity {

    private Button btnSubmitInvoice;
    private Button btnGenerateEwayBill;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_ewaybaill_generation_layout);
        btnSubmitInvoice=(Button)findViewById(R.id.btnSubmitInvoice);
        btnSubmitInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EWayBillGenerationOptionActivity.this, CameraActivity.class);
                startActivity(intent);

            }
        });
        btnGenerateEwayBill=(Button)findViewById(R.id.btnGenerateEwayBill);
        btnGenerateEwayBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EWayBillGenerationOptionActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }
}
