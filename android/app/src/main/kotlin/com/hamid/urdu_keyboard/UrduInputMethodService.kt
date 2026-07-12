package com.hamid.urdu_keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout
import android.graphics.Color
import android.view.Gravity

class UrduInputMethodService : InputMethodService() {
    override fun onCreateInputView(): View {
        // مین عمودی کنٹینر (Vertical Container)
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#121212")) // خوبصورت ڈارک بیک گراؤنڈ
            setPadding(16, 16, 16, 16) // یہاں سنٹیکس کی غلطی کو بالکل ٹھیک کر دیا گیا ہے
        }

        // اردو حروفِ تہجی کی لائنیں (Keyboard Rows)
        val row1 = listOf("آ", "ا", "ب", "پ", "ت", "ٹ", "ث", "ج", "چ", "ح", "خ")
        val row2 = listOf("د", "ڈ", "ذ", "ر", "ڑ", "ز", "ژ", "س", "ش", "ص", "ض")
        val row3 = listOf("ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ", "ل", "م", "ن")
        val row4 = listOf("و", "ہ", "ھ", "ء", "ی", "ے")

        val allRows = listOf(row1, row2, row3, row4)

        // ہر لائن کو کی بورڈ میں افقی (Horizontal) طور پر جوڑنا
        for (row in allRows) {
            val rowLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 5, 0, 5)
                }
            }

            for (key in row) {
                val button = Button(this).apply {
                    text = key
                    textSize = 20f
                    setTextColor(Color.WHITE)
                    setBackgroundColor(Color.parseColor("#2C2C2C")) // بٹن کا رنگ
                    
                    // بٹن کا سائز اور مارجن
                    layoutParams = LinearLayout.LayoutParams(
                        0, 
                        LinearLayout.LayoutParams.WRAP_CONTENT, 
                        1f
                    ).apply {
                        setMargins(4, 4, 4, 4)
                    }

                    // بٹن دبانے پر ٹیکسٹ ٹائپ ہونا
                    setOnClickListener {
                        currentInputConnection?.commitText(key, 1)
                    }
                }
                rowLayout.addView(button)
            }
            mainLayout.addView(rowLayout)
        }

        // سب سے نیچے والی فائنل رو (Space, Delete, Enter)
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

        // 1. ڈیلیٹ بٹن (DEL)
        val deleteButton = Button(this).apply {
            text = "DEL"
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#D32F2F")) // سرخ رنگ
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.5f).apply {
                setMargins(4, 4, 4, 4)
            }
            setOnClickListener {
                currentInputConnection?.deleteSurroundingText(1, 0)
            }
        }

        // 2. اسپیس بار (Space)
        val spaceButton = Button(this).apply {
            text = "خلائی جگہ (Space)"
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#424242"))
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 4f).apply {
                setMargins(4, 4, 4, 4)
            }
            setOnClickListener {
                currentInputConnection?.commitText(" ", 1)
            }
        }

        // 3. انٹر بٹن (Enter / Next)
        val enterButton = Button(this).apply {
            text = "درج کریں"
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#1B5E20")) // سبز رنگ
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.5f).apply {
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
