package digi.coders.digitalkrishimitraa.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;
import com.wangsun.upi.payment.UpiPayment;
import com.wangsun.upi.payment.model.PaymentDetail;

import java.util.ArrayList;
import java.util.List;

import digi.coders.digitalkrishimitraa.Activity.Donation_fullview_Activity;
import digi.coders.digitalkrishimitraa.Activity.MainActivity;
import digi.coders.digitalkrishimitraa.Activity.Message_Activity;
import digi.coders.digitalkrishimitraa.Activity.Post_fullview_Activity;
import digi.coders.digitalkrishimitraa.Activity.Registration_Activity;
import digi.coders.digitalkrishimitraa.Helper.Constants;
import digi.coders.digitalkrishimitraa.Helper.Refresh;
import digi.coders.digitalkrishimitraa.Model.DonateModel;
import digi.coders.digitalkrishimitraa.Model.JobModel;
import digi.coders.digitalkrishimitraa.R;
import digi.coders.digitalkrishimitraa.databinding.DonationLayoutBinding;
import digi.coders.digitalkrishimitraa.databinding.JobDesignBinding;

public class Donation_Adapter extends RecyclerView.Adapter<Donation_Adapter.TaskDataHolder> {

    Context context;
    DonationLayoutBinding binding;
    List<DonateModel> postModelList;
    Refresh refresh;
    public Donation_Adapter(Context context, List<DonateModel> postModelList, Refresh refresh) {
        this.context = context;
        this.postModelList=postModelList;
        this.refresh = refresh;
    }

    @NonNull
    @Override
    public TaskDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DonationLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskDataHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDataHolder holder, int position) {
        DonateModel model=postModelList.get(position);

        binding.goal.setText("Goal : ₹ "+model.getTotaldonation().toString());
        binding.percentage.setText(model.getPercentage().toString()+" %");
        binding.raised.setText("₹ "+model.getRaised().toString());


        Picasso.get().load(Constants.IMAGE_URL+model.getPatientageimage()).placeholder(R.drawable.patient)
                .into(binding.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Donation_fullview_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("raised", model.getRaised().toString());
                intent.putExtra("goal", model.getTotaldonation().toString());
                intent.putExtra("percentage", model.getPercentage().toString());
                intent.putExtra("desc", model.getDescription().toString());
                intent.putExtra("id", model.getId().toString());
                intent.putExtra("image", model.getPatientageimage().toString());
                context.startActivity(intent);
            }
        });

        binding.donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });


    }
    @Override
    public int getItemCount() { return postModelList.size(); }

    public class TaskDataHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView liketxt;
        ImageView likeimg;
        Button donation;
        LinearLayout likefull,comment;
        public TaskDataHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    private void showBottomSheetDialog() {

        LayoutInflater layoutFactory = LayoutInflater.from(context);
        final View dangerDialogView = layoutFactory.inflate(R.layout.dialog_donation, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(context).create();
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

        ArrayList<String> existingApps = UpiPayment.getExistingUpiApps(context);
        existingApps.add("paytm");
        existingApps.add("google pay");
        existingApps.add("bhim");

        PaymentDetail payment = new PaymentDetail("vishuwassrivastava.1999-1@oksbi", "",
                "", "", "", "" + ".00");


        new UpiPayment((FragmentActivity) context)
                .setPaymentDetail(payment)
                .setUpiApps(existingApps)
                .setCallBackListener(new UpiPayment.OnUpiPaymentListener() {
                    @Override
                    public void onError(String s) {
                        Toast.makeText(context, "transaction failed: " + s, Toast.LENGTH_LONG).show();
//                        afterPaymentSuccess("FAILED");
                        ((FragmentActivity) context).finish();
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
                        ((FragmentActivity) context).finish();

                    }

                    @Override
                    public void onSubmitted(com.wangsun.upi.payment.model.TransactionDetails transactionDetails) {
                        Toast.makeText(context, "transaction pending: " + transactionDetails, Toast.LENGTH_LONG).show();
                    }

                }).pay();
    }

}
