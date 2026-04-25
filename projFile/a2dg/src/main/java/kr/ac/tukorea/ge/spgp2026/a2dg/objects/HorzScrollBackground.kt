package kr.ac.tukorea.ge.spgp2026.a2dg.objects

import android.graphics.Canvas
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext

open class HorzScrollBackground (
    gctx: GameContext,
    resId: Int,
    private val speed: Float,
) : Sprite(gctx, resId) {
    private val screenWidth = gctx.metrics.width
    private val screenHeight = gctx.metrics.height
    private val tileHeight = bitmapHeight * screenWidth / bitmapWidth.toFloat()

    init{
        setCenterProportionalHeight(screenWidth / 2f, screenHeight / 2f, screenWidth)
    }
}