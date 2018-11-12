package ViewModel;

import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Job;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class JobViewModel {

        private MutableLiveData<List<Job>> result = new MutableLiveData<>();

        public void fetchJobs() throws IOException {
            OkHttpClient client = new OkHttpClient();
//                String credential = Credentials.basic("authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6OTg3NjU0MzIxLCJyb2xlX3R5cGUiOiJDQU5ESURBVEUifQ.07DIULD4B4GJUQO1-XFIaaLAUOHcSGBCmLWYofhJYMA");

            Request request = new Request.Builder()
//                    .addHeader("authorization","eyJhbGciOiJIUzI1NiJ9.eyJpZCI6OTg3NjU0MzIxLCJyb2xlX3R5cGUiOiJDQU5ESURBVEUifQ.07DIULD4B4GJUQO1-XFIaaLAUOHcSGBCmLWYofhJYMA")
                    .header("authorization","eyJhbGciOiJIUzI1NiJ9.eyJpZCI6OTg3NjU0MzIxLCJyb2xlX3R5cGUiOiJDQU5ESURBVEUifQ.07DIULD4B4GJUQO1-XFIaaLAUOHcSGBCmLWYofhJYMA")
                    .url("https://apigw.qa.empregoligado.net/applicant/api/v1/jobs?page=1&limit=10")
                    .build();

            Gson gson = new Gson();

            JsonParser p = new JsonParser();
            JsonObject resultado = p.parse(client.newCall(request).execute().body().string())
                    .getAsJsonObject().getAsJsonObject("collection");

            JsonArray jo = resultado.get("_embedded").getAsJsonArray();
            ArrayList<Job> jobs = new ArrayList<>();
            for(JsonElement je : jo)
            {
                jobs.add(gson.fromJson(je,Job.class));
            }
            result.postValue(jobs);

        }
        public MutableLiveData<List<Job>> getJobs() {
            return result;
        }

}
