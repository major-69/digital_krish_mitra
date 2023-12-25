package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import digi.coders.digitalkrishimitraa.Helper.CameraUtils;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.databinding.ActivityApplyJobBinding;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Apply_Job_Activity extends AppCompatActivity {

    ActivityApplyJobBinding binding;
    Uri selectedFileUri;
    public static final int  PICK_PDF_REQUEST=123;
    String userid,jobid;

    File storefile=null;
    public static final int MEDIA_TYPE_PDF = 3,SELECT_PDF = 1;;
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";
    String DOC_TYPE="",realpath,pdfstr="";
    private static String imageStoragePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityApplyJobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Apply_Job_Activity.this);
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();

        jobid=getIntent().getStringExtra("jobid").toString();

        binding.imagedemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                pdf();

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
//                if(ContextCompat.checkSelfPermission(Apply_Job_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
//                {
//                    ActivityCompat.requestPermissions(Apply_Job_Activity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},100);
//
//                }
//                else {
////                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
////                    intent.setType("application/pdf");
////                    startActivityForResult(intent, PICK_PDF_REQUEST);
//                  pdf();
//
//                }

            }
        });


    }

    private void pdf(){
        DOC_TYPE="pdf";
        imageStoragePath = filePathPdf();

        Uri fileUri = CameraUtils.getOutputMediaFileUri(Apply_Job_Activity.this, storefile);

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);
    }
    private String filePathPdf(){
        ContextWrapper contextWrapper=new ContextWrapper(Apply_Job_Activity.this);
        File pdfile=contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        storefile =new File(pdfile,"MYPDF.pdf");
        return  storefile.getPath();

    }

    private void pdfatteched(Intent data) {
        CameraUtils.refreshGallery(Apply_Job_Activity.this, imageStoragePath);

        dumpImageMetaData(data.getData());

        try {
            String id = DocumentsContract.getDocumentId(data.getData());
            InputStream inputStream = getContentResolver().openInputStream(data.getData());

            File file = new File(getCacheDir().getAbsolutePath()+"/"+id);
            writeFile(inputStream, file);
            String filePath = file.getAbsolutePath();

            Log.e("PDF_STRING",filePath);
            pdfstr=getBase64FromPath(filePath);
//            image_uploadwomework.setImageResource(R.drawable.ic_pdf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dumpImageMetaData(Uri uri) {

        Cursor cursor = Apply_Job_Activity.this.getContentResolver()
                .query(uri, null, null, null, null, null);

        try {

            if (cursor != null && cursor.moveToFirst()) {

                @SuppressLint("Range") String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                @SuppressLint("Range") String displaypath = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Log.i("TAG", "Display Name: " + displayName);
                Log.i("TAG@123", "Display Path: " + displaypath);
//                text_filename.setText(displayName);
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);

                String size = null;
                if (!cursor.isNull(sizeIndex)) {

                    size = cursor.getString(sizeIndex);
                } else {
                    size = "Unknown";
                }
                Log.i("TAG", "Size: " + size);
            }
        } finally {
            cursor.close();
        }
    }
    void writeFile(InputStream in, File file) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if ( out != null ) {
                    out.close();
                }
                in.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
    public static String getBase64FromPath(String path) {
        String base64 = "";
        try {
            File file = new File(String.valueOf(path));
            byte[] buffer = new byte[(int) file.length() + 100];
            @SuppressWarnings("resource")
            int length = new FileInputStream(file).read(buffer);
            base64 = Base64.encodeToString(buffer, 0, length,
                    Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }



    public void back(View view) {finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedFileUri = data.getData();

            String pdfName = getFileNameFromUri(selectedFileUri);

            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedFileUri);
                if (inputStream != null) {
                    pdfatteched(data);

                    binding.pdf.setVisibility(View.VISIBLE);
                    binding.imagedemo.setVisibility(View.GONE);
                    binding.nameofpdf.setText(pdfName);
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @SuppressLint("Range")
    private String getFileNameFromUri(Uri uri) {
        String displayName = null;
        try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return displayName;
    }

    public void finish(View view) {
        if (validateRegistrationform()){
            if (pdfstr==null||pdfstr.equalsIgnoreCase("")){
                Toast.makeText(Apply_Job_Activity.this, "Select PDF", Toast.LENGTH_SHORT).show();
            }
            else {
                applyjob();
            }


        }
    }

    private boolean validateRegistrationform() {
        boolean status=true;
        if(TextUtils.isEmpty(binding.inputName.getText())){
            binding.inputName.setError("Applicant Name required");
            binding.inputName.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.mobile.getText())){
            binding.mobile.setError("Mobile no. required");
            binding.mobile.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.email.getText())){
            binding.email.setError("Email required");
            binding.email.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.inputprohead.getText())){
            binding.inputprohead.setError("Profile headline required");
            binding.inputprohead.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.inputExp.getText())){
            binding.inputExp.setError("Experience required");
            binding.inputExp.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.inputQualification.getText())){
            binding.inputQualification.setError("Qualification required");
            binding.inputQualification.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.skill.getText())){
            binding.skill.setError("Key Skill required");
            binding.skill.requestFocus();
            status=false;
        }
        else if (TextUtils.isEmpty(binding.location.getText())){
            binding.location.setError("Location required");
            binding.location.requestFocus();
            status=false;
        }
        return status;
    }

    public void applyjob(){
        ProgressDialog progressDialog=new ProgressDialog(Apply_Job_Activity.this);
        progressDialog.setMessage("Apply job......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RetrofitClient.getClient().jobapply(userid,jobid,binding.inputName.getText().toString(),binding.mobile.getText().toString(),binding.email.getText().toString(),binding.inputprohead.getText().toString(),
                binding.inputExp.getText().toString(),binding.inputQualification.getText().toString(),binding.skill.getText().toString(),binding.location.getText().toString(),pdfstr).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()){
                    JsonArray jsonArray= response.body().getAsJsonArray();
                    JsonObject jsonObject=jsonArray.get(0).getAsJsonObject();

                    String result= jsonObject.get("status").getAsString();
                    String message= jsonObject.get("message").getAsString();

                    if (result.equalsIgnoreCase("success")){

                        Toast.makeText(Apply_Job_Activity.this, message, Toast.LENGTH_SHORT).show();

                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }
                else {
                    Toast.makeText(getApplicationContext(),"API called failed",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }




}