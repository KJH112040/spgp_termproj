package kr.ac.tukorea.spgp2026.Game.scenes

import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Button
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.HorzScrollBackground
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IGameObject
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.Scene
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.World
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kr.ac.tukorea.spgp2026.R

class GameOverScene (
    gctx: GameContext,
    birdID: Int,
    score:Int
) : Scene(gctx){
    enum class Layer{
        BACKGROUND,
        TOUCH
    }

    override val clipsRect = true
    override val isTransparent = true
    override val world = World(Layer.entries.toTypedArray()).apply{
        listOf(
            R.mipmap.bg_res,
            R.mipmap.clouds
        ).forEach { resId ->
            add(HorzScrollBackground(gctx, resId, 0f), Layer.BACKGROUND)
        }
    }
    override fun touchObjects(): List<IGameObject> {
        return world.objectsAt(Layer.TOUCH)
    }
}