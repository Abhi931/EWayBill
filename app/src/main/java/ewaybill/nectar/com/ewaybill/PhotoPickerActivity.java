package ewaybill.nectar.com.ewaybill;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ewaybill.nectar.com.ewaybill.utils.AppConstants;

public abstract class PhotoPickerActivity extends AppCompatActivity {
    public static final String TAG = PhotoPickerActivity.class.getCanonicalName();
    private static final int PHOTO_PICKING_CODE = 211;
    private static final int REQUEST_PERMISSIONS_CODE = 212;
    private String[] requiredPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private File mImageF;
    private String mChooserTitle = "Please choose an image";


    protected void initImagePickProcess(Activity activity, @Nullable String chooserTitle) {
        if (chooserTitle != null) mChooserTitle = chooserTitle;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(
                        activity,
                        "Please grant access to these permissions in order to edit your Image",
                        Toast.LENGTH_SHORT).show();
            }
            requestPermissions();
        } else {
            initPhotoPickerIntent();
        }
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, requiredPermissions, REQUEST_PERMISSIONS_CODE);
    }


    private void initPhotoPickerIntent() {
        try {
            List<Intent> matchingIntents = new ArrayList<>();
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
          //  Intent galleryIntent = new Intent("android.intent.action.MULTIPLE_PICK");
            galleryIntent.setType("image/*");
            galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

            String authorityName = getApplicationContext().getPackageName() + ".provider";
            mImageF = createTempImageFile(this);
            Log.d("mImageF",""+mImageF);
            Uri imageUri = FileProvider.getUriForFile(this, authorityName, mImageF);

            for (ResolveInfo info : getPackageManager().queryIntentActivities(cameraIntent, 0)) {
                Intent intent = new Intent(cameraIntent);
                intent.setPackage(info.activityInfo.packageName);
                intent.setComponent(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
                if (imageUri != null) intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                matchingIntents.add(intent);
            }

            for (ResolveInfo info : getPackageManager().queryIntentActivities(galleryIntent, 0)) {
                Intent intent = new Intent(galleryIntent);
                intent.setPackage(info.activityInfo.packageName);
                intent.setComponent(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
                matchingIntents.add(intent);
            }

            for (Intent intent : matchingIntents) {
                if (intent.getComponent() != null && intent.getComponent().getClassName().equalsIgnoreCase(AppConstants.ANDROID_DOCUMENTS_ACTIVITY)) {
                    matchingIntents.remove(intent);
                    break;
                }
            }

            Intent photoPickingIntent = Intent.createChooser(matchingIntents.get(matchingIntents.size() - 1), mChooserTitle);
            photoPickingIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, matchingIntents.toArray(new Parcelable[matchingIntents.size()]));
            startActivityForResult(photoPickingIntent, PHOTO_PICKING_CODE);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                initPhotoPickerIntent();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        switch (requestCode) {
            case PHOTO_PICKING_CODE:

                // a gallery image
                if (data != null && data.getData() != null) {
                    onNewImagePickSuccess(data.getData(), mImageF);
                } else {
                    if(data!=null) {
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        // a camera image
                        if (data.getClipData() != null) {
                            ClipData mClipData = data.getClipData();
                            ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                            for (int i = 0; i < mClipData.getItemCount(); i++) {

                                ClipData.Item item = mClipData.getItemAt(i);
                                Uri uri = item.getUri();
                                mArrayUri.add(uri);
                                // Get the cursor
                                Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                                // Move to first row
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String imageEncoded = cursor.getString(columnIndex);
                                Log.d("imageEncoded", imageEncoded);
                                InvoiceEWayBillActivity.imagesEncodedList.add(imageEncoded);
                                cursor.close();
                                File file = new File(imageEncoded);
                                onNewImagePickSuccess(FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file), file);
                            }
                            Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                        }
                    }
                    else
                    {
                        onNewImagePickSuccess(FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", mImageF), mImageF);
                    }
                  //  onNewImagePickSuccess(FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", mImageF), mImageF);
                }
                break;
        }
    }


    protected abstract void onNewImagePickSuccess(Uri uri, File tempFile);

    public static File createTempImageFile(Context context) {
        String timestamp = new SimpleDateFormat("ddMMyyyy_HH:mm:ss", Locale.getDefault()).format(new Date());
        String fileName = "Nectar_" + timestamp;
        File storageDir = context.getExternalCacheDir();
        File imageFile = null;
        try {
            imageFile = File.createTempFile(
                    fileName,
                    ".jpg",
                    storageDir);
        } catch (IOException e) {
          //  LoggerUtils.logD(TAG, e.getMessage());
        }

        if (imageFile != null && !imageFile.exists()) imageFile.mkdirs();
        return imageFile;
    }
}
