package com.example.baitap01

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object { private const val TAG = "Baitap01" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // ===== BÀI 4 =====
        val edtNumbers = findViewById<EditText>(R.id.edtNumbers)
        val btnTestArray = findViewById<Button>(R.id.btnTestArray)
        btnTestArray.setOnClickListener {
            val raw = edtNumbers.text.toString().trim()
            if (raw.isEmpty()) {
                Toast.makeText(this, "Nhập mảng số trước (vd: 1, 2, 3 4 5)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tách theo dấu phẩy hoặc khoảng trắng
            val tokens = raw.split(Regex("[,\\s]+")).filter { it.isNotBlank() }
            if (tokens.isEmpty()) {
                Toast.makeText(this, "Không đọc được số hợp lệ.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val numbers = tokens.mapNotNull { it.toIntOrNull() }
            if (numbers.isEmpty()) {
                Toast.makeText(this, "Tất cả mục đều không phải số hợp lệ.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (numbers.size < tokens.size) {
                Toast.makeText(this, "Một vài mục không hợp lệ đã bị bỏ qua.", Toast.LENGTH_SHORT).show()
            }

            val evens = numbers.filter { it % 2 == 0 }
            val odds  = numbers.filter { it % 2 != 0 }

            Log.d(TAG, "Số chẵn: $evens")
            Log.d(TAG, "Số lẻ: $odds")
            Toast.makeText(this, "Đã in chẵn/lẻ lên Logcat", Toast.LENGTH_SHORT).show()
        }

        // ===== BÀI 5 =====
        val edtInput = findViewById<EditText>(R.id.edtInput)
        val btnProcess = findViewById<Button>(R.id.btnProcess)
        val txtEcho = findViewById<TextView>(R.id.txtEcho)

        btnProcess.setOnClickListener {
            val s = edtInput.text.toString()
            txtEcho.text = s

            // Đảo theo từ và IN HOA: "I LOVE YOU" -> "YOU LOVE I"
            val reversedUpper = s.trim()
                .split(Regex("\\s+"))
                .filter { it.isNotEmpty() }
                .reversed()
                .joinToString(" ")
                .uppercase()

            Toast.makeText(this, reversedUpper, Toast.LENGTH_SHORT).show()
        }
    }
}
