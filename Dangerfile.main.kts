#!/usr/bin/env kscript

//@file:DependsOn("com.danger:danger-kotlin-library:0.1.0")

import com.danger.dangerkotlin.*

val danger = Danger(args)

val allSourceFiles = danger.git.modifiedFiles + danger.git.createdFiles

val sourceChanges = allSourceFiles.firstOrNull { it.contains("src")  }
val isTrivial = danger.github!!.pullRequest.title.contains("#trivial")

if (danger.git.createdFiles.size + danger.git.modifiedFiles.size - danger.git.deletedFiles.size > 10) {
    warn("Big PR, try to keep changes smaller if you can")
}

if (danger.github!!.pullRequest.title.contains("WIP" ,false)) {
    warn("PR is classed as Work in Progress")
}

warn("TEST")
