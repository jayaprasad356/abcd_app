package com.app.abcdapp.chat.adapters;

import static com.app.abcdapp.chat.constants.IConstants.EXTRA_USER_ID;
import static com.app.abcdapp.chat.constants.IConstants.NAME;
import static com.app.abcdapp.chat.constants.IConstants.ONE;
import static com.app.abcdapp.chat.constants.IConstants.TICKET_ID;
import static com.app.abcdapp.chat.constants.IConstants.TYPE;
import static com.app.abcdapp.chat.constants.IConstants.ZERO;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.abcdapp.chat.MessageActivity;
import com.app.abcdapp.R;
import com.app.abcdapp.chat.managers.Utils;
import com.app.abcdapp.chat.models.Ticket;
import com.app.abcdapp.chat.views.SingleClickListener;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TicketAdapters extends RecyclerView.Adapter<TicketAdapters.ViewHolder> implements FastScrollRecyclerView.SectionedAdapter {

    private final Activity mContext;
    private final ArrayList<Ticket> mTickets;
    private final String type;


    public TicketAdapters(Activity mContext, ArrayList<Ticket> ticketsList, String type) {
        this.mContext = mContext;
        this.mTickets = ticketsList;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_tickets, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Ticket ticket = mTickets.get(i);
        viewHolder.tvName.setText(ticket.getName());
        viewHolder.tvMobile.setText(ticket.getMobile());
        viewHolder.tvTitle.setText(ticket.getTitle());
        viewHolder.tvDescription.setText(ticket.getDescription());
        viewHolder.tvCategory.setText(ticket.getCategory());
        if (!type.equals("Pending")){
            viewHolder.itemView.setOnClickListener(new SingleClickListener() {
                @Override
                public void onClickView(View v) {
                    final Intent intent = new Intent(mContext, MessageActivity.class);
                    intent.putExtra(EXTRA_USER_ID, ticket.getId());
                    intent.putExtra(TICKET_ID, ticket.getId());
                    intent.putExtra(NAME, ticket.getName());
                    intent.putExtra(TYPE, type);
                    mContext.startActivity(intent);
                }
            });

        }


    }

    @NonNull
    @NotNull
    @Override
    public String getSectionName(final int position) {
        if (!Utils.isEmpty(mTickets)) {
            return mTickets.get(position).getName().substring(ZERO, ONE);
        } else {
            return null;
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvName,tvMobile,tvTitle,tvDescription,tvCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }

    @Override
    public int getItemCount() {
        return mTickets.size();
    }
}
