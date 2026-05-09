package kr.ac.tukorea.spgp2026.Game.scenes

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Button
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.DrawableSprite
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IGameObject
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.Scene
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.World
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kr.ac.tukorea.spgp2026.R

class PauseScene(gctx: GameContext) : Scene(gctx) {
    enum class Layer{
        BACKGROUND,
        TOUCH
    }

    override val clipsRect = true
    override val isTransparent = true

    override val world = World(Layer.entries.toTypedArray()).apply{
        add(DrawableSprite(ColorDrawable(Color.argb(128, 0, 0, 0))).apply {
            setCenter(gctx.metrics.width / 2f, gctx.metrics.height / 2f)
            setSize(gctx.metrics.width, gctx.metrics.height)
        }, Layer.BACKGROUND)

        add(Button(gctx,R.mipmap.continue_btn,
            gctx.metrics.width / 2f, gctx.metrics.height / 2f - 150f,
            200f, 100f){ pressed ->
            if(pressed){
                pop()
            }
            true
        }, Layer.TOUCH)

        add(Button(gctx,R.mipmap.setting_btn,
            gctx.metrics.width / 2f, gctx.metrics.height / 2f,
            200f, 100f){ pressed ->
            if(pressed){
                // 설정 어떻게 만들지
            }
            true
        }, Layer.TOUCH)

        add(Button(gctx,R.mipmap.mv2title_btn,
            gctx.metrics.width / 2f, gctx.metrics.height / 2f + 150f,
            200f, 100f){ pressed ->
            if(pressed){
                gctx.sceneStack.popAll()
            }
            true
        }, Layer.TOUCH)
    }
    override fun touchObjects(): List<IGameObject> {
        return world.objectsAt(Layer.TOUCH)
    }
}