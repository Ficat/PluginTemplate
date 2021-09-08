fun mvpContractJava(
    mvpName: String,
    contractPackage: String,
) = """

package ${contractPackage};

public interface ${mvpName}Contract {
    interface View extends IView{
		
    }
	
    interface Model extends IModel{

    }
}
"""