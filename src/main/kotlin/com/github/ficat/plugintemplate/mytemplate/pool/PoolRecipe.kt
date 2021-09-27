package com.github.ficat.plugintemplate.mytemplate.pool

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import poolJava
import poolKt

fun RecipeExecutor.poolRecipe(
    moduleData: ModuleTemplateData,
    poolName: String,
    packageName: String,
    isJava: Boolean,
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

    var pool: String = if (isJava) {
        poolJava(packageName,poolName)
    } else {
        poolKt(packageName,poolName)
    }

    // 保存
    val fileSuffix = if (isJava) "java" else "kt"
    save(pool, srcOut.resolve("${poolName}.${fileSuffix}"))
}