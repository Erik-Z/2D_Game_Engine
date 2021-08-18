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

    fun getVector(): DoubleArray {
        return this.vect
    }

    /**
     * Gets the X value of the vector.
     * @return the X value of the vector
     */
    fun getX(): Double {
        return this.vect[0]
    }

    /**
     * Gets the Y value of the vector.
     * @return the Y value of the vector
     */
    fun getY(): Double {
        return this.vect[1]
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

    fun setX(x: Float) {
        this.vect[0] = x.toDouble()
    }

    fun setY(y: Float) {
        this.vect[1] = y.toDouble()
    }

    fun setX(x: Int){
        this.vect[0] = x.toDouble()
    }

    fun setY(y: Int){
        this.vect[0] = y.toDouble()
    }

    fun setVector(v: DoubleArray){
        this.vect = v
    }

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