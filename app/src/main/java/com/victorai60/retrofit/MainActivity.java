package com.victorai60.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);

        final GithubService githubService = ServiceGenerator.createService(GithubService.class);
        githubService.posts(new Callback<List<Post>>() {

            @Override
            public void success(List<Post> posts, Response response) {
                for (Post post : posts) {
                    System.out.println(post.title + " (" + post.author + ")");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.getMessage());
            }
        });
        githubService.post(1, new Callback<Post>() {

            @Override
            public void success(Post post, Response response) {
                System.out.println(post.author);
                textView.setText(post.author);
                System.out.println(Thread.currentThread().getName());
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.getMessage());
            }
        });

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(githubService.post(3).get(0).author);
            }
        }).start();
    }
}
