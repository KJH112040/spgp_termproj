package kr.ac.tukorea.spgp2026.Game.scenes

import android.graphics.Rect
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Button
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.HorzScrollBackground
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.IGameObject
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.ImageNumber
import kr.ac.tukorea.ge.spgp2026.a2dg.objects.Sprite
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.Scene
import kr.ac.tukorea.ge.spgp2026.a2dg.scene.World
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kr.ac.tukorea.spgp2026.Game.objs.Player.Companion.SRC_WIDTH
import kr.ac.tukorea.spgp2026.R

class GameOverScene (
    gctx: GameContext,
    private val birdID: Int,
    private val score:Int
) : Scene(gctx){
    enum class Layer{
        BACKGROUND,
        TOUCH
    }

    override val clipsRect = true
    override val isTransparent = true
    private val id = when(birdID){
        R.mipmap.blue_bird -> R.mipmap.c0
        R.mipmap.red_bird -> R.mipmap.c1
        R.mipmap.yellow_bird -> R.mipmap.c2
        else -> R.mipmap.c0
    }
    override val world = World(Layer.entries.toTypedArray()).apply{
        listOf(
            R.mipmap.bg_res,
            R.mipmap.clouds
        ).forEach { resId ->
            add(HorzScrollBackground(gctx, resId, 0f), Layer.BACKGROUND)
        }

        add(Sprite(gctx, R.mipmap.game_over).apply {
            x = gctx.metrics.width / 2f
            y = GAMEOVER_TEXT_H / 2f
            width = GAMEOVER_TEXT_W
            height = GAMEOVER_TEXT_H
            syncDstRect()
        }, Layer.BACKGROUND)

        add(Sprite(gctx, id).apply {
            x = gctx.metrics.width / 2f - PLAYER_WIDTH
            y = gctx.metrics.height / 2f
            width = PLAYER_WIDTH
            height = PLAYER_HEIGHT
            syncDstRect()
        }, Layer.BACKGROUND)

        add(ImageNumber(
            gctx,
            R.mipmap.number,
            gctx.metrics.width / 2f + PLAYER_WIDTH,
            gctx.metrics.height / 2f,
            60f).apply {
           value = score
        }, Layer.BACKGROUND)

        add(Button(gctx,R.mipmap.mv2title_btn,
            gctx.metrics.width / 2f, gctx.metrics.height / 2f + PLAYER_HEIGHT * 2.5f,
            300f, 150f){ pressed ->
            if(pressed){
                gctx.sceneStack.popAll()
            }
            true
        }, Layer.TOUCH)
    }
    override fun touchObjects(): List<IGameObject> {
        return world.objectsAt(Layer.TOUCH)
    }

    companion object {
        const val PLAYER_WIDTH = 200f
        const val PLAYER_HEIGHT = 200f
        const val  GAMEOVER_TEXT_W = 800f
        const val  GAMEOVER_TEXT_H = 600f
    }
}