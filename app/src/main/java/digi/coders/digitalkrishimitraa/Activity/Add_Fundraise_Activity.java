package digi.coders.digitalkrishimitraa.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityAddFundraiseBinding;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Fundraise_Activity extends AppCompatActivity {

    ActivityAddFundraiseBinding binding;
    public static final int  selectedpic=123;
    Uri selectedImaguri;
    String userid,patientname,disease,totaldonation,patientage,strdescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddFundraiseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Add_Fundraise_Activity.this);
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid=usermodel.getId();

        binding.imagedemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(Add_Fundraise_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Add_Fundraise_Activity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},100);

                }
                else {
                    Intent a = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(a, selectedpic);
                }
            }
        });


        List<String> fundraiseList = new ArrayList<>();
        fundraiseList.add(getResources().getString(R.string.choose));
        fundraiseList.add(getResources().getString(R.string.trackter));
        fundraiseList.add(getResources().getString(R.string.Rotavator));
        fundraiseList.add(getResources().getString(R.string.Troli));
        fundraiseList.add(getResources().getString(R.string.Solar_Pump));
        fundraiseList.add(getResources().getString(R.string.Spray_Machine));
        fundraiseList.add(getResources().getString(R.string.Land_purchase));
        fundraiseList.add(getResources().getString(R.string.Medical_treatment));


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Add_Fundraise_Activity.this,R.layout.spinner_layout,fundraiseList);
        binding.equipmentName.setAdapter(arrayAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == selectedpic && resultCode == RESULT_OK && null != data) {
            try {
                selectedImaguri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImaguri, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
//                pdfstr = cursor.getString(columnIndex);
//                pdfstr= getBase64FromPath(picturePath);
                cursor.close();
                binding.imagedemo.setVisibility(View.GONE);
                binding.imagepost.setVisibility(View.VISIBLE);
                binding.imagepost.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void back(View view) {finish();
    }

    public void finish(View view) {
//        if (validateRegistrationform() ){
//            patientname=binding.inputName.getText().toString();
//            disease=binding.inputDisease.getText().toString();
//            totaldonation=binding.inputTotaldonation.getText().toString();
//            patientage=binding.age.getText().toString();
//            strdescription=binding.jobdesc.getText().toString();
//
//            //addfundraise();
//        }
    }

//    private boolean validateRegistrationform() {
//        boolean status=true;
//        if(TextUtils.isEmpty(binding.inputName.getText())){
//            binding.inputName.setError("Patient name required");
//            binding.inputName.requestFocus();
//            status=false;
//        }
//        else if (TextUtils.isEmpty(binding.inputTotaldonation.getText())){
//            binding.inputTotaldonation.setError("Total Donation required");
//            binding.inputTotaldonation.requestFocus();
//            status=false;
//        }
//        else if (TextUtils.isEmpty(binding.inputDisease.getText())){
//            binding.inputDisease.setError("Disease required");
//            binding.inputDisease.requestFocus();
//            status=false;
//        }
//        else if (TextUtils.isEmpty(binding.age.getText())){
//            binding.age.setError("Patient Age required");
//            binding.age.requestFocus();
//            status=false;
//        }
//        return status;
//    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    public void addfundraise(){

        ProgressDialog progressDialog=new ProgressDialog(Add_Fundraise_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        File file1 = new File(getRealPathFromURI(selectedImaguri));
        RequestBody requestFile1 = RequestBody.create(MediaType.parse(getContentResolver().getType(selectedImaguri)), file1);
        MultipartBody.Part image1 = MultipartBody.Part.createFormData("patientageimage", file1.getName(), requestFile1);
        RequestBody id=RequestBody.create(MediaType.parse("text/plain"),userid);
        RequestBody spatientname=RequestBody.create(MediaType.parse("text/plain"),patientname);
        RequestBody sdisease=RequestBody.create(MediaType.parse("text/plain"),disease);
        RequestBody stotaldonation=RequestBody.create(MediaType.parse("text/plain"),totaldonation);
        RequestBody spatientage=RequestBody.create(MediaType.parse("text/plain"),patientage);
        RequestBody description=RequestBody.create(MediaType.parse("text/plain"),strdescription);

        RetrofitClient.getClient().addfund(id,spatientname,sdisease,stotaldonation,spatientage,description,image1).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONArray jsonArray = new JSONArray(new Gson().toJson(response.body()));
                        JSONObject object = jsonArray.getJSONObject(0);
                        Log.d("uwbuwbvu", "onResponse: "+jsonArray.toString());

                        if(object.getString("status").equalsIgnoreCase("success")){

                            finish();

                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(Add_Fundraise_Activity.this, ""+object.getString("msg"), Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                    progressDialog.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(Add_Fundraise_Activity.this, t.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }

}