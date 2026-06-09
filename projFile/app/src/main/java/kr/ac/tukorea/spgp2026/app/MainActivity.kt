package kr.ac.tukorea.spgp2026.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.ac.tukorea.spgp2026.BuildConfig
import kr.ac.tukorea.spgp2026.R
import kr.ac.tukorea.spgp2026.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var birdId = 0
    private var currPage = birdId
    private val characterImg = intArrayOf(
        R.mipmap.c0, R.mipmap.c1, R.mipmap.c2
        )
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(binding.root)

//        if (BuildConfig.DEBUG) {
//            Handler(Looper.getMainLooper()).postDelayed({
//                startGameActivity()
//            }, 1000)
//        }
        binding.characterSettingButton.setOnClickListener {
            binding.characterLayout.visibility = View.VISIBLE
            currPage = birdId
            binding.characterImg.setImageResource(characterImg[currPage])
            binding.selectButton.isEnabled = false
        }

        binding.closeButton.setOnClickListener {
            binding.characterLayout.visibility = View.GONE
        }
    }

    fun onStartGameClicked(view: View) {
        startGameActivity()
    }

    private fun startGameActivity() {
        Log.d(javaClass.simpleName, "Start Game")
        val intent = Intent(this, gameFlyActivity::class.java)
        intent.putExtra(gameFlyActivity.KEY_BIRD_ID, birdId)
        startActivity(intent)
    }

    fun onPrevCliked(view: View) {
        showImg(currPage - 1)
    }
    fun onNextCliked(view: View) {
        showImg(currPage + 1)
    }

    private fun showImg(page: Int) {
        val total = characterImg.size

        currPage = (page + total) % total

        binding.selectButton.isEnabled = currPage != birdId

        binding.characterImg.setImageResource(characterImg[currPage]
        )
    }

    fun onSelectCliked(view: View) {
        birdId = currPage
        binding.selectButton.isEnabled = false
    }
}