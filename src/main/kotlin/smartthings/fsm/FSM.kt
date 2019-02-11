package smartthings.fsm

open class FSM() {
    lateinit var states: Map<String, State>
    var currentState: State? = null

    fun initialize(startingState: String, states: Map<String, State>) {
        this.states = states
        setState(startingState)
    }

    fun setState(key: String) {
        if (!this.states.containsKey(key)) {
            throw Exception("Invalid state $key")
        }

        if (this.currentState != null) {
            this.currentState!!.exit()
        }
        val newState: State? = this.states[key]
        newState!!.enter()
        this.currentState = newState
    }

    fun getState(): State? {
        return this.currentState
    }

    open fun serialize() {
        // TODO: Serialize to json
    }

    open fun deserialize() {
        // TODO: Deserialize from json
    }
}


open class State(val key: String, val context: FSM) {
    open fun enter() {
        println("Enter state: $key")
    }

    open fun exit() {
        println("Exit state: $key")
    }
}
