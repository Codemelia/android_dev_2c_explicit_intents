package sg.edu.nus.iss.a2c_explicit_intents

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ShowActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_show)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set click listener for back button
        val backBtn = findViewById<Button>(R.id.back_btn)
        backBtn.setOnClickListener {
            finish() // Goes back to previous page
        }

        // Find text view
        val textView = findViewById<TextView>(R.id.show_text)

        // Get quote from intent
        val iQuote = intent.getStringExtra(
            getString(R.string.quote_label)
        )

        // If empty quote, "No quote available"
        val quote = if (iQuote.isNullOrEmpty()) {
            getString(R.string.empty_quote_message)
        } else {
            iQuote
        }

        // Set quote to text view
        textView.text = quote

    }
}