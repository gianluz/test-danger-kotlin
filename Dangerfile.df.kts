@file:DependsOn("com.gianluz:danger-kotlin-android-lint-plugin:0.0.3")

import com.gianluz.dangerkotlin.androidlint.AndroidLint
import systems.danger.kotlin.danger
import systems.danger.kotlin.register
import systems.danger.kotlin.warn

register plugin AndroidLint

danger(args) {

    val argsString = buildString {
        args.forEach { append(it).append(" ") }
    }
    warn("Bitrise test : args [$argsString]")

    val allSourceFiles = git.modifiedFiles + git.createdFiles

    if (git.createdFiles.size + git.modifiedFiles.size - git.deletedFiles.size > 10) {
        warn("Big PR, try to keep changes smaller if you can")
    }

    if (github.pullRequest.title.contains("WIP", false)) {
        warn("PR is classed as Work in Progress")
    }

    AndroidLint.report("./app/build/reports/lint-results-debug.xml")
}
