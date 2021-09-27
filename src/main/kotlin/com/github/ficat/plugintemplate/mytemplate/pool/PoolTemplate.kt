package com.github.ficat.plugintemplate.mytemplate.pool

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val PoolTemplate
    get() = template {
        revision = 10//当版本为AndroidStudio ArcticFox|2020.3.1及其以上时，要注释掉该参数revision才可创建jar包（提示Unresolved reference：revision）
        name = "快速池化类"
        description = "一键快速生成池化类"
        minApi = MIN_API//当版本为AndroidStudio ArcticFox|2020.3.1及其以上时，要注释掉该参数revision才可创建jar包（提示Unresolved reference：minApi）
        minBuildApi = MIN_API//当版本为AndroidStudio ArcticFox|2020.3.1及其以上时，要注释掉该参数revision才可创建jar包（提示Unresolved reference：minBuildApi）

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val poolObjectName = stringParameter {
            name = "PoolObject Name"
            default = "PoolObject"
            help = "输入PoolObject名"
            constraints = listOf(Constraint.NONEMPTY)
        }

        //java or kotlin, if true it's java or kotlin (default java)
        val javaOrKotlin = booleanParameter {
            name = "Is Java? (or Kotlin)"
            help = "生成的模板语言是否为Java"
            visible = { true }
            enabled = { true }
            default = true
        }

        val packageName = defaultPackageNameParameter

        widgets(
            TextFieldWidget(poolObjectName),
            PackageNameWidget(packageName),
            CheckBoxWidget(javaOrKotlin),
        )

        recipe = { data: TemplateData ->
            poolRecipe(
                data as ModuleTemplateData,
                poolObjectName.value,
                packageName.value,
                javaOrKotlin.value
            )
        }
    }

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        default = "com.mycompany.myapp"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }
