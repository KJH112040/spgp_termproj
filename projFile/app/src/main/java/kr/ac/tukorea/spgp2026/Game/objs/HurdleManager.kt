package kr.ac.tukorea.spgp2026

import android.graphics.Canvas
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IGameObject
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kotlin.random.Random

class HurdleManager(
    private val gctx : GameContext
) : IGameObject {
    private var stepUpTime = 30f
    private var step = 1
    private var hurdleTime = 0f
    private var speed = 100f
    private var genInterval = DISTANCE/speed
    private var gap = 500f
    private var prev_y = gctx.metrics.height / 2f
    override fun update(gctx: GameContext) {
        hurdleTime -= gctx.frameTime
        stepUpTime -= gctx.frameTime

        if(stepUpTime < 0f){
            if(step % 2 == 0)
                if(gap > MIN_GAP) gap = maxOf(MIN_GAP, gap - 30)
            if(speed < MAX_HURDLE_SPEED) speed = minOf(MAX_HURDLE_SPEED, speed + 20f)

            genInterval = maxOf(MIN_GEN_INTERVAL, DISTANCE/speed)
            step += 1
            stepUpTime = 30f
        }
        if(hurdleTime > 0f) return

        generate()
        hurdleTime = genInterval
    }

    private fun generate(){
        val scene = gctx.scene as? MainScene ?: return
        val miny = prev_y - 250f
        val maxy = prev_y + 250f
        val y = (miny + Random.nextFloat() * (maxy - miny))
            .coerceIn(600f, gctx.metrics.height - 600f)

        scene.world.add(TopHurdle.get(gctx,y,speed,gap), MainScene.Layer.HURDLE)
        scene.world.add(BottomHurdle.get(gctx,y,speed,gap), MainScene.Layer.HURDLE)

        prev_y = y
    }

    override fun draw(canvas: Canvas) {

    }

    companion object{
        const val DISTANCE = 600f
        const val MIN_GEN_INTERVAL = 0.5f
        const val MAX_HURDLE_SPEED = DISTANCE/MIN_GEN_INTERVAL
        const val MIN_GAP = 150f
    }
}