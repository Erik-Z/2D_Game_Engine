package baseAPIs

import java.awt.GraphicsConfiguration
import java.awt.GraphicsEnvironment


class ScreenData {
    companion object{
        val GRAPHICS_CONFIG: GraphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration
        var scalingMode: ScalingMode = ScalingMode.AXIS_X_AND_Y
        var DEFAULT_WIDTH: Int = 1024
        var DEFAULT_HEIGHT: Int = 768
        private var gameWidth: Int = 0
        private var gameHeight: Int = 0
        private var gameLeft: Int = 0
        private var gameTop: Int = 0
        private var screenWidth: Int = 0
        private var screenHeight: Int = 0
        private var scaleX: Double = 0.0
        private var scaleY: Double = 0.0
        private var scaleXOn: Boolean = false
        private var scaleYOn: Boolean = false
        private var scaleVec: Vector2 = Vector2(1, 1)
        private var posVec: Vector2? = null
        private var origGameWidth: Int = 0
        private var origGameHeight: Int = 0

        fun getGameWidth(): Int {
            return ScreenData.gameWidth
        }

        fun setGameWidth(w: Int) {
            ScreenData.gameWidth = w
        }

        fun getGameTop(): Int {
            return ScreenData.gameTop
        }

        fun getGameBottom(): Int {
            return ScreenData.gameTop + ScreenData.gameHeight
        }

        fun setGameTop(t: Int) {
            ScreenData.gameTop = t
        }

        fun getGameLeft(): Int {
            return ScreenData.gameLeft
        }

        fun getGameRight(): Int {
            return ScreenData.gameLeft + ScreenData.gameWidth
        }

        fun setGameLeft(l: Int) {
            ScreenData.gameLeft = l
        }

        fun getGameHeight(): Int {
            return ScreenData.gameHeight
        }


        fun setGameHeight(h: Int) {
            ScreenData.gameHeight = h
        }

        fun setScreenWidth(w: Int) {
            ScreenData.screenWidth = w
        }

        fun getScreenWidth(): Int {
            return ScreenData.screenWidth
        }

        fun setScreenHeight(h: Int) {
            ScreenData.screenHeight = h
        }

        fun getScreenHeight(): Int {
            return ScreenData.screenHeight
        }

        fun getScaleX(): Double {
            return ScreenData.scaleX
        }

        fun setScaleX(x: Double) {
            ScreenData.scaleX = x
        }

        fun getScaleY(): Double {
            return ScreenData.scaleY
        }

        fun setScaleY(y: Double) {
            ScreenData.scaleY = y
        }

        fun getScaleXOn(): Boolean {
            return ScreenData.scaleXOn
        }

        fun setScaleXOn(b: Boolean) {
            ScreenData.scaleXOn = b
        }

        fun getScaleYOn(): Boolean {
            return ScreenData.scaleYOn
        }

        fun setScaleYOn(b: Boolean) {
            ScreenData.scaleYOn = b
        }

        fun getScale(): Vector2? {
            return ScreenData.scaleVec
        }

        fun setScale(v: Vector2) {
            ScreenData.scaleVec = v
        }

        fun getPosition(): Vector2? {
            return ScreenData.posVec
        }

        private fun calculateTop() {
            ScreenData.gameTop = (ScreenData.screenHeight - ScreenData.gameHeight) / 2
        }

        private fun calculateLeft() {
            ScreenData.gameLeft = (ScreenData.screenWidth - ScreenData.gameWidth) / 2
        }
        
        private fun calculateScaleX(agg: Boolean) {
            val test = 32.0
            var resF: Double
            var resI: Double
            var prctDiffX: Double
            val panic = 5000
            var count: Int
            var resIi = 0
            var dir = -1.0
            var diff = 0.0
            var diffSm = 1000000.0
            var prctDiffXSm = 1.0
            prctDiffX = ScreenData.screenWidth as Double / ScreenData.gameWidth as Double
            dir = -1.0
            resF = test * prctDiffX
            resI = resF.toInt().toFloat().toDouble()
            resIi = resI.toInt()
            count = 0
            diff = Math.abs(resF - resI)
            while ((diff > 0.01 || resIi % 2 != 0) && count < panic) {
                prctDiffX += dir * 0.000250
                resF = test * prctDiffX
                resI = resF.toInt().toDouble()
                resIi = resI.toInt()
                count++
                diff = Math.abs(resF - resI)
                if (diff < diffSm) {
                    diffSm = diff
                    prctDiffXSm = prctDiffX
                }
            }
            if (count >= panic) {
                prctDiffX = prctDiffXSm
                diff = diffSm
            }
            ScreenData.scaleXOn = true
            ScreenData.scaleYOn = true
            if (agg) {
                ScreenData.scaleX += prctDiffX
                ScreenData.scaleY += prctDiffX
            } else {
                ScreenData.scaleX = prctDiffX
                ScreenData.scaleY = prctDiffX
            }
            ScreenData.gameWidth = (ScreenData.gameWidth * prctDiffX) as Int
            ScreenData.gameHeight = (ScreenData.gameHeight * prctDiffX) as Int
            calculateTop()
            calculateLeft()
            ScreenData.scaleVec = Vector2(ScreenData.scaleX, ScreenData.scaleY)
            ScreenData.posVec = Vector2(ScreenData.gameLeft, ScreenData.gameTop)
            APIUtils.wr("calculate Scale X: Found X,Y Scale: $prctDiffX, ResF: $resF, ResI: $resI, Diff: $diff, Count: $count")
        }

        private fun calculateScaleY(agg: Boolean) {
            val test = 32.0
            var resF: Double
            var resI: Double
            var prctDiffY: Double
            val panic = 5000
            var count: Int
            var resIi = 0
            var dir = -1.0
            var diff = 0.0
            var diffSm = 1000000.0
            var prctDiffYSm = 1.0
            prctDiffY = ScreenData.screenHeight as Double / ScreenData.gameHeight as Double
            dir = -1.0
            resF = test * prctDiffY
            resI = resF.toInt().toDouble()
            resIi = resI.toInt()
            count = 0
            diff = Math.abs(resF - resI)
            while ((diff > 0.01 || resIi % 2 != 0) && count < panic) {
                prctDiffY += dir * 0.000250
                resF = test * prctDiffY
                resI = resF.toInt().toDouble()
                resIi = resI.toInt()
                count++
                diff = Math.abs(resF - resI)
                if (diff < diffSm) {
                    diffSm = diff
                    prctDiffYSm = prctDiffY
                }
            }
            if (count >= panic) {
                prctDiffY = prctDiffYSm
                diff = diffSm
            }
            ScreenData.scaleXOn = true
            ScreenData.scaleYOn = true
            if (agg) {
                ScreenData.scaleX += prctDiffY
                ScreenData.scaleY += prctDiffY
            } else {
                ScreenData.scaleX = prctDiffY
                ScreenData.scaleY = prctDiffY
            }
            ScreenData.gameWidth = (ScreenData.gameWidth * prctDiffY) as Int
            ScreenData.gameHeight = (ScreenData.gameHeight * prctDiffY) as Int
            calculateTop()
            calculateLeft()
            ScreenData.scaleVec = Vector2(ScreenData.scaleX, ScreenData.scaleY)
            ScreenData.posVec = Vector2(ScreenData.gameLeft, ScreenData.gameTop)
            APIUtils.wr("calculate Scale Y: Found Updated X, Y Scale: $prctDiffY, ResF: $resF, ResI: $resI, Diff: $diff, Count: $count")
        }

        fun calculateScaleAndOffset() {
            ScreenData.gameWidth = ScreenData.origGameWidth
            ScreenData.gameHeight = ScreenData.origGameHeight
            if (ScreenData.screenHeight === ScreenData.gameHeight && ScreenData.screenWidth === ScreenData.gameWidth) {
                ScreenData.scaleX = 1.0f.toDouble()
                ScreenData.scaleY = 1.0f.toDouble()
                ScreenData.gameTop = 0
                ScreenData.gameLeft = 0
                ScreenData.scaleXOn = false
                ScreenData.scaleYOn = false
            } else {
                if (ScreenData.scalingMode === ScalingMode.AXIS_X) {
                    calculateScaleX(false)
                } else if (ScreenData.scalingMode === ScalingMode.AXIS_Y) {
                    calculateScaleY(false)
                } else if (ScreenData.scalingMode === ScalingMode.AXIS_X_AND_Y) {
                    calculateScaleX(false)
                    if (ScreenData.gameHeight > ScreenData.screenHeight) {
                        calculateScaleY(true)
                    }
                } else {
                    ScreenData.scaleX = 1.0f.toDouble()
                    ScreenData.scaleY = 1.0f.toDouble()
                    ScreenData.gameTop = 0
                    ScreenData.gameLeft = 0
                    ScreenData.scaleXOn = false
                    ScreenData.scaleYOn = false
                }
            }
        }

        override fun toString(): String {
            var ret = ""
            ret += "Screen Width: " + ScreenData.getScreenWidth().toString() + System.lineSeparator()
            ret += "Screen Height: " + ScreenData.getScreenHeight().toString() + System.lineSeparator()
            ret += "Game Width: " + ScreenData.getGameWidth().toString() + System.lineSeparator()
            ret += "Game Height: " + ScreenData.getGameHeight().toString() + System.lineSeparator()
            ret += "Game Offset X: " + ScreenData.getGameLeft().toString() + System.lineSeparator()
            ret += "Game Offset Y: " + ScreenData.getGameTop().toString() + System.lineSeparator()
            ret += "Scale X: " + ScreenData.getScaleX().toString() + System.lineSeparator()
            ret += "Scale Y: " + ScreenData.getScaleY().toString() + System.lineSeparator()
            return ret
        }
    }
    enum class ScalingMode {
        AXIS_X, AXIS_Y, AXIS_X_AND_Y, NONE
    }

    constructor() {
        ScreenData.gameWidth = DEFAULT_WIDTH
        ScreenData.gameHeight = DEFAULT_HEIGHT
        ScreenData.origGameWidth = ScreenData.gameWidth
        ScreenData.origGameHeight = ScreenData.gameHeight
        ScreenData.gameLeft = 0
        ScreenData.gameTop = 0
        ScreenData.screenWidth = DEFAULT_WIDTH
        ScreenData.screenHeight = DEFAULT_HEIGHT
        ScreenData.scaleX = 1.0
        ScreenData.scaleY = 1.0
        ScreenData.scaleVec = Vector2(ScreenData.scaleX, ScreenData.scaleY)
        ScreenData.posVec = Vector2(ScreenData.gameLeft, ScreenData.gameTop)
    }

    constructor(w: Int, h: Int) {
        ScreenData.gameWidth = w
        ScreenData.gameHeight = h
        ScreenData.origGameWidth = ScreenData.gameWidth
        ScreenData.origGameHeight = ScreenData.gameHeight
        ScreenData.gameLeft = 0
        ScreenData.gameTop = 0
        ScreenData.screenWidth = w
        ScreenData.screenHeight = h
        ScreenData.scaleX = 1.0f.toDouble()
        ScreenData.scaleY = 1.0f.toDouble()
        ScreenData.scaleVec = Vector2(ScreenData.scaleX, ScreenData.scaleY)
        ScreenData.posVec = Vector2(ScreenData.gameLeft, ScreenData.gameTop)
    }

    constructor(ScreenWidth: Int, ScreenHeight: Int, GameWidth: Int, GameHeight: Int) {
        ScreenData.screenWidth = ScreenWidth
        ScreenData.screenHeight = ScreenHeight
        ScreenData.gameWidth = GameWidth
        ScreenData.gameHeight = GameHeight
        ScreenData.origGameWidth = ScreenData.gameWidth
        ScreenData.origGameHeight = ScreenData.gameHeight
        ScreenData.calculateScaleAndOffset()
        ScreenData.scaleVec = Vector2(ScreenData.scaleX, ScreenData.scaleY)
        ScreenData.posVec = Vector2(ScreenData.gameLeft, ScreenData.gameTop)
    }
}