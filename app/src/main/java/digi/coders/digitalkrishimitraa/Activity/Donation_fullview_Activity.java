package digi.coders.digitalkrishimitraa.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.wangsun.upi.payment.UpiPayment;
import com.wangsun.upi.payment.model.PaymentDetail;

import org.json.JSONArray;
import org.json.JSONObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.ActivityDonationFullviewBinding;

public class Donation_fullview_Activity extends AppCompatActivity {

    ActivityDonationFullviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityDonationFullviewBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());


        try {
            binding.raised.setText( "₹ "+getIntent().getStringExtra("raised").toString());
            binding.goal.setText("Goal : ₹ "+ getIntent().getStringExtra("goal").toString());
            binding.percentage.setText( getIntent().getStringExtra("percentage").toString()+" %");
            binding.description.setText( getIntent().getStringExtra("desc").toString());

            Picasso.get().load(getIntent().getStringExtra("image").toString()).placeholder(R.drawable.patient)
                    .into(binding.image);
        }
        catch (Exception e){

        }

       binding.donate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showBottomSheetDialog();
           }
       });


    }

    public void back(View view) {finish();
    }
    private void showBottomSheetDialog() {

        LayoutInflater layoutFactory = LayoutInflater.from(Donation_fullview_Activity.this);
        final View dangerDialogView = layoutFactory.inflate(R.layout.dialog_donation, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(Donation_fullview_Activity.this).create();
        deleteDialog.setView(dangerDialogView);
        deleteDialog.setCancelable(false);

        EditText donation= dangerDialogView.findViewById(R.id.donation);

        dangerDialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });

        dangerDialogView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUpiPayment();
                deleteDialog.dismiss();
            }
        });

        dangerDialogView.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               donation.setText("100");
            }
        });

        dangerDialogView.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation.setText("200");
            }
        });

        dangerDialogView.findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation.setText("500");
            }
        });

        dangerDialogView.findViewById(R.id.four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation.setText("1000");
            }
        });

        dangerDialogView.findViewById(R.id.five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation.setText("2000");
            }
        });

        dangerDialogView.findViewById(R.id.six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation.setText("5000");
            }
        });

        dangerDialogView.findViewById(R.id.seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation.setText("10000");
            }
        });

        deleteDialog.show();
    }

    private void startUpiPayment() {

        ArrayList<String> existingApps = UpiPayment.getExistingUpiApps(Donation_fullview_Activity.this);
        existingApps.add("paytm");
        existingApps.add("google pay");
        existingApps.add("bhim");

        PaymentDetail payment = new PaymentDetail("vishuwassrivastava.1999-1@oksbi", "",
                "", "", "", "" + ".00");


        new UpiPayment(Donation_fullview_Activity.this)
                .setPaymentDetail(payment)
                .setUpiApps(existingApps)
                .setCallBackListener(new UpiPayment.OnUpiPaymentListener() {
                    @Override
                    public void onError(String s) {
                        Toast.makeText(getApplicationContext(), "transaction failed: " + s, Toast.LENGTH_LONG).show();
//                        afterPaymentSuccess("FAILED");
                        finish();
                    }

                    @Override
                    public void onSuccess(com.wangsun.upi.payment.model.TransactionDetails transactionDetails) {
                        Log.e("easy payment",
                                "component1 " + transactionDetails.component1() + ", " +
                                        "component2 " + transactionDetails.component2() + ", " +
                                        "component3 " + transactionDetails.component3() + ", " +
                                        "component4 " + transactionDetails.component4() + ", " +
                                        "component5 " + transactionDetails.component5() + ", " +
                                        "getAppName " + transactionDetails.getAppName() + ", " +
                                        "getApprovalRefNo " + transactionDetails.getApprovalRefNo() + ", " +
                                        "getResponseCode " + transactionDetails.getResponseCode() + ", " +
                                        "getStatus " + transactionDetails.getStatus() + ", " +
                                        "getTransactionId " + transactionDetails.getTransactionId() + ", " +
                                        "getTransactionRefId " + transactionDetails.getTransactionRefId()
                        );
//                        txnId = "UPI DATA : AppName=" + transactionDetails.getAppName() + " Status=" + transactionDetails.getStatus() +
//                                " ResponseCode=" + transactionDetails.getResponseCode() + " TransactionId=" + transactionDetails.getTransactionId() +
//                                " ApprovalRefNo=" + transactionDetails.getApprovalRefNo() + " TransactionRefId=" + transactionDetails.getTransactionRefId();
//                        transid=transactionDetails.getTransactionId();
//                        afterPaymentSuccess("SUCCESS");
                    }

                    @Override
                    public void onSubmitted(com.wangsun.upi.payment.model.TransactionDetails transactionDetails) {
                        Toast.makeText(getApplicationContext(), "transaction pending: " + transactionDetails, Toast.LENGTH_LONG).show();
                        finish();
                    }

                }).pay();
    }


}