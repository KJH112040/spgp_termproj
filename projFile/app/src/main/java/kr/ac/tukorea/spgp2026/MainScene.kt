package kr.ac.tukorea.spgp2026

import android.view.MotionEvent
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Button
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.HorzScrollBackground
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IGameObject
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.Scene
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.World
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext

class MainScene(gctx: GameContext) : Scene(gctx){
    enum class Layer {
        BACKGROUND,
        HURDLE,
        PLAYER,
        TOUCH,
        CONTROLLER,
        UI
    }

    override val clipsRect = true
    val player = Player(gctx)
    private val hurdles = HurdleManager(gctx)
    private val collisionChecker = CollisionChecker(gctx)
    override val world = World(Layer.entries.toTypedArray()).apply {
        listOf(
            R.mipmap.bg_res to BACKGROUND_SPEED,
            R.mipmap.clouds to CLOUD_SPEED
        ).forEach { (resId, speed) ->
            add(HorzScrollBackground(gctx,resId,speed), Layer.BACKGROUND)
        }
        add(collisionChecker,Layer.CONTROLLER)
        add(hurdles,Layer.CONTROLLER)

        add(player, Layer.PLAYER)

        add(Button(gctx, R.mipmap.btn_pause, gctx.metrics.width - 100f, 100f, PAUSE_BUTTON_SIZE, PAUSE_BUTTON_SIZE) { pressed ->
            if (pressed) {

            }
            true
        }, Layer.TOUCH)
    }

    override fun touchObjects(): List<IGameObject> {
        return world.objectsAt(Layer.TOUCH)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val handle = super.onTouchEvent(event)
        if(handle) return true

        if(event.actionMasked == MotionEvent.ACTION_DOWN){
            player.fly()
            return true
        }
        return false
    }
    companion object {
        private const val BACKGROUND_SPEED = -100f
        private const val CLOUD_SPEED = -150f
        private const val PAUSE_BUTTON_SIZE = 100f
    }
}