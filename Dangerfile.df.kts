//@file:DependsOn("com.danger:danger-kotlin-library:0.1.0")
@file:DependsOn("com.gianluz:danger-kotlin-android-lint-plugin:0.0.3")

import com.gianluz.dangerkotlin.androidlint.AndroidLint
import systems.danger.kotlin.Danger
import systems.danger.kotlin.register
import systems.danger.kotlin.warn

register plugin AndroidLint

val danger = Danger(args)

val allSourceFiles = danger.git.modifiedFiles + danger.git.createdFiles

val sourceChanges = allSourceFiles.firstOrNull { it.contains("src") }
val isTrivial = danger.github.pullRequest.title.contains("#trivial")

if (danger.git.createdFiles.size + danger.git.modifiedFiles.size - danger.git.deletedFiles.size > 10) {
    warn("Big PR, try to keep changes smaller if you can")
}

if (danger.github.pullRequest.title.contains("WIP", false)) {
    warn("PR is classed as Work in Progress")
}

AndroidLint.report("./app/build/reports/lint-results.xml")