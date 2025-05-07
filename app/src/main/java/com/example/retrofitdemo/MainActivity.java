package com.example.retrofitdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    TextView textQuote, textAuthor;;
    Button btnFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textQuote = findViewById(R.id.textQuote);
        textAuthor = findViewById(R.id.textAuthor);
        btnFetch= findViewById(R.id.btnFetch);


        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchQuote();
            }
        });


    }

    private void fetchQuote() {
        MyApi api= ApiClient.getClient().create(MyApi.class);
        Call<Quote> call= api.getQuote();

        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                if (response.isSuccessful()){
                    Quote quote= response.body();
                    textQuote.setText(quote.getQuote());
                    textAuthor.setText("- "+quote.getAuthor());
                }
                else {
                    textQuote.setText("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                textQuote.setText("Error...");
                textAuthor.setText(" ");
            }
        });

    }
}