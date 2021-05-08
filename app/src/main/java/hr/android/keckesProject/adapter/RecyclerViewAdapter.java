package hr.android.keckesProject.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import hr.android.keckesProject.R;
import hr.android.keckesProject.activities.DetailsActivity;
import hr.android.keckesProject.data.DataCollection;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private DataCollection dataCollection;
    private Context context;

    private boolean on_attach = true;
    private int lastSavedPosition = 0;
    private int lastPosition = -1;

    public RecyclerViewAdapter(Context context, DataCollection dataCollection) {
        this.context = context;
        this.dataCollection = dataCollection;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(dataCollection.getCollection().get(position).getName());
        holder.imageView.setImageResource(dataCollection.getCollection().get(position).getImgID());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickAnimation(holder.backgroundLayout);

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("data", dataCollection.getCollection().get(position));
                context.startActivity(intent);

            }
        });

        if(on_attach){
            addItemAnimation(holder.itemView, position);
        }
        else {
            addItemAnimation(holder.itemView, position - lastSavedPosition);
        }
        lastPosition = position;

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                on_attach = false;
                lastSavedPosition = lastPosition;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataCollection.getItemCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout;
        ConstraintLayout backgroundLayout;
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.row_constraint_layout);
            textView = itemView.findViewById(R.id.recTextView);
            imageView = itemView.findViewById(R.id.recImageView);
            backgroundLayout = itemView.findViewById(R.id.element_constraint_layout);
        }
    }

    public void addItemAnimation(View view, int position){

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_slide_in);
        animation.setStartOffset(200 * position);
        view.startAnimation(animation);
    }

    public void clickAnimation(View view){
        int colorFrom = context.getColor(R.color.background);
        int colorTo = context.getColor(R.color.background_click);
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo, colorFrom);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }
}
