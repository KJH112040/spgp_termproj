package kr.ac.tukorea.spgp2026

import android.graphics.Canvas
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IGameObject
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.collidesWith
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext

class CollisionChecker(private val gctx: GameContext): IGameObject {
    override fun update(gctx: GameContext) {
        val scene = gctx.scene as? MainScene ?: return
        val player = scene.player

        if(player.collisionRect.bottom > gctx.metrics.height) {
            gctx.sceneStack.popAll()
            return //떨어짐 처리
        }

        scene.world.forEachReversedAt(MainScene.Layer.HURDLE){ hurdleObj ->
            val hurdle = hurdleObj as? TopHurdle ?:
            hurdleObj as? BottomHurdle ?: return@forEachReversedAt

            if(!player.collidesWith(hurdle)) return@forEachReversedAt

            player.hit()
            if(player.hp <= 0) gctx.sceneStack.popAll()
        }
    }

    override fun draw(canvas: Canvas) {

    }
}