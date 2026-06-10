package kr.ac.tukorea.spgp2026.Game.scenes

import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Button
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IGameObject
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.Scene
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.World
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kr.ac.tukorea.spgp2026.R

class GameOverScene (gctx: GameContext) : Scene(gctx){
    enum class Layer{
        TOUCH
    }
    override val world = World(Layer.entries.toTypedArray()).apply{

    }
    override fun touchObjects(): List<IGameObject> {
        return world.objectsAt(Layer.TOUCH)
    }
}