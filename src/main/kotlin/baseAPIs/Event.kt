package baseAPIs

class Event {
    private var parentHandler: EventHandler
    private var message: String
    private var id: Int
    private var type: Int
    private var targetHandler: EventHandler?
    private var extra: Any?
    private var prevEvent: Event?

    val EVENT_ID_UP = 0
    val EVENT_ID_DOWN = 1
    val EVENT_ID_LEFT = 2
    val EVENT_ID_RIGHT = 3
    val EVENT_ID_ENTER = 4
    val EVENT_ID_SPACE = 5
    val EVENT_ID_BACK = 6
    val EVENT_ID_ESC = 7

    constructor(parentHandler: EventHandler, msg: String, ID: Int, type: Int, targetHandler: EventHandler, ex: Any?) {
        this.parentHandler = parentHandler;
        this.message = msg;
        this.id = ID;
        this.type = type;
        this.targetHandler = targetHandler;
        this.extra = ex;
        this.prevEvent = null;
    }

    fun getPrevEvent(): Event? {
        return this.prevEvent
    }

    fun setPrevEvent(p: Event) {
        this.prevEvent = p
    }

    fun getParentEventHandler(): EventHandler {
        return parentHandler
    }

    fun setParentEventHandler(e: EventHandler) {
        this.parentHandler = e
    }

    fun getTargetEventHandler(): EventHandler? {
        return this.targetHandler
    }

    fun setTargetEventHandler(e: EventHandler) {
        this.targetHandler = e
    }

    fun getEventId(): Int {
        return this.id
    }

    fun SetEventId(s: Int) {
        this.id = s
    }

    fun getMessage(): String {
        return this.message
    }

    fun setMessage(s: String) {
        this.message = s
    }

    fun getEventType(): Int {
        return this.type
    }

    fun setEventType(type: Int) {
        this.type = type
    }

    fun getExtra(): Any? {
        return this.extra
    }

    fun setExtra(obj: Any) {
        this.extra = obj
    }

    fun fire() {
        if (this.targetHandler != null) {
            this.targetHandler!!.handleEvent(this)
        } else {
            APIUtils.wr("cannot fire event because event handler is null")
        }
    }

    override fun toString(): String {
        return "Event: Msg: ${this.message} Id: ${this.id} Type: ${this.type}"
    }
}