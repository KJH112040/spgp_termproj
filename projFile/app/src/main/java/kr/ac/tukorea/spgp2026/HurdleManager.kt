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
    private var genInterval = 15f
    private var speed = 50f
    private var gap = 500f
    private var prev_y = gctx.metrics.height / 2f
    override fun update(gctx: GameContext) {
        hurdleTime -= gctx.frameTime
        stepUpTime -= gctx.frameTime
        if(hurdleTime > 0f) return

        generate()
        if(stepUpTime < 0f){
            if(step > 60) return
            if(step % 2 == 0)
                if(gap > MIN_GAP) gap -= 10f
            if(step % 6 == 0)
                if(genInterval > MIN_GEN_INTERVAL) genInterval -= 1f
            if(speed < MAX_HURDLE_SPEED) speed += 50f
            step += 1
            stepUpTime = 30f
        }
        hurdleTime = genInterval
    }

    private fun generate(){
        val scene = gctx.scene as? MainScene ?: return
        // 점점 빨라지는 기둥의 속도, 생성 주기가 짧아짐. 빠져나갈 수 있는 간격이 줄어듬.
        // 간격의 위치는 이전 간격 위치 기준 random +-100
        val miny = prev_y - 100f
        val maxy = prev_y + 100f
        val y = (miny + Random.nextFloat() * (maxy - miny))
            .coerceIn(650f, gctx.metrics.height - 650f)

        scene.world.add(Hurdle.get(gctx,y,speed,gap), MainScene.Layer.HURDLE)

        prev_y = y
    }

    override fun draw(canvas: Canvas) {

    }

    companion object{
        const val MIN_GEN_INTERVAL = 5f
        const val MAX_HURDLE_SPEED = 300f
        const val MIN_GAP = 150f
    }
}