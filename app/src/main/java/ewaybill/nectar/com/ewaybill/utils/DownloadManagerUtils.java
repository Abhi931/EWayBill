package ewaybill.nectar.com.ewaybill.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import ewaybill.nectar.com.ewaybill.R;

public class DownloadManagerUtils {

    private static final String TAG = DownloadManagerUtils.class.getSimpleName();
    private final Context mContext;
    public final Uri mDownloadUri;
    public static ArrayList<Long> list = new ArrayList<>();
    public DownloadManager mDownloadManager;
    private long refid;

    public DownloadManagerUtils(Context context, Uri uri){
        this.mContext = context;
        this.mDownloadUri = uri;
        isStoragePermissionGranted();
    }

    public  boolean isStoragePermissionGranted() {
        try{
            if (Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(mContext,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else {

                    ActivityCompat.requestPermissions((Activity)mContext,
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    return false;
                }
            }
            else { //permission is automatically granted on sdk<23 upon installation
                return true;
            }
        }
        catch (Exception e){
            Log.d(TAG, e.getMessage());
        }

        return true;
    }


    public void downloadSingleFile(){
        try {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.clear();

            String fileName = mDownloadUri.toString().substring(mDownloadUri.toString().lastIndexOf('/') + 1);
            mDownloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(mDownloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setAllowedOverRoaming(false);
            request.setMimeType(FileUtils.getMimeType1(mDownloadUri.toString()));
            request.setTitle(fileName);
            request.setDescription("Downloading attachment..");
            request.setVisibleInDownloadsUi(true);
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);


            refid = mDownloadManager.enqueue(request);
            Log.e("OUT", "" + refid);

            list.add(refid);
        }
        catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }

    public void downloadMultipleFile(){
        try{
            list.clear();

            for(int i = 0; i < 2; i++)
            {
                DownloadManager.Request request = new DownloadManager.Request(mDownloadUri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);
                request.setTitle("GadgetSaint Downloading " + "Sample_" + i + ".png");
                request.setDescription("Downloading " + "Sample_" + i + ".png");
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/GadgetSaint/"  + "/" + "Sample_" + i + ".png");

                refid = mDownloadManager.enqueue(request);

                Log.e("OUTNM", "" + refid);

                list.add(refid);

            }
        }
        catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }

    /**
     * Used to open the downloaded attachment.
     *
     * @param context    Content.
     * @param downloadId Id of the downloaded file to open.
     */
    public static void openDownloadedAttachment(final Context context, final long downloadId) {
        try {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            Cursor cursor = downloadManager.query(query);
            if (cursor.moveToFirst()) {
                int downloadStatus = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                String downloadLocalUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                String downloadMimeType = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_MEDIA_TYPE));
                if ((downloadStatus == DownloadManager.STATUS_SUCCESSFUL) && downloadLocalUri != null) {
                    openDownloadedAttachment(context, Uri.parse(downloadLocalUri), downloadMimeType);
                }
            }
            cursor.close();
        }
        catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }

    private static void openDownloadedAttachment(final Context context, Uri attachmentUri, final String attachmentMimeType) {
        try{
            if(attachmentUri!=null) {
                // Get Content Uri.
                if (ContentResolver.SCHEME_FILE.equals(attachmentUri.getScheme())) {
                    // FileUri - Convert it to contentUri.
                    File file = new File(attachmentUri.getPath());
                    attachmentUri = FileProvider.getUriForFile(context, "com.tagtaste.android.provider", file);
                }

                Intent openAttachmentIntent = new Intent(Intent.ACTION_VIEW);
                openAttachmentIntent.setDataAndType(attachmentUri, attachmentMimeType);
                openAttachmentIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    context.startActivity(openAttachmentIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, context.getString(R.string.unable_to_open_file), Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }

}
