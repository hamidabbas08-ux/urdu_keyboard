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
      theme: ThemeData(
        brightness: Brightness.dark,
        fontFamily: 'Jameel_Noori_Nastaleeq', 
      ),
      home: const HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  static const platform = MethodChannel('com.hamid.urdu_keyboard/keyboard_control');

  Future<void> _openKeyboardSettings() async {
    try {
      await platform.invokeMethod('openKeyboardSettings');
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }

  Future<void> _showKeyboardPicker() async {
    try {
      await platform.invokeMethod('showKeyboardPicker');
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
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
            style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold, fontFamily: 'Jameel_Noori_Nastaleeq'),
          ),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 24.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Icon(Icons.keyboard, size: 80, color: Colors.amber),
            const SizedBox(height: 24),
            const Text(
              'صرف 2 آسان مراحل میں کی بورڈ فعال کریں',
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold, fontFamily: 'Jameel_Noori_Nastaleeq'),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 40),
            
            // مرحلہ 1 کا بٹن
            ElevatedButton.icon(
              style: ElevatedButton.styleFrom(
                backgroundColor: const Color(0xFF2C2C2C),
                minimumSize: const Size.fromHeight(65),
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(15)),
                side: const BorderSide(color: Colors.amber, width: 1),
              ),
              onPressed: _openKeyboardSettings,
              icon: const Icon(Icons.settings, color: Colors.amber),
              label: const Text(
                'مرحلہ 1: کی بورڈ لسٹ میں آن کریں',
                style: TextStyle(fontSize: 18, color: Colors.white, fontFamily: 'Jameel_Noori_Nastaleeq'),
              ),
            ),
            const SizedBox(height: 20),
            
            // مرحلہ 2 کا بٹن (میپ جیسا پاپ اپ چوائس)
            ElevatedButton.icon(
              style: ElevatedButton.styleFrom(
                backgroundColor: const Color(0xFF2C2C2C),
                minimumSize: const Size.fromHeight(65),
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(15)),
                side: const BorderSide(color: Colors.green, width: 1),
              ),
              onPressed: _showKeyboardPicker,
              icon: const Icon(Icons.touch_app, color: Colors.green),
              label: const Text(
                'مرحلہ 2: اردو کی بورڈ منتخب کریں',
                style: TextStyle(fontSize: 18, color: Colors.white, fontFamily: 'Jameel_Noori_Nastaleeq'),
              ),
            ),
            const SizedBox(height: 40),
            
            // ٹیسٹ باکس
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              decoration: BoxDecoration(
                color: const Color(0xFF1E1E1E),
                borderRadius: BorderRadius.circular(15),
                border: Border.all(color: const Color(0xFF2C2C2C)),
              ),
              child: const TextField(
                decoration: InputDecoration(
                  hintText: 'یہاں کلک کر کے واٹس ایپ کی طرح ٹیسٹ کریں...',
                  hintStyle: TextStyle(color: Colors.grey, fontFamily: 'Jameel_Noori_Nastaleeq'),
                  border: InputBorder.none,
                ),
                style: TextStyle(color: Colors.white, fontFamily: 'Jameel_Noori_Nastaleeq'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
