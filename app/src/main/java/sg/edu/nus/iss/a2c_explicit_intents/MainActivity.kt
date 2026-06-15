package sg.edu.nus.iss.a2c_explicit_intents

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Use launcher to launch Write Quote on click of Write Button
    var launcher: ActivityResultLauncher<Intent>? = null

    // Variable to hold quote returned from WriteActivity
    private var quote = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Register launcher for WriteActivity
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            result: ActivityResult ->
                if (result.resultCode == RESULT_OK) { // If WriteActivity returns OK
                    val data = result.data // Retrieve data sent back from WriteActivity
                    quote = data?.getStringExtra(getString(R.string.quote_label)).toString() // Parse to string quote

                    // Success toast message
                    Toast.makeText(
                        this,
                        getString(R.string.ok_message),
                        Toast.LENGTH_SHORT
                    ).show()
                } else { // If WriteActivity doesn't return OK
                    Toast.makeText( // Show toast message
                        this,
                        getString(R.string.not_ok_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        // Find buttons in layout
        val writeBtn = findViewById<Button>(R.id.write_btn)
        val showBtn = findViewById<Button>(R.id.show_btn)

        // Set listener and launch launcher on click write button
        writeBtn.setOnClickListener {
            var intent = Intent(this, WriteActivity::class.java)
            launcher?.launch(intent)
        }

        // Set listener and launch launcher on click show button
        showBtn.setOnClickListener {
            var intent = Intent(this, ShowActivity::class.java)
            intent.putExtra(getString(R.string.quote_label), quote) // Send quote through intent
            startActivity(intent) // Launcher not required as not expecting response from ShowActivity
        }

    }
}