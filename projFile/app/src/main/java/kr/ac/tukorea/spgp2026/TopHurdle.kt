package kr.ac.tukorea.spgp2026

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Rect
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IBoxCollidable
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IRecyclable
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Sprite
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kr.ac.tukorea.spgp2026.Player.Companion.COLLISION_INSET_X
import kr.ac.tukorea.spgp2026.Player.Companion.COLLISION_INSET_Y

class TopHurdle private constructor(
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
        srcRect = Rect(0,0,26,161)
        syncDstRect(top = 0f, bottom = y - gap / 2f)
        updateCollisionRect()
    }

    fun init(y: Float, speed: Float, gap: Float): TopHurdle{
        this.x = gctx.metrics.width + width / 2f
        this.y = y
        this.speed = speed
        this.gap = gap
        syncDstRect(top = 0f, bottom = y - gap / 2f)
        updateCollisionRect()
        return this
    }

    override fun update(gctx: GameContext) {
        x -= speed * gctx.frameTime

        if(x + width / 2f < 0f){
            val scene = gctx.scene as? MainScene ?: return
            scene.world.remove(this, MainScene.Layer.HURDLE)
        }

        syncDstRect(top = 0f, bottom = y - gap / 2f)
        updateCollisionRect()
    }
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
    }
    private fun updateCollisionRect(){
        collisionRect.set(dstRect)
        collisionRect.inset(COLLISION_INSET_X, COLLISION_INSET_Y)
    }
    override fun onRecycle() {
    }
    companion object{
        const val HURDLE_WIDTH = 150f
        const val DEFAULT_SPEED = 100f
        const val COLLISION_INSET_X = 5f
        const val COLLISION_INSET_Y = 5f
        fun get(gctx: GameContext, y: Float, speed: Float, gap: Float): TopHurdle{
            val scene = gctx.scene as? MainScene ?: return TopHurdle(gctx).init(y, speed, gap)
            val Thurdle = scene.world.obtain(TopHurdle::class.java) ?: TopHurdle(gctx)
            return Thurdle.init(y, speed, gap)
        }
    }
}