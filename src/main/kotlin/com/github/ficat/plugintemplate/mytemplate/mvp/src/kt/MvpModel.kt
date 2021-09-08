fun mvpModelKt(
    mvpName: String,
    modelPackage: String,
    contractPackage: String
) = """
    
package $modelPackage

import ${contractPackage}.${mvpName}Contract

class ${mvpName}Model: ${mvpName}Contract.Model {

}
"""