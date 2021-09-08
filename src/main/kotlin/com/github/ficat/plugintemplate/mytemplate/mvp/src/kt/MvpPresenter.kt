fun mvpPresenterKt(
    mvpName: String,
    presenterPackage: String,
    modelPackage: String,
    contractPackage: String
) = """

package $presenterPackage

import ${contractPackage}.${mvpName}Contract
import ${modelPackage}.${mvpName}Model

class ${mvpName}Presenter(view: ${mvpName}Contract.View) : BaseMvpPresenter<${mvpName}Contract.View, ${mvpName}Contract.Model>(view) {

    override fun createModel(): ${mvpName}Contract.Model {
        return ${mvpName}Model()
    }

}
"""