package com.example.sendemail_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private EditText EditMailTo, EditCCTo;
    private EditText EditMailSubject;
    private EditText EditMailMessage;
    private  Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditMailTo = findViewById(R.id.edtMailTo);
        EditMailSubject = findViewById(R.id.edtMailSubject);
        EditMailMessage = findViewById(R.id.edtMailMessage);
        EditCCTo = findViewById(R.id.edtCCTo);

         buttonSend = findViewById(R.id.btnSend);
         buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendMail();
            }
        });
    }

    private void SendMail() {
        String recipientList = EditMailTo.getText().toString();
        //aaa@gmail.com, bbb@hotmail.com, ...
        String[] recipients = recipientList.split(",");

        String ccList = EditCCTo.getText().toString();
        String[] cc = ccList.split(",");

        String subject = EditMailSubject.getText().toString();
        String message = EditMailMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_CC,cc);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
