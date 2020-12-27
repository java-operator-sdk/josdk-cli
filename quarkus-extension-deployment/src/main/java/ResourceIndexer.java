import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.arc.runtime.ArcRecorder;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.runtime.RuntimeValue;

import javax.ejb.Singleton;
import java.util.HashMap;

public class ResourceIndexer {

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    public SyntheticBeanBuildItem buildItem(ArcRecorder recorder) {
        return SyntheticBeanBuildItem.configure(HashMap.class).scope(Singleton.class).supplier(
                recorder.createSupplier(new RuntimeValue<Object>(new HashMap<String, String>()))
        ).done();

    }

}
