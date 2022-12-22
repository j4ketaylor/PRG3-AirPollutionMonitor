package com.example.PRG3AirPollutionMonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

public class Emergency extends AppCompatActivity {

    TextView emergency_information_text;
    TextView whatToDo_title;
    TextView whatToDo_text;
    TextView whatToDo_info;
    TextView call_999_title;
    TextView call_999_text;
    TextView AE_info;
    TextView call_111_title;
    TextView call_111_text;
    TextView NHS111_info;
    TextView urgentSymptoms_title;
    TextView urgentSymptoms_text;
    TextView urgentSymptoms_info;
    TextView routine_title;
    TextView routine_text;
    TextView reference_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        emergency_information_text = (TextView) findViewById(R.id.emergency_information);
        emergency_information_text.setText("Emergency Information\n");

        whatToDo_title = (TextView) findViewById(R.id.whatToDo_title);
        whatToDo_title.setText("What to do if you have an asthma attack:");

        whatToDo_text = (TextView) findViewById(R.id.whatToDo_text);
        whatToDo_text.setText("1. Sit up straight - try to keep calm.\n2. Take one puff of your "+
                            "reliever inhaler (usually blue) once every 30 to 60 seconds up to "+
                            "10 puffs.\n3. If you feel worse at any point, or you do not feel "+
                            "better after 10 puffs, call 999 for an ambulance.\n4. If the ambulance "+
                            "has not arrived after 10 minutes and your symptoms are not improving, "+
                            " repeat step 2.\n5. If your symptoms are no better after repeating "+
                            "step 2, and the ambulance has still not arrived, contact 999 again "+
                            "immediately.");

        whatToDo_info = (TextView) findViewById(R.id.whatToDo_info);
        whatToDo_info.setText("\nNever be frightened of calling for help in an emergency.\n"+
                            "\nTry to take the details of your medicines (or your personal "+
                            "asthma action plan) with you to hospital if possible.\n"+
                            "\nIf your symptoms improve and you do not need to call 999, get an "+
                            "urgent same-day appointment to see a GP or asthma nurse.\n");

        call_999_title = (TextView) findViewById(R.id.call_999_title);
        call_999_title.setText("Call 999 for an ambulance if:");

        call_999_text = (TextView) findViewById(R.id.call_999_text);
        call_999_text.setText("you're having an asthma attack and your symptoms don't improve after"+
                                " ten puffs of your reliever inhaler, or if you feel worse at any point.");

        AE_info = (TextView) findViewById(R.id.AE_info);
        String AE_link = "\nFind out what to expect in A&E here: "+
                "https://www.asthma.org.uk/advice/nhs-care/emergency-asthma-care/asthma-in-a-and-e/\n";
        AE_info.setText(AE_link);
        Linkify.addLinks(AE_info,Linkify.WEB_URLS);

        call_111_title = (TextView) findViewById(R.id.call_111_title);
        call_111_title.setText("Call 111 if:");

        call_111_text = (TextView) findViewById(R.id.call_111_text);
        call_111_text.setText("◉ your GP surgery is closed\n"+
                                "◉ your symptoms are getting in the way of your everyday activities\n"+
                                "◉ your symptoms are waking you up at night\n"+
                                "◉ you're using your reliever inhaler three or more times a week.");

        NHS111_info = (TextView) findViewById(R.id.NHS111_info);
        String NHS111_link = "\nThe NHS 111 service is available 24 hours a day, seven days a week: "+
                "https://www.nhs.uk/using-the-nhs/nhs-services/urgent-and-emergency-care/nhs-111/\n";
        NHS111_info.setText(NHS111_link);
        Linkify.addLinks(NHS111_info,Linkify.WEB_URLS);

        urgentSymptoms_title = (TextView) findViewById(R.id.urgentSymptoms_title);
        urgentSymptoms_title.setText("Ask for an urgent appointment with your GP or asthma nurse if:");

        urgentSymptoms_text = (TextView) findViewById(R.id.urgentSymptoms_text);
        urgentSymptoms_text.setText("◉ your symptoms are coming back (wheeze, tightness in "+
                                    "your chest, feeling breathless, cough)\n◉ you're waking "+
                                    "up at night because of your asthma\n◉ your symptoms are "+
                                    "getting in the way of your day-to-day routine (work, "+
                                    "family life, exercising)\n◉ you're having symptoms, and "+
                                    "using your reliever inhaler, three or more times a week.");

        urgentSymptoms_info = (TextView) findViewById(R.id.urgentSymptoms_info);
        urgentSymptoms_info.setText("\nIf your GP or nurse has given you a specific phone "+
                                    "number to call when you're concerned about asthma, continue "+
                                    "to use that number.\n\nBut if you're finding it hard to "+
                                    "breathe, or your reliever inhaler isn't helping, call 999.\n");

        routine_title = (TextView) findViewById(R.id.routine_title);
        routine_title.setText("Book a routine appointment with your GP or asthma nurse if:");

        routine_text = (TextView) findViewById(R.id.routine_text);
        routine_text.setText("◉ you're due an annual asthma review\n◉ you're worried about the "+
                            "side effects of your medicines, or medicines not working as well\n"+
                            "◉ you need health advice - for example, information about giving "+
                            "up smoking\n◉ you've just come out of hospital after an asthma "+
                            "attack - book an appointment within two days.");

        reference_text = (TextView) findViewById(R.id.reference_text);
        String reference_links = "\nSources: \n"+
                "https://www.asthma.org.uk/advice/manage-your-asthma/when-to-go-to-hospital\n"+
                "https://www.nhs.uk/conditions/asthma/asthma-attack/\n";
        reference_text.setText(reference_links);
        Linkify.addLinks(reference_text,Linkify.WEB_URLS);

    }


}