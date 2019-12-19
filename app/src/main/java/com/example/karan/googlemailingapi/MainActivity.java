package com.example.karan.googlemailingapi;

import android.media.MediaCas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static javax.mail.Message.RecipientType.*;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3;
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e3=findViewById(R.id.editText3);
        b1=findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread()
                {
                    public void run()
                    {
                      String to = e1.getText().toString();
                      String sub = e2.getText().toString();
                      String message = e3.getText().toString();

                      String host = "smtp.gmail.com"; // google mailing service protocol
                      String from ="sehgalasha64@gmail.com";
                      String pass = "SEHGAL64ASHA";


                      try
                      {
                          Properties p = new Properties();
                          Session session = Session.getInstance(p); // choose javax ad import from it only
                          MimeMessage msg = new MimeMessage(session);
                          InternetAddress toId = new InternetAddress(to);
                          InternetAddress fromId = new InternetAddress(from);

                          msg.setRecipient(Message.RecipientType.TO,toId);

                          msg.setFrom(fromId);
                          msg.setSubject(sub);
                          msg.setText(message);

                          Transport tpt = session.getTransport("smtps");

                          tpt.connect(host,from,pass);

                          tpt.sendMessage(msg,msg.getAllRecipients());

                          tpt.close();
                      }
                      catch (Exception E)
                      {

                      }
                    }
                };
                t.start();
            }
        });
    }
}
