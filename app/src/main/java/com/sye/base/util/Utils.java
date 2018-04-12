package com.sye.base.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.inputmethod.InputMethodManager;

import com.sye.base.BuildConfig;
import com.sye.base.R;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

public class Utils {

    public static class Connection {

        public static boolean isOnline(Context context) {
            NetworkInfo netInfo = getNetworkInfo(context);
            return (netInfo != null && netInfo.isConnected());
        }

        private static NetworkInfo getNetworkInfo(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo();
        }

        public static boolean isGPSEnabled(Context context) {
            LocationManager manager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        public static boolean isConnectedWifi(Context context){
            NetworkInfo info = getNetworkInfo(context);
            return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
        }

        public static boolean isConnectedMobile(Context context){
            NetworkInfo info = getNetworkInfo(context);
            return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
        }

    }

    public static class DateAndTime {

        public static Date getDateObject() {
            return Calendar.getInstance(Locale.getDefault()).getTime();
        }

        public static String getTime(String outputFormat) {
            Date date = getDateObject();
            String date_s = date.toString();
            SimpleDateFormat dt = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy", Locale.getDefault());
            try {
                date = dt.parse(date_s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat dt1 = new SimpleDateFormat(outputFormat, Locale.getDefault());
            return dt1.format(date);
        }

        public static String getDate(String outputFormat) {
            Date date = getDateObject();
            String date_s = date.toString();
            SimpleDateFormat dt = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy", Locale.getDefault());
            try {
                date = dt.parse(date_s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat dt1 = new SimpleDateFormat(outputFormat, Locale.getDefault());
            return dt1.format(date);
        }

        public static String dateParse(String strDate, String input, String output) {
            String dateSurvey = "";
            SimpleDateFormat formatter = new SimpleDateFormat(input, Locale.getDefault());
            try {
                Date date = formatter.parse(strDate);
                SimpleDateFormat format = new SimpleDateFormat(output, Locale.getDefault());
                dateSurvey = format.format(date);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return dateSurvey;
        }

    }

    public static class Actions {

        public static void callContact(Context context, String phoneNumber) {

            if (phoneNumber != null && !phoneNumber.equals("") && !phoneNumber.equals(" ")) {
                //Intent call = new Intent(Intent.ACTION_CALL);
                Intent call = new Intent(Intent.ACTION_DIAL);
                //expresion regular para limpiar el string de caracteres q no sean digitos
                call.setData(Uri.parse("tel:" + phoneNumber.replaceAll("\\D+", "")));
                context.startActivity(call);
            }
        }

        public static void sendEmail(Context context, String email, @Nullable String subject) {

            if (email != null && !email.equals("") && !email.equals(" ")) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto: " + email));
                if (subject != null) intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                context.startActivity(Intent.createChooser(intent,
                        context.getResources().getString(R.string.dialog_title_send_email)));
            }
        }

        public static void openPlayStore(Context context) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID));
            context.startActivity(intent);
        }

        public static void updateSystemFiles(Context context, String path) {
            Intent mediaScan = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.parse(path);
            mediaScan.setData(contentUri);
            context.sendBroadcast(mediaScan);
        }

        public static boolean deleteFromDevice(String path) {
            File file = new File(path);
            return file.exists() && file.delete();
        }

        public static void showKeyboardIfHidden(Context context) {
            InputMethodManager imm = (InputMethodManager)
                    context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (imm.isActive()){
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY); // hide
            }
        }

        public static boolean opentApplicationDetails(Context context) {
            try {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException exception) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                context.startActivity(intent);
                return false;
            }
        }

        public static void shareTextToExternal(Context context, String message) {
            Intent chooser = new Intent();
            chooser.setAction(Intent.ACTION_SEND);
            chooser.setType("text/plain");
            chooser.putExtra(Intent.EXTRA_TEXT, message);
            context.startActivity(Intent.createChooser(chooser, context.getString(R.string.label_share_via)));
        }

    }

    public static class Components {

        //IntentService

        //BroadcastReceiver
    }
}
