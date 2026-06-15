package sg.edu.nus.iss.a2c_explicit_intents

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WriteActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_write)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set listener for OK button
        val okBtn = findViewById<Button>(R.id.ok_btn)
        okBtn.setOnClickListener {

            // Get quote from EditText
            val quote = findViewById<EditText>(R.id.quote_edit).text.toString()

            // Generate response for click
            var response = Intent()
            response.putExtra(getString(R.string.quote_label), quote)

            // Set result based on result code
            if (quote.isEmpty()) { // If quote is empty, return CANCELED

                // Alert builder and get confirmation to cancel
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.not_ok_message))
                    .setMessage(getString(R.string.alert_message))

                    // Yes btn -> Set result CANCELED and finish
                    .setPositiveButton(getString(R.string.alert_yes)) { _,_ ->
                        setResult(RESULT_CANCELED, response)
                        finish()
                    }

                    // No btn -> Dismiss
                    .setNegativeButton(getString(R.string.alert_no)) { dialog,_ ->
                        dialog.dismiss()
                    }

                    .show()

            // If quote has content, return OK with quote and finish
            } else {
                setResult(RESULT_OK, response)
                finish()
            }

        }

    }

}