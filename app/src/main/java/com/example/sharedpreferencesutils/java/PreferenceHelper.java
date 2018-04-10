package com.example.sharedpreferencesutils.java;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceHelper {

    private PreferenceHelper() {
    }

    private static void edit(Context context, Performer<SharedPreferences.Editor> performer) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        performer.performOperation(editor);
        editor.apply();
    }

    public static <T> void setValue(Context context, String key, T value) {
        if (value instanceof String) {
            edit(context, (editor) -> editor.putString(key, (String) value));
        } else if (value instanceof Boolean) {
            edit(context, (editor) -> editor.putBoolean(key, (Boolean) value));
        } else if (value instanceof Integer) {
            edit(context, (editor) -> editor.putInt(key, (Integer) value));
        } else if (value instanceof Float) {
            edit(context, (editor) -> editor.putFloat(key, (Float) value));
        } else {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    public static <T> T getValue(Context context, String key, Class<?> klass, T defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Object value;
        if (klass.equals(String.class)) {
            value = prefs.getString(key, (String) defaultValue);
        } else if (klass.equals(Boolean.class)) {
            value = prefs.getBoolean(key, (Boolean) defaultValue);
        } else if (klass.equals(Integer.class)) {
            value = prefs.getInt(key, (Integer) defaultValue);
        } else if (klass.equals(Float.class)) {
            value = prefs.getFloat(key, (Float) defaultValue);
        } else {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
        return (T) value;
    }
}
