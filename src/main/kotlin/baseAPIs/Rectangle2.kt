package baseAPIs

import java.awt.Rectangle

class Rectangle2(var rect: Rectangle) {
    constructor():this(Rectangle(0, 0, 1, 1))
    constructor(left: Int, top: Int, bottom: Int, right: Int):this(Rectangle(left, top, (right - left), (bottom - top)))
    constructor(v: Vector2, width: Int, height: Int):this(v.getXInt(), v.getYInt(), width, height)
    constructor(rect: Rectangle2):this(rect.getLeft(), rect.getTop(), rect.getWidth(), rect.getHeight())

    fun shiftRectangle(shiftLR: Int, shiftUD: Int) {
        rect = Rectangle(this.rect.x + shiftLR, this.rect.y + shiftUD, this.rect.width, this.rect.height)
    }

    fun getShiftedRectangle(shiftLR: Int, shiftUD: Int): Rectangle2{
        return Rectangle2(
            this.rect.x + shiftLR,
            this.rect.y + shiftUD,
            this.rect.x + shiftLR + this.rect.width,
            this.rect.y + shiftUD + this.rect.height
        )
    }

    fun clone(): Rectangle2{
        return Rectangle2(this)
    }

    fun getLeft(): Int {
        return rect.x
    }

    fun getTop(): Int {
        return rect.y
    }

    fun getRight(): Int {
        return (rect.x + rect.width)
    }

    fun getBottom(): Int {
        return (rect.y + rect.height)
    }

    fun getWidth(): Int {
        return rect.width
    }

    fun setWidth(w: Int) {
        rect.setSize(w, rect.height)
    }

    fun getHeight(): Int {
        return rect.height
    }

    fun setHeight(h: Int) {
        rect.setSize(rect.width, h);
    }

    fun getRectangle(): Rectangle {
        return rect
    }

    fun setRectangle(r: Rectangle) {
        this.rect = r
    }

    fun getDifferenceX(rect: Rectangle2, dir: Int, opp: Boolean, l2r: Boolean): Int {
        if (DirConsts.DIR_LEFT == dir && !opp && l2r) {
            return (this.getLeft() - rect.getRight())
        } else if (DirConsts.DIR_LEFT == dir && !opp && !l2r){
            return (this.getLeft() - rect.getLeft())
        } else if (DirConsts.DIR_LEFT == dir && opp && l2r) {
            return (rect.getLeft() - this.getRight())
        } else if (DirConsts.DIR_LEFT == dir && opp && !l2r) {
            return (rect.getLeft() - this.getLeft())
        } else if (DirConsts.DIR_RIGHT == dir && !opp && l2r) {
            return (this.getRight() - rect.getLeft())
        } else if (DirConsts.DIR_RIGHT == dir && !opp && !l2r){
            return (this.getRight() - rect.getRight());
        } else if (DirConsts.DIR_RIGHT == dir && opp && l2r) {
            return (rect.getRight() - this.getLeft())
        } else if (DirConsts.DIR_RIGHT == dir && opp && !l2r) {
            return (rect.getRight() - this.getRight())
        }
        return 0
    }

    fun getDifferenceX(x: Int, dir: Int, opp: Boolean): Int{
        if (DirConsts.DIR_LEFT == dir && !opp) {
            return (this.getLeft() - x)
        } else if (DirConsts.DIR_LEFT == dir && opp) {
            return (x - this.getLeft())
        } else if (DirConsts.DIR_RIGHT == dir && !opp){
            return (this.getRight() - x)
        } else if (DirConsts.DIR_RIGHT == dir && opp) {
            return (x - this.getRight())
        }
        return 0
    }

    fun getDifferenceY(rect: Rectangle2, dir: Int, opp: Boolean, l2r: Boolean): Int{
        if (DirConsts.DIR_TOP == dir && !opp && l2r){
            return (this.getTop() - rect.getBottom())
        } else if (DirConsts.DIR_TOP == dir && !opp && !l2r) {
            return (this.getTop() - rect.getTop())
        } else if (DirConsts.DIR_TOP == dir && opp && l2r) {
            return (rect.getTop() - this.getBottom())
        } else if (DirConsts.DIR_TOP == dir && opp && !l2r) {
            return (rect.getTop() - this.getTop())
        } else if (DirConsts.DIR_BOTTOM == dir && !opp && l2r) {
            return (this.getBottom() - rect.getTop())
        } else if (DirConsts.DIR_BOTTOM == dir && !opp && !l2r) {
            return (this.getBottom() - rect.getBottom())
        } else if (DirConsts.DIR_BOTTOM == dir && opp && l2r) {
            return (rect.getBottom() - this.getTop())
        } else if (DirConsts.DIR_BOTTOM == dir && opp && !l2r) {
            return (rect.getBottom() - this.getBottom())
        }
        return 0
    }

    fun getDifferenceY(y: Int, dir: Int, opp: Boolean): Int{
        if (DirConsts.DIR_TOP == dir && !opp) {
            return (this.getTop() - y)
        } else if (DirConsts.DIR_TOP == dir && opp) {
            return (y - this.getTop())
        } else if (DirConsts.DIR_BOTTOM == dir && !opp) {
            return (this.getBottom() - y)
        } else if (DirConsts.DIR_BOTTOM == dir && opp) {
            return (y - this.getBottom())
        }
        return 0
    }

    fun getPosition(): Vector2 {
        return Vector2(this.getLeft(), this.getTop())
    }

    fun setPosition(v: Vector2){
        this.rect.setLocation(v.getXInt(), v.getYInt())
    }

    fun setPosition(x: Int, y: Int){
        this.rect.setLocation(x, y)
    }

    override fun toString(): String {
        return "Rect2: L:${this.getLeft()} R:${this.getRight()} T:${this.getTop()} B:${this.getBottom()} " +
                "W:${this.getWidth()} H:${this.getHeight()}"
    }

    override fun equals(obj: Any?): Boolean {
        return when(obj){
            is Rectangle2 -> {
                this.getLeft() == obj.getLeft() && this.getRight() == obj.getRight() &&
                this.getTop() == obj.getTop() && this.getBottom() == obj.getBottom()
            }
            else -> false
        }
    }
}
