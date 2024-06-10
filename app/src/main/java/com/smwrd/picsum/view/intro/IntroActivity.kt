package com.smwrd.picsum.view.intro

import android.os.Bundle
import com.smwrd.picsum.R
import com.smwrd.picsum.base.XmlIntroActivity
import com.smwrd.picsum.databinding.ActivityIntroBinding
import com.smwrd.picsum.view.main.MainActivity

class IntroActivity : XmlIntroActivity<ActivityIntroBinding>(
    R.layout.activity_intro,
    MainActivity::class.java
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}