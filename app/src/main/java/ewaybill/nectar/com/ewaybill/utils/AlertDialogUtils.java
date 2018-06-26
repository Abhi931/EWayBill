package ewaybill.nectar.com.ewaybill.utils;

import android.content.Context;
import android.view.View;

import ewaybill.nectar.com.ewaybill.InvoiceEWayBillActivity;
import ewaybill.nectar.com.ewaybill.R;

public class AlertDialogUtils {



    public static void showDiscardDialog(final Context context
            , final String title, String message, String positiveButtonTitle, String negativeButtonTitle) {
        final MaterialDialog dialog = new MaterialDialog(context);


        dialog.setTitle(title)
                .setMessage(message)
                //Use this for a custom layout resource
                .setCustomViewResource(R.layout.dialog_layout_base)
                //Or pass the View
                //.setCustomView(yourView);
                //Set cancelable on touch outside (default true)
                //.dismissOnTouchOutside(false)
                .setupPositiveButton(positiveButtonTitle, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (context instanceof InvoiceEWayBillActivity) {
                            PermissionUtils.launchAppSettings(context);
                            dialog.dismiss();
                        }
                    }
                }).setupNegativeButton(negativeButtonTitle, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
