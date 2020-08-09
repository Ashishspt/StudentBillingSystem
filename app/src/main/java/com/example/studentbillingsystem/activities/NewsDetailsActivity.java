package com.example.studentbillingsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentbillingsystem.R;
import com.example.studentbillingsystem.helpers.AppActivity;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppActivity {
    private TextView tvNewsTitle,tvNewsDescription;
    private ImageView imgNews,backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        initializedView();
        initializedListners();
    }

    @Override
    protected void initializedView() {
        tvNewsTitle=findViewById(R.id.tvNewsTitle);
        tvNewsDescription=findViewById(R.id.tvNewsDescription);
        imgNews=findViewById(R.id.imgNews);
        backArrow=findViewById(R.id.backArrow);

    }

    @Override
    protected void initializedListners() {
        String title=getIntent().getStringExtra("title");
        String description=getIntent().getStringExtra("details");
        String imageUrl=getIntent().getStringExtra("imgUrl");

        tvNewsTitle.setText(title);
        tvNewsDescription.setText(description);
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.mela)
                .into(imgNews);
backArrow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
});

    }

}