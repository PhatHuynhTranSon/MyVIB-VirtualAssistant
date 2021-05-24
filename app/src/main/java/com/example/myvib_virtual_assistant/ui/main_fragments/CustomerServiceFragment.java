package com.example.myvib_virtual_assistant.ui.main_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.adapter.ChatAdapter;
import com.example.myvib_virtual_assistant.adapter.ChatWrapper;
import com.example.myvib_virtual_assistant.chat.ChatRetriever;
import com.example.myvib_virtual_assistant.chat.ChatRetrieverCreator;
import com.example.myvib_virtual_assistant.chat.ChatRetrieverListener;
import com.example.myvib_virtual_assistant.data.models.Chat;
import com.example.myvib_virtual_assistant.speech.MySpeechRecognizer;
import com.example.myvib_virtual_assistant.speech.MySpeechRecognizerListener;
import com.example.myvib_virtual_assistant.speech.SpeechRecognizerBuilder;

import java.util.ArrayList;
import java.util.List;


public class CustomerServiceFragment extends Fragment implements View.OnKeyListener, ChatRetrieverListener, View.OnClickListener, MySpeechRecognizerListener {
    //Views
    RecyclerView chatRecyclerView;
    ChatAdapter chatAdapter;

    //Button and EditText
    ImageView chatImage;
    EditText chatEdiText;

    //Chat retriever
    ChatRetriever mChatRetriever;

    //Speech recognizer
    MySpeechRecognizer mSpeechRecognizer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_service, container, false);

        //Initialize variables
        chatRecyclerView = view.findViewById(R.id.chatRecyclerView);
        chatEdiText = view.findViewById(R.id.chatEditText);
        chatImage = view.findViewById(R.id.chatSpeechImage);

        //Set on enter enter
        chatEdiText.setOnKeyListener(this);

        //Set on click
        chatImage.setOnClickListener(this);

        //Initialize recycler view
        initializeRecyclerView();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize chat retriever
        mChatRetriever = ChatRetrieverCreator.get(getContext());

        //Initialize speech recognizer
        mSpeechRecognizer = SpeechRecognizerBuilder.get(getContext());
    }

    private void initializeRecyclerView() {
        //Create a list with first greeting chat
        ChatWrapper firstChat = generateFirstChatMessage();
        List<ChatWrapper> chatList = new ArrayList<>();
        chatList.add(firstChat);

        //Create adapter
        chatAdapter = new ChatAdapter(getContext(), chatList);

        //Set list
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatRecyclerView.setAdapter(chatAdapter);
    }

    private ChatWrapper generateFirstChatMessage() {
        //Set up list
        Chat chat = new Chat();
        chat.setResponse(getGreetingMessage());
        ChatWrapper chatWrapperAi = ChatWrapper.fromAI(chat);
        return chatWrapperAi;
    }

    private String getGreetingMessage() {
        return getString(R.string.greetings);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //Check if right component
        if (v.getId() == R.id.chatEditText) {
            //Check if Enter is click
            if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                //Get chat from box and send message to customer serv
                sendChatMessage(getChatFromChatBox());
                //Then clear chat box
                clearChatBox();
            }
        }
        return false;
    }

    private String getChatFromChatBox() {
        return chatEdiText.getText().toString();
    }

    private void clearChatBox() {
        chatEdiText.getText().clear();
    }

    private void sendChatMessage(String chat) {
        //Append chat from user
        appendChatMessage(ChatWrapper.fromUser(chat));

        //Then call API
        mChatRetriever.sendChat(chat, this);
    }

    private void appendChatMessage(ChatWrapper chatWrapper) {
        chatAdapter.appendChat(chatWrapper);
    }
    private void scrollChatSectionToBottom() {
        chatRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
    }

    @Override
    public void onResult(Chat chat) {
        //On chat received -> Append to chat
        appendChatMessage(ChatWrapper.fromAI(chat));

        //Scroll to bottom
        scrollChatSectionToBottom();
    }

    @Override
    public void onReady() {
        //DO NOTHING
    }

    @Override
    public void onEnd() {
        //DO NOTHING
    }

    @Override
    public void onResult(String chat) {
        //Send message using the result
        sendChatMessage(chat);
    }

    @Override
    public void onError(Throwable t) {
        displayError();
    }

    public void displayError() {
        Toast.makeText(getContext(), R.string.can_not_chat, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        //Check if correct item
        if (v.getId() == R.id.chatSpeechImage) {
            mSpeechRecognizer.startListening(this);
        }
    }
}