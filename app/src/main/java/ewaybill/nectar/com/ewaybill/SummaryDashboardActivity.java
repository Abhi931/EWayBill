package ewaybill.nectar.com.ewaybill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SummaryDashboardActivity extends Activity implements View.OnClickListener {

    private ImageButton homeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);
        initView();
        initListener();

    }

    private void initView(){

        homeBtn=(ImageButton)findViewById(R.id.homeBtn);
    }

    private void initListener(){
        homeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.homeBtn:
                 Intent intent = new Intent(SummaryDashboardActivity.this, EwayBillRegistration.class);
                 startActivity(intent);
                 break;
         }
    }
}
