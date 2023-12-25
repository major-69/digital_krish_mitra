package digi.coders.digitalkrishimitraa.Activity;

import static digi.coders.digitalkrishimitraa.Activity.Apply_Job_Activity.getBase64FromPath;

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
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.databinding.ActivityAddPostBinding;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Post_Activity extends AppCompatActivity {

    ActivityAddPostBinding binding;
    public static final int selectedpic = 123;
    Uri selectedImaguri;
    String userid, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.title.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.title, InputMethodManager.SHOW_IMPLICIT);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Add_Post_Activity.this);
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid = usermodel.getId();

        binding.imagedemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Add_Post_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Add_Post_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

                } else {
                    Intent a = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(a, selectedpic);
                }
            }
        });
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
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void back(View view) {
        finish();
    }

    public void finish(View view) {

        if (selectedImaguri == null || selectedImaguri.toString().equalsIgnoreCase("")) {
            Toast.makeText(Add_Post_Activity.this, "Select Img", Toast.LENGTH_SHORT).show();
        } else {
            desc = binding.title.getText().toString();
            addpost();
        }

        //if ()

    }

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

    public void addpost() {

        ProgressDialog progressDialog = new ProgressDialog(Add_Post_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        File file1 = new File(getRealPathFromURI(selectedImaguri));
        RequestBody requestFile1 = RequestBody.create(MediaType.parse(getContentResolver().getType(selectedImaguri)), file1);
        MultipartBody.Part image1 = MultipartBody.Part.createFormData("img", file1.getName(), requestFile1);
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), userid);
        RequestBody spatientname = RequestBody.create(MediaType.parse("text/plain"), desc);

        RetrofitClient.getClient().addpost(id, spatientname, image1).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONArray jsonArray = new JSONArray(new Gson().toJson(response.body()));
                        JSONObject object = jsonArray.getJSONObject(0);

                        if (object.getString("status").equalsIgnoreCase("success")) {
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Add_Post_Activity.this, "" + object.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "" + e.toString(), Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                    }
                    progressDialog.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(Add_Post_Activity.this, "->"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }
}