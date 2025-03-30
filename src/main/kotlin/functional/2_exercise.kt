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
 **/

data class StudentRecord(val studentId: String, val course: String, val grade: Double)

fun List<StudentRecord>.filterByGradeAbove(threshold: Double): List<StudentRecord> = filter { it.grade > threshold }
fun List<StudentRecord>.averageGradeForCourse(courseName: String): Double {
    val records = filter { it.course == courseName }
    return if (records.isNotEmpty()) records.map { it.grade }.average() else Double.NaN
}
fun List<StudentRecord>.groupByPassingStatus(predicate: (Double) -> Boolean): Map<Boolean, List<StudentRecord>> =
    groupBy { predicate(it.grade) }

fun main() {
    // Sample student records
    val records = listOf(
        StudentRecord("A123", "Math", 85.0),
        StudentRecord("B456", "Math", 75.0),
        StudentRecord("C789", "Math", 90.0),
        StudentRecord("A123", "Science", 92.0),
        StudentRecord("B456", "Science", 68.0),
        StudentRecord("C789", "Science", 88.0),
        StudentRecord("A123", "History", 78.0),
        StudentRecord("B456", "History", 82.0),
        StudentRecord("C789", "History", 95.0)
    )
    // Examples of operations
    val mathRecords = records.filterByGradeAbove(80.0)
    val mathAverage = records.averageGradeForCourse("Math")
    val passingRecords = records.groupByPassingStatus { it >= 70.0 }
    // Output results
    println("Math records above 80: $mathRecords")
    println("Average grade for Math: $mathAverage")
    println("Passing records: ${passingRecords[true]}")
}