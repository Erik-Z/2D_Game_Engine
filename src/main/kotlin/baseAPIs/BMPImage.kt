package baseAPIs

import java.awt.Image




class BMPImage: GameObj {
    enum class BmpDrawMode {
        DRAW_BMP_FULL, DRAW_BMP_BASIC_CACHE, DRAW_BMP_BASIC
    }
    // TODO: Not sure if this is global will look into this later
    // TODO: three more constructors
    companion object{
        var ID_SRC = 0
    }
    private var origin: Vector2
    private var scaling: Vector2
    private var srcRect: Rectangle2
    private var dstRect: Rectangle2?
    private var b: Image?
    private var rotation: Float
    private var idStr: String
    private var id: Int
    var DRAW_MODE = BmpDrawMode.DRAW_BMP_BASIC
    constructor(){
        this.origin = Vector2()
        this.scaling = Vector2(1, 1)
        this.srcRect = Rectangle2(0, 0, 1, 1)
        this.dstRect = Rectangle2(0, 0, 1, 1)
        this.b = null
        this.rotation = 0f
        this.id = ID_SRC
        this.idStr = "${this.id}"
        ID_SRC++
    }

    constructor(obj: GameObj): super(obj){
        this.origin = Vector2()
        this.scaling = Vector2(1, 1)
        this.srcRect = Rectangle2(0, 0, 1, 1)
        this.dstRect = Rectangle2(0, 0, 1, 1)
        this.b = null
        this.rotation = 0f
        this.id = ID_SRC
        this.idStr = "${this.id}"
        ID_SRC++
    }

    constructor(img: Image):super(){
        this.origin = Vector2()
        this.scaling = Vector2(1, 1)
        this.srcRect = Rectangle2(0, 0, img.getWidth(null), img.getHeight(null))
        this.dstRect = null
        this.b = img
        this.rotation = 0f

        this.setPosition(Vector2())
        this.setWidth(this.b!!.getWidth(null))
        this.setHeight(this.b!!.getHeight(null));
        this.setIsVisible(true);
        this.setObjColor(null)

        this.id = ID_SRC
        this.idStr = "${this.id}"
        ID_SRC++
    }

    override fun clone(): GameObj {
        return BMPImage(this)
    }

    fun cloneTyped(): BMPImage {
        return BMPImage(this)
    }

    fun getIdStr(r: Float): String {
        return "${this.idStr}_${r}"
    }

    fun getIdStr(v: Vector2): String{
        return "${this.idStr}_${v.getXFloat()}x${v.getYFloat()}"
    }

    fun getIdStr(r: Float, v: Vector2): String {
        return "${this.idStr}_${r}_${v.getXFloat()}x${v.getYFloat()}"
    }

    fun getID(r: Float): Int{
        return Integer.parseInt("${this.id}0${r.toInt()}")
    }

    fun getID(v: Vector2): Int{
        return Integer.parseInt("${this.idStr}0${(v.getXFloat() * 10).toInt()}0${(v.getYFloat() * 10).toInt()}")
    }

    fun getID(r: Float, v: Vector2): Int{
        return Integer.parseInt("${this.idStr}0${r}0${(v.getXFloat() * 10).toInt()}0${(v.getYFloat() * 10).toInt()}")
    }

    fun getBmpIdStr(): String {
        return this.idStr
    }

    fun setBmpIdStr(i: String) {
        this.idStr = i
    }

    fun getBmpId(): Int {
        return this.id
    }

    fun getTexture2D(): Image? {
        return this.b
    }

    fun setTexture2D(b: Image) {
        this.b = b
    }

    fun getSrcRect(): Rectangle2 {
        return this.srcRect
    }

    fun setSrcRect(r: Rectangle2) {
        this.srcRect = r
    }

    fun getDstRect(): Rectangle2? {
        return this.dstRect
    }

    fun setDstRect(r: Rectangle2) {
        this.dstRect = r
    }

    fun getRotation(): Float {
        return this.rotation
    }

    fun setRotation(r: Float) {
        this.rotation = r
    }

    fun getOrigin(): Vector2 {
        return this.origin
    }

    fun setOrigin(v: Vector2) {
        this.origin = v
    }

    fun getScaling(): Vector2{
        return this.scaling
    }

    fun setScaling(v: Vector2){
        this.scaling = v
    }

    fun getScaledHeight(): Double {
        return if (getScaling() == null) {
            super.getHeight().toDouble()
        } else {
            super.getHeight() * getScaling().getX()
        }
    }

    fun getUnscaledHeight(): Int{
        return super.getHeight()
    }

    override fun getHeight(): Int {
        return this.getScaledHeight().toInt()
    }

    fun getHeightFloat(): Float{
        return this.getScaledHeight().toFloat()
    }

    fun getScaledWidth(): Double {
        return if (this.getScaling() == null) {
            super.getWidth().toDouble()
        } else {
            super.getWidth() * getScaling().getY()
        }
    }

    fun getUnscaledWidth(): Int {
        return super.getWidth()
    }

    override fun getWidth(): Int {
        return getScaledWidth().toInt()
    }

    fun getWidthFloat(): Float {
        return getScaledWidth().toFloat()
    }

    override fun draw(p: Pen) {
        if (this.isVisible) {
            if (this.DRAW_MODE === BmpDrawMode.DRAW_BMP_FULL) {
                p.drawBmp(this)
            } else if (this.DRAW_MODE === BmpDrawMode.DRAW_BMP_BASIC) {
                p.drawBmpBasic(this)
            } else if (this.DRAW_MODE === BmpDrawMode.DRAW_BMP_BASIC_CACHE) {
                p.drawBmpFromCache(this)
            }
        }
    }

    override fun equals(obj: Any?): Boolean {
        return when(obj){
            is BMPImage -> {
                super.equals(obj) &&
                this.getDstRect() == obj.getDstRect() &&
                this.getHeight() == obj.getHeight() &&
                this.getWidth() == obj.getWidth() &&
                this.getHeightFloat() == obj.getHeightFloat() &&
                this.getWidthFloat() == obj.getWidthFloat() &&
                ((this.getTexture2D() == null && obj.getTexture2D() == null) ||
                 (this.getTexture2D() != null && obj.getTexture2D() == null && this.getTexture2D() == obj.getTexture2D())) &&
                ((this.getOrigin() == null && obj.getOrigin() == null) ||
                 (this.getOrigin() != null && obj.getOrigin() != null && this.getOrigin() == obj.getOrigin())) &&
                this.getRotation() == obj.getRotation() &&
                this.getScaledHeight() == obj.getScaledHeight() &&
                this.getScaledWidth() == obj.getScaledWidth() &&
                ((this.getScaling() == null && obj.getScaling() == null) ||
                 (this.getScaling() != null && obj.getScaling() != null && this.getScaling() == obj.getScaling())) &&
                ((this.getSrcRect() == null && obj.getSrcRect() == null) ||
                 (this.getSrcRect() != null && obj.getSrcRect() != null && this.getSrcRect() == obj.getSrcRect())) &&
                this.getUnscaledHeight() == this.getUnscaledHeight() &&
                this.getUnscaledWidth() == this.getUnscaledWidth() &&
                this.DRAW_MODE == obj.DRAW_MODE
            }
            else -> false
        }
    }
}