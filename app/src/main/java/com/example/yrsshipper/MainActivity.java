package com.example.yrsshipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yrsshipper.Common.Common;
import com.example.yrsshipper.Model.Shipper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {

    FButton btn_sign_in;
    MaterialEditText edt_phone,edt_password;

    FirebaseDatabase database;
    DatabaseReference shippers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        setContentView(R.layout.activity_main);

        btn_sign_in=(FButton)findViewById(R.id.btnSignIn);
        edt_password=(MaterialEditText) findViewById(R.id.ediePassword);
        edt_phone=(MaterialEditText) findViewById(R.id.editPhone);

        //firebase
        database=FirebaseDatabase.getInstance();
        shippers=database.getReference(Common.SHIPPER_TABLE);


        //כניסה לשליח באמצעות טלפון וסיסמא
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(edt_phone.getText().toString(),edt_password.getText().toString());
                

            }

            //כניסה לשליח במידה וטעה בסיסמא יופיע הודעה שהסיסמא שגויה
            //במידה ולא מילא את השדות יוםיע הודעה למלאות את השדות
            //במידה והכניס מספר טלפון של שליח שלא קיים יופיע הודעה בהתאם (שליח לא קיים)
            private void login(final String phone, final String password) {
                shippers.child(phone)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                if(snapshot.exists()&& !phone.isEmpty() && !password.isEmpty())
                                {
                                    Shipper shipper= snapshot.getValue(Shipper.class);
                                    if(shipper.getPassword().equals(password))
                                    {
                                     //login succeed
                                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                        Common.currentShipper=shipper;
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this,"Password incorrect!",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                else if(snapshot.exists() && phone.isEmpty() && password.isEmpty())
                                {
                                    Toast.makeText(MainActivity.this,"Please fill in the blanks!",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(MainActivity.this,"The shipper does not exist",Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });




    }
}
