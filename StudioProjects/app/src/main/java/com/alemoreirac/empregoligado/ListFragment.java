package com.alemoreirac.empregoligado;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import Model.Job;
import Util.JobAdapter;
import ViewModel.JobViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    JobViewModel jobViewModel;
    List<Job> jobs;
    @BindView(R.id.lstv_Jobs) ListView lstvJobs;
    @BindView(R.id.txv_Loading) TextView txvLoading;
    @BindView(R.id.pgb_Loading) ProgressBar pgbLoading;
    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        jobViewModel = new JobViewModel();
        fetchData();
        jobViewModel.getJobs().observe(this, new Observer<List<Job>>() {
            @Override
            public void onChanged(@Nullable List<Job> resultJobs) {
                jobs = resultJobs;
                txvLoading.setVisibility(View.INVISIBLE);
                pgbLoading.setVisibility(View.INVISIBLE);
                JobAdapter mAdapter = new JobAdapter(resultJobs,getActivity());
                lstvJobs.setAdapter(mAdapter);
            }
        });

        lstvJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Job j = jobs.get(i);
                Intent it = new Intent(getActivity(),JobDetailActivity.class);
//                it.putExtra("job_id",j.getId());
                it.putExtra("job",j);
                startActivity(it);
            }
        });
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                    txvLoading.setVisibility(View.VISIBLE);
                    pgbLoading.setVisibility(View.VISIBLE);
                    jobViewModel.fetchJobs();
                } catch (Exception e)
                {

                }
                return null;
            }


        };

        asyncTask.execute();
    }
}
