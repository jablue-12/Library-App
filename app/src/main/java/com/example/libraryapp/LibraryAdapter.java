package com.example.libraryapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryVieHolder> implements Filterable {

    private List<LibraryItems> libraryList;
    private List<LibraryItems> listFull;

    private OnItemClickListener mlistener;

    //constructor
    public LibraryAdapter (List<LibraryItems> mList){
        libraryList = mList;
        //deep copy of the list
        listFull = new ArrayList<>(mList);
    }



    public interface OnItemClickListener{
        void onItemCLick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }


    //state the static class first before extending the main class
    public static class LibraryVieHolder extends RecyclerView.ViewHolder{

        public ImageView bookImgView;
        public TextView bookTitleTxtView;
        public TextView bookPriceTxtView;

        public LibraryVieHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            bookImgView = itemView.findViewById(R.id.bookImageView);
            bookTitleTxtView = itemView.findViewById(R.id.bookTitleTextView);
            bookPriceTxtView = itemView.findViewById(R.id.priceBookTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemCLick(position);
                        }
                    }

                }
            });




        }
    }

    @NonNull
    @Override
    public LibraryVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_items,parent,false);
        LibraryVieHolder lvh = new LibraryVieHolder(view,mlistener);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryVieHolder holder, int position) {
        LibraryItems item = libraryList.get(position);
        holder.bookImgView.setImageResource(item.getImageResource());
        holder.bookTitleTxtView.setText(item.getBook());
        holder.bookPriceTxtView.setText("Price: $" + item.getNumBooks());



    }

    @Override
    public int getItemCount() {
        return libraryList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    //impelment the methods for filter
    private Filter filter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            //parameter is the input
            List<LibraryItems> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                //nothing is typed in the searchview so add all items from the listFull
                filteredList.addAll(listFull);
            }else{
                //when there is something typed in the searchview.
                //change input to lower case and remove spaces so it is not case sensitive
                //when searching for an item.
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(LibraryItems item : listFull){
                    //change the item to lowercase so its not case sensitive.
                    if(item.getBook().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }//end for

            }//end if-else

            FilterResults results = new FilterResults();

            results.values = filteredList;

            return results;

        }//end FilterResults

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            libraryList.clear();
            libraryList.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };



}
