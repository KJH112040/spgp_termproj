package kr.ac.tukorea.spgp2026

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.AnimSprite
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IBoxCollidable
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Sprite
import kr.ac.tukorea.ge.spgp2026.a2dg.util.Gauge
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kotlin.math.abs

class Player(
    gctx: GameContext
): AnimSprite(gctx,R.mipmap.blue_bird,FPS), IBoxCollidable {
    override var width = PLAYER_WIDTH
    override var height = PLAYER_HEIGHT
    override var x = gctx.metrics.width / 2f
    override var y = gctx.metrics.height / 2f
    override val collisionRect = RectF()
    val minPlayerY = PLAYER_HEIGHT / 2f
    val maxPlayerY = gctx.metrics.height - PLAYER_HEIGHT / 2f
    var hp = DEFAULT_HP
    private var sp = 0f
    private var hitCoolTime = 0f
    private var setAlphaTime = 0f
    private val gauge = Gauge(
        0.1f,
        gctx.view.context.getColor(R.color.hp_gauge_fg),
        gctx.view.context.getColor(R.color.hp_gauge_bg),
    )

    init{
        srcRect = Rect(0, 0, SRC_WIDTH, SRC_WIDTH)
        syncDstRect()
        updateCollisionRect()
    }

    override fun update(gctx: GameContext){
        if(hitCoolTime > 0f){
            hitCoolTime -= gctx.frameTime
            setAlphaTime += gctx.frameTime

            if(setAlphaTime > 0.25f){
                paint.alpha = if(paint.alpha == 255) 128 else 255
                setAlphaTime = 0f
            }
        }else {
            paint.alpha = 255
            setAlphaTime = 0f
        }

        y += sp * gctx.frameTime
        sp += SPEED * gctx.frameTime
        y = y.coerceAtLeast(minPlayerY)

        syncDstRect()
        updateCollisionRect()
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)

        val gaugeWidth = width * 1f
        val gaugeX = x - gaugeWidth / 2f
        val gaugeY = dstRect.top
        gauge.draw(canvas, gaugeX, gaugeY, gaugeWidth, hp.toFloat() / DEFAULT_HP)
    }

    private fun updateCollisionRect(){
        collisionRect.set(dstRect)
        collisionRect.inset(COLLISION_INSET_X, COLLISION_INSET_Y)
    }

    fun fly(){
        sp = -SPEED
    }

    fun hit(){
        if(hitCoolTime > 0f) return
        hitCoolTime = 3.5f
        hp -= 1
    }

    companion object{
        const val DEFAULT_HP = 5
        const val SPEED = 500f
        const val PLAYER_WIDTH = 150f
        const val PLAYER_HEIGHT = 150f
        const val SRC_WIDTH = 40
        const val FPS = 15f
        const val COLLISION_INSET_X = 20f
        const val COLLISION_INSET_Y = 20f
    }
}