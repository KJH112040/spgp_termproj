package kr.ac.tukorea.spgp2026.Game.objs

import kr.ac.tukorea.ge.spgp2026.a2dg.objects.ImageNumber
import kr.ac.tukorea.ge.spgp2026.a2dg.view.GameContext
import kr.ac.tukorea.spgp2026.R

class Score(gctx: GameContext): ImageNumber(
    gctx,
    R.mipmap.number,
    gctx.metrics.width / 2f,
    100f,
    60f

) {
    init {
        setValueImmediately(0)
    }
}