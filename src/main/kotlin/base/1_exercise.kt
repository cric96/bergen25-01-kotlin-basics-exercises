package base

/**
 * Goal:
 * Practice variable declaration (`val`/`var`), basic types (`Int`, `Double`), null safety (`?`, `?.`, `?:`), conditional expressions (`if`/`else`), and basic list processing.
 *
 * Write a Kotlin function `analyzeData` that takes a `List<Double?>` as input. This list represents sensor readings, where `null` indicates a failed reading.
 * The function should calculate and return a summary string containing:
 * 1.  The total number of readings received (including nulls).
 * 2.  The number of valid (non-null) readings.
 * 3.  The average of the valid readings. If there are no valid readings, report "N/A".
 * 4.  The minimum valid reading. If there are no valid readings, report "N/A".
 * 5.  The maximum valid reading. If there are no valid readings, report "N/A".
 *
 * **Requirements:**
 * -   The function signature should be: `fun analyzeData(readings: List<Double?>): String`
 * -   Handle the case where the input list is empty.
 * -   Handle the case where the list contains only `null` values.
 * -   Use null-safe operators (`?.`, `?:`) and/or checks (`if (x != null)`).
 * -   Format the output string clearly, for example:
 *     ```
 *     Total readings: 10
 *     Valid readings: 7
 *     Average: 25.5
 *     Min: 10.1
 *     Max: 45.8
 *     ```
 *     or for no valid data:
 *     ```
 *     Total readings: 5
 *     Valid readings: 0
 *     Average: N/A
 *     Min: N/A
 *     Max: N/A
 *
 * Hints!
 * -    Use a `for` loop to iterate over the readings.
 * -    (Optional) Use the `buildString` function to construct the output string.
 * -    (Optional) Use the `minOf` and `maxOf` functions to calculate the minimum and maximum readings.
 * -    (Optional) Use the `filterNotNull` function to remove `null` values from the list.
 * -    (Optional) Use count to count the number of valid readings.
 * -    (Optional) Use the `sumByDouble` function to calculate the sum of the readings.
 * -    (Optional) Use the `takeIf` function to conditionally calculate the average.
 */

fun analyzeData(readings: List<Double?>): String = TODO()
fun main() {
    // Test cases
    val readings1 = listOf(10.1, 20.2, null, 30.3, 40.4, 50.5)
        val resultReadings1 = "Total readings: 6\nValid readings: 5\nAverage: 30.3\nMin: 10.1\nMax: 50.5\n"
    assert(resultReadings1 == analyzeData(readings1))

    val readings2 = listOf(null, null, null)
    val resultReadings2 = "Total readings: 3\nValid readings: 0\nAverage: N/A\nMin: N/A\nMax: N/A\n"
    assert(resultReadings2 == analyzeData(readings2))

    val readings3 = emptyList<Double?>()
    val resultReadings3 = "Total readings: 0\nValid readings: 0\nAverage: N/A\nMin: N/A\nMax: N/A\n"
    assert(resultReadings3 == analyzeData(readings3))
}