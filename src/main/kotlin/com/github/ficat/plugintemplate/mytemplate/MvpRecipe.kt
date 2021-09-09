package com.github.ficat.plugintemplate.mytemplate

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import mvpActivityJava
import mvpActivityKt
import mvpContractJava
import mvpContractKt
import mvpFragmentJava
import mvpFragmentKt
import mvpModelJava
import mvpModelKt
import mvpPresenterJava
import mvpPresenterKt
import mvpXml

fun RecipeExecutor.mvvmActivityRecipe(
    moduleData: ModuleTemplateData,
    mvpName: String,
    layoutName: String,
    packageName: String,
    isJava: Boolean,
    generateFragment: Boolean,
    useAndroidX: Boolean,
    placeToSpecifiedPackage: Boolean
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

    //mvp相关类相对父路径
    var pathContract = ""
    var pathModel = ""
    var pathPresenter = ""
    var pathActivity = ""
    var pathFragment = ""
    //mvp相关类包名
    var contractPackage = packageName
    var modelPackage = packageName
    var presenterPackage = packageName
    var activityPackage = packageName
    var fragmentPackage = packageName
    //将生成的相关类放到指定包下
    if (placeToSpecifiedPackage) {
        pathContract = "mvp/contract/"
        pathModel = "mvp/model/"
        pathPresenter = "mvp/presenter/"
        pathActivity = "mvp/view/activity/"
        pathFragment = "mvp/view/fragment/"
        contractPackage = "${packageName}.${pathContract.substring(0,pathContract.length-1).replace("/", ".")}"
        modelPackage = "${packageName}.${pathModel.substring(0,pathModel.length-1).replace("/", ".")}"
        presenterPackage = "${packageName}.${pathPresenter.substring(0,pathPresenter.length-1).replace("/", ".")}"
        activityPackage = "${packageName}.${pathActivity.substring(0,pathActivity.length-1).replace("/", ".")}"
        fragmentPackage = "${packageName}.${pathFragment.substring(0,pathFragment.length-1).replace("/", ".")}"
    }

    var mvpContract: String
    var mvpModel: String
    var mvpPresenter: String
    var mvpActivity: String
    var mvpFragment: String
    if (isJava) {
        mvpContract = mvpContractJava(mvpName, contractPackage)
        mvpModel = mvpModelJava(mvpName, modelPackage, contractPackage)
        mvpPresenter = mvpPresenterJava(mvpName, presenterPackage, modelPackage, contractPackage)
        mvpActivity = mvpActivityJava(
            projectData.applicationPackage ?: packageName,
            mvpName,
            layoutName,
            activityPackage,
            presenterPackage,
            contractPackage
        )
        mvpFragment = mvpFragmentJava(
            projectData.applicationPackage ?: packageName,
            mvpName,
            layoutName,
            fragmentPackage,
            presenterPackage,
            contractPackage,
            if (useAndroidX) "androidx.annotation.NonNull" else "android.support.annotation.NonNull",
            if (useAndroidX) "androidx.annotation.Nullable" else "android.support.annotation.Nullable"
        )
    } else {
        mvpContract = mvpContractKt(mvpName, contractPackage)
        mvpModel = mvpModelKt(mvpName, modelPackage, contractPackage)
        mvpPresenter = mvpPresenterKt(mvpName, presenterPackage, modelPackage, contractPackage)
        mvpActivity = mvpActivityKt(
            projectData.applicationPackage ?: packageName,
            mvpName,
            layoutName,
            activityPackage,
            presenterPackage,
            contractPackage
        )
        mvpFragment = mvpFragmentKt(
            projectData.applicationPackage ?: packageName,
            mvpName,
            layoutName,
            fragmentPackage,
            presenterPackage,
            contractPackage
        )
    }
    val mvpXml = mvpXml()

    // 保存
    val fileSuffix = if (isJava) "java" else "kt"
    save(mvpContract, srcOut.resolve("${pathContract}${mvpName}Contract.${fileSuffix}"))
    save(mvpModel, srcOut.resolve("${pathModel}${mvpName}Model.${fileSuffix}"))
    save(mvpPresenter, srcOut.resolve("${pathPresenter}${mvpName}Presenter.${fileSuffix}"))
    save(mvpXml, resOut.resolve("layout/${layoutName}.xml"))
    if (generateFragment) {
        save(mvpFragment, srcOut.resolve("${pathFragment}${mvpName}Fragment.${fileSuffix}"))
    } else {
        save(mvpActivity, srcOut.resolve("${pathActivity}${mvpName}Activity.${fileSuffix}"))
        generateManifest(
            moduleData = moduleData,
            activityClass = "${mvpName}Activity",
            activityTitle = mvpName,//当版本为AndroidStudio ArcticFox|2020.3.1及其以上时，因对应的wizard-template无该参数，故要注释activityTitle
            packageName = activityPackage,
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = false,
        )
    }
}