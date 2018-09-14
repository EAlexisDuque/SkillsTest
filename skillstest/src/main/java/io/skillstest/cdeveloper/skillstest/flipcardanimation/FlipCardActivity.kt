package io.skillstest.cdeveloper.skillstest.flipcardanimation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.skillstest.cdeveloper.skillstest.R
import kotlinx.android.synthetic.main.activity_flip_card.*


class FlipCardActivity : AppCompatActivity() {

    var mShowingBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card)


        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, CardFrontFragment())
                    .commit()
        }


        btnGirar.setOnClickListener {
            flipCard()
        }


        container.setOnClickListener {
            flipCard()
        }
    }


    fun flipCard() {
        if (mShowingBack) {
            supportFragmentManager.popBackStack()
            mShowingBack = false
            return
        }

        mShowingBack = true

        supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.container, CardBackFragment())
                .addToBackStack(null)
                .commit()
    }
}
