fun mvpActivityKt(
    applicationPackage: String?,
    mvpName: String,
    layoutName: String,
    activityPackage: String,
    presenterPackage: String,
    contractPackage: String
) = """

package $activityPackage

import android.os.Bundle
import ${applicationPackage}.R
import ${contractPackage}.${mvpName}Contract
import ${presenterPackage}.${mvpName}Presenter

class ${mvpName}Activity: BaseMvpActivity<${mvpName}Presenter>(),${mvpName}Contract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${layoutName})
    }

    override fun createPresenter(): ${mvpName}Presenter {
        return ${mvpName}Presenter(this)
    }

}
"""