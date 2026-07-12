import 'package:flutter/material.dart';

void main() {
  runApp(const UrduKeyboardApp());
}

class UrduKeyboardApp extends StatelessWidget {
  const UrduKeyboardApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Urdu Keyboard',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        brightness: Brightness.dark,
        primarySwatch: Colors.green,
        fontFamily: 'JameelNoori', // جمیل نوری فونٹ پوری ایپ پر لاگو کرنے کے لیے
      ),
      home: const HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF121212),
      appBar: AppBar(
        backgroundColor: const Color(0xFF1A1A1A),
        title: const Text(
          'جمیل نوری اردو کی بورڈ',
          style: TextStyle(color: Colors.white, fontSize: 24, fontFamily: 'JameelNoori'),
        ),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            const Text(
              'اردو کی بورڈ ایپ میں خوش آمدید',
              textAlign: TextAlign.center,
              style: TextStyle(
                color: Colors.white,
                fontSize: 26,
              ),
            ),
            const SizedBox(height: 15),
            const Text(
              'جمیل نوری نستعلیق لکھائی کے ساتھ اپنے موبائل میں اردو ٹائپنگ کو آسان بنائیں۔ کی بورڈ استعمال کرنے کے لیے نیچے دیے گئے بٹنوں پر کلک کریں۔',
              textAlign: TextAlign.center,
              style: TextStyle(
                color: Colors.grey,
                fontSize: 18,
              ),
            ),
            const SizedBox(height: 40),
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: const Color(0xFF2D2D2D),
                padding: const EdgeInsets.symmetric(vertical: 15),
              ),
              onPressed: () {
                // اینڈرائیڈ کی بورڈ سیٹنگز کھولنے کا کوڈ بعد میں یہاں آئے گا
              },
              child: const Text(
                '1۔ کی بورڈ فعال (Enable) کریں',
                style: TextStyle(color: Colors.white, fontSize: 20),
              ),
            ),
            const SizedBox(height: 15),
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: const Color(0xFF2D2D2D),
                padding: const EdgeInsets.symmetric(vertical: 15),
              ),
              onPressed: () {
                // ان پٹ میتھڈ سوئچ کرنے کا کوڈ بعد میں یہاں آئے گا
              },
              child: const Text(
                '2۔ اردو کی بورڈ منتخب کریں',
                style: TextStyle(color: Colors.white, fontSize: 20),
              ),
            ),
            const SizedBox(height: 40),
            // ٹیسٹنگ کے لیے ٹیکسٹ فیلڈ
            TextField(
              style: const TextStyle(color: Colors.white, fontSize: 22),
              decoration: InputDecoration(
                hintText: 'یہاں کلک کر کے اردو لکھیں...',
                hintStyle: const TextStyle(color: Colors.grey, fontSize: 18),
                filled: true,
                fillColor: const Color(0xFF1A1A1A),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(10),
                  borderSide: BorderSide.none,
                ),
              ),
              textAlign: TextAlign.right,
            ),
          ],
        ),
      ),
    );
  }
}
