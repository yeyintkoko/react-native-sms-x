package com.someone.sendsms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by yeyintkoko on 11/4/16.
 */

public class SendSMS extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public SendSMS(ReactApplicationContext reactContext) {

        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "SendSMS";
    }

    //---sends an SMS message to another device---
    @ReactMethod
    public void send(String phoneNumber, String message, final Callback callback ){

        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(reactContext, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(reactContext, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        reactContext.registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        callback.invoke("SMS sent");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        callback.invoke("Generic failure");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        callback.invoke("No service");
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        callback.invoke("Null PDU");
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        callback.invoke("Radio off");
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        reactContext.registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        System.out.println("SMS delivered");
                        //callback.invoke("SMS delivered");
                        break;
                    case Activity.RESULT_CANCELED:
                        System.out.println("SMS not delivered");
                        //callback.invoke("SMS not delivered");
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);

    }

}
