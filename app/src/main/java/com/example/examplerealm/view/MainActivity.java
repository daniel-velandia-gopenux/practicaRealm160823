package com.example.examplerealm.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.examplerealm.R;
import com.example.examplerealm.databinding.ActivityMainBinding;
import com.example.examplerealm.databinding.DialogCreateBoardBinding;
import com.example.examplerealm.model.Board;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        realm = Realm.getDefaultInstance();
        showDialogForCreatingBoard("title", "message");
    }

    private void showDialogForCreatingBoard(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        DialogCreateBoardBinding bindingDialog = DialogCreateBoardBinding
                .inflate(LayoutInflater.from(this));

        if(!title.isEmpty()) builder.setTitle(title);
        if(!message.isEmpty()) builder.setMessage(message);

        builder.setView(bindingDialog.getRoot());

        bindingDialog.etBoardTitle.getText();

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardTitle = bindingDialog.etBoardTitle.getText().toString().trim();
                if(!boardTitle.isEmpty())
                    createNewBoard(boardTitle);
                else
                    Toast.makeText(
                            MainActivity.this,
                            "The title is required to created a new board",
                            Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void createNewBoard(String boardTitle) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Board board = new Board(boardTitle);
                realm.copyFromRealm(board);
            }
        });
    }
}