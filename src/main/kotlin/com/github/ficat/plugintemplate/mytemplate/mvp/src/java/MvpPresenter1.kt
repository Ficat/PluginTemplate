fun mvpPresenterJava(
    mvpName: String,
    presenterPackage: String,
    modelPackage: String,
    contractPackage: String
) = """

package ${presenterPackage};

import ${contractPackage}.${mvpName}Contract;
import ${modelPackage}.${mvpName}Model;

public class ${mvpName}Presenter extends BaseMvpPresenter<${mvpName}Contract.View, ${mvpName}Contract.Model> {
    public ${mvpName}Presenter(${mvpName}Contract.View view) {
        super(view);
    }
	
	@Override
    protected ${mvpName}Contract.Model createModel() {
        return new ${mvpName}Model();
    }
}
"""





