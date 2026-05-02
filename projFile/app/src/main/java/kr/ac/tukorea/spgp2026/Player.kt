package kr.ac.tukorea.spgp2026

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IBoxCollidable
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Sprite
import kr.ac.tukorea.ge.spgp2026.a2dg.util.Gauge
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kotlin.math.abs

class Player(
    val gctx: GameContext
): Sprite(gctx,R.mipmap.blue_bird), IBoxCollidable {
    override var width = PLAYER_WIDTH
    override var height = PLAYER_HEIGHT
    override var x = gctx.metrics.width / 2f
    override var y = gctx.metrics.height / 2f
    override val collisionRect = RectF()
    private var Hp = DEFAULT_HP
    init{
        srcRect = Rect(0, 0, SRC_WIDTH, SRC_WIDTH)
        syncDstRect()
        updateCollisionRect()
    }

    override fun update(gctx: GameContext){
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

    companion object{
        const val DEFAULT_HP = 5
        const val SPEED = 300f
        const val PLAYER_WIDTH = 150f
        const val PLAYER_HEIGHT = 150f
        const val SRC_WIDTH = 40
        const val COLLISION_INSET_X = 135f
        const val COLLISION_INSET_Y = 135f
    }
}