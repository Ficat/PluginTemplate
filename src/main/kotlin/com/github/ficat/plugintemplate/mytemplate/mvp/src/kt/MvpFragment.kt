fun mvpFragmentKt(
    applicationPackage: String?,
    mvpName: String,
    layoutName: String,
    fragmentPackage: String,
    presenterPackage: String,
    contractPackage: String
) = """

package $fragmentPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ${applicationPackage}.R
import ${contractPackage}.${mvpName}Contract
import ${presenterPackage}.${mvpName}Presenter

class ${mvpName}Fragment: BaseMvpFragment<${mvpName}Presenter>(),${mvpName}Contract.View {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.${layoutName},container,false)
    }

    override fun createPresenter(): ${mvpName}Presenter {
        return ${mvpName}Presenter(this)
    }
}
"""