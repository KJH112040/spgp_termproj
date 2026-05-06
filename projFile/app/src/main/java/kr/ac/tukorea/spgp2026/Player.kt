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
    private var Hp = DEFAULT_HP
    private var sp = 0f
    init{
        srcRect = Rect(0, 0, SRC_WIDTH, SRC_WIDTH)
        syncDstRect()
        updateCollisionRect()
    }

    override fun update(gctx: GameContext){
        //터치하지 않았을 때 계속 아래로 하강
        //터치했을 때 위로 날기.
        y += sp * gctx.frameTime
        sp += SPEED * gctx.frameTime
        y = y.coerceAtLeast(minPlayerY)

        //if(y >= maxPlayerY) gameover(아예 바닥으로 떨어졌을 경우 hp 관계 없이 바로 종료되도록)
        syncDstRect()
        updateCollisionRect()
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
    }

    private fun updateCollisionRect(){
        collisionRect.set(dstRect)
        collisionRect.inset(COLLISION_INSET_X, COLLISION_INSET_Y)
    }

    fun fly(){
        sp = -SPEED
    }

    companion object{
        const val DEFAULT_HP = 5
        const val SPEED = 700f
        const val PLAYER_WIDTH = 150f
        const val PLAYER_HEIGHT = 150f
        const val SRC_WIDTH = 40
        const val FPS = 5f
        const val COLLISION_INSET_X = 20f
        const val COLLISION_INSET_Y = 20f
    }
}