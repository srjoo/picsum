package com.smwrd.picsum.base

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.annotation.LayoutRes
import androidx.core.animation.doOnEnd
import androidx.databinding.ViewDataBinding
import kotlin.concurrent.thread
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

abstract class XmlIntroActivity<T : ViewDataBinding> (
    @LayoutRes layoutId: Int,
    private val startActivity: Class<out Activity>,
    private val minDataWaitTime: Long = 500L,
    private val splashAnimationTime: Long = 1000L,
) : XmlActivity<T>(layoutId) {
    private var isReady = false
    private var isStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        onInstallSplashScreen()
        super.onCreate(savedInstanceState)
        onInitData()
        onInitSplashScreen()
    }

    protected open fun onInstallSplashScreen() {
        installSplashScreen()
    }

    protected open fun onInitData() {
        thread(start = true) {
            val startTime = System.currentTimeMillis();
            onLoadData()
            val endTime = System.currentTimeMillis();
            if(endTime - startTime < minDataWaitTime) {
                Thread.sleep(endTime - startTime)
            }
            isReady = true
        }
    }

    protected open fun onLoadData() {

    }

    protected open fun onInitSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) { // API 31
            onSplashAnimation()
        } else {
            // 이하에서는 SplashScreen이 존재하지 않는다
            onStartActivity()
        }
    }

    protected open fun onSplashAnimation() {
        // Android 12 미만 : 바로 UI가 뜨도록
        val content: View = findViewById(android.R.id.content)
        // SplashScreen이 생성되고 그려질 때 계속해서 호출된다.
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.
                    return if (isReady) {
                        // 3초 후 Splash Screen 제거
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content is not ready
                        false
                    }
                }
            }
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) { // API 31
            // splashScreen이 종료 될 때 애니메이션 컨트롤.
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // Create your custom animation.
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y, // View.TRANSLATION_X 로 하면 왼쪽으로 사라짐.
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = splashAnimationTime // Splash Screen이 사라지는 시간.
                isStart = true

                // animation 종료 후 SplashScreenView remove
                slideUp.doOnEnd {
                    splashScreenView.remove()
                    onStartActivity()
                }
                slideUp.start()
            }
        }
    }

    protected open fun onStartActivity() {
        thread(start = true) {
            Thread.sleep(1000)
            startActivity(Intent(this, startActivity))
        }
    }
}