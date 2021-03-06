package com.freelance.razak.classportal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Razak on 2/5/2017.
 */

public class LogicGateCalcActivity extends Fragment {
    protected ToggleButton toggle1,toggle2,ortoggle1,ortoggle2,nottoggle1;
    protected TextView logictext,logiccalc,ortextresult,nottextresult;
    protected ImageView andgateimg,orgateimg,notgateimg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.logic_gate_layout,container,false);

        andgateimg = (ImageView) v.findViewById(R.id.andgatepic);
        orgateimg = (ImageView) v.findViewById(R.id.orgatepic);
        notgateimg = (ImageView) v.findViewById(R.id.notgatepic);
        toggle1 = (ToggleButton) v.findViewById(R.id.toggleButton1);
        toggle2 = (ToggleButton) v.findViewById(R.id.toggleButton2);
        ortoggle1 = (ToggleButton) v.findViewById(R.id.orToggle1);
        ortoggle2 = (ToggleButton) v.findViewById(R.id.orToggle2);
        nottoggle1 = (ToggleButton) v.findViewById(R.id.notToggle1);
        nottextresult = (TextView) v.findViewById(R.id.notTextresult);
        ortextresult = (TextView) v.findViewById(R.id.orTextResult);
        logictext = (TextView) v.findViewById(R.id.logicText);
        logiccalc = (TextView) v.findViewById(R.id.logicGateId);
        nottoggle1click();
        toggle1click();
        toggle2click();
        ortoggle1click();
        ortoggle2click();
        andResult();
        orResult();
        andimgbitmap();
        orimgbitmap();
        notimgbitmap();
        return v;
    }

    public void nottoggle1click(){
        nottoggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notResult();
            }
        });
    }

    public void ortoggle1click(){
        ortoggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orResult();
            }
        });
    }

    public void ortoggle2click(){
        ortoggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orResult();
            }
        });
    }

    public void toggle1click(){
        toggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                andResult();
            }
        });
    }

    public void toggle2click(){
        toggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                andResult();
            }
        });
    }

    public void andResult(){
        boolean toggle1checked = toggle1.isChecked();
        boolean toggle2checked = toggle2.isChecked();

        if(toggle1checked){
            Drawable d = getResources().getDrawable(R.drawable.toggledesignon);
            toggle1.setBackgroundDrawable(d);
        }else{
            Drawable d = getResources().getDrawable(R.drawable.toggledesign);
            toggle1.setBackgroundDrawable(d);
        }

        if(toggle2checked){
            Drawable d = getResources().getDrawable(R.drawable.toggledesignon);
            toggle2.setBackgroundDrawable(d);
        }else{
            Drawable d = getResources().getDrawable(R.drawable.toggledesign);
            toggle2.setBackgroundDrawable(d);
        }

        if(toggle1checked && toggle2checked){
            logictext.setText("ON");
        }else{
            logictext.setText("OFF");
        }
    }

    public void orResult(){
        boolean toggle1checked = ortoggle1.isChecked();
        boolean toggle2checked = ortoggle2.isChecked();

        if(toggle1checked){
            Drawable d = getResources().getDrawable(R.drawable.toggledesignon);
            ortoggle1.setBackgroundDrawable(d);
        }else{
            Drawable d = getResources().getDrawable(R.drawable.toggledesign);
            ortoggle1.setBackgroundDrawable(d);
        }

        if(toggle2checked){
            Drawable d = getResources().getDrawable(R.drawable.toggledesignon);
            ortoggle2.setBackgroundDrawable(d);
        }else{
            Drawable d = getResources().getDrawable(R.drawable.toggledesign);
            ortoggle2.setBackgroundDrawable(d);
        }

        if(toggle1checked || toggle2checked){
            ortextresult.setText("ON");
        }else{
            ortextresult.setText("OFF");
        }
    }

    public void notResult(){
        boolean nottoggle1checked = nottoggle1.isChecked();
        if(nottoggle1checked){
            Drawable d = getResources().getDrawable(R.drawable.toggledesignon);
            nottoggle1.setBackgroundDrawable(d);
        }else{
            Drawable d = getResources().getDrawable(R.drawable.toggledesign);
            nottoggle1.setBackgroundDrawable(d);
        }

        if(nottoggle1checked){
            nottextresult.setText("OFF");
        }else{
            nottextresult.setText("ON");
        }
    }

    //  Convert image to bitmap
    public void andimgbitmap(){
        andgateimg.buildDrawingCache();
        andgateimg.getDrawingCache();
    }

    public void orimgbitmap(){
        orgateimg.buildDrawingCache();
        orgateimg.getDrawingCache();
    }

    public void notimgbitmap(){
        notgateimg.buildDrawingCache();
        notgateimg.getDrawingCache();
    }
    //  Convert image to bitmap

}
