package {{groupid}};

import io.javaoperatorsdk.operator.api.Context;
import io.javaoperatorsdk.operator.api.Controller;
import io.javaoperatorsdk.operator.api.ResourceController;
import io.javaoperatorsdk.operator.api.UpdateControl;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DoneableDeployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;
import io.fabric8.kubernetes.client.dsl.ServiceResource;
import io.fabric8.kubernetes.client.utils.Serialization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.fabric8.kubernetes.client.CustomResource;
import io.javaoperatorsdk.operator.api.DeleteControl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller(crdName = "mycustomresources.{{groupid}}")
public class SampleController implements ResourceController<MyCustomResource> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final KubernetesClient kubernetesClient;

    public SampleController(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }


    @Override
    public UpdateControl<MyCustomResource> createOrUpdateResource(MyCustomResource customResource, Context<MyCustomResource> context) {
        return null;
    }

    @Override
    public DeleteControl deleteResource(MyCustomResource customResource, Context<MyCustomResource> context) {
        return null;
    }

}
