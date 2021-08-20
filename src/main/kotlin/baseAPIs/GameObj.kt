package baseAPIs

class GameObj(var pos: Vector2, var w:Int, var h:Int, var isVisible: Boolean, var color: Color2, var hasParent: Boolean,
              var parent: GameObj?, var name:String, var uid: String) {
    constructor():this(Vector2(), 0, 0, true, Color2(), false, null, "", "")
    constructor(name: String, ID: String):this(Vector2(), 0, 0, true, Color2(), false, null, name, ID)
    constructor(x:Int, y:Int, W:Int, H: Int, isV: Boolean, c:Color2,):this(Vector2(x, y), W, H, isV, c, false, null, "", "")
    constructor(W: Int, H: Int):this(Vector2(), W, H, true, Color2(), false, null, "", "")
    constructor(x: Int, y: Int, W: Int, H: Int, isV: Boolean, c: Color2, name: String, ID: String):this(Vector2(x, y), W, H, isV, c, false, null, name, ID)
    constructor(v: Vector2, W: Int, H: Int, isV: Boolean, c: Color2):this(v, W, H, isV, c, false, null, "", "")
    constructor(v: Vector2, W: Int, H: Int, isV: Boolean, c: Color2, n: String, i: String):this(v, W, H, isV, c, false, null, n, i)
    constructor(obj: GameObj):this(obj.getPosition(), obj.getWidth(), obj.getHeight(), obj.getIsVisible(), obj.getObjColor(), obj.getHasParent(), obj.getParent(), obj.getObjName(), obj.getID())

    fun clone(): GameObj{
        return GameObj(this)
    }

    fun draw(p: Pen) {
        if (this.isVisible){

        }
    }

    fun update(updateTick: Int, currentTimeMS: Long, msSinceLastFrame: Long): Boolean{
        if (this.getIsVisible()){

        }
        return false
    }

    fun getIsVisible(): Boolean {
        return this.isVisible
    }

    fun setIsVisible(isV: Boolean){
        this.isVisible = isV
    }

    fun getWidth(): Int{
        return this.w
    }

    fun setWidth(W: Int) {
        this.w = W
    }

    fun getHeight(): Int{
        return this.h
    }

    fun setHeight(H: Int) {
        this.h = H
    }

    fun getPosition(): Vector2{
        return this.pos
    }

    fun setPosition(v: Vector2) {
        this.pos = v
    }

    fun setPosition(x: Int, y: Int) {
        this.pos = Vector2(x, y)
    }

    fun getObjColor(): Color2 {
        return this.color
    }

    fun setObjColor(c: Color2) {
        this.color = c
    }

    fun getX(): Int{
        return this.getPosition().getXInt()
    }

    fun setX(x: Int){
        this.getPosition().setX(x)
    }

    fun getY(): Int{
        return this.getPosition().getYInt()
    }

    fun setY(y: Int){
        this.getPosition().setY(y)
    }

    fun getObjName(): String{
        return this.name
    }

    fun setObjName(n: String) {
        this.name = n
    }

    fun getID(): String{
        return this.uid
    }

    fun setID(i: String) {
        this.uid = i
    }

    @JvmName("getHasParent1")
    fun getHasParent(): Boolean{
        return this.hasParent
    }

    @JvmName("setHasParent1")
    fun setHasParent(b: Boolean){
        this.hasParent = b
    }

    @JvmName("getParent1")
    fun getParent(): GameObj?{
        return this.parent
    }

    @JvmName("setParent1")
    fun setParent(o: GameObj?){
        this.parent = o
    }

    fun getRectangle(): Rectangle2{
        return Rectangle2(this.getX(), this.getY(), this.getY() + this.getHeight(), this.getX() + this.getWidth())
    }

    override fun toString(): String {
        return "GameObj: Name: ${this.getObjName()} ID: ${this.getID()} - ${this.getPosition()} HasParent: ${this.getHasParent()} Width: ${this.getWidth()} Height: ${this.getHeight()}"
    }

    override fun equals(obj: Any?): Boolean {
        return when(obj){
            is GameObj -> {
                this.getHasParent() == obj.getHasParent() &&
                this.getPosition() == obj.getPosition() &&
                this.getWidth() == obj.getWidth() &&
                this.getHeight() == obj.getHeight() &&
                this.getObjColor() == obj.getObjColor() &&
                this.getID() == obj.getID() &&
                this.getObjName() == obj.getObjName() &&
                this.getParent() == obj.getParent()
            }
            else -> false
        }
    }
}