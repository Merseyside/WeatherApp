package com.merseyside.admin.weatherapp.data.exception;

import android.content.Context;

import com.merseyside.admin.weatherapp.R;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by ivan_ on 11.10.2017.
 */

public class ErrorMessageFactory {

    public static String create(Context context, Exception exception) {

        String message;
        if (exception instanceof JSONException) {
            message = context.getString(R.string.json_error_message);
        } else if (exception instanceof IOException) {
            message = context.getString(R.string.io_error_message);
        } else {
            message = context.getString(R.string.unknown_error);
        }

        return message;
    }

    public static String create(Throwable throwable) {
        return throwable.toString();
    }
}
