package com.bitcode.marvelseries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MarvelSeriesAdapter extends RecyclerView.Adapter<MarvelSeriesAdapter.MarvelSeriesViewHolder> {




    private Context mContext;
    private ArrayList<MarvelSeriesData> mMarvelSeriesDetails;

    public MarvelSeriesAdapter(Context mContext, ArrayList<MarvelSeriesData> mMarvelSeriesDetails) {
        this.mContext = mContext;
        this.mMarvelSeriesDetails = mMarvelSeriesDetails;
    }

    @NonNull
    @Override
    public MarvelSeriesAdapter.MarvelSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.marvel_series_data,parent,false);

        return new MarvelSeriesViewHolder (view);    }

    @Override
    public void onBindViewHolder(@NonNull MarvelSeriesAdapter.MarvelSeriesViewHolder holder, int position) {

        MarvelSeriesData currentData= mMarvelSeriesDetails.get(position);

        String ImageUrl=currentData.getImageurl ();
        String Name=currentData.getName ();
        String FA=currentData.getFirstappearance ();
        String Team=currentData.getTeam ();
        String CreatedBy=currentData.getCreatedby ();
        String Bio=currentData.getBio ();


        holder.mTxtViewName.setText(Name);
        holder.mTxtViewFA.setText(FA);
        holder.mTxtViewTeam.setText(Team);
        holder.mTxtViewCreatedBy.setText(CreatedBy);
        holder.mTxtViewBio.setText(Bio);


       Picasso.with(mContext).load(ImageUrl).fit().centerInside().into(holder.mImageView);



    }

    @Override
    public int getItemCount() {
        return mMarvelSeriesDetails.size();    }

    public class MarvelSeriesViewHolder extends RecyclerView.ViewHolder {


        public ImageView mImageView;
        public TextView mTxtViewName;
        public TextView mTxtViewFA;
        public TextView mTxtViewTeam;
        public TextView mTxtViewCreatedBy;
        public TextView mTxtViewBio;


        public MarvelSeriesViewHolder(@NonNull View itemView) {
            super (itemView);

           mImageView=itemView.findViewById(R.id.Url_Image);
            mTxtViewName=itemView.findViewById(R.id.Marvel_Name);
            mTxtViewBio=itemView.findViewById(R.id.Marvel_Bio);
            mTxtViewTeam=itemView.findViewById(R.id.Marvel_Team);
            mTxtViewCreatedBy=itemView.findViewById(R.id.Marvel_CreatedBy);
            mTxtViewFA=itemView.findViewById(R.id.Marvel_FA);



        }
    }

    public interface MarvelSeries{

        void onMarvelSeriesClick();
    }
}
