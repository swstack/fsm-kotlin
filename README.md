# Finite State Machine pattern for the JVM

### Example

```kotlin
class Switch() : FSM() {

    fun turnOn() {
        val state = this.currentState as SwitchState
        state.turnOn()
    }

    fun turnOff() {
        val state = this.currentState as SwitchState
        state.turnOff()
    }
}

open class SwitchState(key: String, context: FSM) : State(key, context) {
    open fun turnOn() {
        println("Unhandled event: turnOn")
    }

    open fun turnOff() {
        println("Unhandled event: turnOff")
    }
}

class StateOff(key: String, context: FSM) : SwitchState(key, context) {
    override fun enter() {
        println("State off")
    }

    override fun exit() {}

    override fun turnOn() {
        this.context.setState(STATE_ON)
    }
}

class StateOn(key: String, context: FSM) : SwitchState(key, context) {
    override fun enter() {
        println("State on")
    }

    override fun exit() {}

    override fun turnOff() {
        this.context.setState(STATE_OFF)
    }
 }
```

### Run tests:

```
./gradlew test
```

