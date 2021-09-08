fun mvpModelJava(
    mvpName: String,
    modelPackage: String,
    contractPackage: String
) = """

package ${modelPackage};

import ${contractPackage}.${mvpName}Contract;

public class ${mvpName}Model extends BaseMvpModel implements ${mvpName}Contract.Model {
    
	
}
"""
