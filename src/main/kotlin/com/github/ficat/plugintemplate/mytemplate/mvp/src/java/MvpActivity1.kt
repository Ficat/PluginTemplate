fun mvpActivityJava(
    applicationPackage: String?,
    mvpName: String,
    layoutName: String,
    activityPackage: String,
    presenterPackage: String,
    contractPackage: String
) = """
package ${activityPackage};

import android.os.Bundle;

import ${applicationPackage}.R;
import ${contractPackage}.${mvpName}Contract;
import ${presenterPackage}.${mvpName}Presenter;

public class ${mvpName}Activity extends BaseMvpActivity<${mvpName}Presenter> implements ${mvpName}Contract.View {
   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${layoutName});
    }
	
	@Override
    protected ${mvpName}Presenter createPresenter() {
        return new ${mvpName}Presenter(this);
    }
}
"""