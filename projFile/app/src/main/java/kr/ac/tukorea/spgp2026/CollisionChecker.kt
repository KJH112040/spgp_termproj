package kr.ac.tukorea.spgp2026

import android.graphics.Canvas
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IGameObject
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.collidesWith
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext

class CollisionChecker(private val gctx: GameContext): IGameObject {
    override fun update(gctx: GameContext) {
        val scene = gctx.scene as? MainScene ?: return
        val player = scene.player

        scene.world.forEachReversedAt(MainScene.Layer.HURDLE){ hurdleObj ->
            val hurdle = hurdleObj as? TopHurdle ?:
            hurdleObj as? BottomHurdle ?: return@forEachReversedAt

            if(player.collidesWith(hurdle)){
                return@forEachReversedAt
            }
        }
    }

    override fun draw(canvas: Canvas) {

    }
}