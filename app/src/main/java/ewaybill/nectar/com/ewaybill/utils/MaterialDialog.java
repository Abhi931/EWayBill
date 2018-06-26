package ewaybill.nectar.com.ewaybill.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import ewaybill.nectar.com.ewaybill.R;


public class MaterialDialog extends AlertDialog {

    private Context mContext;
    private TextView mTitleTextView;
    private TextView mContent;
    private Button mPositive;
    private Button mNegative;
    private FrameLayout mCustomContainer;
    private ScrollView mScrollText;

    private String title;
    private String contentText;
    private View customView;
    private @LayoutRes
    Integer customResId;
    private int drawableID = -1;

    private Button.OnClickListener mPositiveClickListener;
    private Button.OnClickListener mNegativeClickListener;
    private TextView.OnClickListener mTitleClickListener;

    private String positiveText;
    private String negativeText;
    private boolean canDismiss = true;
    private ImageView mDialogImageView;

    public MaterialDialog(Context context) {
        super(context);
        this.mContext = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_layout_base);
        mDialogImageView = (ImageView) findViewById(R.id.dialogImageView);
        mTitleTextView = (TextView) findViewById(R.id.dialog_title);
        mContent = (TextView) findViewById(R.id.dialog_message);
        mCustomContainer = (FrameLayout) findViewById(R.id.content);
        mPositive = (Button) findViewById(R.id.dialog_positive_button);
        mNegative = (Button) findViewById(R.id.dialog_negative_button);
        mScrollText = (ScrollView) findViewById(R.id.scrolltext);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (title != null) {
            mTitleTextView.setText(title);
        } else {
            mTitleTextView.setVisibility(View.GONE);
        }

        if (contentText != null) {
            mContent.setText(contentText);
        } else {
            mContent.setVisibility(View.GONE);
            mScrollText.setVisibility(View.GONE);
        }

        if (customView != null && customResId == null) {
            mCustomContainer.addView(customView);
        } else if (customView == null && customResId != null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView = inflater.inflate(customResId, null, false);
            mCustomContainer.addView(customView);
        } else if (customView == null && customResId == null) {
            mContent.setVisibility(View.GONE);
        }

        if (positiveText != null && mPositiveClickListener != null) {
            mPositive.setText(positiveText);
            mPositive.setOnClickListener(mPositiveClickListener);
        } else {
            mPositive.setVisibility(View.GONE);
        }

        if (negativeText != null && mNegativeClickListener != null) {
            mNegative.setText(negativeText);
            mNegative.setOnClickListener(mNegativeClickListener);
        } else {
            mNegative.setVisibility(View.GONE);
        }

        if (mTitleTextView != null && mTitleClickListener != null) {
            mTitleTextView.setOnClickListener(mTitleClickListener);
        }

        if(drawableID != -1){
            mDialogImageView.setVisibility(View.VISIBLE);
            mDialogImageView.setImageResource(drawableID);
        }
        else {
            mDialogImageView.setVisibility(View.GONE);
        }
        this.setCanceledOnTouchOutside(canDismiss);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    public MaterialDialog setTitle(String t) {
        this.title = t;
        return this;
    }

    public MaterialDialog setupTitleButton(String t, TextView.OnClickListener listener) {
        this.title = t;
        this.mTitleClickListener = listener;
        return this;
    }

    public MaterialDialog setMessage(String m) {
        this.contentText = m;
        return this;
    }

    public MaterialDialog setupPositiveButton(String text, Button.OnClickListener listener) {
        this.positiveText = text;
        this.mPositiveClickListener = listener;
        return this;
    }

    public MaterialDialog setupNegativeButton(String text, Button.OnClickListener listener) {
        this.negativeText = text;
        this.mNegativeClickListener = listener;
        return this;
    }

    public MaterialDialog setCustomView(View v) {
        this.customView = v;
        return this;
    }

    public MaterialDialog setCustomViewResource(int ResId) {
        this.customResId = ResId;
        return this;
    }

    public MaterialDialog dismissOnTouchOutside(boolean dismiss) {
        this.canDismiss = dismiss;
        return this;
    }

    public MaterialDialog setDrawableID(int drawableID) {
        this.drawableID = drawableID;
        return this;
    }

    public View getCustomView() {
        return this.customView;
    }


    public static class Builder {
        private Context context;
        private String title;
        private String contentText;
        private @Nullable
        View customView;
        private @LayoutRes
        Integer customResId;
        private @DrawableRes
        int drawableID = -1;
        private String positiveText;
        private String negativeText;
        private boolean canDismiss = true;
        private Button.OnClickListener onPositiveClickListener;
        private Button.OnClickListener onNegativeClickListener;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public Builder setCustomView(View customView) {
            this.customView = customView;
            return this;
        }

        public Builder setLayoutResId(@LayoutRes Integer customResId) {
            this.customResId = customResId;
            return this;
        }

        public Builder setDrawableID(int drawableID) {
            this.drawableID = drawableID;
            return this;
        }

        public Builder setPositiveButton(String positiveText, Button.OnClickListener onPositiveClickListener) {
            this.positiveText = positiveText;
            this.onPositiveClickListener = onPositiveClickListener;
            return this;
        }

        public Builder setNegativeButton(String negativeText, Button.OnClickListener onNegativeClickListener) {
            this.negativeText = negativeText;
            this.onNegativeClickListener = onNegativeClickListener;
            return this;
        }

        public Builder setCanDismiss(boolean canDismiss) {
            this.canDismiss = canDismiss;
            return this;
        }

        public MaterialDialog createInstance() {
            return new MaterialDialog(context);
        }
    }
}