package com.example.myvib_virtual_assistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvib_virtual_assistant.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {
    private static final int CHAT_SENT = 1;
    private static final int CHAT_RECEIVED = 2;

    private Context context;
    private List<ChatWrapper> chatList;

    public ChatAdapter(Context context, List<ChatWrapper> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == CHAT_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ai_chat_layout, parent, false);
            return new ReceivedChatViewHolder(view);
        } else if (viewType == CHAT_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_chat_layout, parent, false);
            return new SentChatViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatWrapper chatWrapper = chatList.get(position);

        switch (holder.getItemViewType()) {
            case CHAT_RECEIVED:
                ((ReceivedChatViewHolder) holder).bind(chatWrapper);
                break;
            case CHAT_SENT:
                ((SentChatViewHolder) holder).bind(chatWrapper);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void appendChat(ChatWrapper chatWrapper) {
        chatList.add(chatWrapper);
        notifyDataSetChanged();
    }

    public void removeLatestChat() {
        //Get last position
        int lastPosition = getItemCount() - 1;
        //Remove item at that position
        chatList.remove(lastPosition);
    }

    @Override
    public int getItemViewType(int position) {
        ChatWrapper chatWrapper = chatList.get(position);
        if (chatWrapper.getFrom() == ChatEnum.AI) {
            return CHAT_RECEIVED;
        } else {
            return CHAT_SENT;
        }
    }
}
