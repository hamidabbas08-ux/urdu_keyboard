package com.hamid.urdu_keyboard

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.content.Intent
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.content.Context

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.hamid.urdu_keyboard/keyboard_control"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "openKeyboardSettings") {
                // یہ انٹینٹ سیمسنگ اور دیگر فونز پر سیدھا وہ لسٹ کھولے گا جہاں کی بورڈ آن/آف ہوتے ہیں
                val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                result.success(true)
            } else if (call.method == "showKeyboardPicker") {
                // یہ بالکل وہی میپ جیسا پاپ اپ چوائس باکس اسکرین پر فوری ظاہر کرے گا
                val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.showInputMethodPicker()
                result.success(true)
            } else {
                result.notImplemented()
            }
        }
    }
}
