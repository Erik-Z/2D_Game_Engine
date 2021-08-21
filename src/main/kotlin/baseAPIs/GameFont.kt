package baseAPIs

import java.awt.Font
import java.awt.Rectangle
import java.awt.font.FontRenderContext
import java.awt.geom.Rectangle2D


class GameFont: GameObj {
    enum class FontType(val i: Int){
        NORMAL(1), BOLD(2), ITALIC(3), NONE(-1)
    }
    private val frc: FontRenderContext
    private var text: String
    private var font: Font?
    private var fontSize: Int = -1
    private var fontType: FontType = FontType.NONE

    constructor(): super() {
        this.frc = FontRenderContext(null, true, true)
        this.text = ""
        this.font = null
    }

    constructor(obj: GameObj): super(obj){
        this.frc = FontRenderContext(null, true, true)
        this.text = ""
        this.font = null
    }

    constructor(font: Font, fontType: FontType): super(){
        this.frc = FontRenderContext(null, true, true)
        this.text = ""
        this.font = font
        this.setFontType(fontType)
    }

    constructor(font: Font, fontSize: Int, fontType: FontType){
        this.frc = FontRenderContext(null, true, true)
        this.text = ""
        this.font = font
        this.setFontType(fontType)
        this.setFontSize(fontSize)
    }

    constructor(font: Font, text: String, x: Int, y: Int, color: Color2): super(){
        this.frc = FontRenderContext(null, true, true)
        this.font = font
        this.text = text
        this.setPosition(Vector2(x, y))
        this.setIsVisible(true)
        this.setObjColor(color)
    }

    override fun clone(): GameFont {
        return GameFont(this)
    }

    fun getText(): String{
        return this.text
    }

    fun setText(s: String?){
        if (s != null){
            this.text = s
            if("" != this.text){
                val r: Rectangle2D = this.font!!.getStringBounds(text, this.frc)
                val r2: Rectangle = r.bounds
                this.setWidth(r2.getWidth().toInt())
                this.setHeight(r2.getHeight().toInt())
            } else {
                this.setWidth(0)
                this.setHeight(0)
            }
        } else {
            this.text == null
            this.setWidth(0)
            this.setHeight(0)
        }
    }

    fun getFontSize(): Int{
        return this.fontSize
    }

    fun setFontSize(sz: Int){
        if (sz > 0 && sz <= FontData.MAX_FONT_SIZE) {
            this.fontSize = sz
            this.font = this.font!!.deriveFont(sz as Float)
        } else {
            APIUtils.wr("MmgFont: Error size must be greater than 0 and less than " + FontData.MAX_FONT_SIZE)
        }
    }

    fun getFontType(): FontType{
        return this.fontType
    }

    fun setFontType(ft: FontType){
        this.fontType = ft
    }

    fun getFont(): Font?{
        return this.font
    }

    fun setFont(tf: Font){
        this.font = tf
    }

    override fun draw(p: Pen){
        if (isVisible){
            p.drawText(this)
        }
    }

    override fun equals(obj: Any?): Boolean {
        return when(obj){
            is GameFont -> {
                super.equals(obj)
                this.getFont() == obj.getFont() &&
                this.getText() == obj.getText()
            }
            else -> false
        }
    }
}