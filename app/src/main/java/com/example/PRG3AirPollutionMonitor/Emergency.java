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
        emergency_information_NHS_text.setText("Symptoms of an asthma attack\n" +
                "\n" +
                "Signs that you may be having an asthma attack include:\n" +
                "\n" +
                "your symptoms are getting worse (cough, breathlessness, wheezing or tight chest)\n" +
                "your reliever inhaler (usually blue) is not helping\n" +
                "you're too breathless to speak, eat or sleep\n" +
                "your breathing is getting faster and it feels like you cannot catch your breath\n" +
                "your peak flow score is lower than normal\n" +
                "children may also complain of a tummy or chest ache\n" +
                "The symptoms will not necessarily occur suddenly. In fact, they often come on slowly over a few hours or days.\n" +
                "\n" +
                "What to do if you have an asthma attack\n" +
                "\n" +
                "If you think you're having an asthma attack, you should:\n" +
                "\n" +
                "Sit up straight – try to keep calm.\n" +
                "Take one puff of your reliever inhaler (usually blue) every 30 to 60 seconds up to 10 puffs.\n" +
                "If you feel worse at any point, or you do not feel better after 10 puffs, call 999 for an ambulance.\n" +
                "If the ambulance has not arrived after 10 minutes and your symptoms are not improving, repeat step 2.\n" +
                "If your symptoms are no better after repeating step 2, and the ambulance has still not arrived, contact 999 again immediately.\n" +
                "Never be frightened of calling for help in an emergency.\n" +
                "\n" +
                "Try to take the details of your medicines (or your personal asthma action plan) with you to hospital if possible.\n" +
                "\n" +
                "If your symptoms improve and you do not need to call 999, get an urgent same-day appointment to see a GP or asthma nurse.\n" +
                "\n" +
                "This advice is not for people on SMART or MART treatment. If this applies to you, ask a GP or asthma nurse what to do if you have an asthma attack.\n" +
                "\n" +
                "After an asthma attack\n" +
                "\n" +
                "You should see a GP or asthma nurse within 48 hours of leaving hospital, or ideally on the same day if you did not need hospital treatment.\n" +
                "\n" +
                "About 1 in 6 people treated in hospital for an asthma attack need hospital care again within 2 weeks, so it's important to discuss how you can reduce your risk of future attacks.\n" +
                "\n" +
                "Talk to a doctor or nurse about any changes that may need to be made to manage your condition safely.\n" +
                "\n" +
                "For example, the dose of your treatment may need to be adjusted or you may need to be shown how to use your inhaler correctly.\n" +
                "\n" +
                "Preventing asthma attacks\n" +
                "\n" +
                "The following steps can help you reduce your risk of having an asthma attack:\n" +
                "\n" +
                "follow your personal asthma action plan and take all of your medicines as prescribed\n" +
                "have regular asthma reviews with a GP or asthma nurse – these should be done at least once a year\n" +
                "check with a GP or asthma nurse that you're using your inhaler correctly\n" +
                "avoid things that trigger your symptoms whenever possible\n" +
                "Do not ignore your symptoms if they're getting worse or you need to use your reliever inhaler more often than usual.\n" +
                "\n" +
                "Follow your action plan and make an urgent appointment to see a GP or asthma nurse if your symptoms continue to get worse.\n" +
                "\n" +
                "Advice for friends and family\n" +
                "\n" +
                "It's important that your friends and family know how to help in an emergency.\n" +
                "\n" +
                "It can be useful to make copies of your personal asthma action plan and share it with others who may need to know what to do when you have an attack.\n" +
                "\n" +
                "You can photocopy your existing plan, or you could download a blank personal asthma action plan from Asthma UK and fill it in for anyone who might need a copy.\n" +
                "\n" +
                "Or you could take a photo of your action plan on your phone, so you can show or send it to others easily.\n" +
                "\n" + "Data from: https://www.nhs.uk/conditions/asthma/asthma-attack/ \n");
    }


}