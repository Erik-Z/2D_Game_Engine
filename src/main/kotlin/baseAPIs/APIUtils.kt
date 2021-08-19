package baseAPIs

import java.lang.Exception

object APIUtils {
    var LOGGING: Boolean = true

    fun wr(s: String){
        if (this.LOGGING){
            println(s)
        }
    }

    fun wrError(e: Exception){
        if (this.LOGGING){
            var elements: Array<StackTraceElement> = e.stackTrace
            for (element in elements){
                System.err.println(element)
            }
        }
    }

    fun wrError(s: String){
        if (this.LOGGING){
            System.err.println(s)
        }
    }
}