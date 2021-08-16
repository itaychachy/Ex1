package com.example.training_ex1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * RecyclerViewAdapter class for the MainActivity's RecyclerView
 * @author itaychachy
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContactViewHolder> {

    private final ArrayList<Contact> contactList;
    private final LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    /**
     * Initialize object
     * @param context current context
     * @param contactList a list of Contacts
     */
    RecyclerViewAdapter(final Context context, final ArrayList<Contact> contactList) {
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
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        return new ContactViewHolder(view);
    }

    /**
     * Binds the data to the TextView in each row
     * @param holder current ViewHolder object
     * @param position the ViewHolder's position
     */
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
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
    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView myTextView;

        /**
         * Construct new ViewHolder
         * @param itemView itemView
         */
        ContactViewHolder(final View itemView) {
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
            if (itemClickListener != null) itemClickListener.onContactClick(view, contactList.get(getAdapterPosition()));
        }
    }


    void setClickListener(final ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * Inner Interface for the MainActivity to implement in case of a Contact being pressed
     */
    public interface ItemClickListener {
        void onContactClick(final View view, final Contact contact);
    }
}