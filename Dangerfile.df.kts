@file:DependsOn("com.gianluz:danger-kotlin-android-lint-plugin:0.0.3")

import com.gianluz.dangerkotlin.androidlint.AndroidLint
import systems.danger.kotlin.danger
import systems.danger.kotlin.register
import systems.danger.kotlin.warn

register plugin AndroidLint

danger(args) {

    warn("Bitrise test : args $args")

    val allSourceFiles = git.modifiedFiles + git.createdFiles

    val sourceChanges = allSourceFiles.firstOrNull { it.contains("src") }
    val isTrivial = github.pullRequest.title.contains("#trivial")

    if (git.createdFiles.size + git.modifiedFiles.size - git.deletedFiles.size > 10) {
        warn("Big PR, try to keep changes smaller if you can")
    }

    if (github.pullRequest.title.contains("WIP", false)) {
        warn("PR is classed as Work in Progress")
    }

    AndroidLint.scan("app").forEach {
        AndroidLint.report(it)
    }
}
