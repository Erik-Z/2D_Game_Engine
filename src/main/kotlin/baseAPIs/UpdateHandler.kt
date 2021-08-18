package baseAPIs

interface UpdateHandler {
    /**
     * Handle the incoming update event.
     * @param obj   The update event object.
     */
    fun handleUpdate(obj: Any?);
}