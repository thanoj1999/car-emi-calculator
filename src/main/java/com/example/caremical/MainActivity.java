package com.example.caremical;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button b1,b2,b3;
    EditText t1,t2,t3,t4,t5,t6,t7;
    TextView tt1;
    myDataBase myDatabase;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.cal);
        b1.setOnClickListener(this);
        b2=(Button)findViewById(R.id.save);
        b2.setOnClickListener(this);
        b3=(Button)findViewById(R.id.show);
        b3.setOnClickListener(this);
        t1=(EditText)findViewById(R.id.id);
        t2=(EditText)findViewById(R.id.name);
        t3=(EditText)findViewById(R.id.date);
        t4=(EditText)findViewById(R.id.pamount);
        t5=(EditText)findViewById(R.id.dpay);
        t6=(EditText)findViewById(R.id.rate);
        t7=(EditText)findViewById(R.id.term);
        tt1=(TextView)findViewById(R.id.result);
        txt=(TextView)findViewById(R.id.data);
        myDatabase = new myDataBase(getBaseContext(), myDataBase.DATABASE_NAME, null, 1);

    }


    public void onClick(View v){
        double p,r,n,d,emi;
        p=Double.parseDouble(t4.getText().toString());
        d=Double.parseDouble(t5.getText().toString());
        p=p-d;
        r=Double.parseDouble(t6.getText().toString());
        r=r/(12*100);
        n=Double.parseDouble(t7.getText().toString());
        emi=p*(r*Math.pow((1+r),n))/(Math.pow((1+r),n)-1);
        emi=emi/12;
        emi=Math.round(emi*100.0)/100.0;

        String emians = "Rs. "+String.valueOf(emi);
        /* double emi = principleAmount * (interestRate * Math.pow((1 + interestRate), loanTerm)) / (Math.pow((1 + interestRate), loanTerm) - 1); */
        tt1.setText(emians);
        if (v.equals(b2)) {
            String Id = t1.getText().toString();
            String name = t2.getText().toString();
            String dat = t3.getText().toString();
            String amount = t4.getText().toString();
            String pay = t5.getText().toString();
            String ra = t6.getText().toString();
            String year = t7.getText().toString();
            String rs = tt1.getText().toString();
            SQLiteDatabase database = myDatabase.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("ID", Id);
            cv.put("NAME", name);
            cv.put("DATE", dat);
            cv.put("PRINCIPAL_AMOUNT", amount);
            cv.put("DOWN_PAYMENT", pay);
            cv.put("RATE", ra);
            cv.put("YEAR", year);
            cv.put("EMI", rs);
            database.insert("CAR_EMI", null, cv);
            Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_LONG).show();

        }
        else if(v.equals(b3))
        {
            txt = (TextView)findViewById(R.id.data);
            SQLiteDatabase database=myDatabase.getReadableDatabase();
            Cursor cursor= database.query("CAR_EMI",
                    new String[]{"ID","NAME","DATE","PRINCIPAL_AMOUNT","DOWN_PAYMENT","RATE","YEAR","EMI"},null,null,null,null,null);
            txt.setText("ID\tNAME\tPHONE\tPRINCIPAL_AMOUNT\tDOWN_PAYMENT\tRATE\tYEAR\tEMI\n");
            while(cursor.moveToNext())
            {
                txt.append(cursor.getString(0)+"\t");
                txt.append(cursor.getString(1)+"\t");
                txt.append(cursor.getString(2)+"\t");
                txt.append(cursor.getString(3)+"\t");
                txt.append(cursor.getString(4)+"\t");
                txt.append(cursor.getString(5)+"\t");
                txt.append(cursor.getString(6)+"\t");
                txt.append(cursor.getString(7)+"\n");
            }
        }
    }
}