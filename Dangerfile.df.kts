@file:DependsOn("com.gianluz:danger-kotlin-android-lint-plugin:0.1.0")

import com.gianluz.dangerkotlin.androidlint.*
import systems.danger.kotlin.*

register plugin AndroidLint

danger(args) {

    val argsString = buildString {
        args.forEach { append(it).append(" ") }
    }

    warn("Bitrise test : args [$argsString]")

    if (git.createdFiles.size + git.modifiedFiles.size - git.deletedFiles.size > 10) {
        warn("Big PR, try to keep changes smaller if you can")
    }

    if (github.pullRequest.title.contains("WIP", false)) {
        warn("PR is classed as Work in Progress")
    }

    androidLint {
        report("./app/build/reports/lint-results-debug.xml")
    }
}
