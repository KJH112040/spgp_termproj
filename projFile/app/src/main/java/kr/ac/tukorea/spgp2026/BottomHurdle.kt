package kr.ac.tukorea.spgp2026

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Rect
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IBoxCollidable
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IRecyclable
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Sprite
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext

class BottomHurdle private constructor(
    private val gctx: GameContext
) : Sprite(gctx,R.mipmap.pipe), IBoxCollidable, IRecyclable {
    override var width = HURDLE_WIDTH
    var gap = 150f
        private set
    override var x = gctx.metrics.width + width / 2f
    override var y = gctx.metrics.height / 2
    private var speed = DEFAULT_SPEED

    override val collisionRect = RectF()

    init{
        srcRect = Rect(28,0,54,161)
        syncDstRect(top = y + gap / 2f, bottom = gctx.metrics.height)
    }

    fun init(y: Float, speed: Float, gap: Float): BottomHurdle{
        this.x = gctx.metrics.width + width / 2f
        this.y = y
        this.speed = speed
        this.gap = gap
        return this
    }

    override fun update(gctx: GameContext) {
        x -= speed * gctx.frameTime

        if(x + width / 2f < 0f){
            val scene = gctx.scene as? MainScene ?: return
            scene.world.remove(this, MainScene.Layer.HURDLE)
        }

        syncDstRect(top = y + gap / 2f, bottom = gctx.metrics.height)
    }
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
    }
    override fun onRecycle() {
    }
    companion object{
        const val HURDLE_WIDTH = 150f
        const val DEFAULT_SPEED = 100f
        fun get(gctx: GameContext, y: Float, speed: Float, gap: Float): BottomHurdle{
            val scene = gctx.scene as? MainScene ?: return BottomHurdle(gctx).init(y, speed, gap)
            val Bhurdle = scene.world.obtain(BottomHurdle::class.java) ?: BottomHurdle(gctx)
            return Bhurdle.init(y, speed, gap)
        }
    }
}