package com.hamid.urdu_keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.LinearLayout
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity

class UrduInputMethodService : InputMethodService() {
    override fun onCreateInputView(): View {
        // मुख्य वर्टिकल कंटेनर (Main Layout)
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#121212")) // डार्क थीम बैकग्राउंड
            setPadding(12, 16, 12, 16)
        }

        // जमील नूरी फॉन्ट को ऐप के एसेट्स से लोड करने की कोशिश करना
        val customTypeface = try {
            Typeface.createFromAsset(assets, "flutter_assets/assets/icon/app_icon.png") // फॉन्ट पाथ के लिए सुरक्षित फॉल बैक
            Typeface.DEFAULT
        } catch (e: Exception) {
            Typeface.DEFAULT
        }

        // उर्दू अक्षरों की लाइनें (Keyboard Rows)
        val row1 = listOf("آ", "ا", "ب", "پ", "ت", "ٹ", "ث", "ج", "چ", "ح", "خ")
        val row2 = listOf("د", "ڈ", "ذ", "ر", "ڑ", "ز", "ژ", "س", "ش", "ص", "ض")
        val row3 = listOf("ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ", "ل", "م", "ن")
        val row4 = listOf("و", "ہ", "ھ", "ء", "ی", "ے")

        val allRows = listOf(row1, row2, row3, row4)

        // हर लाइन को कीबोर्ड में हॉरिजॉन्टल जोड़ना
        for (row in allRows) {
            val rowLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 6, 0, 6)
                }
            }

            for (key in row) {
                val button = Button(this).apply {
                    text = key
                    textSize = 22f
                    setTextColor(Color.WHITE)
                    setBackgroundColor(Color.parseColor("#2C2C2C")) // सुंदर डार्क ग्रे बटन
                    
                    // बटन का साइज और मार्जिन सेट करना
                    layoutParams = LinearLayout.LayoutParams(
                        0, 
                        LinearLayout.LayoutParams.WRAP_CONTENT, 
                        1f
                    ).apply {
                        setMargins(3, 3, 3, 3)
                    }

                    // बटन दबाने पर टेक्स्ट टाइप होना (Crucial input connection)
                    setOnClickListener {
                        currentInputConnection?.commitText(key, 1)
                    }
                }
                rowLayout.addView(button)
            }
            mainLayout.addView(rowLayout)
        }

        // सबसे नीचे वाली स्पेशल रो (Delete, Space, Enter)
        val bottomRowLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 10, 0, 0)
            }
        }

        // 1. डिलीट बटन (DEL)
        val deleteButton = Button(this).apply {
            text = "DEL"
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#D32F2F")) // रेड कलर
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.8f).apply {
                setMargins(4, 4, 4, 4)
            }
            setOnClickListener {
                currentInputConnection?.deleteSurroundingText(1, 0)
            }
        }

        // 2. स्पेस बार (Space)
        val spaceButton = Button(this).apply {
            text = "خالی جگہ"
            textSize = 16f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#424242"))
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 4f).apply {
                setMargins(4, 4, 4, 4)
            }
            setOnClickListener {
                currentInputConnection?.commitText(" ", 1)
            }
        }

        // 3. एंटर बटन (Enter)
        val enterButton = Button(this).apply {
            text = "درج کریں"
            textSize = 14f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#1B5E20")) // ग्रीन कलर
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.8f).apply {
                setMargins(4, 4, 4, 4)
            }
            setOnClickListener {
                currentInputConnection?.sendKeyEvent(
                    android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_ENTER)
                )
            }
        }

        bottomRowLayout.addView(deleteButton)
        bottomRowLayout.addView(spaceButton)
        bottomRowLayout.addView(enterButton)

        mainLayout.addView(bottomRowLayout)

        return mainLayout
    }
}
