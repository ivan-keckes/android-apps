package hr.android.keckesProject.activities;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import hr.android.keckesProject.R;
import hr.android.keckesProject.data.Data;
import hr.android.keckesProject.databinding.ActivityDetailsBinding;
import hr.android.keckesProject.receivers.ScreenOnReceiver;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private Data data;
    private ScreenOnReceiver screenOnReceiver = new ScreenOnReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        if(getIntent().hasExtra("data")){
            data = getIntent().getParcelableExtra("data");

            binding.imageView.setImageResource(data.getImgID());
            binding.titleTextView.setText(data.getName());
            binding.detailTextView.setText(data.getDescription());

            binding.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
                    intent.putExtra("imgID", data.getImgID());
                    startActivity(intent);
                }
            });

            binding.buttonLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/" + data.getLink()));
                    try{
                        startActivity(intent);
                    }
                    catch(ActivityNotFoundException exception){
                        Toast.makeText(getApplicationContext(), "Activity not found!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            finish();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.share:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog alertDialog = builder.setMessage(R.string.share_message)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent broadcastIntent = new Intent();
                                broadcastIntent.setAction("hr.android.broadcast.share");
                                broadcastIntent.putExtra("content", data.getName());
                                sendBroadcast(broadcastIntent);

                                Toast.makeText(getApplicationContext(), "Broadcast sent: " + data.getName(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getColor(R.color.orange));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getColor(R.color.orange));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
