package kr.ac.tukorea.spgp2026

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Rect
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IBoxCollidable
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IRecyclable
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Sprite
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext

class Hurdle /*private constructor*/(
    private val gctx: GameContext
) : Sprite(gctx,R.mipmap.pipe), IBoxCollidable, IRecyclable {
    private val TOP_PIPE = Rect(0,0,26,161)
    private val BOTTOM_PIPE = Rect(28,0,54,161)
    override var width = HURDLE_WIDTH
    override var height = 100f
    override var x = gctx.metrics.width/2//-HURDLE_WIDTH / 2f
    override var y = 0f
    private var speed = DEFAULT_SPEED
    private val dstTop = RectF()
    private val dstBottom = RectF()
    override val collisionRect = RectF()

    init{

    }

//    fun init(): Hurdle{
//        return this
//    }


    override fun update(gctx: GameContext) {
        dstTop.set(
            x - width / 2f,
            y - height / 2f,
            x + width / 2f,
            y + height / 2f,
        )
    }
    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap,TOP_PIPE,dstTop,null)

    }
    override fun onRecycle() {
    }
    companion object{
        const val HURDLE_WIDTH = 100f
        const val DEFAULT_SPEED = 50f
//        fun get(gctx: GameContext): Hurdle{
//            val hurdle = Hurdle()
//            return hurdle.init()
//        }
    }
}