package com.alemoreirac.empregoligado;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.List;

import Model.Interview;
import Model.Job;
import Util.InterViewsDialog;
import Util.JobAdapter;
import Util.StringUtil;
import ViewModel.InterviewViewModel;
import ViewModel.JobViewModel;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class JobDetailActivity extends AppCompatActivity {

    @BindView(R.id.txv_Job_Title) TextView txvTitle;
    @BindView(R.id.txv_Alternate_Name_Job) TextView txvAlternateName;
    @BindView(R.id.txv_Description_Job) TextView txvDescription;
    @BindView(R.id.txv_Company_Address) TextView txvCompanyAddress;
    @BindView(R.id.btn_Interviews) Button btnInterview;
    @BindView(R.id.btn_Navigate)Button btnNavigate;
    Activity activity;
    Job job;
    InterviewViewModel interviewViewModel;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        getSupportActionBar().setTitle("Detalhes da vaga");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff00C662));

        interviewViewModel = new InterviewViewModel();
        ButterKnife.bind(this);
        job = (Job)getIntent().getSerializableExtra("job");

        Drawable imgNavigate = getResources().getDrawable( R.drawable.navigate);
        imgNavigate.setBounds( 0, 0, 60, 60 );
        btnNavigate.setCompoundDrawables( imgNavigate, null, null, null );

        Drawable imgCalendar = getResources().getDrawable( R.drawable.calendar);
        imgCalendar.setBounds( 0, 0, 60, 60 );
        btnInterview.setCompoundDrawables( imgCalendar, null, null, null );
//        interviewViewModel.getJobs().postValue();

        txvTitle.setText(StringUtil.checkValue(job.getTitle(),-1,"(No Title)"));
        txvAlternateName.setText(StringUtil.checkValue(job.getAlternative_name(),-1,"(No Alter_title)"));
        txvDescription.setText(StringUtil.checkValue(job.getDescription(),-1,"(No Description)"));
        txvCompanyAddress.setText(StringUtil.checkValue(job.getAddressString(),-1,"(No Address)"));

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+job.getAddress().getCoordinates().getLatitude()+job.getAddress().getCoordinates().getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        btnInterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData();
            }
        });

        interviewViewModel.getInterviews().observe(this, new Observer<List<Interview>>() {
            @Override
            public void onChanged(@Nullable List<Interview> resultInterviews) {
                InterViewsDialog.showDialog(JobDetailActivity.this,resultInterviews);
            }
        });

    }
    public void fetchData()
    {

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>()
        {


            @Override
            protected Void doInBackground(Void... params)
            {
                try
                {
                    interviewViewModel.fetchInterviewsByJobId(job.getId());
                } catch (Exception e)
                {
                    e.toString();
                }
                return null;
            }


        };

        asyncTask.execute();
    }


}
