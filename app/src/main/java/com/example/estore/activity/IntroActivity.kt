package com.example.estore.activity

import android.content.Intent
import android.os.Bundle
import com.example.estore.databinding.ActivityIntroAcivityBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroAcivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroAcivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getStarted.setOnClickListener {
            Intent(this@IntroActivity, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}