package org.example.base

/**
 * Goal:
 * Practice functions, `when` expressions, string manipulation, and handling different data types within a list (`List<Any>`).
 *
 * Create a Kotlin function `transformText` that accepts a `List<Any>` containing a mix of `String` and `Int` values.
 * The function should process each element based on its type and a transformation command embedded within strings:
 * 1.  If the element is an `Int`, convert it to its string representation.
 * 2.  If the element is a `String`:
 *     *   If it starts with `"UPPER:"`, return the rest of the string in uppercase (e.g., `"UPPER:hello"` becomes `"HELLO"`).
 *     *   If it starts with `"LOWER:"`, return the rest of the string in lowercase (e.g., `"LOWER:WORLD"` becomes `"world"`).
 *     *   If it starts with `"REVERSE:"`, return the rest of the string reversed (e.g., `"REVERSE:kotlin"` becomes `"niltok"`).
 *     *   Otherwise, return the string as is.
 * 3.  Ignore elements of any other type.
 *
 * The function should return a `List<String>` containing the transformed results for all valid `Int` and `String` elements,
 * in their original order.
 *
 * **Requirements:**
 * -   Use a `when` expression to check the type of each element (`is Int`, `is String`).
 * -   Inside the `String` check, use another `when` or `if/else if/else` chain to handle the transformation commands.
 * -   Utilize standard library functions for string manipulation (e.g., `uppercase()`, `lowercase()`, `reversed()`, `startsWith()`, `substringAfter()`).
 * -   Ensure elements that are neither `Int` nor `String` are skipped correctly.
 *
 * **Example:**
 * Input: `listOf("UPPER:example", 123, "LOWER:TEST", "REVERSE:abc", true, "そのまま", 45.6)`
 * Output: `["EXAMPLE", "123", "test", "cba", "そのまま"]`
 *
 * **Hints:**
 * -   You can iterate through the list using a `for` loop.
 * -   Smart casting within the `when` branches will allow you to treat elements as their specific type without explicit casting after the `is` check.
 * -   Be careful with edge cases like empty strings after the command prefix (e.g., `"UPPER:"`).
 * -   (Optional) consider to use `filterIsInstance` to filter the list to only `String` and `Int` elements.
 * -   (Optional) consider using `mapNotNull` to transform and filter elements in a single step.
 */

fun transformText(list: List<Any>): List<String> = list.mapNotNull {
    when(it) {
            is Int -> it.toString()
            is String -> {
                when {
                    it.startsWith("UPPER:") -> it.substringAfter(":").uppercase()
                    it.startsWith("LOWER:") -> it.substringAfter(":").lowercase()
                    it.startsWith("REVERSE:") -> it.substringAfter(":").reversed()
                    else -> it
                }
            }
            else -> null
        }
    }

fun main() {
    val input = listOf("UPPER:example", 123, "LOWER:TEST", "REVERSE:abc", true, "そのまま", 45.6)
    val output = transformText(input)
    val test = listOf("EXAMPLE", "123", "test", "cba", "そのまま")
    assert(output == test)
}