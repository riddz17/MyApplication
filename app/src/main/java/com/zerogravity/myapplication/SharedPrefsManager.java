package com.zerogravity.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import com.securepreferences.SecurePreferences;

/**
 * A Singleton for managing Application Sharedpreferencess. <br/> The default operating mode is
 * {@link Context#MODE_PRIVATE} <br/> <br/> <b>IMPORTANT:</b> The class is not thread safe. It
 * should work fine in most circumstances since the write and read operations are fast. However if
 * you call edit for bulk updates and do not commit your changes there is a possibility of data loss
 * if a background thread has modified preferencess at the same time. <br/> <br/> <b>Usage:</b>
 * <br/> <p> int sampleInt = ApppreferencessEnumExample.instance(context).getInt(Key.SAMPLE_INT);
 * ApppreferencessEnumExample.instance().set(Key.SAMPLE_INT, sampleInt);
 * <p/>
 * If {@link SharedPrefsManager#instance(Context)} has been called once, you can simple use
 * {@link SharedPrefsManager#instance()} to save some precious line space.
 */

public class SharedPrefsManager {

  private static final String PREFERENCE_NAME = "default_prefs";
  private static SharedPrefsManager instance;
  private SecurePreferences preferences;
  private SharedPreferences.Editor editor;
  private boolean bulkUpdate = false;

  private SharedPrefsManager(Context context) {

    preferences = new SecurePreferences(context.getApplicationContext(),"",PREFERENCE_NAME);
  }

  public static SharedPrefsManager instance(Context context) {
    if (instance == null) {
      instance = new SharedPrefsManager(context.getApplicationContext());
    }
    return instance;
  }

  /**
   * Convenient method for obtaining the Singleton instance of this class without passing Context.
   * <br/> This method is to be used only after {@link SharedPrefsManager#instance(Context)} has
   * been called once.
   *
   * @return Singleton instance of {@link SharedPrefsManager}.
   * @throws IllegalArgumentException If {@link #instance(Context)} has not been called at least
   * once before
   */
  public static SharedPrefsManager instance() throws IllegalArgumentException {
    if (instance != null) {
      return instance;
    }

    //Option 1:
    throw new IllegalArgumentException(
        "Should use instance(Context) at least once before using this method.");

    //Option 2:
    // Alternatively, you can create a new instance here
    // with something like this:
    // instance(MyCustomApplication.getAppContext());
  }

  public void put(Key key, String val) {
    doEdit();
    editor.putString(key.name(), val);
    doCommit();
  }

  public void put(Key key, int val) {
    doEdit();
    editor.putInt(key.name(), val);
    doCommit();
  }

  public void put(Key key, boolean val) {
    doEdit();
    editor.putBoolean(key.name(), val);
    doCommit();
  }

  public void put(Key key, float val) {
    doEdit();
    editor.putFloat(key.name(), val);
    doCommit();
  }

  /**
   * Convenience method for storing doubles.
   * <p/>
   * There may be instances where the accuracy of a double is desired. Sharedpreferencess does not
   * handle doubles so they have to cast to and from String.
   *
   * @param key The enum of the preferences to store.
   * @param val The new value for the preferences.
   */
  public void put(Key key, double val) {
    doEdit();
    editor.putString(key.name(), String.valueOf(val));
    doCommit();
  }

  public void put(Key key, long val) {
    doEdit();
    editor.putLong(key.name(), val);
    doCommit();
  }

  public String getString(Key key, String defaultValue) {
    return preferences.getString(key.name(), defaultValue);
  }

  public String getString(Key key) {
    return preferences.getString(key.name(), null);
  }

  public int getInt(Key key) {
    return preferences.getInt(key.name(), 0);
  }

  public int getInt(Key key, int defaultValue) {
    return preferences.getInt(key.name(), defaultValue);
  }

  public long getLong(Key key) {
    return preferences.getLong(key.name(), 0);
  }

  public long getLong(Key key, long defaultValue) {
    return preferences.getLong(key.name(), defaultValue);
  }

  public float getFloat(Key key) {
    return preferences.getFloat(key.name(), 0);
  }

  public float getFloat(Key key, float defaultValue) {
    return preferences.getFloat(key.name(), defaultValue);
  }

  /**
   * Convenience method for retrieving doubles.
   * <p/>
   * There may be instances where the accuracy of a double is desired. Sharedpreferencess does not
   * handle doubles so they have to cast to and from String.
   *
   * @param key The enum of the preferences to fetch.
   */
  public double getDouble(Key key) {
    return getDouble(key, 0);
  }

  /**
   * Convenience method for retrieving doubles.
   * <p/>
   * There may be instances where the accuracy of a double is desired. Sharedpreferencess does not
   * handle doubles so they have to cast to and from String.
   *
   * @param key The enum of the preferences to fetch.
   */
  public double getDouble(Key key, double defaultValue) {
    try {
      return Double.valueOf(preferences.getString(key.name(), String.valueOf(defaultValue)));
    } catch (NumberFormatException nfe) {
      return defaultValue;
    }
  }

  public boolean getBoolean(Key key, boolean defaultValue) {
    return preferences.getBoolean(key.name(), defaultValue);
  }

  public boolean getBoolean(Key key) {
    return preferences.getBoolean(key.name(), false);
  }

  /**
   * Remove keys from Sharedpreferencess.
   *
   * @param keys The enum of the key(s) to be removed.
   */
  public void remove(Key... keys) {
    doEdit();
    for (Key key : keys) {
      editor.remove(key.name());
    }
    doCommit();
  }

  /**
   * Remove all keys from Sharedpreferencess.
   */
  public void clear() {
    doEdit();
    editor.clear();
    doCommit();
  }

  public void edit() {
    bulkUpdate = true;
    editor = preferences.edit();
  }

  public void commit() {
    bulkUpdate = false;
    editor.commit();
    editor = null;
  }


  private void doEdit() {
    if (!bulkUpdate && editor == null) {
      editor = preferences.edit();
    }
  }

  private void doCommit() {
    if (!bulkUpdate && editor != null) {
      editor.commit();
      editor = null;
    }
  }

  /**
   * Enum representing preferences keys. Add new field for each keys specific to the application
   * need.
   */
  public enum Key {
    SAMPLE_STR,
    SAMPLE_INT
  }
}