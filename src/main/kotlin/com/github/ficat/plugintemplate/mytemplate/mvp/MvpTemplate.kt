package com.github.ficat.plugintemplate.mytemplate.mvp

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val MvpTemplate
    get() = template {
        revision = 10//当版本为AndroidStudio ArcticFox|2020.3.1及其以上时，要注释掉该参数revision才可创建jar包（提示Unresolved reference：revision）
        name = "一键MVP"
        description = "一键生成MVP相关类"
        minApi = MIN_API//当版本为AndroidStudio ArcticFox|2020.3.1及其以上时，要注释掉该参数revision才可创建jar包（提示Unresolved reference：minApi）
        minBuildApi = MIN_API//当版本为AndroidStudio ArcticFox|2020.3.1及其以上时，要注释掉该参数revision才可创建jar包（提示Unresolved reference：minBuildApi）

        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val mvpName = stringParameter {
            name = "MVP Name"
            default = "MvpDemo"
            help = "输入MVP名（不包含Activity/Fragment）"
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

        //generate fragment，if true generate fragment or generate activity (default activity)
        val generateFragment = booleanParameter {
            name = "Generate Fragment"
            help = "是否生成Fragment？勾选后生成fragment的mvp相关类？（默认生成Activity）"
            visible = { true }
            enabled = { true }
            default = false
        }

        //use androidx or support, if true use androidx or use support (default androidx)
        val androidxOrSupport = booleanParameter {
            name = "Use AndroidX"
            help = "是否生成Fragment？勾选后生成fragment的mvp相关类？（默认生成Activity）"
            visible = { javaOrKotlin.value }
            enabled = { true }
            default = true
        }

        val placeToSpecifiedPackage = booleanParameter {
            name = "Place generated classes to specified package"
            help = "将生成的类放到指定的mvp包中"
            visible = { true }
            enabled = { true }
            default = true
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_mvp_demo"
            help = "请输入布局名"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = {
                if (generateFragment.value) {
                    "${fragmentToLayout(mvpName.value)}"
                } else {
                    "${activityToLayout(mvpName.value)}"
                }
            }
        }

        val packageName = defaultPackageNameParameter

        widgets(
            TextFieldWidget(mvpName),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            CheckBoxWidget(javaOrKotlin),
            CheckBoxWidget(generateFragment),
            CheckBoxWidget(androidxOrSupport),
            CheckBoxWidget(placeToSpecifiedPackage)
        )

        //测试发现无效，暂时屏蔽
//        thumb { File("template_blank_activity.png") }

        recipe = { data: TemplateData ->
            mvvmActivityRecipe(
                data as ModuleTemplateData,
                mvpName.value,
                layoutName.value,
                packageName.value,
                javaOrKotlin.value,
                generateFragment.value,
                androidxOrSupport.value,
                placeToSpecifiedPackage.value
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