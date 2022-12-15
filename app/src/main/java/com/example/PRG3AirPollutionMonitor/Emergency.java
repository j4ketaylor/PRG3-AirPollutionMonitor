package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Emergency extends AppCompatActivity {

    TextView emergency_information_text;
    TextView emergency_information_NHS_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        emergency_information_text = (TextView) findViewById(R.id.emergency_information);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        emergency_information_text.setTypeface(customFont);
        emergency_information_text.setText("Emergency Information\n");

        emergency_information_NHS_text = (TextView) findViewById(R.id.emergency_information_NHS);
        emergency_information_NHS_text.setText("---Symptoms of an asthma attack---\n" +
                "\n" +
                "Signs that you may be having an asthma attack include:\n" +
                "\n" +
                "- Your symptoms are getting worse (cough, breathlessness, wheezing or tight chest)\n" +
                "- Your reliever inhaler (usually blue) is not helping\n" +
                "- You're too breathless to speak, eat or sleep\n" +
                "- Your breathing is getting faster and it feels like you cannot catch your breath\n" +
                "- Your peak flow score is lower than normal\n" +
                "- Children may also complain of a tummy or chest ache\n\n" +
                "The symptoms will not necessarily occur suddenly. In fact, they often come on slowly over a few hours or days.\n" +
                "\n" +
                "---What to do if you have an asthma attack---\n" +
                "\n" +
                "If you think you're having an asthma attack, you should:\n" +
                "\n" +
                "Sit up straight – try to keep calm.\n" +
                "- Take one puff of your reliever inhaler (usually blue) every 30 to 60 seconds up to 10 puffs.\n" +
                "- If you feel worse at any point, or you do not feel better after 10 puffs, call 999 for an ambulance.\n" +
                "- If the ambulance has not arrived after 10 minutes and your symptoms are not improving, repeat step 2.\n" +
                "- If your symptoms are no better after repeating step 2, and the ambulance has still not arrived, contact 999 again immediately.\n" +
                "- Never be frightened of calling for help in an emergency.\n" +
                "\n" +
                "Try to take the details of your medicines (or your personal asthma action plan) with you to hospital if possible.\n" +
                "\n" +
                "If your symptoms improve and you do not need to call 999, get an urgent same-day appointment to see a GP or asthma nurse." +
                "\n\n" + "Data from: https://www.nhs.uk/conditions/asthma/asthma-attack/ \n");
    }


}