/*******************************************************************************
 * Any modification, copies of sections of this file must be attached with this
 * license and shown clearly in the developer's project. The code can be used
 * as long as you state clearly you do not own it. Any violation might result in
 *  a take-down.
 *
 * MIT License
 *
 * Copyright (c) 2016, 2017 Anthony Law
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package com.github.mob41.osumer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Config {
    
    public static final String CHECK_UPDATE_FREQ_EVERY_STARTUP = "everyStartup";
    
    public static final String CHECK_UPDATE_FREQ_EVERY_ACT = "everyAct";
    
    public static final String CHECK_UPDATE_FREQ_NEVER = "never";
    
    public static final String CHECK_UPDATE_ALGO_PER_VER_PER_BRANCH = "perVerPerBranch";
            
    public static final String CHECK_UPDATE_ALGO_LATEST_VER_PER_BRANCH = "latestVerPerBranch";
    
    public static final String CHECK_UPDATE_ALGO_LATEST_VER_OVERALL = "latestVerOverall";
    
    public static final String CHECK_UPDATE_ALGO_STABLITY = "stablity";

    public static final String DEFAULT_DATA_FILE_NAME = "osumer_configuration.json";

    private static final String KEY_UPDATE_SOURCE = "updateSource";

    private static final int KEY_UPDATE_SOURCE_DEFAULT_VALUE = Osumer.updateSourceStrToInt(Osumer.OSUMER_BRANCH);

    private static final String KEY_DEFAULT_BROWSER = "defaultBrowser";

    private static final String KEY_DEFAULT_BROWSER_DEFAULT_VALUE = "IEXPLORE.EXE";
    
    private static final String KEY_DEFAULT_OPEN_BEATMAP_ACTION = "defaultOpenBeatmapAction";
    
    private static final int KEY_DEFAULT_OPEN_BEATMAP_ACTION_DEFAULT_VALUE = 0;
    
    private static final String KEY_DEFAULT_BEATMAP_SAVE_LOCATION = "defaultBeatmapSaveLocation";
    
    private static final String KEY_DEFAULT_BEATMAP_SAVE_LOCATION_DEFAULT_VALUE = System.getenv("USERPROFILE") + "\\osumerDownloads";

    private static final String KEY_OE_ENABLED = "oeenabled";

    private static final boolean KEY_OE_ENABLED_DEFAULT_VALUE = true;

    private static final String KEY_NO_UI_ARG_SWITCH_BROWSER = "switchToBrowserIfWithoutUiArg";

    private static final boolean KEY_NO_UI_ARG_SWITCH_BROWSER_DEFAULT_VALUE = false;

    private static final String KEY_AUTO_SWITCH_BROWSER = "autoSwitchBrowser";

    private static final boolean KEY_AUTO_SWITCH_BROWSER_DEFAULT_VALUE = true;

    private static final String KEY_GETTING_STARTED_STARTUP = "gettingStartedOnStartup_Version" + Osumer.OSUMER_VERSION
            + "-" + Osumer.OSUMER_BRANCH + "-" + Osumer.OSUMER_BUILD_NUM;

    private static final boolean KEY_GETTING_STARTED_STARTUP_DEFAULT_VALUE = true;

    private static final String KEY_SERVER_PRORITY = "serverPrority";

    private static final String[] KEY_SERVER_PRORITY_DEFAULT_VALUE = { "osu! forum" }; // bloodcat
                                                                                       // not
                                                                                       // available

    private static final String KEY_ENC_ASK_ON_STARTUP = "usrPwdEncAskOnStartup";

    private static final boolean KEY_ENC_ASK_ON_STARTUP_DEFAULT_VALUE = true;

    private static final String KEY_USER = "user";

    private static final String KEY_PASS = "pass";

    private static final String KEY_ENC = "usrPwdEnc";

    private static final boolean KEY_ENC_DEFAULT_VALUE = false;

    private static final String KEY_ENC_SALT = "usrPwdEncSalt";

    private static final String KEY_ENC_IV = "usrPwdEncIv";

    private static final String KEY_RUN_DAEMON_ON_WIN_STARTUP = "runDaemonOnWinStartup";

    private static final boolean KEY_RUN_DAEMON_ON_WIN_STARTUP_DEFAULT_VALUE = true;

    private static final String KEY_DAEMON_DISABLED = "daemonDisabled";

    private static final boolean KEY_DAEMON_DISABLED_DEFAULT_VALUE = false;

    private static final String KEY_QUEUE_MAX_THREADS = "queuingMaxThreads";

    private static final int KEY_QUEUE_MAX_THREADS_DEFAULT_VALUE = 4;

    private static final String KEY_QUEUE_NEXT_CHECK_DELAY = "queueingNextCheckDelay";

    private static final int KEY_QUEUE_NEXT_CHECK_DELAY_DEFAULT_VALUE = 2000;

    private static final String KEY_QUEUE_MASSIVE_DOWNLOADING_THREADS = "queuingRemoveLimit";

    private static final boolean KEY_QUEUE_MASSIVE_DOWNLOADING_THREADS_DEFAULT_VALUE = false;

    private static final String KEY_CHECK_UPDATE_FREQ = "updateFreq";

    private static final String KEY_CHECK_UPDATE_FREQ_DEFAULT_VALUE = "everyAct";

    private static final String KEY_CHECK_UPDATE_ALGO = "updateCompareAlgo";

    private static final String KEY_CHECK_UPDATE_ALGO_DEFAULT_VALUE = "latestVerPerBranch";

    private static final String KEY_AUTO_ACCEPT_CRITICAL_UPDATES = "updateCriticalAuto";

    private static final boolean KEY_AUTO_ACCEPT_CRITICAL_UPDATES_DEFAULT_VALUE = true;

    private static final String KEY_AUTO_DOWNLOAD_APPLY_PATCHES = "updatePatchesAuto";

    private static final boolean KEY_AUTO_DOWNLOAD_APPLY_PATCHES_DEFAULT_VALUE = true;

    private static final String KEY_PLUGIN_SUMS = "plugSums";

    private static final String KEY_ENABLE_TONE_BEFORE_DOWNLOAD = "toneBeforeDownload";

    private static final boolean KEY_ENABLE_TONE_BEFORE_DOWNLOAD_DEFAULT_VALUE = true;
    
    private static final String KEY_SELECTED_TONE_BEFORE_DOWNLOAD = "toneBeforeDownloadPath";
    
    private static final String KEY_SELECTED_TONE_BEFORE_DOWNLOAD_DEFAULT_VALUE = "C:\\Windows\\Media\\Windows Logon.wav";

    private static final String KEY_ENABLE_TONE_AFTER_DOWNLOAD = "toneAfterDownload";

    private static final boolean KEY_ENABLE_TONE_AFTER_DOWNLOAD_DEFAULT_VALUE = true;
    
    private static final String KEY_SELECTED_TONE_AFTER_DOWNLOAD = "toneAfterDownloadPath";
    
    private static final String KEY_SELECTED_TONE_AFTER_DOWNLOAD_DEFAULT_VALUE = "C:\\Windows\\Media\\Windows Background.wav";
    
    private static final String KEY_LEGACY_ENABLE_OLDSITE_BEATMAP_REDIRECTING = "legacy_enable_oldsite_beatmap_redirecting";
    
    private static final boolean KEY_LEGACY_ENABLE_OLDSITE_BEATMAP_REDIRECTING_DEFAULT_VALUE = true;
    
    private static final String KEY_LEGACY_DISABLE_OLDSITE_BEATMAP_REDIRECTING_STARTUP_NOTIFICATION = "legacy_disable_oldsite_beatmap_redirecting_startup_notification";
    
    private static final boolean KEY_LEGACY_DISABLE_OLDSITE_BEATMAP_REDIRECTING_STARTUP_NOTIFICATION_DEFAULT_VALUE = false;

    private JSONObject json;

    private final String dataFilePath;

    private final String dataFileName;

    private String unlockKey = null;

    public Config(String dataFilePath, String dataFileName) {
        this.dataFilePath = dataFilePath;
        this.dataFileName = dataFileName;
    }
    
    public void setUpdateSource(int source){
        json.put(KEY_UPDATE_SOURCE, source);
    }

    public int getUpdateSource() {
        return json.getInt(KEY_UPDATE_SOURCE);
    }
    
    public void setDefaultBrowser(String browser){
        json.put(KEY_DEFAULT_BROWSER, browser);
    }

    public String getDefaultBrowser() {
        return json.getString(KEY_DEFAULT_BROWSER);
    }
    
    public void setDefaultOpenBeatmapAction(int action) {
        json.put(KEY_DEFAULT_OPEN_BEATMAP_ACTION, action);
    }
    
    public int getDefaultOpenBeatmapAction() {
        return json.getInt(KEY_DEFAULT_OPEN_BEATMAP_ACTION);
    }
    
    public void setDefaultBeatmapSaveLocation(String location) {
        json.put(KEY_DEFAULT_BEATMAP_SAVE_LOCATION, location);
    }
    
    public String getDefaultBeatmapSaveLocation() {
        return json.getString(KEY_DEFAULT_BEATMAP_SAVE_LOCATION);
    }
    
    public void setOEEnabled(boolean enabled){
        json.put(KEY_OE_ENABLED, enabled);
    }

    public boolean isOEEnabled() {
        return json.getBoolean(KEY_OE_ENABLED);
    }
    
    public void setSwitchToBrowserIfWithoutUiArg(boolean enabled){
        json.put(KEY_NO_UI_ARG_SWITCH_BROWSER, enabled);
    }

    public boolean isSwitchToBrowserIfWithoutUiArg() {
        return json.getBoolean(KEY_NO_UI_ARG_SWITCH_BROWSER);
    }
    
    public void setAutoSwitchBrowser(boolean enabled){
        json.put(KEY_AUTO_SWITCH_BROWSER, enabled);
    }

    public boolean isAutoSwitchBrowser() {
        return json.getBoolean(KEY_AUTO_SWITCH_BROWSER);
    }
    
    public void setShowGettingStartedOnStartup(boolean enabled){
        json.put(KEY_GETTING_STARTED_STARTUP, enabled);
    }

    public boolean isShowGettingStartedOnStartup() {
        return json.getBoolean(KEY_GETTING_STARTED_STARTUP);
    }
    
    public void setServerPrority(String[] strs){
        json.put(KEY_SERVER_PRORITY, strs);
    }

    public String[] getServerPrority() {
        JSONArray arr = json.getJSONArray(KEY_SERVER_PRORITY);
        String[] out = new String[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            out[i] = arr.getString(i);
        }
        return out;
    }
    
    public void setEncPassAskOnStartup(boolean enabled){
        json.put(KEY_ENC_ASK_ON_STARTUP, enabled);
    }

    public boolean isEncPassAskOnStartup() {
        return json.getBoolean(KEY_ENC_ASK_ON_STARTUP);
    }
    
    public void setUser(String user){
        if (isUserPassEncrypted()){
            //Do enc stuff
        } else {
            json.put(KEY_USER, user);
        }
    }

    public String getUser() {
        String raw = json.getString(KEY_USER);
        if (isUserPassEncrypted()) {
            // do enc stuff
            return null;
        } else {
            return new String(Base64.decodeBase64(raw), StandardCharsets.UTF_8);
        }
    }
    
    public void setPass(String pass){
        if (isUserPassEncrypted()){
            //Do enc stuff
        } else {
            json.put(KEY_PASS, pass);
        }
    }

    public String getPass() {
        String raw = json.getString(KEY_PASS);
        if (isUserPassEncrypted()) {
            // do enc stuff
            return null;
        } else {
            return new String(Base64.decodeBase64(raw), StandardCharsets.UTF_8);
        }
    }
    
    public void setUserPassEncrypted(boolean enabled){
        json.put(KEY_ENC, enabled);
    }

    public boolean isUserPassEncrypted() {
        return json.getBoolean(KEY_ENC);
    }
    
    private void setSalt(String salt){
        json.put(KEY_ENC_SALT, salt);
    }
    
    private String getSalt(){
        return json.getString(KEY_ENC_SALT);
    }
    
    private void setIv(String iv){
        json.put(KEY_ENC_IV, iv);
    }
    
    private String getIv(){
        return json.getString(KEY_ENC_IV);
    }
    
    public void setRunDaemonOnWinStartup(boolean enabled){
        json.put(KEY_RUN_DAEMON_ON_WIN_STARTUP, enabled);
    }
    
    public boolean isRunDaemonOnWinStartup(){
        return json.getBoolean(KEY_RUN_DAEMON_ON_WIN_STARTUP);
    }
    
    public void setDaemonDisabled(boolean disabled){
        json.put(KEY_DAEMON_DISABLED, disabled);
    }
    
    public boolean isDaemonDisabled(){
        return json.getBoolean(KEY_DAEMON_DISABLED);
    }
    
    public void setMaxThreads(int threads){
        json.put(KEY_QUEUE_MAX_THREADS, threads);
    }
    
    public int getMaxThreads(){
        int x = json.getInt(KEY_QUEUE_MAX_THREADS);
        if (x > 8 && !isMassiveDownloadingThreads()){
            return 8;
        } else {
            return x;
        }
    }
    
    public void setNextCheckDelay(int ms){
        json.put(KEY_QUEUE_NEXT_CHECK_DELAY, ms);
    }
    
    public int getNextCheckDelay(){
        return json.getInt(KEY_QUEUE_NEXT_CHECK_DELAY);
    }
    
    public void setMassiveDownloadingThreads(boolean enabled){
        json.put(KEY_QUEUE_MASSIVE_DOWNLOADING_THREADS, enabled);
    }
    
    public boolean isMassiveDownloadingThreads(){
        return json.getBoolean(KEY_QUEUE_MASSIVE_DOWNLOADING_THREADS);
    }
    
    public void setCheckUpdateFreq(String freqStr){
        json.put(KEY_CHECK_UPDATE_FREQ, freqStr);
    }
    
    public String getCheckUpdateFreq(){
        return json.getString(KEY_CHECK_UPDATE_FREQ);
    }
    
    public void setCheckUpdateAlgo(String algoStr){
        json.put(KEY_CHECK_UPDATE_ALGO, algoStr);
    }
    
    public String getCheckUpdateAlgo(){
        return json.getString(KEY_CHECK_UPDATE_ALGO);
    }
    
    public void setAutoAcceptCriticalUpdates(boolean enabled){
        json.put(KEY_AUTO_ACCEPT_CRITICAL_UPDATES, enabled);
    }
    
    public boolean isAutoAcceptCriticalUpdates(){
        return json.getBoolean(KEY_AUTO_ACCEPT_CRITICAL_UPDATES);
    }
    
    public void setAutoDownloadApplyPatches(boolean enabled){
        json.put(KEY_AUTO_DOWNLOAD_APPLY_PATCHES, enabled);
    }
    
    public boolean isAutoDownloadApplyPatches(){
        return json.getBoolean(KEY_AUTO_DOWNLOAD_APPLY_PATCHES);
    }
    
    public void setPluginSums(String[] sums){
        json.put(KEY_PLUGIN_SUMS, sums);
    }
    
    public String[] getPluginSums(){
        JSONArray arr = json.getJSONArray(KEY_PLUGIN_SUMS);
        String[] out = new String[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            out[i] = arr.getString(i);
        }
        return out;
    }
    
    public void setEnableToneBeforeDownload(boolean enabled){
        json.put(KEY_ENABLE_TONE_BEFORE_DOWNLOAD, enabled);
    }
    
    public boolean isEnableToneBeforeDownload(){
        return json.getBoolean(KEY_ENABLE_TONE_BEFORE_DOWNLOAD);
    }
    public String getToneBeforeDownloadPath() {
        return json.getString(KEY_SELECTED_TONE_BEFORE_DOWNLOAD);
    }
    
    public void setToneBeforeDownloadPath(String path) {
        json.put(KEY_SELECTED_TONE_BEFORE_DOWNLOAD, path);
    }
    
    public void setEnableToneAfterDownload(boolean enabled){
        json.put(KEY_ENABLE_TONE_AFTER_DOWNLOAD, enabled);
    }
    
    public String getToneAfterDownloadPath() {
        return json.getString(KEY_SELECTED_TONE_AFTER_DOWNLOAD);
    }
    
    public void setToneAfterDownloadPath(String path) {
        json.put(KEY_SELECTED_TONE_AFTER_DOWNLOAD, path);
    }
    
    public boolean isEnableToneAfterDownload(){
        return json.getBoolean(KEY_ENABLE_TONE_AFTER_DOWNLOAD);
    }
    
    public void setLegacyEnableOldSiteBeatmapRedirecting(boolean enabled){
        json.put(KEY_LEGACY_ENABLE_OLDSITE_BEATMAP_REDIRECTING, enabled);
    }
    
    public boolean isLegacyEnableOldSiteBeatmapRedirecting() {
        return json.getBoolean(KEY_LEGACY_ENABLE_OLDSITE_BEATMAP_REDIRECTING);
    }
    
    public void setLegacyDisableOldSiteBeatmapRedirectingStartupNotification(boolean enabled){
        json.put(KEY_LEGACY_DISABLE_OLDSITE_BEATMAP_REDIRECTING_STARTUP_NOTIFICATION, enabled);
    }
    
    public boolean isLegacyDisableOldSiteBeatmapRedirectingStartupNotification() {
        return json.getBoolean(KEY_LEGACY_DISABLE_OLDSITE_BEATMAP_REDIRECTING_STARTUP_NOTIFICATION);
    }

    private static boolean fillJson(JSONObject json) {
        boolean modified = false;
        modified |= ifNullPutValue(json, KEY_UPDATE_SOURCE, KEY_UPDATE_SOURCE_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_DEFAULT_BROWSER, KEY_DEFAULT_BROWSER_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_DEFAULT_OPEN_BEATMAP_ACTION, KEY_DEFAULT_OPEN_BEATMAP_ACTION_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_DEFAULT_BEATMAP_SAVE_LOCATION, KEY_DEFAULT_BEATMAP_SAVE_LOCATION_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_OE_ENABLED, KEY_OE_ENABLED_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_NO_UI_ARG_SWITCH_BROWSER, KEY_NO_UI_ARG_SWITCH_BROWSER_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_AUTO_SWITCH_BROWSER, KEY_AUTO_SWITCH_BROWSER_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_GETTING_STARTED_STARTUP, KEY_GETTING_STARTED_STARTUP_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_SERVER_PRORITY, KEY_SERVER_PRORITY_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_ENC_ASK_ON_STARTUP, KEY_ENC_ASK_ON_STARTUP_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_USER, "");
        modified |= ifNullPutValue(json, KEY_PASS, "");
        modified |= ifNullPutValue(json, KEY_ENC, KEY_ENC_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_ENC_SALT, "");
        modified |= ifNullPutValue(json, KEY_ENC_IV, "");
        modified |= ifNullPutValue(json, KEY_RUN_DAEMON_ON_WIN_STARTUP, KEY_RUN_DAEMON_ON_WIN_STARTUP_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_DAEMON_DISABLED, KEY_DAEMON_DISABLED_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_QUEUE_MAX_THREADS, KEY_QUEUE_MAX_THREADS_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_QUEUE_NEXT_CHECK_DELAY, KEY_QUEUE_NEXT_CHECK_DELAY_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_QUEUE_MASSIVE_DOWNLOADING_THREADS,
                KEY_QUEUE_MASSIVE_DOWNLOADING_THREADS_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_CHECK_UPDATE_FREQ, KEY_CHECK_UPDATE_FREQ_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_CHECK_UPDATE_ALGO, KEY_CHECK_UPDATE_ALGO_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_AUTO_ACCEPT_CRITICAL_UPDATES,
                KEY_AUTO_ACCEPT_CRITICAL_UPDATES_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_AUTO_DOWNLOAD_APPLY_PATCHES,
                KEY_AUTO_DOWNLOAD_APPLY_PATCHES_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_PLUGIN_SUMS, new JSONArray());
        modified |= ifNullPutValue(json, KEY_ENABLE_TONE_BEFORE_DOWNLOAD,
                KEY_ENABLE_TONE_BEFORE_DOWNLOAD_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_SELECTED_TONE_BEFORE_DOWNLOAD,
                KEY_SELECTED_TONE_BEFORE_DOWNLOAD_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_ENABLE_TONE_AFTER_DOWNLOAD, KEY_ENABLE_TONE_AFTER_DOWNLOAD_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_SELECTED_TONE_AFTER_DOWNLOAD,
                KEY_SELECTED_TONE_AFTER_DOWNLOAD_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_LEGACY_ENABLE_OLDSITE_BEATMAP_REDIRECTING, KEY_LEGACY_ENABLE_OLDSITE_BEATMAP_REDIRECTING_DEFAULT_VALUE);
        modified |= ifNullPutValue(json, KEY_LEGACY_DISABLE_OLDSITE_BEATMAP_REDIRECTING_STARTUP_NOTIFICATION, KEY_LEGACY_DISABLE_OLDSITE_BEATMAP_REDIRECTING_STARTUP_NOTIFICATION_DEFAULT_VALUE);
        return modified;
    }

    private static boolean ifNullPutValue(JSONObject json, String key, Object val) {
        if (json.isNull(key)) {
            json.put(key, val);
            return true;
        } else {
            return false;
        }
    }

    public void load() throws IOException {
        File file = new File(dataFilePath + "/" + dataFileName);
        if (!file.exists()) {
            write();
            return;
        }
        FileInputStream in = new FileInputStream(file);

        String data = "";
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        while ((line = reader.readLine()) != null) {
            data += line;
        }
        reader.close();

        /* 
         * Preferences prefs = Preferences.userRoot();
         * 
         * String data = prefs.get("com.github.mob41.osumer.configuration",
         * null);
         * 
         * if (data == null){ write(); return; }
         */

        try {
            json = new JSONObject(data);
            if (fillJson(json)) {
                write();
            }
        } catch (JSONException e) {
            throw new IOException("Invalid configuration data structure", e);
        }
    }

    public void write() throws IOException {

        File folder = new File(dataFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(dataFilePath + "/" + dataFileName);
        if (!file.exists()) {
            file.createNewFile();

            if (json == null) {
                json = new JSONObject();
            }
            fillJson(json);
        }
        FileOutputStream out = new FileOutputStream(file);
        PrintWriter writer = new PrintWriter(out, true);
        writer.println(json.toString(5));
        writer.close();
        out.flush();
        out.close();

        /*
         * Preferences prefs = Preferences.userRoot();
         * 
         * if (json == null){ json = new JSONObject(); }
         * 
         * prefs.put("com.github.mob41.osumer.configuration", json.toString());
         * 
         * try { prefs.flush(); } catch (BackingStoreException e) { throw new
         * IOException("Error occurred", e); }
         */
    }

}
