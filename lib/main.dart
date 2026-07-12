import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData.dark(),
      home: const HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  static const platform = MethodChannel('com.hamid.urdu_keyboard/keyboard_control');

  // بٹن 1 کا فنکشن
  Future<void> _openKeyboardSettings() async {
    try {
      await platform.invokeMethod('openKeyboardSettings');
    } on PlatformException catch (e) {
      print("Failed to open settings: '${e.message}'.");
    }
  }

  // بٹن 2 کا فنکشن
  Future<void> _showKeyboardPicker() async {
    try {
      await platform.invokeMethod('showKeyboardPicker');
    } on PlatformException catch (e) {
      print("Failed to show picker: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF121212),
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        title: const Center(
          child: Text(
            'جمیل نوری اردو کی بورڈ',
            style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
          ),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 24.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'اردو کی بورڈ ایپ میں خوش آمدید',
              style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 16),
            const Text(
              'جمیل نوری نستعلیق لکھائی کے ساتھ اپنے موبائل میں اردو ٹائپنگ کو آسان بنائیں۔ کی بورڈ استعمال کرنے کے لیے نیچے دیے گئے بٹنوں پر کلک کریں۔',
              style: TextStyle(fontSize: 16, color: Colors.grey),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 40),
            
            // بٹن 1: کی بورڈ فعال کریں
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: const Color(0xFF2C2C2C),
                minimumSize: const Size.fromHeight(60),
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(30)),
              ),
              onPressed: _openKeyboardSettings,
              child: const Text(
                '1 کریں (Enable) - کی بورڈ فعال',
                style: TextStyle(fontSize: 18, color: Colors.white),
              ),
            ),
            const SizedBox(height: 16),
            
            // بٹن 2: اردو کی بورڈ منتخب کریں
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: const Color(0xFF2C2C2C),
                minimumSize: const Size.fromHeight(60),
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(30)),
              ),
              onPressed: _showKeyboardPicker,
              child: const Text(
                '2 - اردو کی بورڈ منتخب کریں',
                style: TextStyle(fontSize: 18, color: Colors.white),
              ),
            ),
            const SizedBox(height: 40),
            
            // فائنل ٹیسٹ باکس (جہاں کلک کرنے سے کی بورڈ کھل کر سامنے آئے گا)
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              decoration: BoxDecoration(
                color: const Color(0xFF1E1E1E),
                borderRadius: BorderRadius.circular(15),
                border: Border.all(color: const Color(0xFF2C2C2C)),
              ),
              child: const TextField(
                decoration: InputDecoration(
                  hintText: 'یہاں کلک کر کے کی بورڈ ٹیسٹ کریں...',
                  hintStyle: TextStyle(color: Colors.grey),
                  border: InputBorder.none,
                ),
                style: TextStyle(color: Colors.white),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
