package com.example.PRG3AirPollutionMonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Emergency extends AppCompatActivity {

    TextView emergency_information_text;
    TextView emergency_information_NHS_text;

    private int titleColor = Color.parseColor("#0E86D4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        emergency_information_text = (TextView) findViewById(R.id.emergency_information);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        emergency_information_text.setTypeface(customFont);
        emergency_information_text.setText("Emergency Information\n");

        emergency_information_NHS_text = (TextView) findViewById(R.id.emergency_information_NHS);

        SpannableStringBuilder symptomsTitle = new SpannableStringBuilder();
        symptomsTitle.append("Symptoms of an asthma attack\n");
        symptomsTitle.setSpan(new ForegroundColorSpan(titleColor),0,symptomsTitle.length(),0);
        symptomsTitle.setSpan(new AbsoluteSizeSpan(75),0,symptomsTitle.length(),0);
        symptomsTitle.setSpan(new StyleSpan(Typeface.BOLD),0,symptomsTitle.length(),0);
        symptomsTitle.append("\nSigns that you may be having an asthma attack include:\n");
        symptomsTitle.setSpan(new AbsoluteSizeSpan(60),28,symptomsTitle.length(),0);
        symptomsTitle.setSpan(new ForegroundColorSpan(Color.BLACK),28,symptomsTitle.length(),0);

        SpannableStringBuilder symptomsBullets = new SpannableStringBuilder();
        symptomsBullets.append("\n• Your symptoms are getting worse (cough, breathlessness, wheezing or tight chest)\n");
        symptomsBullets.append("• Your reliever inhaler (usually blue) is not helping\n");
        symptomsBullets.append("• You're too breathless to speak, eat or sleep\n");
        symptomsBullets.append("• Your breathing is getting faster and it feels like you cannot catch your breath\n");
        symptomsBullets.append("• Your peak flow score is lower than normal\n");
        symptomsBullets.append("• Children may also complain of a tummy or chest ache\n\n");
        symptomsBullets.setSpan(new AbsoluteSizeSpan(55),0,symptomsBullets.length(),0);
        symptomsBullets.append("The symptoms will not necessarily occur suddenly. In fact, they often come on slowly over a few hours or days.\n");
        symptomsBullets.setSpan(new AbsoluteSizeSpan(60),365,symptomsBullets.length(),0);
        symptomsBullets.setSpan(new ForegroundColorSpan(Color.BLACK),365,symptomsBullets.length(),0);

        SpannableStringBuilder whatToDoTitle = new SpannableStringBuilder();
        whatToDoTitle.append("\n\nWhat to do if you have an asthma attack\n");
        whatToDoTitle.setSpan(new ForegroundColorSpan(titleColor),0,whatToDoTitle.length(),0);
        whatToDoTitle.setSpan(new AbsoluteSizeSpan(75),0,whatToDoTitle.length(),0);
        whatToDoTitle.setSpan(new StyleSpan(Typeface.BOLD),0,whatToDoTitle.length(),0);

        SpannableStringBuilder whatToDoText = new SpannableStringBuilder();
        whatToDoText.append("\nIf you think you're having an asthma attack, you should:\n");
        whatToDoText.setSpan(new AbsoluteSizeSpan(60),0,whatToDoText.length(),0);
        whatToDoText.setSpan(new ForegroundColorSpan(Color.BLACK),0,whatToDoText.length(),0);
        whatToDoText.append("\n1. Sit up straight – try to keep calm.\n");
        whatToDoText.append("2. Take one puff of your reliever inhaler (usually blue) every 30 to 60 seconds up to 10 puffs.\n");
        whatToDoText.append("3. If you feel worse at any point, or you do not feel better after 10 puffs, call 999 for an ambulance.\n");
        whatToDoText.append("4. If the ambulance has not arrived after 10 minutes and your symptoms are not improving, repeat step 2.\n");
        whatToDoText.append("5. If your symptoms are no better after repeating step 2, and the ambulance has still not arrived, contact 999 again immediately.\n");
        whatToDoText.setSpan(new AbsoluteSizeSpan(55),61,whatToDoText.length(),0);
        whatToDoText.append("\nNever be frightened of calling for help in an emergency.\n");
        whatToDoText.append("\nTry to take the details of your medicines (or your personal asthma action plan) with you to hospital if possible.\n");
        whatToDoText.append("\nIf your symptoms improve and you do not need to call 999, get an urgent same-day appointment to see a GP or asthma nurse.\n");
        whatToDoText.setSpan(new AbsoluteSizeSpan(60),534,whatToDoText.length(),0);
        whatToDoText.setSpan(new ForegroundColorSpan(Color.BLACK),534,whatToDoText.length(),0);

        SpannableStringBuilder nhsLink = new SpannableStringBuilder();
        nhsLink.append("\n\nFor more information, please visit: https://www.nhs.uk/conditions/asthma/asthma-attack/\n");
        nhsLink.setSpan(new AbsoluteSizeSpan(75),0,nhsLink.length(),0);
        nhsLink.setSpan(new StyleSpan(Typeface.BOLD),0,nhsLink.length(),0);
        ClickableSpan nhsLinkSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.nhs.uk/conditions/asthma/asthma-attack/"));
                view.getContext().startActivity(intent);
            }
        };
        nhsLink.setSpan(nhsLinkSpan,37,nhsLink.length(),0);

        SpannableStringBuilder emergency_info = new SpannableStringBuilder();
        emergency_info.append(symptomsTitle);
        emergency_info.append(symptomsBullets);
        emergency_info.append(whatToDoTitle);
        emergency_info.append(whatToDoText);
        emergency_info.append(nhsLink);

        emergency_information_NHS_text.setMovementMethod(LinkMovementMethod.getInstance());
        emergency_information_NHS_text.setText(emergency_info);
    }


}