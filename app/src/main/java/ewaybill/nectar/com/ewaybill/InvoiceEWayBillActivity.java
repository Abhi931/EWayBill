package ewaybill.nectar.com.ewaybill;

/**
 * Created by Abhishek on 4/18/2018.
 */

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;*/

import com.google.gson.JsonObject;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import ewaybill.nectar.com.ewaybill.adapter.PdfAdapter;
import ewaybill.nectar.com.ewaybill.model.Pdf;
import ewaybill.nectar.com.ewaybill.retrofit.CallbackWithRetry;
import ewaybill.nectar.com.ewaybill.utils.AlertDialogUtils;
import ewaybill.nectar.com.ewaybill.utils.AppConstants;
import ewaybill.nectar.com.ewaybill.utils.FileUtils;
import ewaybill.nectar.com.ewaybill.utils.GalleryCameraUtils;
import ewaybill.nectar.com.ewaybill.utils.PermissionUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class InvoiceEWayBillActivity extends PhotoPickerActivity implements View.OnClickListener {

    //Declaring views
    private Button buttonChoose;
    private ImageButton buttonUpload;

    private TextView editText;

    public static final String UPLOAD_URL = "http://internetfaqs.net/AndroidPdfUpload/upload.php";
    public static final String PDF_FETCH_URL = "http://internetfaqs.net/AndroidPdfUpload/getPdfs.php";

    ImageView imageView;
    private ImageView imageHolder;
    public final int requestCode = 20;
    //Image request code
    private int PICK_PDF_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;


    //Uri to store the image uri
    private Uri filePath;
    private Uri filePath1;

    //ListView to show the fetched Pdfs from the server
    ListView listView,filelist;

    //button to fetch the intiate the fetching of pdfs.
    Button buttonFetch;

    //Progress bar to check the progress of obtaining pdfs
    ProgressDialog progressDialog;

    //an array to hold the different pdf objects
    ArrayList<Pdf> pdfList = new ArrayList<Pdf>();

    public static ArrayList<String> imagesEncodedList=new ArrayList<>();

    //pdf adapter

    PdfAdapter pdfAdapter;
    private Button uploadInvoiceFromSystem;
    private Button takeInvoicePicture;
    private Button scanInvoicePicture;
    private File mFinalFile = null;
    private String loginUserId;
    private LinearLayout constraintLayout;
    private Button nextButton;
    private ImageButton homeBtn;
    ArrayList<String> filePaths=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_invoice_details);

        //Requesting storage permission
        // requestStoragePermission();

        Intent i=getIntent();
        loginUserId= i.getStringExtra("USERID");

        //Initializing views
        constraintLayout=(LinearLayout)findViewById(R.id.constraintLayout);
        // buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (ImageButton) findViewById(R.id.buttonUpload);
        takeInvoicePicture=(Button)findViewById(R.id.takeInvoicePicture);
        scanInvoicePicture=(Button)findViewById(R.id.scanInvoicePicture);
        //   uploadInvoiceFromSystem=(Button) findViewById(R.id.buttonUpload);
        editText = (TextView) findViewById(R.id.editTextName);
        nextButton=(Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
        homeBtn=(ImageButton)findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);
        //initializing ListView
        listView = (ListView) findViewById(R.id.listView);
        filelist=(ListView) findViewById(R.id.filelist);
        filelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(imagesEncodedList.get(position).contains("jpg")||imagesEncodedList.get(position).contains("png"))
                {
                Bitmap bitmap = BitmapFactory.decodeFile(imagesEncodedList.get(position));
                bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);

                final Dialog dialog = new Dialog(InvoiceEWayBillActivity.this);
                dialog.setContentView(R.layout.openfile);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button

                ImageView image = (ImageView) dialog.findViewById(R.id.openfile);

                image.setImageBitmap(bitmap);
                Button dialogButton = (Button) dialog.findViewById(R.id.cancel);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();}
                else
                {
                    File file = new File(imagesEncodedList.get(position));
                    try {
                        openFile(InvoiceEWayBillActivity.this,file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        //initializing buttonFetch
        buttonFetch = (Button) findViewById(R.id.buttonFetchPdf);

        //initializing progressDialog

        progressDialog = new ProgressDialog(this);

        //Setting clicklistener
        // buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        // buttonFetch.setOnClickListener(this);
        //  uploadInvoiceFromSystem.setOnClickListener(this);
        takeInvoicePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent photoImageIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoImageIntent,requestCode);*/
                if(imagesEncodedList.size()<=5)
                {
                    FilePickerBuilder.getInstance().setMaxCount(5)
                            .setSelectedFiles(imagesEncodedList)
                            .setActivityTheme(R.style.AppTheme)
                            .pickDocument(InvoiceEWayBillActivity.this);
                }
                else
                {
                    Snackbar.make(constraintLayout, "You can upload maximum 6 Invoices", Snackbar.LENGTH_LONG).show();
                }

                /*if (PermissionUtils.checkAndRequestStoragePermissions(InvoiceEWayBillActivity.this)) {
                    GalleryCameraUtils.pdf(InvoiceEWayBillActivity.this);
                }*/
            }
        });


        scanInvoicePicture.setOnClickListener(this);

       /* //setting listView on item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pdf pdf = (Pdf) parent.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(pdf.getUrl()));
                startActivity(intent);

            }
        });*/
    }

    /*

     * This is the method responsible for pdf upload
     * We need the full pdf path and the name for the pdf in this method
     * */
    public void openFile(Context context, File url) throws IOException {
        // Create URI
        File file=url;
       // Uri uri = Uri.fromFile(file);
        Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // Check what kind of file you are trying to open, by comparing the url with extensions.
        // When the if condition is matched, plugin sets the correct intent (mime) type,
        // so Android knew what application to use to open the file
        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if(url.toString().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if(url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if(url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if(url.toString().contains(".zip") || url.toString().contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/x-wav");
        } else if(url.toString().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if(url.toString().contains(".wav") || url.toString().contains(".mp3")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav");
        } else if(url.toString().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if(url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg");
        } else if(url.toString().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if(url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            //if you want you can also define the intent type for any other file

            //additionally use else clause below, to manage other unknown extensions
            //in this case, Android will show all applications installed on the device
            //so you can choose which application to use
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (intent.resolveActivity(InvoiceEWayBillActivity.this.getPackageManager()) != null) {
            InvoiceEWayBillActivity.this.startActivity(intent);
        }
      //  startActivity(intent);
    }

    public void uploadMultipart() {
        //getting name for the pdf
        String name = editText.getText().toString().trim();

        //getting the actual path of the pdf
        String path = FilePath.getPath(this, filePath);

        if (path == null) {

            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        } else {
            //Uploading code
            try {
                String uploadId = UUID.randomUUID().toString();

                //Creating a multi part request
                new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                        .addFileToUpload(path, "pdf") //Adding file
                        .addParameter("name", name) //Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload

            } catch (Exception exc) {
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //method to show file chooser
    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);
    }

    //handling the ima chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== FilePickerConst.REQUEST_CODE)
        if(resultCode==RESULT_OK && data!=null)
        {
            imagesEncodedList = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
            Log.d("filespath", String.valueOf(imagesEncodedList.size()));
            filelist.setAdapter(new FileAdapter(InvoiceEWayBillActivity.this,InvoiceEWayBillActivity.imagesEncodedList));
        if(imagesEncodedList.size()>0)
        {
            StringBuilder sb = new StringBuilder();
            String s=null;
            for (int i=0;i<imagesEncodedList.size();i++)
            {

                String docfile=imagesEncodedList.get(i);
                File file = new File(docfile);

                sb.append(file.getName());
                sb.append("\n");
                // setSelectedImage(file);
                s=sb.toString();
            }
            editText.setText(s);
        }

            //use them anywhere
        }
        else
        if(requestCode == AppConstants.REQUEST_OPEN_DOCUMENTS_FOLDER) {
            if (resultCode == RESULT_OK) {

                if (data != null) {
                    Uri uri = data.getData();
                    File f = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        InputStream is = null;
                        try {
                            is = getContentResolver().openInputStream(uri);
                            File tempFile = FileUtils.createTempPdfFile(this);
                            org.apache.commons.io.FileUtils.copyInputStreamToFile(is, tempFile);
                            setSelectedImage(tempFile);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        else if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            //File f = new File(filePath.toString());
            String filename=(filePath.toString()).substring((filePath.toString()).lastIndexOf("/")+1);
            File file = new File(String.valueOf(filePath));
            String strFileName = file.getName();
            editText.setText(filename);




        } if(this.requestCode == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageHolder.setImageBitmap(bitmap);
        }
    }

    /*//Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }*/

    @Override
    protected void onNewImagePickSuccess(Uri uri, File tempFile) {
      /*  String absoluteFilePath = GalleryCameraUtils.getRealPathFromURI(uri, this);
        setSelectedImage(absoluteFilePath != null && !absoluteFilePath.isEmpty()
                ? new File(absoluteFilePath) : tempFile);*/

      if(InvoiceEWayBillActivity.imagesEncodedList.size()>0) {

          filelist.setAdapter(new FileAdapter(InvoiceEWayBillActivity.this,InvoiceEWayBillActivity.imagesEncodedList));
          StringBuilder sb = new StringBuilder();
          String s=null;
          for (String element : InvoiceEWayBillActivity.imagesEncodedList) {
              String prefix = "";
              File file = new File(element);

              sb.append(file.getName());
              sb.append("\n");
              // setSelectedImage(file);
               s=sb.toString();


          }
          editText.setText(s);

      }
      else
      {
          String absoluteFilePath = GalleryCameraUtils.getRealPathFromURI(uri, this);
          setSelectedImage(absoluteFilePath != null && !absoluteFilePath.isEmpty()
                  ? new File(absoluteFilePath) : tempFile);
      }
    }

    public void setSelectedImage(File absolutePath) {
        if (absolutePath != null) {
            mFinalFile = absolutePath;
            editText.setText(absolutePath.getName());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == nextButton) {
            Intent intent=new Intent(InvoiceEWayBillActivity.this,SubmitInvoiceActivity.class);
            intent.putExtra("file",mFinalFile);
            startActivity(intent);
        }

      /*   if (v == buttonUpload) {
            uploadMultipart();
        }

        if (v == buttonFetch) {
            // getPdfs();
        }

        if (v == scanInvoicePicture) {

        }*/


        if (v == buttonUpload) {
            // uploadMultipart();
        }

       /* if (v == buttonFetch) {
            // getPdfs();
        }*/

        if (v == scanInvoicePicture) {
            if(imagesEncodedList.size()<=5)
            {
                initImagePickProcess(this, null);
            }
            else
            {
                Snackbar.make(constraintLayout, "You can upload maximum 6 Invoices", Snackbar.LENGTH_LONG).show();
            }


        }
    }

/* private void getPdfs() {

        progressDialog.setMessage("Fetching Pdfs... Please Wait");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PDF_FETCH_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(MainActivity.this,obj.getString("message"), Toast.LENGTH_SHORT).show();

                            JSONArray jsonArray = obj.getJSONArray("pdfs");

                            for(int i=0;i<jsonArray.length();i++){

                                //Declaring a json object corresponding to every pdf object in our json Array
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //Declaring a Pdf object to add it to the ArrayList  pdfList
                                Pdf pdf  = new Pdf();
                                String pdfName = jsonObject.getString("name");
                                String pdfUrl = jsonObject.getString("url");
                                pdf.setName(pdfName);
                                pdf.setUrl(pdfUrl);
                                pdfList.add(pdf);

                            }

                            pdfAdapter=new PdfAdapter(MainActivity.this,R.layout.list_layout, pdfList);

                            listView.setAdapter(pdfAdapter);

                            pdfAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);

    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        try {
            switch (requestCode) {
                case PermissionUtils.PERMISSION_READ_STORAGE: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // Permission Granted
                        // Launch file selector
                        GalleryCameraUtils.openDocumentsFolder(this);

                    } else {
                        // Permission Denied
                        boolean showRationale = false;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                            // Compare if 'Don't ask again' has been checked
                            if (!showRationale) {
                                // Launch Alert Dialogue to open settings
                                AlertDialogUtils.showDiscardDialog(this, getString(R.string.alert_storage_permission_title),
                                        getString(R.string.alert_storage_permission_message), getString(R.string.go_to_settings), getString(R.string.cancel));
                            } else {
                                // Launch Snackbar if deny was pressed
                                Toast.makeText(this, getString(R.string.snackbar_storage_permission_message), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
            }
        }
        catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }

}