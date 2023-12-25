package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squareup.picasso.Picasso;

import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityJobFullviewBinding;

public class Job_fullview_Activity extends AppCompatActivity {

    ActivityJobFullviewBinding binding;
    String postid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityJobFullviewBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());


        try {
            binding.name.setText( getIntent().getStringExtra("title").toString());
            binding.time.setText( getIntent().getStringExtra("time").toString());
            binding.companyname.setText( getIntent().getStringExtra("company").toString());
            binding.experience.setText( getIntent().getStringExtra("experience").toString());
            binding.address.setText( getIntent().getStringExtra("location").toString());
            binding.experience.setText( getIntent().getStringExtra("experience").toString());
            binding.qualification.setText( getIntent().getStringExtra("qualification").toString());
            binding.jobtype.setText( getIntent().getStringExtra("jobtype").toString());
            binding.description.setText( getIntent().getStringExtra("desc").toString());
            binding.packages.setText( getIntent().getStringExtra("packages").toString());
            postid= getIntent().getStringExtra("id").toString();

        }
        catch (Exception e){

        }

       binding.apply.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               deleteuserdata();
           }
       });

    }

    public void back(View view) {finish();
    }

    public void finish(View view) {finish();
    }

    private void deleteuserdata() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Job_fullview_Activity.this);
        builder.setMessage(R.string.YouwanttoApply);
        builder.setTitle(R.string.Confirm);
        builder.setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getApplicationContext(),Apply_Job_Activity.class);
                intent.putExtra("jobid", postid);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}