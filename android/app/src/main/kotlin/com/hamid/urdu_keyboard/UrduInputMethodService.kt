package com.hamid.urdu_keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.content.Context

class UrduInputMethodService : InputMethodService() {
    override fun onCreateInputView(): View {
        val density = resources.displayMetrics.density
        val rowHeight = (48 * density).toInt()

        // मुख्य कीबोर्ड लेआउट
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#121212"))
            setPadding(8, 12, 8, 12)
        }

        val customTypeface = try {
            Typeface.createFromAsset(assets, "flutter_assets/assets/fonts/Jameel_Noori_Nastaleeq.ttf")
        } catch (e: Exception) {
            Typeface.DEFAULT
        }

        // उर्दू के अक्षर
        val row1 = listOf("آ", "ا", "ب", "پ", "ت", "ٹ", "ث", "ج", "چ", "ح")
        val row2 = listOf("خ", "د", "ڈ", "ذ", "ر", "ڑ", "ز", "ژ", "س", "ش")
        val row3 = listOf("ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ")
        val row4 = listOf("ل", "م", "ن", "و", "ہ", "ھ", "ء", "ی", "ے")

        val allRows = listOf(row1, row2, row3, row4)

        for (row in allRows) {
            val rowLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    rowHeight
                ).apply {
                    setMargins(0, 3, 0, 3)
                }
            }

            for (key in row) {
                val button = Button(this).apply {
                    text = key
                    textSize = 20f
                    setTextColor(Color.WHITE)
                    setBackgroundColor(Color.parseColor("#2C2C2C"))
                    typeface = customTypeface
                    setPadding(0, 0, 0, 0)
                    minWidth = 0
                    minHeight = 0
                    
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1f
                    ).apply {
                        setMargins(2, 2, 2, 2)
                    }

                    setOnClickListener {
                        currentInputConnection?.commitText(key, 1)
                    }
                }
                rowLayout.addView(button)
            }
            mainLayout.addView(rowLayout)
        }

        // सबसे नीचे वाली स्पेशल रो (बदलिए, Delete, Space, Enter)
        val bottomRowLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                rowHeight
            ).apply {
                setMargins(0, 4, 0, 0)
            }
        }

        // 1. कीबोर्ड बदलें बटन (यह वही पॉप-अप लाएगा जो आपको चाहिए)
        val switchKeyButton = Button(this).apply {
            text = "🌐" // ग्लोबल आइकॉन जो कीबोर्ड बदलने के लिए इंटरनेशनल स्टैंडर्ड है
            textSize = 16f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#37474F")) // अलग रंग ताकि पहचान में आए
            setPadding(0, 0, 0, 0)
            minWidth = 0
            minHeight = 0
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.5f).apply {
                setMargins(2, 2, 2, 2)
            }
            setOnClickListener {
                // यह लाइन तुरंत एंडरॉयड का कीबोर्ड चूसर पॉप-अप खोल देगी
                val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.showInputMethodPicker()
            }
        }

        // 2. डिलीट बटन
        val deleteButton = Button(this).apply {
            text = "DEL"
            textSize = 14f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#D32F2F"))
            setPadding(0, 0, 0, 0)
            minWidth = 0
            minHeight = 0
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.5f).apply {
                setMargins(2, 2, 2, 2)
            }
            setOnClickListener {
                currentInputConnection?.deleteSurroundingText(1, 0)
            }
        }

        // 3. स्पेस बार
        val spaceButton = Button(this).apply {
            text = "خالی جگہ"
            textSize = 16f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#424242"))
            typeface = customTypeface
            setPadding(0, 0, 0, 0)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 3.8f).apply {
                setMargins(2, 2, 2, 2)
            }
            setOnClickListener {
                currentInputConnection?.commitText(" ", 1)
            }
        }

        // 4. एंटर बटन
        val enterButton = Button(this).apply {
            text = "درج کریں"
            textSize = 13f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#1B5E20"))
            typeface = customTypeface
            setPadding(0, 0, 0, 0)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.5f).apply {
                setMargins(2, 2, 2, 2)
            }
            setOnClickListener {
                currentInputConnection?.sendKeyEvent(
                    android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_ENTER)
                )
            }
        }

        bottomRowLayout.addView(switchKeyButton)
        bottomRowLayout.addView(deleteButton)
        bottomRowLayout.addView(spaceButton)
        bottomRowLayout.addView(enterButton)
        mainLayout.addView(bottomRowLayout)

        return mainLayout
    }
}
