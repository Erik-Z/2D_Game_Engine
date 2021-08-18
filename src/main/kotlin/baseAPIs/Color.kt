package baseAPIs

import java.awt.Color

class Color2(private var c: Color) {
    constructor():this(Color.WHITE)
    constructor(c: Color2):this(c.getColor())

    fun clone(): Color2{
        return Color2(this)
    }

    fun getColor(): Color {
        return this.c
    }

    fun setColor(C: Color){
        this.c = C
    }

    override fun toString(): String {
        return "R:${this.c.red}, G:${this.c.green}, B:${this.c.blue}, A:${this.c.alpha}"
    }
}