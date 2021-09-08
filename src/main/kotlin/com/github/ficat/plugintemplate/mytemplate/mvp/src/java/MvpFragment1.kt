fun mvpFragmentJava(
    applicationPackage: String?,
    mvpName: String,
    layoutName: String,
    fragmentPackage: String,
    presenterPackage: String,
    contractPackage: String,
    nonNullPackage: String,
    nullablePackage: String
) = """

package ${fragmentPackage};

import android.os.Bundle;

import ${nonNullPackage};
import ${nullablePackage};
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ${applicationPackage}.R;
import ${contractPackage}.${mvpName}Contract;
import ${presenterPackage}.${mvpName}Presenter;

public class ${mvpName}Fragment extends BaseMvpFragment<${mvpName}Presenter> implements ${mvpName}Contract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.${layoutName}, container, false);
    }
	
    @Override
    protected ${mvpName}Presenter createPresenter() {
        return new ${mvpName}Presenter(this);
    }
}
"""