package wya.whereyaat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by bshah on 9/25/2016.
 */

public class PlaceThingAdapter extends RecyclerView.Adapter<PlaceThingAdapter.PlaceThingAdapterHolder> {
    private List<PlaceThing> placeThings;
    private Context context;
    private DatabaseReference mDatabase;

    public PlaceThingAdapter(List<PlaceThing> placeThingsHolder, Context contextHolder) {
        placeThings = placeThingsHolder;
        context = contextHolder;
    }

    @Override
    public int getItemCount() {
        return placeThings.size();
    }

    @Override
    public void onBindViewHolder(PlaceThingAdapterHolder placeThingsViewHolder, int i) {
        final PlaceThing placeThing = placeThings.get(i);

        placeThingsViewHolder.locationTitle.setText(placeThing.getTitle());
        placeThingsViewHolder.locationAddress.setText(placeThing.getAddress());
        /*
        if(placeThing.getImage() != null){
            placeThingsViewHolder.locationPhoto.setImageBitmap(placeThing.getImage());
        }*/

        placeThingsViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                mDatabase.child("users").child(user.getUid()).setValue(placeThing);
                Intent intent = new Intent(context, MapsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public PlaceThingAdapterHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new PlaceThingAdapterHolder(itemView);
    }

    public static class PlaceThingAdapterHolder extends RecyclerView.ViewHolder {
        protected TextView locationTitle;
        protected TextView locationAddress;
        protected ImageView locationPhoto;
        protected CardView cv;

        public PlaceThingAdapterHolder(View v) {
            super(v);
            locationTitle =  (TextView) v.findViewById(R.id.locationTitle);
            locationAddress =  (TextView) v.findViewById(R.id.locationAddress);
            locationPhoto =  (ImageView) v.findViewById(R.id.locationPhoto);
            cv = (CardView) v.findViewById(R.id.cv);
        }
    }
}
