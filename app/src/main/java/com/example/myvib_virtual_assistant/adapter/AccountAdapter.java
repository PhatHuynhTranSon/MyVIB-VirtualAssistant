package com.example.myvib_virtual_assistant.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.data.models.Account;
import com.example.myvib_virtual_assistant.string.StringUtils;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    //Listener
    public interface AccountListener {
        void onClick(Account account);
    }
    AccountListener listener;

    //Account list
    private List<Account> accountList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Hold
        public TextView accountText;
        public TextView nameText;
        public View view;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            cardView = view.findViewById(R.id.accountCardView);
            accountText = view.findViewById(R.id.addressText);
            nameText = view.findViewById(R.id.nameText);

            //Set on click listener
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Get position
                    int position = getAdapterPosition();
                    //Call on click
                    if (listener != null) listener.onClick(accountList.get(position));
                }
            });
        }
    }

    public AccountAdapter(AccountListener listener, List<Account> accountList) {
        this.listener = listener;
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_account, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Account account = accountList.get(position);
        holder.accountText.setText(StringUtils.formatBankAndAccount(account.getBank(), account.getAccountId()));
        holder.nameText.setText(account.getName());
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public void addAll(List<Account> accounts) {
        accountList.addAll(accounts);
        notifyDataSetChanged();
    }
}
