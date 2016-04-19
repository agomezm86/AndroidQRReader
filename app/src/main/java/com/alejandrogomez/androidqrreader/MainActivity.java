package com.alejandrogomez.androidqrreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button qrCodeButton = (Button)findViewById(R.id.button);
        qrCodeButton.setOnClickListener(this);

        textView = (TextView)findViewById(R.id.text_view);
        textView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.button:
                showQRCodeView();
                break;
        }
    }

    private void showQRCodeView() {
        Intent intent = new Intent(getString(R.string.QR_intent_name));
        intent.putExtra(getString(R.string.QR_scan_name), getString(R.string.QR_mode_name));
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contentString = intent.getStringExtra(getString(R.string.QR_scan_result));
                textView.setVisibility(View.VISIBLE);
                textView.setText(contentString);
            } else {
                Toast.makeText(this, getString(R.string.QR_code_scan_cancel),
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
