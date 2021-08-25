package baseAPIs

import java.awt.Graphics2D
import java.awt.Image
import java.awt.RenderingHints
import java.awt.Transparency
import java.awt.image.BufferedImage


class Game9Slice: GameObj {
    private var offset: Int = 0
    private var src: BMPImage? = null
    private var dest: BMPImage? = null

    constructor(offset: Int, src: BMPImage, w: Int, h: Int):super() {
        this.offset = offset
        this.src = src
        this.setWidth(w)
        this.setHeight(h)
        this.drawDest()
        this.setPosition(Vector2())
        this.setIsVisible(true)
    }
    
    constructor(offset: Int, src: BMPImage, w: Int, h: Int, pos: Vector2):super() {
        this.offset = offset
        this.src = src
        this.setWidth(w)
        this.setHeight(h)
        this.drawDest()
        this.setPosition(pos)
        this.setIsVisible(true)
    }

    constructor(obj: Game9Slice):super() {
        this.setOffset(obj.getOffset())
        if (obj.getSrc() == null) {
            this.setSrc(obj.getSrc())
        } else {
            this.setSrc(obj.getSrc()!!.cloneTyped())
        }
        this.setWidth(obj.getWidth())
        this.setHeight(obj.getHeight())
        this.drawDest()
        if (obj.getDest() == null) {
            this.setDest(obj.getDest())
        } else {
            this.setDest(obj.getDest()!!.cloneTyped())
        }
        if (obj.getPosition() == null) {
            setPosition(obj.getPosition())
        } else {
            setPosition(obj.getPosition().clone())
        }
        setIsVisible(obj.getIsVisible())
    }

    fun setOffset(i: Int) {
        this.offset = i
    }

    fun getOffset(): Int {
        return this.offset
    }

    fun getSrc(): BMPImage? {
        return this.src
    }

    fun setSrc(b: BMPImage?) {
        this.src = b
    }

    fun getDest(): BMPImage? {
        return this.dest
    }

    fun setDest(b: BMPImage?) {
        this.dest = b
    }

    fun drawDest() {
        val alpha = true
        val b: BMPImage? = getSrc()
        val img: Image = b?.getTexture2D()!!
        val bg: BufferedImage = ScreenData.GRAPHICS_CONFIG.createCompatibleImage(
            getWidth(),
            getHeight(),
            if (alpha) Transparency.TRANSLUCENT else Transparency.OPAQUE
        )
        val g = bg.graphics as Graphics2D
        if (Pen.ADV_RENDER_HINTS === true) {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        }
        val fs = offset

        //TOP
        //draw top left
        g.drawImage(img, 0, 0, fs, fs, 0, 0, fs, fs, null)

        //draw scaled top center
        g.drawImage(img, fs, 0, getWidth() - fs, fs, fs, 0, b.getWidth() - fs, fs, null)

        //draw top right
        g.drawImage(img, getWidth() - fs, 0, getWidth(), fs, b.getWidth() - fs, 0, b.getWidth(), fs, null)

        //MIDDLE
        //draw middle left
        g.drawImage(img, 0, fs, fs, getHeight() - fs, 0, fs, fs, b.getHeight() - fs, null)

        //draw middle center
        g.drawImage(img, fs, fs, getWidth() - fs, getHeight() - fs, fs, fs, b.getWidth() - fs, b.getHeight() - fs, null)

        //draw middle right
        g.drawImage(
            img,
            getWidth() - fs,
            fs,
            getWidth(),
            getHeight() - fs,
            b.getWidth() - fs,
            fs,
            b.getWidth(),
            b.getHeight() - fs,
            null
        )

        //BOTTOM
        //draw bottom left
        g.drawImage(img, 0, getHeight() - fs, fs, getHeight(), 0, b.getHeight() - fs, fs, b.getHeight(), null)

        //draw scaled bottom center
        g.drawImage(
            img,
            fs,
            getHeight() - fs,
            getWidth() - fs,
            getHeight(),
            fs,
            b.getHeight() - fs,
            b.getWidth() - fs,
            b.getHeight(),
            null
        )

        //draw bottom right
        g.drawImage(
            img,
            getWidth() - fs,
            getHeight() - fs,
            getWidth(),
            getHeight(),
            b.getWidth() - fs,
            b.getHeight() - fs,
            b.getWidth(),
            b.getHeight(),
            null
        )
        this.dest = BMPImage(bg)
    }

    /**
     * Creates a basic clone of this class.
     *
     * @return      A clone of this class.
     */
    override fun clone(): GameObj {
        return Game9Slice(this)
    }

    fun cloneTyped(): Game9Slice? {
        return Game9Slice(this)
    }

    fun getDestColor(): Color2? {
        return this.dest?.getObjColor()
    }

    fun setDestColor(c: Color2?) {
        this.dest?.setObjColor(c)
    }

    override fun draw(p: Pen) {
        if (this.isVisible) {
            if (this.dest != null) {
                p.drawBmp(this.dest!!, getPosition())
            }
        }
    }

    /**
     * Tests if this object is equal to another Mmg9Slice object.
     *
     * @param obj     An Mmg9Slice object instance to compare to.
     * @return      Returns true if the objects are considered equal and false otherwise.
     */
    override fun equals(obj: Any?): Boolean {
        if (obj == null) {
            return false
        }
        return when(obj){
            is Game9Slice -> {
                super.equals(obj) &&
                this.getOffset() == obj.getOffset() &&
                (obj.getSrc() == null && getSrc() == null || obj.getSrc() != null && getSrc() != null && obj.getSrc()!! == getSrc()) &&
                (obj.getDest() == null && getDest() == null || obj.getDest() != null && getDest() != null && obj.getDest()!! == getDest())
            }
            else -> false
        }
    }
}