package digi.coders.digitalkrishimitraa.Activity;

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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.io.File;

import digi.coders.digitalkrishimitraa.Helper.Constants;
import digi.coders.digitalkrishimitraa.Helper.RetrofitClient;
import digi.coders.digitalkrishimitraa.Model.UserModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityEditProfileBinding;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_Profile_Activity extends AppCompatActivity {

    ActivityEditProfileBinding binding;
    public static final int selectedpic = 123;
    Uri selectedImaguri;
    String sname = "", semail = "", smobile = "", shouse = "", sstreet = "", scity = "", sstate = "", spassword = "", saltnumber = "", userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userdata = pref.getString("userdata", "{}");
        UserModel usermodel = new Gson().fromJson(userdata, UserModel.class);
        userid = usermodel.getId();

        binding.password.setText(usermodel.getPassword());
        binding.passwordcon.setText(usermodel.getPassword());
        binding.name.setText(usermodel.getName());
        binding.mobile.setText(usermodel.getMobile());
        binding.email.setText(usermodel.getEmail());
        binding.street.setText(usermodel.getStreetArea());
        binding.city.setText(usermodel.getCity());
        binding.state.setText(usermodel.getStates());

        Picasso.get().load(Constants.IMAGE_URL + usermodel.getImage()).placeholder(R.drawable.icon)
                .into(binding.logo);

        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(Edit_Profile_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Edit_Profile_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

                } else {
                    Intent a = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(a, selectedpic);
                }
            }
        });

        binding.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(Edit_Profile_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Edit_Profile_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                } else {
                    Intent a = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(a, selectedpic);
                }
            }
        });

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.passwordcon.getText().toString().equalsIgnoreCase(binding.password.getText().toString())) {
                    binding.passwordcon.setError("Password are not same");
                    binding.password.setError("Password are not same");
                    binding.passwordcon.requestFocus();
                    binding.password.requestFocus();

                }
                {
                    if (selectedImaguri == null) {

                    } else {
                        sname = binding.name.getText().toString();
                        semail = binding.email.getText().toString();
                        smobile = binding.mobile.getText().toString();
                        shouse = binding.houseno.getText().toString();
                        sstreet = binding.street.getText().toString();
                        scity = binding.city.getText().toString();
                        sstate = binding.state.getText().toString();
                        spassword = binding.password.getText().toString();
                        updateprofile();
                    }

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
                binding.logo.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
            }
        }
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

    public void updateprofile() {

        ProgressDialog progressDialog = new ProgressDialog(Edit_Profile_Activity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        File file1 = new File(getRealPathFromURI(selectedImaguri));
        RequestBody requestFile1 = RequestBody.create(MediaType.parse(getContentResolver().getType(selectedImaguri)), file1);
        MultipartBody.Part image1 = MultipartBody.Part.createFormData("image", file1.getName(), requestFile1);
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), userid);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), sname);
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), semail);
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), smobile);
        RequestBody house = RequestBody.create(MediaType.parse("text/plain"), shouse);
        RequestBody street = RequestBody.create(MediaType.parse("text/plain"), sstreet);
        RequestBody city = RequestBody.create(MediaType.parse("text/plain"), scity);
        RequestBody state = RequestBody.create(MediaType.parse("text/plain"), sstate);
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), spassword);
        RequestBody altnumber = RequestBody.create(MediaType.parse("text/plain"), saltnumber);

        RetrofitClient.getClient().editprofile(id, name, mobile, email, house, street, city, state, password, altnumber, image1).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.d("profileupdate", "onResponse: " + response.body().toString());
                        JsonArray jsonArray = response.body().getAsJsonArray();
                        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

                        String result = jsonObject.get("status").getAsString();
                        String message = jsonObject.get("message").getAsString();

                        if (result.equalsIgnoreCase("success")) {

                            JsonArray jsonArray1 = jsonObject.get("data").getAsJsonArray();
                            JsonObject jsonObject1 = jsonArray1.get(0).getAsJsonObject();

                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = pref.edit();
                            editor.clear();
                            editor.apply();

                            SharedPreferences pref1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor1 = pref1.edit();
                            editor1.putString("userdata", jsonObject1.toString());
                            editor1.apply();
                            editor1.commit();

                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        progressDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                    }
                    progressDialog.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(Edit_Profile_Activity.this, t.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }


    public void back(View view) {
        finish();
    }
}