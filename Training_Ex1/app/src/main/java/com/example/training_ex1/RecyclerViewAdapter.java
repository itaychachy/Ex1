package com.example.training_ex1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * RecyclerViewAdapter class for the MainActivity's RecyclerView
 * @author itaychachy
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Contact> contactList;
    private final LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    /**
     * Initialize object
     * @param context current context
     * @param contactList a list of Contacts
     */
    RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.contactList = contactList;
    }

    /**
     * Inflates the row layout from xml when needed
     * @param parent parent view
     * @param viewType type
     * @return new ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds the data to the TextView in each row
     * @param holder current ViewHolder object
     * @param position the ViewHolder's position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String name = contactList.get(holder.getAdapterPosition()).getName();
        holder.myTextView.setText(name);
    }

    /**
     * @return total number of Contact
     */
    @Override
    public int getItemCount() {
        return contactList.size();
    }


    /**
     * Inner ViewHolder class. Stores and recycles views as they are scrolled off screen
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView myTextView;

        /**
         * Construct new ViewHolder
         * @param itemView itemView
         */
        ViewHolder(final View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.ContactName);
            itemView.setOnClickListener(this);
        }

        /**
         * Activates onClickItem in case of a ViewHolder was clicked
         * @param view current view
         */
        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onContactClick(view, getAdapterPosition());
        }
    }

    /**
     * @param position current position of ViewHolder that was pressed
     * @return Contact
     */
    Contact getItem(int position) {
        return contactList.get(position);
    }

    /**
     * Allows clicks events to be caught
     * @param itemClickListener ItemClickListener object
     */
    void setClickListener(final ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * Inner Interface for the MainActivity to implement in case of a Contact being pressed
     */
    public interface ItemClickListener {
        void onContactClick(View view, int position);
    }
}