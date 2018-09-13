package io.skillstest.cdeveloper.skillstest.flipcardanimation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
    }


    private fun flipCard() {
        if (mShowingBack) {
            supportFragmentManager.popBackStack()
            mShowingBack = false
            return
        }

        // Flip to the back.

        mShowingBack = true

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        supportFragmentManager.beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, CardBackFragment())

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit()
    }
}
