package hr.android.keckesProject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import hr.android.keckesProject.R;
import hr.android.keckesProject.databinding.ActivityImageBinding;

public class ImageActivity extends AppCompatActivity {

    private ActivityImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityImageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if(getIntent().hasExtra("imgID")) {
            int imgID = getIntent().getIntExtra("imgID", R.drawable.no_image);

            binding.activityImageView.setImageResource(imgID);

            binding.activityImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        else {
            finish();
        }
    }
}