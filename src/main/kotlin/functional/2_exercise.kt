package functional

/**
 * Goal:
 * Practice creating extension functions for standard Kotlin collections (`List`) to perform
 * domain-specific data analysis tasks, using lambdas for customizable criteria.
 *
 * Tasks:
 * 1. Define Data Structure:
 *   - Create a `data class` named `StudentRecord` with properties: `studentId: String`,
 *     `course: String`, `grade: Double` (e.g., 0.0 to 100.0).
 *
 * 2. Implement Extension Functions on `List<StudentRecord>`:
 *   - Create an extension function `filterByGradeAbove` that takes a `threshold: Double` and returns
 *     a new `List<StudentRecord>` containing only records with grades above the threshold.
 *   - Create an extension function `averageGradeForCourse` that takes a `courseName: String` and returns
 *     the average grade for that specific course. Return 0.0 or Double.NaN if no matching records exist.
 *   - Create an extension function `groupByPassingStatus` that takes a lambda predicate `(Double) -> Boolean`
 *     and returns a `Map<Boolean, List<StudentRecord>>` where true maps to passing records and false to failing ones.
 *
 * 3. Create some sample data and demonstrate the usage of these extension functions.
 **/


fun main() {

}