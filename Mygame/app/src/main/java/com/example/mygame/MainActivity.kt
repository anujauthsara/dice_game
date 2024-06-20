// MainActivity.kt
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mygame.R
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var textTarget: TextView
    private lateinit var textRollsLeft: TextView
    private lateinit var textResult: TextView
    private lateinit var textTotal: TextView
    private lateinit var textHighScore: TextView
    private lateinit var btnRoll: Button

    private var target = 15
    private var rollsLeft = 5
    private var total = 0
    private var highScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)

        textTarget = findViewById(R.id.textTarget)
        textRollsLeft = findViewById(R.id.textRollsLeft)
        textResult = findViewById(R.id.textResult)
        textTotal = findViewById(R.id.textTotal)
        textHighScore = findViewById(R.id.textHighScore)
        btnRoll = findViewById(R.id.btnRoll)

        updateUI()

        btnRoll.setOnClickListener {
            if (rollsLeft > 0) {
                val rollResult = rollDice()
                total += rollResult
                rollsLeft--
                updateUI()

                if (total >= target) {
                    showWin()
                }
            }
        }
    }

    private fun rollDice(): Int {
        val randomNum = Random.nextInt(1, 7)
        textResult.text = "Result: $randomNum"
        return randomNum
    }

    private fun updateUI() {
        textTarget.text = "Target: $target"
        textRollsLeft.text = "Rolls left: $rollsLeft"
        textTotal.text = "Total: $total"
        textHighScore.text = "High Score: $highScore"
    }

    private fun showWin() {
        if (total > highScore) {
            highScore = total
        }
        updateUI()
        if (total >= target) {
            startActivity(Intent(this, WinActivity::class.java))
        } else {
            startActivity(Intent(this, LoseActivity::class.java))
        }
    }
}
