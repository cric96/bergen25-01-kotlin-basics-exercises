package classes

/**
 * Goal:
 * Implement a generic repository pattern using interfaces, abstract classes (or open classes), and concrete implementations.
 * Focus on generics, interface properties, constructors, and visibility.
 *
 * Tasks:
 * 1.  Define a Generic Interface `Repository<T, ID>`:
 *     -   This interface should represent a generic data store.
 *     -   It should have two generic type parameters: `T` for the entity type and `ID` for the entity's identifier type.
 *     -   Define the following **abstract** functions:
 *         -   `fun findById(id: ID): T?`
 *         -   `fun findAll(): List<T>`
 *         -   `fun save(entity: T): T`
 *         -   `fun delete(id: ID): Boolean`
 *     -   Define an **abstract read-only property**:
 *         -   `val entityType: String` (representing the simple name of the class `T`)
 *
 * 2.  Create an `open` Base Class `BaseRepository<T, ID>`:
 *     -   This class should implement the `Repository<T, ID>` interface.
 *     -   It should have a `protected` primary constructor that accepts an initial collection of entities (e.g., `initialData: Map<ID, T>`).
 *     -  Implement basic storage using a `protected var` mutable map (e.g., `dataStore: MutableMap<ID, T>`).
 *          Initialize it in the `init` block or directly using the constructor parameter.
 *     -   Provide *partial* or *basic* implementations for the interface methods using the `dataStore`.
 *          For example, `findAll()` could simply return `dataStore.values.toList()`. You can leave `save` or `delete` abstract if desired,
 *          or provide basic map operations. *Mark the class as `open` so it can be extended.*
 *
 * 3.  Create a Concrete Class `UserRepository`:
 *     -   Define a simple data class `User(val id: String, var name: String, var email: String)`.
 *     -   `UserRepository` should inherit from `BaseRepository<User, String>`.
 *     -   Provide a primary constructor. It might call the `super` constructor with an empty map or some default users.
 *     -   Override any necessary methods from `BaseRepository` if you left them abstract or want to add specific logic
 *          Ensure the `save` method correctly adds or updates the user in the `dataStore`.
 *     -   Make the internal `dataStore` accessible only within the class or subclasses (ensure correct visibility from `BaseRepository`).
 *
 * 4.  Demonstrate Usage:
 *     -   Instantiate `UserRepository`.
 *     -   Add a few `User` objects using `save`.
 *     -   Retrieve a user using `findById`.
 *     -   Retrieve all users using `findAll`.
 *     -   Delete a user using `delete`.
 *     -   Print the `entityType`.
 */

interface Repository<T, ID> {
    fun findById(id: ID): T?
    fun findAll(): List<T>
    fun save(entity: T): T
    fun delete(id: ID): Boolean
    val entityType: String
}

open class BaseRepository<T, ID>(initialData: Map<ID, T>) : Repository<T, ID> {
    protected val dataStore: MutableMap<ID, T> = initialData.toMutableMap()

    override fun findById(id: ID): T? = dataStore[id]

    override fun findAll(): List<T> = dataStore.values.toList()

    override fun save(entity: T): T {
        // Simplified save operation
        dataStore[getId(entity)] = entity
        return entity
    }

    override fun delete(id: ID): Boolean = dataStore.remove(id) != null

    protected open fun getId(entity: T): ID {
        throw NotImplementedError("getId is not implemented")
    }

    override val entityType: String = this::class.simpleName ?: "Unknown"
}

data class User(val id: String, var name: String, var email: String)

class UserRepository(initialData: Map<String, User> = emptyMap()) : BaseRepository<User, String>(initialData) {
    override fun getId(entity: User): String = entity.id
}

fun main() {
    val userRepository = UserRepository()

    // Add users
    val user1 = User("1", "John Doe", "john@example.com")
    val user2 = User("2", "Jane Smith", "jane@example.com")
    val user3 = User("3", "Bob Johnson", "bob@example.com")

    userRepository.save(user1)
    userRepository.save(user2)
    userRepository.save(user3)

    // Retrieve a user by ID
    val foundUser = userRepository.findById("2")
    assert(foundUser == user2) { "Found user should be user2" }

    // Retrieve all users
    val allUsers = userRepository.findAll()
    assert(allUsers.size == 3) { "Should have 3 users" }
    assert(allUsers.containsAll(listOf(user1, user2, user3))) { "All users should be present" }

    // Delete a user
    val deleted = userRepository.delete("1")
    assert(deleted) { "Deletion should return true" }

    // Verify deletion
    val remainingUsers = userRepository.findAll()
    assert(remainingUsers.size == 2) { "Should have 2 users after deletion" }
    assert(!remainingUsers.contains(user1)) { "User1 should be deleted" }
    assert(remainingUsers.containsAll(listOf(user2, user3))) { "User2 and User3 should remain" }

    // Test entity type
    assert(userRepository.entityType == "UserRepository") { "Entity type should be UserRepository" }

    println("All tests passed!")
}
