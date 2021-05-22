package com.example.myvib_virtual_assistant.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvib_virtual_assistant.R;

public class ReceivedChatViewHolder extends RecyclerView.ViewHolder {
    private TextView chatText;

    public ReceivedChatViewHolder(@NonNull View itemView) {
        super(itemView);
        chatText = itemView.findViewById(R.id.chatText);
    }

    public void bind(ChatWrapper chat) {
        chatText.setText(chat.getChat().getResponse());
    }
}
