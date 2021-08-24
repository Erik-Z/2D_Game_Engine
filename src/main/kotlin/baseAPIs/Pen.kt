package baseAPIs

import java.awt.*
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage


class Pen(private var pen: Graphics?, private var color: Color?, private var tmpImg: Image?, private var tmpIDString: String,
          private var cacheOn: Boolean, private var tmpF: Font?, private var tmpC: Color?) {
    constructor():this(null, null, null, "", false, null, null)
    constructor(p: Graphics):this(p, null, null, "", false, null, null)
    constructor(img: Image):this(img.graphics, null, null, "", false, null, null)
    constructor(p: Graphics, c: Color):this(p, c, null, "", false, null, null)

    /**
     *  Static Variables for this class
     */
    companion object {
        const val FONT_NORMALIZE_POSITION: Boolean = false
        const val ADV_RENDER_HINTS: Boolean = true
        val TRANSPARENT: Color = Color(0f, 0f, 0f, 1f)
    }

    fun getCacheOn(): Boolean{
        return this.cacheOn
    }

    fun setCacheOn(b: Boolean){
        this.cacheOn = b
    }

    fun drawText(f: GameFont){
        this.tmpF = this.pen?.font
        this.tmpC = this.pen?.color
        this.pen?.color = f.getObjColor()?.getColor()
        this.pen?.font = f.getFont()
        if(FONT_NORMALIZE_POSITION){
            pen?.drawString(
                f.getText(),
                HelperFunctions.NormalizeFontPositionX(f.getPosition().getXInt(), f),
                HelperFunctions.NormalizeFontPositionY(f.getPosition().getYInt(), f)
            )
        } else {
            pen?.drawString(f.getText(), f.getPosition().getXInt(), f.getPosition().getYInt());
        }
        pen?.font = tmpF
        pen?.color = tmpC
    }

    fun drawText(f: GameFont, x: Int, y:Int){
        this.tmpF = this.pen?.font
        this.tmpC = this.pen?.color
        this.pen?.color = f.getObjColor()?.getColor()
        this.pen?.font = f.getFont()
        if(FONT_NORMALIZE_POSITION){
            pen?.drawString(
                f.getText(),
                HelperFunctions.NormalizeFontPositionX(x, f),
                HelperFunctions.NormalizeFontPositionY(y, f)
            )
        } else {
            pen?.drawString(f.getText(), x, y);
        }
        pen?.font = tmpF
        pen?.color = tmpC
    }

    fun drawText(f: GameFont, v: Vector2){
        this.tmpF = this.pen?.font
        this.tmpC = this.pen?.color
        this.pen?.color = f.getObjColor()?.getColor()
        this.pen?.font = f.getFont()
        if(FONT_NORMALIZE_POSITION){
            pen?.drawString(
                f.getText(),
                HelperFunctions.NormalizeFontPositionX(v.getXInt(), f),
                HelperFunctions.NormalizeFontPositionY(v.getYInt(), f)
            )
        } else {
            pen?.drawString(f.getText(), v.getXInt(), v.getYInt());
        }
        pen?.font = tmpF
        pen?.color = tmpC
    }

    fun rotateImage(width: Int, height: Int, img: Image?, angle: Int, originX: Int, originY: Int): Image? {
        var bi = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val g = bi.createGraphics()
        if (Pen.ADV_RENDER_HINTS) {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        }
        val at = AffineTransform()
        if (originX == -1 || originY == -1) {
            at.rotate(Math.toRadians(angle.toDouble()), (width / 2).toDouble(), (height / 2).toDouble())
        } else {
            at.rotate(Math.toRadians(angle.toDouble()), originX.toDouble(), originY.toDouble())
        }
        g.drawImage(img, 0, 0, null)
        val op = AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR)
        bi = op.filter(bi, null)
        g.dispose()
        return bi
    }

    fun scaleImage(img: Image, scaleX: Double, scaleY: Double): Image? {
        val w = (img.getWidth(null) * scaleX).toInt()
        val h = (img.getHeight(null) * scaleY).toInt()
        val rImage = BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)
        val g = rImage.createGraphics()
        if (Pen.ADV_RENDER_HINTS) {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        }
        g.drawImage(img, 0, 0, w, h, null)
        g.dispose()
        return rImage
    }

    fun scaleImage(img: Image, scale: Vector2): Image? {
        return this.scaleImage(img, scale.getX(), scale.getY())
    }

    fun createColorTile(w: Int, h: Int, c: Color2): Image? {
        val rImage = BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)
        val g = rImage.createGraphics()
        g.color = c.getColor()
        g.fillRect(0, 0, w, h)
        g.dispose()
        return rImage
    }

    fun setAdvRenderHints() {
        if (Pen.ADV_RENDER_HINTS) {
            val g = pen as Graphics2D
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        } else {
            APIUtils.wr("ADV_RENDER_HINTS is set to false.")
        }
    }

    fun drawBmpBasic(idStr: String?, position: Vector2) {
        tmpImg = idStr?.let { MediaTracker.getBmpValue(it) }
        if (tmpImg != null) {
            this.drawBmp(tmpImg, position.getXInt(), position.getYInt())
        }
    }

    fun drawBmpBasic(b: BMPImage) {
        this.drawBmp(b, b.getPosition())
    }

    fun drawBmp(img: Image?, x: Int, y: Int) {
        this.pen!!.drawImage(img, x, y, null)
    }

    fun drawBmp(b: BMPImage, position: Vector2) {
        if (this.color != null) {
            this.pen!!.drawImage(b.getTexture2D(), position.getXInt(), position.getYInt(), this.color, null)
        } else if (b.getObjColor() != null) {
            this.pen!!.drawImage(b.getTexture2D(), position.getXInt(), position.getYInt(), b.getObjColor()!!.getColor(), null)
        } else {
            this.pen!!.drawImage(b.getTexture2D(), position.getXInt(), position.getYInt(), null)
        }
    }

    fun drawBmp(b: BMPImage, x: Int, y: Int) {
        if (color != null) {
            this.pen!!.drawImage(b.getTexture2D(), x, y, this.color, null)
        } else if (b.getObjColor() != null) {
            this.pen!!.drawImage(b.getTexture2D(), x, y, b.getObjColor()!!.getColor(), null)
        } else {
            this.pen!!.drawImage(b.getTexture2D(), x, y, null)
        }
    }

    fun drawBmp(b: BMPImage, position: Vector2, rotation: Float) {
        this.drawBmp(b, position, Vector2(b.getWidth() / 2, b.getHeight() / 2), rotation)
    }

    fun drawBmp(b: BMPImage, position: Vector2, origin: Vector2, rotation: Float) {
        var tmpIdStr = b.getIdStr(rotation)
        if (rotation != 0f) {
            if (this.cacheOn && MediaTracker.hasBmpKey(tmpIdStr)) {
                this.tmpImg = MediaTracker.getBmpValue(tmpIdStr)
            } else {
                this.tmpImg = this.rotateImage(
                    b.getWidth(),
                    b.getHeight(),
                    b.getTexture2D(),
                    rotation.toInt(),
                    origin.getXInt(),
                    origin.getYInt()
                )
                if (this.cacheOn) {
                    this.tmpImg?.let { MediaTracker.cacheImage(tmpIdStr, it) }
                }
            }
        } else {
            this.tmpImg = b.getTexture2D()
        }
        if (this.color != null) {
            this.pen!!.drawImage(this.tmpImg, position.getXInt(), position.getYInt(), this.color, null)
        } else if (b.getObjColor() != null) {
            this.pen!!.drawImage(this.tmpImg, position.getXInt(), position.getYInt(), b.getObjColor()!!.getColor(), null)
        } else {
            this.pen!!.drawImage(this.tmpImg, position.getXInt(), position.getYInt(), null)
        }
    }

    fun drawBmp(b: BMPImage, srcRect: Rectangle2?, dstRect: Rectangle2?) {
        var tmpIdStr = b.getBmpIdStr()
        tmpImg = if (this.cacheOn && MediaTracker.hasBmpKey(tmpIdStr) === true) {
            MediaTracker.getBmpValue(tmpIdStr)
        } else {
            b.getTexture2D()
        }
        if (srcRect != null && dstRect != null) {
            this.pen!!.drawImage(
                this.tmpImg,
                dstRect.getLeft(),
                dstRect.getTop(),
                dstRect.getRight(),
                dstRect.getBottom(),
                srcRect.getLeft(),
                srcRect.getTop(),
                srcRect.getRight(),
                srcRect.getBottom(),
                null
            )
        } else if (srcRect == null) {
            if (dstRect != null) {
                this.pen!!.drawImage(
                    this.tmpImg,
                    dstRect.getLeft(),
                    dstRect.getTop(),
                    dstRect.getRight(),
                    dstRect.getBottom(),
                    0,
                    0,
                    b.getWidth(),
                    b.getHeight(),
                    null
                )
            }
        } else if (dstRect == null) {
            pen!!.drawImage(
                tmpImg,
                0,
                0,
                b.getWidth(),
                b.getHeight(),
                srcRect.getLeft(),
                srcRect.getTop(),
                srcRect.getRight(),
                srcRect.getBottom(),
                null
            )
        } else {
            pen!!.drawImage(tmpImg, 0, 0, b.getWidth(), b.getHeight(), 0, 0, b.getWidth(), b.getHeight(), null)
        }
    }

    fun drawBmp(
        b: BMPImage,
        position: Vector2,
        srcRect: Rectangle2?,
        dstRect: Rectangle2?,
        scaling: Vector2?,
        origin: Vector2,
        rotation: Float
    ) {
        var tmpIdStr: String
        if (rotation != 0f && scaling != null && (scaling.getX() !== 1.0 || scaling.getY() !== 1.0)) {
            tmpIdStr = b.getIdStr(rotation, scaling)
        } else if (rotation != 0f) {
            tmpIdStr = b.getIdStr(rotation)
        } else if (scaling != null && (scaling.getX() !== 1.0 || scaling.getY() !== 1.0)) {
            tmpIdStr = b.getIdStr(scaling)
        } else {
            tmpIdStr = b.getBmpIdStr()
        }
        if (this.cacheOn && MediaTracker.hasBmpKey(tmpIdStr)) {
            this.tmpImg = MediaTracker.getBmpValue(tmpIdStr)
        } else if (rotation != 0f && scaling != null && (scaling.getX() !== 1.0 || scaling.getY() !== 1.0)) {
            if (this.cacheOn && MediaTracker.hasBmpKey(b.getIdStr(rotation))) {
                this.tmpImg = MediaTracker.getBmpValue(b.getIdStr(rotation))
            } else {
                this.tmpImg = rotateImage(
                    b.getWidth(),
                    b.getHeight(),
                    b.getTexture2D(),
                    rotation.toInt(),
                    origin.getXInt(),
                    origin.getYInt()
                )
                if (this.cacheOn) {
                    tmpImg?.let { MediaTracker.cacheImage(b.getIdStr(rotation), it) }
                }
            }
            tmpImg = this.scaleImage(this.tmpImg!!, scaling.getX(), scaling.getY())
            if (this.cacheOn) {
                MediaTracker.cacheImage(tmpIdStr, this.tmpImg!!)
            }
        } else if (rotation != 0f) {
            if (this.cacheOn && MediaTracker.hasBmpKey(tmpIdStr)) {
                this.tmpImg = MediaTracker.getBmpValue(tmpIdStr)
            } else {
                tmpImg = rotateImage(
                    b.getWidth(),
                    b.getHeight(),
                    b.getTexture2D(),
                    rotation.toInt(),
                    origin.getXInt(),
                    origin.getYInt()
                )
                if (this.cacheOn) {
                    MediaTracker.cacheImage(tmpIdStr, this.tmpImg!!)
                }
            }
        } else if (scaling != null && (scaling.getX() !== 1.0 || scaling.getY() !== 1.0)) {
            if (this.cacheOn && MediaTracker.hasBmpKey(tmpIdStr)) {
                this.tmpImg = MediaTracker.getBmpValue(tmpIdStr)
            } else {
                this.tmpImg = scaleImage(this.tmpImg!!, scaling.getX(), scaling.getY())
                if (this.cacheOn) {
                    MediaTracker.cacheImage(tmpIdStr, this.tmpImg!!)
                }
            }
        } else {
            this.tmpImg = b.getTexture2D()
        }
        if (dstRect == null) {
            if (srcRect == null) {
                if (color != null) {
                    pen!!.drawImage(tmpImg, position.getXInt(), position.getYInt(), color, null)
                } else if (b.getObjColor() != null) {
                    pen!!.drawImage(tmpImg, position.getXInt(), position.getYInt(), b.getObjColor()!!.getColor(), null)
                } else {
                    pen!!.drawImage(tmpImg, position.getXInt(), position.getYInt(), null)
                }
            } else {
                //src rect is not null
                if (color != null) {
                    pen!!.drawImage(
                        tmpImg,
                        position.getXInt(),
                        position.getYInt(),
                        position.getXInt() + srcRect.getWidth(),
                        position.getYInt() + srcRect.getHeight(),
                        srcRect.getLeft(),
                        srcRect.getTop(),
                        srcRect.getRight(),
                        srcRect.getBottom(),
                        this.color,
                        null
                    )
                } else if (b.getObjColor() != null) {
                    pen!!.drawImage(
                        tmpImg,
                        position.getXInt(),
                        position.getYInt(),
                        position.getXInt() + srcRect.getWidth(),
                        position.getYInt() + srcRect.getHeight(),
                        srcRect.getLeft(),
                        srcRect.getTop(),
                        srcRect.getRight(),
                        srcRect.getBottom(),
                        b.getObjColor()!!.getColor(),
                        null
                    )
                } else {
                    pen!!.drawImage(
                        tmpImg,
                        position.getXInt(),
                        position.getYInt(),
                        position.getXInt() + srcRect.getWidth(),
                        position.getYInt() + srcRect.getHeight(),
                        srcRect.getLeft(),
                        srcRect.getTop(),
                        srcRect.getRight(),
                        srcRect.getBottom(),
                        null
                    )
                }
            }
        } else {
            if (srcRect == null) {
                if (color != null) {
                    pen!!.drawImage(
                        tmpImg,
                        dstRect.getLeft(),
                        dstRect.getTop(),
                        dstRect.getRight(),
                        dstRect.getBottom(),
                        0,
                        0,
                        b.getWidth(),
                        b.getHeight(),
                        color,
                        null
                    )
                } else if (b.getObjColor() != null) {
                    pen!!.drawImage(
                        tmpImg,
                        dstRect.getLeft(),
                        dstRect.getTop(),
                        dstRect.getRight(),
                        dstRect.getBottom(),
                        0,
                        0,
                        b.getWidth(),
                        b.getHeight(),
                        b.getObjColor()!!.getColor(),
                        null
                    )
                } else {
                    pen!!.drawImage(
                        tmpImg,
                        dstRect.getLeft(),
                        dstRect.getTop(),
                        dstRect.getRight(),
                        dstRect.getBottom(),
                        0,
                        0,
                        b.getWidth(),
                        b.getHeight(),
                        null
                    )
                }
            } else {
                if (color != null) {
                    pen!!.drawImage(
                        tmpImg,
                        dstRect.getLeft(),
                        dstRect.getTop(),
                        dstRect.getRight(),
                        dstRect.getBottom(),
                        srcRect.getLeft(),
                        srcRect.getTop(),
                        srcRect.getRight(),
                        srcRect.getBottom(),
                        color,
                        null
                    )
                } else if (b.getObjColor() != null) {
                    pen!!.drawImage(
                        tmpImg,
                        dstRect.getLeft(),
                        dstRect.getTop(),
                        dstRect.getRight(),
                        dstRect.getBottom(),
                        srcRect.getLeft(),
                        srcRect.getTop(),
                        srcRect.getRight(),
                        srcRect.getBottom(),
                        b.getObjColor()!!.getColor(),
                        null
                    )
                } else {
                    pen!!.drawImage(
                        tmpImg,
                        dstRect.getLeft(),
                        dstRect.getTop(),
                        dstRect.getRight(),
                        dstRect.getBottom(),
                        srcRect.getLeft(),
                        srcRect.getTop(),
                        srcRect.getRight(),
                        srcRect.getBottom(),
                        null
                    )
                }
            }
        }
    }

    fun drawBmp(b: BMPImage) {
        drawBmp(b, b.getPosition(), b.getSrcRect(), b.getDstRect(), b.getScaling(), b.getOrigin(), b.getRotation())
    }

    fun drawRect(obj: GameObj) {
        drawRect(obj.getPosition().getXInt(), obj.getPosition().getYInt(), obj.getWidth(), obj.getHeight())
    }

    fun drawRect(obj: GameObj, pos: Vector2) {
        drawRect(pos.getXInt(), pos.getYInt(), obj.getWidth(), obj.getHeight())
    }

    fun drawRect(r: Rectangle2) {
        drawRect(r.getLeft(), r.getTop(), r.getWidth(), r.getHeight())
    }

    fun drawRect(x: Int, y: Int, w: Int, h: Int) {
        pen!!.drawRect(x, y, w, h)
    }

    fun drawBmpFromCache(b: BMPImage) {
        this.drawBmpBasic(b.getBmpIdStr(), b.getPosition())
    }

    fun isEmptyColor(c: Color?): Boolean {
        return c == null
    }

    fun getGraphics(): Graphics? {
        return this.pen
    }

    fun setGraphics(p: Graphics) {
        this.pen = p
    }

    fun getColor(): Color? {
        return this.color
    }

    fun setColor(c: Color) {
        this.color = c
    }
}