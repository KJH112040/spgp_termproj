package kr.ac.tukorea.spgp2026.Game.objs

import android.graphics.Canvas
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IGameObject
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.collidesWith
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kr.ac.tukorea.spgp2026.Game.scenes.GameOverScene
import kr.ac.tukorea.spgp2026.Game.scenes.MainScene

class CollisionChecker(private val gctx: GameContext): IGameObject {
    override fun update(gctx: GameContext) {
        val scene = gctx.scene as? MainScene ?: return
        val player = scene.player

        if(player.collisionRect.bottom > gctx.metrics.height) {
            GameOverScene(gctx, player.id, scene.resultScore).change()
            return
        }

        scene.world.forEachReversedAt(MainScene.Layer.HURDLE){ hurdleObj ->
            val hurdle = hurdleObj as? TopHurdle ?:
            hurdleObj as? BottomHurdle ?: return@forEachReversedAt

            if(hurdle is TopHurdle)
                scene.addScore(hurdle.getScore(player.collisionRect.left))
            if(!player.collidesWith(hurdle)) return@forEachReversedAt

            player.hit()
            if(player.isDead()) {
                GameOverScene(gctx, player.id, scene.resultScore).change()
                return@forEachReversedAt
            }
        }
    }

    override fun draw(canvas: Canvas) {

    }
}