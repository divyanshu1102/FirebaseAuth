package info.androidhive.firebase;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Divyanshu Sharma on 11/26/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> titles=new ArrayList<String>();
    private ArrayList<String> details=new ArrayList<String>();
    private ArrayList<String> images=new ArrayList<String>();

    public RecyclerAdapter(ArrayList<String> titles, ArrayList<String> details, ArrayList<String> images) {
        this.titles = titles;
        this.details = details;
        this.images = images;
    }

    /*
    private String[] titles = {"Chapter One",
            "Chapter Two",
            "Chapter Three",
            "Chapter Four",
            "Chapter Five",
            "Chapter Six",
            "Chapter Seven",
            "Chapter Eight"};

    private String[] details = {"Item one details",
            "Item two details", "Item three details",
            "Item four details", "Item file details",
            "Item six details", "Item seven details",
            "Item eight details"};

    private String[] images = {"Item one details",
            "Item two details", "Item three details",
            "Item four details", "Item file details",
            "Item six details", "Item seven details",
            "Item eight details"};
    */

    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public TextView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (TextView) itemView.findViewById(R.id.userType);
            itemTitle = (TextView)itemView.findViewById(R.id.userName);
            itemDetail=(TextView)itemView.findViewById(R.id.userMajor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    //Listings.goToCommunication();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cards, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles.get(i));// viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details.get(i));
        viewHolder.itemImage.setText(images.get(i));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}