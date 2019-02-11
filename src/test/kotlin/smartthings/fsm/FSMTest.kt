package smartthings.fsm

import org.junit.Assert.assertEquals
import org.junit.Test

const val STATE_OFF = "STATE_OFF"
const val STATE_ON = "STATE_ON"

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

class FSMTest {

    @Test fun testFSM() {
        val switch = Switch()
        switch.initialize(STATE_OFF, hashMapOf(
                STATE_OFF to StateOff(STATE_OFF, switch),
                STATE_ON to StateOn(STATE_ON, switch)
        ))

        assertEquals(switch.getState()?.key, STATE_OFF)
        switch.turnOn()
        assertEquals(switch.getState()?.key, STATE_ON)
        switch.turnOff()
        assertEquals(switch.getState()?.key, STATE_OFF)
    }
}