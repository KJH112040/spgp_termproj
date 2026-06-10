package kr.ac.tukorea.spgp2026.Game.objs

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
import kr.ac.tukorea.spgp2026.R
import kotlin.math.abs

class Player(
    gctx: GameContext,
    private val resID: Int
): AnimSprite(gctx, resID,FPS), IBoxCollidable {
    override var width = PLAYER_WIDTH
    override var height = PLAYER_HEIGHT
    override var x = gctx.metrics.width / 2f
    override var y = gctx.metrics.height / 2f
    override val collisionRect = RectF()
    val minPlayerY = PLAYER_HEIGHT / 2f
    val maxPlayerY = gctx.metrics.height - PLAYER_HEIGHT / 2f
    private var maxHP = 5
    private var SPEED = 400f
    private var gravity = 400f
    private var hp = 0
    private var sp = 0f
    private var hasUsedSkill = false
    private var hitCoolTime = 0f
    private var setAlphaTime = 0f
    private var skillDuration = 0f
    private val gauge = Gauge(
        0.1f,
        gctx.view.context.getColor(R.color.hp_gauge_fg),
        gctx.view.context.getColor(R.color.hp_gauge_bg),
    )

    init{
        when(resID){
            R.mipmap.blue_bird -> {
                maxHP = 5
                SPEED = 400f
                gravity = 400f
            }
            R.mipmap.red_bird -> {
                maxHP = 3
                SPEED = 350f
                gravity = 500f
            }
            R.mipmap.yellow_bird -> {
                maxHP = 7
                SPEED = 450f
                gravity = 300f
            }
        }

        hp = maxHP
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
            if(!(resID==R.mipmap.yellow_bird && skillDuration > 0f)){
                paint.alpha = 255
                setAlphaTime = 0f
            }
        }

        if (skillDuration > 0f){
            if(resID == R.mipmap.yellow_bird){
                setAlphaTime += gctx.frameTime

                if(setAlphaTime > 0.25f){
                    paint.alpha = if(paint.alpha == 255) 128 else 255
                    setAlphaTime = 0f
                }
            }
            skillDuration -= gctx.frameTime
            if (skillDuration > 0f == false){
                when(resID){
                    R.mipmap.yellow_bird -> {
                        paint.alpha = 255
                        setAlphaTime = 0f
                    }

                    R.mipmap.red_bird -> {
                        gravity = 500f
                    }
                }
            }
        }

        y += sp * gctx.frameTime
        sp += gravity * gctx.frameTime
        y = y.coerceAtLeast(minPlayerY)

        syncDstRect()
        updateCollisionRect()
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)

        val gaugeWidth = width * 1f
        val gaugeX = x - gaugeWidth / 2f
        val gaugeY = dstRect.top
        gauge.draw(canvas, gaugeX, gaugeY, gaugeWidth, hp.toFloat() / maxHP)
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
        if(resID==R.mipmap.yellow_bird && skillDuration > 0f) return
        hitCoolTime = 3.5f
        hp -= 1
    }

    fun isDead(): Boolean{
        return hp <= 0
    }

    fun skill(){
        if(hasUsedSkill) return
        when(resID){
            R.mipmap.blue_bird -> {
                if (hp == maxHP) return
                hp += 1
            }
            R.mipmap.red_bird -> {
                gravity = 350f
                skillDuration = 15f
            }
            R.mipmap.yellow_bird -> {
                skillDuration = 15f
            }
        }
        hasUsedSkill = true
    }

    companion object{
        const val PLAYER_WIDTH = 150f
        const val PLAYER_HEIGHT = 150f
        const val SRC_WIDTH = 40
        const val FPS = 15f
        const val COLLISION_INSET_X = 20f
        const val COLLISION_INSET_Y = 20f
    }
}