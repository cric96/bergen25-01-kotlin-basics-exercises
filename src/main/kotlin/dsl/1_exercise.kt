package dsl

/**
 * Goal:
 * Create a type-safe Kotlin DSL to define the structure and basic properties of a simple UI layout. This exercise simulates
 * the declarative nature of Compose UI but focuses purely on building the *data structure* representing the UI, not rendering it.
 *
 **
 * Tasks:
 * 1. Look at the given data classes and enum:
 *    - `UiElement a data class representing the basic elements of the UI.
 *    - `UiScreen(id: String, title: String?, children: List<UiElement>)`
 *    - `UiContainer(id: String, orientation: Orientation, children: List<UiElement>)` implementing `UiElement`
 *    - `UiText(id: String, text: String)` implementing `UiElement`
 *    - `UiButton(id: String, text: String, onClickActionId: String)` implementing `UiElement`
 *    - Define an enum `Orientation { VERTICAL, HORIZONTAL }`.
 *
 * 2. Create Builder Classes:
 *    - Design builder classes (e.g., `ScreenBuilder`, `ContainerBuilder`) that will manage the context and accumulate child elements
 *      during the DSL execution. These builders should internally use mutable lists to collect children.
 *    - Each builder should have a `build()` method that returns the corresponding immutable data structure (`UiScreen`, `UiContainer`).
 *
 * 3. Implement DSL Functions using Extension Functions & Lambdas with Receivers:
 *    - Create a top-level function `defineScreen(id: String, block: ScreenBuilder.() -> Unit): UiScreen`. This function initiates the DSL.
 *    - Inside `ScreenBuilder`, create an extension function `title(value: String)` to set the screen's title.
 *    - Create extension functions on `ScreenBuilder` and `ContainerBuilder` to add child elements:
 *      - `container(id: String, orientation: Orientation = Orientation.VERTICAL, block: ContainerBuilder.() -> Unit)`: Adds a `UiContainer`.
 *        Note the default orientation.
 *      - `text(id: String, content: String)`: Adds a `UiText`.
 *      - `button(id: String, text: String, onClickActionId: String)`: Adds a `UiButton`.
 *    - These functions should:
 *      - Create the appropriate child element (or its builder).
 *      - If the child is a container, execute the nested `block` lambda within the context of the child's builder (`ContainerBuilder`).
 *      - Add the resulting (built) child element to the parent builder's internal list of children.
 *
 * 4. Demonstrate Usage:
 *    - Create a UI screen with the DSL that contains nested containers, text elements, and buttons.
 *    - Print the resulting structure to verify that it matches the definition.
 *    - Verify type safety by ensuring that the DSL prevents adding invalid elements.
 *
 * Expected Usage:
 * ```kotlin
 * val myScreen: UiScreen = defineScreen("mainScreen") {
 *     title("User Profile")
 *
 *     container("userInfo", orientation = Orientation.VERTICAL) {
 *         text("nameLabel", "Name: John Doe")
 *         text("emailLabel", "Email: john.doe@example.com")
 *
 *         container("address", orientation = Orientation.HORIZONTAL) {
 *             text("addressLabel", "City: Metropolis")
 *             // Maybe add more address components later
 *         }
 *     }
 *
 *     container("actions", orientation = Orientation.HORIZONTAL) {
 *         button("saveButton", text = "Save", onClickActionId = "handleSaveProfile")
 *         button("cancelButton", text = "Cancel", onClickActionId = "handleCancel")
 *     }
 * }
 *
 * // For verification, you could print the structure:
 * myScreen.render()
 * ```
 *
 * Requirements:
 * - Type Safety: The DSL should prevent adding invalid elements.
 * - Nesting: Containers must be able to contain other containers and primitive elements.
 * - Immutability: The final `UiScreen` and its nested structure should be immutable.
 * - Readability: The DSL usage should be clear and resemble declarative UI definitions.
 * - Extension Functions: Leverage extension functions and lambdas with receivers effectively.
 **/

interface UiElement {
    val id: String
}

data class UiScreen(
    override val id: String,
    val title: String?,
    val children: List<UiElement>
) : UiElement

data class UiContainer(
    override val id: String,
    val orientation: Orientation,
    val children: List<UiElement>
) : UiElement

data class UiText(
    override val id: String,
    val text: String
) : UiElement

data class UiButton(
    override val id: String,
    val text: String,
    val onClickActionId: String
) : UiElement

enum class Orientation { VERTICAL, HORIZONTAL }

fun UiScreen.render() {
    //println("Rendering screen: $this")
    this.title?.let { println("Title: $it") }
    this.children.forEach { renderElement(it, 1) }
}

fun UiElement.renderElement(element: UiElement, level: Int) {
    val indent = "  ".repeat(level)
    when (element) {
        is UiContainer -> {
            println("$indent- Container: ${element.id} (${element.orientation})")
            element.children.forEach { renderElement(it, level + 1) }
        }
        is UiText -> println("$indent- Text: ${element.id} - ${element.text}")
        is UiButton -> println("$indent- Button: ${element.id} - ${element.text} (Action: ${element.onClickActionId})")
    }
}

class ScreenBuilder {
    // TODO!
}

class ContainerBuilder(val id: String, val orientation: Orientation) {
    // TODO!
}

// ScreenBuilder.() -> Unit means a lambda with receiver of type ScreenBuilder
// Lambda with receiver allows us to access the members of the receiver (ScreenBuilder) directly
// e.g., title("User Profile") instead of title(screenBuilder, "User Profile")
// it is a common pattern in Kotlin DSLs to use lambdas with receivers for building structures
fun defineScreen(id: String, block: ScreenBuilder.() -> Unit): UiScreen = TODO()

fun main() {
    // Create an example of it
    val myScreen: UiScreen = TODO()
    /**
    defineScreen("mainScreen") {
        title("User Profile")

        container("userInfo", orientation = Orientation.VERTICAL) {
            text("nameLabel", "Name: John Doe")
            text("emailLabel", "Email: john.doe@example.com")

            container("address", orientation = Orientation.HORIZONTAL) {
                text("addressLabel", "City: Metropolis")
            }
        }

        container("actions", orientation = Orientation.HORIZONTAL) {
            button("saveButton", text = "Save", onClickActionId = "handleSaveProfile")
            button("cancelButton", text = "Cancel", onClickActionId = "handleCancel")
        }
    }
    */
    // Print the structure for verification
    myScreen.render()
}