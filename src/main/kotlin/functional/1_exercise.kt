package functional

/**
 * Goal:
 * Simulate a basic immutable state update pattern often seen in functional programming and state management libraries like Redux or Compose's `State`. You will model a voting system where votes can be cast for different candidates, but the state object itself is never modified; instead, new state objects are created.
 *
 * Tasks:
 *  1.  Define Data Structures:
 *  - Create a `data class` named `Candidate` with properties: `id: String`, `name: String`.
 *  - Create a `data class` named `VotingState` with a property: `votes: Map<Candidate, Int>`.
 *    The map should store candidates as keys and their corresponding vote counts (`Int`) as values.
 *    Ensure the initial state can be represented (e.g., an empty map).
 *  2.  Implement State Transformation Logic:
 *  - Create a top-level function or a function within an `object` named `VotingSystem` called `castVote`.
 *  - This function should accept the `currentState: VotingState` and the `candidateId: String` for which a vote is being cast.
 *  - It must return a new `VotingState` object reflecting the updated vote count. Do *not* modify the input `currentState`.
 *  - The function should increment the vote count for the given `candidateId`.
 *    If the candidate is not already in the map, they should be added with a vote count of 1.
 *  3. Create a Higher-Order Function for Actions:
 *  - Define a higher-order function named `applyAction`.
 *  - It should take the `currentState: VotingState` and an `action: (VotingState) -> VotingState` lambda as parameters.
 *  - It should return the result of applying the `action` lambda to the `currentState`.

4.  Demonstrate Usage:
 *   Create an initial `VotingState` (e.g., `val initialState = VotingState(emptyMap())`).
 *   Define a list of candidates.
 *   Simulate casting several votes using the `applyAction` function and lambda expressions that internally call your `castVote` logic (or directly use `castVote` if you prefer, but using `applyAction` demonstrates the pattern better).
 *   Print the final `VotingState` to show the accumulated votes. Ensure intermediate states remain unchanged if you were to hold references to them.
 **/

data class Candidate(val id: String, val name: String)
data class VotingState(val votes: Map<Candidate, Int>)

object VotingSystem {
    fun castVote(currentState: VotingState, candidateId: String): VotingState {
        val candidate = currentState.votes.keys.find { it.id == candidateId }
        val updatedVotes = currentState.votes.toMutableMap()
        if (candidate != null) {
            updatedVotes[candidate] = updatedVotes.getValue(candidate) + 1
        } else {
            val newCandidate = Candidate(candidateId, "New Candidate")
            updatedVotes[newCandidate] = 1
        }
        return VotingState(updatedVotes)
    }
}

fun applyAction(currentState: VotingState, action: (VotingState) -> VotingState): VotingState = action(currentState)

fun main() {
    val initialState = VotingState(emptyMap())
    val candidates = listOf("A", "B", "C", "A", "B", "A", "C", "B", "A", "B")
    val finalState = candidates.fold(initialState) { state, candidateId ->
        applyAction(state) { VotingSystem.castVote(it, candidateId) }
    }
    println(finalState)
}