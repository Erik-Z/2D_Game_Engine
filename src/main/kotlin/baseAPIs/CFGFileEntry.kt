package baseAPIs


class CFGFileEntry(cfgFileEntry: CFGFileEntry) : Comparator<CFGFileEntry> {
    enum class CfgEntryType {
        TYPE_DOUBLE, TYPE_STRING, NONE
    }

    var cfgType: CfgEntryType  = CfgEntryType.NONE
    var number: Double? = null
    var str: String = ""
    var name: String = ""

    fun constructor(){}

    fun constructor(obj: CFGFileEntry) {
        number = obj.number
        str = obj.str
        name = obj.name
        cfgType = obj.cfgType
    }

    fun clone(): CFGFileEntry{
        return CFGFileEntry(this)
    }

    override fun compare(o1: CFGFileEntry, o2: CFGFileEntry): Int {
        return o1.name.compareTo(o2.name, true)
    }

    override fun toString(): String {
        var ret = ""
        if (name != "") {
            if (cfgType === CfgEntryType.TYPE_DOUBLE) {
                ret = name.trim { it <= ' ' } + "=" + number.toString()
            } else if (cfgType === CfgEntryType.TYPE_STRING) {
                ret = name.trim { it <= ' ' } + "->" + str
            }
        }
        return ret
    }

    override fun equals(obj: Any?): Boolean {
        return when(obj){
            is CFGFileEntry -> {
                this.name == obj.name &&
                this.number == obj.number &&
                this.str == obj.str
            }
            else -> false
        }
    }
}