package com.sye.base.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fonts {

    public static void changeFonts(Context context, View view) {
        try {
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    changeFonts(context, child);
                }
            } else if (view instanceof TextView) {
                TextView textView = (TextView) view;
                String fontName = Set.FONT_NAME_REGULAR;
                if (textView.getTag() != null) {
                    if (textView.getTag().equals(Set.TAG_STYLE_BOLD))
                        fontName = Set.FONT_NAME_BOLD;
                    else if (textView.getTag().equals(Set.TAG_STYLE_ITALIC))
                        fontName = Set.FONT_NAME_ITALIC;
                    else if (textView.getTag().equals(Set.TAG_STYLE_SEMI))
                        fontName = Set.FONT_NAME_DEMI;
                }

                Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
                textView.setTypeface(typeface);
            }
            if (view instanceof TextInputLayout) {
                Typeface typeface = Typeface.createFromAsset(context.getAssets(), Set.FONT_NAME_REGULAR);
                ((TextInputLayout) view).setTypeface(typeface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
