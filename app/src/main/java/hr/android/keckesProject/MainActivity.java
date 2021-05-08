package hr.android.keckesProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import hr.android.keckesProject.adapter.RecyclerViewAdapter;
import hr.android.keckesProject.data.DataCollection;
import hr.android.keckesProject.databinding.ActivityMainBinding;
import hr.android.keckesProject.receivers.ScreenOnReceiver;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DataCollection dataCollection = new DataCollection();
    private ScreenOnReceiver screenOnReceiver = new ScreenOnReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RecyclerView recyclerView = binding.recyclerView;

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, dataCollection);
        LinearLayoutManager linLayout = new LinearLayoutManager(this);
        linLayout.setItemPrefetchEnabled(true);
        recyclerView.setLayoutManager(linLayout);
        recyclerView.setAdapter(recyclerViewAdapter);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(screenOnReceiver, intentFilter);
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
                                StringBuilder builder = new StringBuilder();
                                dataCollection.getCollection().forEach(data -> builder.append(data.getName() + ", "));
                                builder.setLength(builder.length() - 2);
                                broadcastIntent.putExtra("content", builder.toString());
                                sendBroadcast(broadcastIntent);

                                Toast.makeText(getApplicationContext(), "Broadcast sent: " + builder.toString(), Toast.LENGTH_LONG).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(screenOnReceiver);
    }
}