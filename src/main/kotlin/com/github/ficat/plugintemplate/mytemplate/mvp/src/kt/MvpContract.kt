fun mvpContractKt(
    mvpName: String,
    contractPackage: String,
) = """

package $contractPackage


interface ${mvpName}Contract {
    interface View : IView{

    }

    interface Model : IModel{

    }
}
"""