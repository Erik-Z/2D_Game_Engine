package baseAPIs

class Vector2(private var vect: DoubleArray) {
    constructor():this(doubleArrayOf(0.0, 0.0))
    constructor(x: Double, y: Double):this(doubleArrayOf(x, y))
    constructor(x: Int, y: Int):this(doubleArrayOf(x.toDouble(), y.toDouble()))
    constructor(x: Float, y: Float):this(doubleArrayOf(x.toDouble(), y.toDouble()))
    constructor(x: Double):this(doubleArrayOf(x, x))
    constructor(x: Float):this(doubleArrayOf(x.toDouble(), x.toDouble()))
    constructor(x: Int):this(doubleArrayOf(x.toDouble(), x.toDouble()))
    constructor(v: Vector2):this(doubleArrayOf(v.getX(), v.getY()))

    /**
     * Gets the vector as a DoubleArray.
     * @return the vector as a DoubleArray.
     */
    fun getVector(): DoubleArray {
        return this.vect
    }

    /**
     * Gets the X value of the vector as a Double.
     * @return the X value of the vector as a Double.
     */
    fun getX(): Double {
        return this.vect[0]
    }

    /**
     * Gets the Y value of the vector as a Double.
     * @return the Y value of the vector as a Double
     */
    fun getY(): Double {
        return this.vect[1]
    }

    /**
     * Gets the X value of the vector as an Integer.
     * @return the X value of the vector as an Integer
     */
    fun getXInt(): Int {
        return this.vect[0].toInt()
    }

    /**
     * Gets the Y value of the vector as an Integer.
     * @return the Y value of the vector as an Integer
     */
    fun getYInt(): Int {
        return this.vect[1].toInt()
    }

    /**
     * Gets the X value of the vector as a Floating Point Value.
     * @return the X value of the vector as a Floating Point Value
     */
    fun getXFloat(): Float {
        return this.vect[0].toFloat()
    }

    /**
     * Gets the Y value of the vector as a Floating Point Value.
     * @return the Y value of the vector as a Floating Point Value
     */
    fun getYFloat(): Float {
        return this.vect[1].toFloat()
    }

    /**
     * Sets the X value of the vector.
     * @param x The X value of the vector.
     */
    fun setX(x: Double){
        this.vect[0] = x
    }

    /**
     * Sets the Y value of the vector.
     * @param y The Y value of the vector.
     */
    fun setY(y: Double){
        this.vect[1] = y
    }

    /**
     * Sets the X value of the vector.
     * @param x The X value of the vector.
     */
    fun setX(x: Float) {
        this.vect[0] = x.toDouble()
    }

    /**
     * Sets the Y value of the vector.
     * @param y The Y value of the vector.
     */
    fun setY(y: Float) {
        this.vect[1] = y.toDouble()
    }

    /**
     * Sets the X value of the vector.
     * @param x The X value of the vector.
     */
    fun setX(x: Int){
        this.vect[0] = x.toDouble()
    }

    /**
     * Sets the Y value of the vector.
     * @param y The Y value of the vector.
     */
    fun setY(y: Int){
        this.vect[0] = y.toDouble()
    }

    /**
     * Sets the value of the vector.
     * @param v The value of the vector.
     */
    fun setVector(v: DoubleArray){
        this.vect = v
    }

    /**
     * Returns a new instance of the current vector.
     */
    fun clone(): Vector2 {
        return Vector2(this.vect[0], this.vect[1])
    }

    override operator fun equals(obj: Any?): Boolean {
        return when(obj){
            is Vector2 -> {
                this.vect[0] == obj.getX() && this.vect[1] == obj.getY()
            }
            else -> false
        }
    }

    override fun toString(): String{
        return "{ X:${this.vect[0]}, Y:${this.vect[1]} }"
    }
}