package kr.ac.tukorea.spgp2026.app

import kr.ac.tukorea.ge.spgp2026.a2dg.activity.BaseGameActivity
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.Scene
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kr.ac.tukorea.spgp2026.Game.scenes.MainScene

class gameFlyActivity : BaseGameActivity() {
    override val drawsDebugGrid = true
    override val drawsDebugInfo = true
    override val drawsFpsGraph = true

    override fun createRootScene(gctx: GameContext): Scene {
        return MainScene(gctx)
    }
}