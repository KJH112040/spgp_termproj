package kr.ac.tukorea.spgp2026

import kr.ac.tukorea.ge.spgp2026.a2dg.objects.HorzScrollBackground
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.Scene
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.World
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext

class MainScene(gctx: GameContext) : Scene(gctx){
    enum class Layer {
        BACKGROUND,
        CLOUD,
        HURDLE,
        PLAYER,
        CONTROLLER
    }

    override val clipsRect = true
    private val background = HorzScrollBackground(gctx, R.mipmap.bg_res, BACKGROUND_SPEED)
    private val clouds = HorzScrollBackground(gctx, R.mipmap.clouds,CLOUD_SPEED)
    val player = Player(gctx)
    private val hurdles = HurdleManager(gctx)
    private val collisionChecker = CollisionChecker(gctx)
    override val world = World(Layer.entries.toTypedArray()).apply {
        add(background,Layer.BACKGROUND)
        add(clouds,Layer.CLOUD)
        add(player, Layer.PLAYER)
        add(hurdles,Layer.CONTROLLER)
        add(collisionChecker,Layer.CONTROLLER)
    }

    companion object {
        private const val BACKGROUND_SPEED = -100f
        private const val CLOUD_SPEED = -150f
    }
}