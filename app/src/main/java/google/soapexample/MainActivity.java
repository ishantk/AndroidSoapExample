package google.soapexample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String celsius;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText fahrenheitEditText =  findViewById(R.id.fahrenheitEditText);
        final Button convertButton = findViewById(R.id.convertButton);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fahrenheitEditText.getText().toString().isEmpty()) {
                    getCelsius(fahrenheitEditText.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Fahrenheit value can not be empty.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    Toast.makeText(MainActivity.this, "Response is: "+celsius, Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        }
    });

    private void getCelsius(final String toConvert) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        SoapRequests ex = new SoapRequests();
                        celsius = ex.getCelsiusConversion(toConvert);
                        handler.sendEmptyMessage(101);
                    }
                }
        ).start();
    }
}
