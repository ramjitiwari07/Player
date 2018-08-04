/*
 * Copyright (c) 2016 - Firoz Memon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.ConnectivityManager;

/**
 * Created by firoz
 */
public class Util {
    private Util() {

    }
    public static String isInternetExist(Context context) {
        String internetStatus = "";
        ConnectivityManager connec = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            internetStatus = "true";
        } else {
            internetStatus = "false";
        }
        return  internetStatus;
    }

    public static boolean isLangSupported(Context context, String text) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        int w = 200, h = 80;
        Resources resources = context.getResources();
        float scale = resources.getDisplayMetrics().density;
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Bitmap orig = bitmap.copy(conf, false);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setTextSize((int) (14 * scale));

        // draw text to the Canvas center
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int x = (bitmap.getWidth() - bounds.width()) / 2;
        int y = (bitmap.getHeight() + bounds.height()) / 2;

        canvas.drawText(text, x, y, paint);
        boolean res = false;
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            res = !(orig == bitmap);
        } else {
            res = !orig.sameAs(bitmap);
        }
        orig.recycle();
        bitmap.recycle();
        return res;
    }

    public static void displayAlert(Activity activity, String titleText, String messageText) {
        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(titleText)
                .setMessage(messageText)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }


    public static boolean isInternetWorking(Context context) {
        boolean internetStatus = false;
        if(context != null)
        {
            ConnectivityManager connec = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
            // Check for network connections
            if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                    connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
                internetStatus = true;
            } else {
                internetStatus = false;
            }
        }

        return  internetStatus;
    }


     /* private static String md5(String s) {
        try {
            byte[] bytes = s.getBytes();
            MessageDigest m = MessageDigest.getInstance("MD5");
            byte[] digest = m.digest(bytes);
            String hash = new BigInteger(1, digest).toString(16);
            System.out.println(hash);
            Log.i("hashval", "" + hash.toString());
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }*/
    /*public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }*/
   /*  case R.id.mem_add_ll:
                if(count==1) {
                    count = count + 1;
                    mm_ll1.setVisibility(View.VISIBLE);
                }
                else if(count==2){
                  if(mem_edt1.getText().toString().length()>2){
                    count = count + 1;
                    mm_ll2.setVisibility(View.VISIBLE);
                  }
                  else{
                    showAlertDialog(RegistrationActivity.this, getResources().getString(R.string.err_msg_not_addmem));
                  }
                }
                else if(count==3){

                  if(mem_edt2.getText().toString().length()>2){
                    count = count + 1;
                    mm_ll3.setVisibility(View.VISIBLE);
                  }
                  else{
                    showAlertDialog(RegistrationActivity.this, getResources().getString(R.string.err_msg_not_addmem));

                  }

                }
                else if(count==4){

                  if(mem_edt3.getText().toString().length()>2){
                    count = count + 1;
                    mm_ll4.setVisibility(View.VISIBLE);
                  }
                  else{
                    showAlertDialog(RegistrationActivity.this, getResources().getString(R.string.err_msg_not_addmem));

                  }
                }
                else if(count==5){

                  if(mem_edt4.getText().toString().length()>2){
                    count = count + 1;
                    mm_ll5.setVisibility(View.VISIBLE);
                  }
                  else{
                    showAlertDialog(RegistrationActivity.this, getResources().getString(R.string.err_msg_not_addmem));

                  }
                }
                else if(count==6){

                  if(mem_edt5.getText().toString().length()>2){
                    count = count + 1;
                    mm_ll6.setVisibility(View.VISIBLE);
                  }
                  else{
                    showAlertDialog(RegistrationActivity.this, getResources().getString(R.string.err_msg_not_addmem));

                  }
                }
                else if(count==7){
                  if (getCurrentFocus() != null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                  }
                  if(mem_edt6.getText().toString().length()>2){
                    count = count + 1;
                    mm_ll7.setVisibility(View.VISIBLE);
                  }
                  else{
                    showAlertDialog(RegistrationActivity.this, getResources().getString(R.string.err_msg_not_addmem));

                  }
                }
                else if(count==8){

                  if(mem_edt7.getText().toString().length()>2){
                    count = count + 1;
                    mm_ll8.setVisibility(View.VISIBLE);
                  }
                  else{
                    showAlertDialog(RegistrationActivity.this, getResources().getString(R.string.err_msg_not_addmem));

                  }
                }
                else if(count==9){

                  if(mem_edt8.getText().toString().length()>2){
                    count = count + 1;
                    mm_ll9.setVisibility(View.VISIBLE);
                  }
                  else{
                    showAlertDialog(RegistrationActivity.this, getResources().getString(R.string.err_msg_not_addmem));

                  }
                }
                else if(count==10){

                  if(mem_edt9.getText().toString().length()>2){
                    count = count + 1;
                    mm_ll10.setVisibility(View.VISIBLE);
                  }
                  else{
                    showAlertDialog(RegistrationActivity.this, getResources().getString(R.string.err_msg_not_addmem));

                  }
                }
                else {
                    Toast.makeText(RegistrationActivity.this,"max limit",Toast.LENGTH_LONG).show();
                }
                    break;
            case R.id.memimg1:
                mm_ll1.setVisibility(View.GONE);
                count= count-2;
              mem_edt1.setText("");
                break;
            case R.id.memimg2:
                mm_ll2.setVisibility(View.GONE);
                count= count-1;
              mem_edt2.setText("");
                break;
            case R.id.memimg3:
                mm_ll3.setVisibility(View.GONE);
                count= count-1;
              mem_edt3.setText("");
                break;
            case R.id.memimg4:
                mm_ll4.setVisibility(View.GONE);
                count= count-1;
              mem_edt4.setText("");
                break;
            case R.id.memimg5:
                count= count-1;
                mm_ll5.setVisibility(View.GONE);
              mem_edt5.setText("");
                break;
            case R.id.memimg6:
                mm_ll6.setVisibility(View.GONE);
                count= count-1;
              mem_edt6.setText("");
                break;
            case R.id.memimg7:
                count= count-1;
                mm_ll7.setVisibility(View.GONE);
              mem_edt7.setText("");
                break;
            case R.id.memimg8:
                count= count-1;
                mm_ll8.setVisibility(View.GONE);
              mem_edt8.setText("");
                break;
            case R.id.memimg9:
                count= count-1;
                mm_ll9.setVisibility(View.GONE);
              mem_edt9.setText("");
                break;
            case R.id.memimg10:
                mm_ll10.setVisibility(View.GONE);
                count= count-1;
              mem_edt10.setText("");
                break;*/

  /*  private static String[] units=
            {"",
                    " One",
                    " Two",
                    " Three",
                    " Four",
                    " Five",
                    " Six",
                    " Seven",
                    " Eight",
                    " Nine"
            };*/
   /* private static String[] teen=
            {" Ten",
                    " Eleven",
                    " Twelve",
                    " Thirteen",
                    " Fourteen",
                    " Fifteen",
                    " Sixteen",
                    " Seventeen",
                    " Eighteen",
                    " Nineteen"
            };*/
   /* private static String[] tens=
            { " Twenty",
                    " Thirty",
                    " Forty",
                    " Fifty",
                    " Sixty",
                    " Seventy",
                    " Eighty",
                    " Ninety"
            };*/
  /*  private static String[] maxs=
            {"",
                    "",
                    " Hundred",
                    " Thousand",
                    " Lakh",
                    " Crore"
            };*/

/*  // convert internal Java String format to UTF-8
    public static JSONObject convertStringToUTF8(JSONObject json) {
        JSONObject out = null;
            try {
                out = new JSONObject(json.getJSONObject("UTF-8"), "ISO-8859-1");
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        return out;
    }*/
}
